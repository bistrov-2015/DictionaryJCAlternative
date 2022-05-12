/*
 * класс реализует методы ввода/вывода:
 * public void showDictionary() - метод выводит в консоль выбранный пользователем словарь;
 * public File defineDictionaryType(String dictionaryType) - метод определят с каким файлом необходимо работать, взависимости от того какой словарь выбрал пользователь;
 * public Path definePathtoFile(String numDict) - метод возвращает путь до выбранного файла;
 * public void  findEntryInDictionary() - метод поиска в словаре по ключу и по значению;
 * public void  makeEntryInDictionary() - метод реализующий запись в словарь;
 * public boolean chekRowExistensBeforeDeleting(String searchString, File fileType) - метод проверяющий существование записи перед её удалением;
 * public void  deleteEntryInDictionary() - метод реализует удаление записи из словаря;
 * */
package main.Classes;

import main.Constants.MessegesForUser;
import main.Interfaces.DictionaryInterface;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;


public class SimpleConsoleApplicationIOMethods implements DictionaryInterface {
    private CheckFunctions checkFunctions;
    private RequestFunctions requestFunctions;
    private CommunicationWithTheUser communicationWithTheUser;

    protected SimpleConsoleApplicationIOMethods(CheckFunctions checkFunctions, RequestFunctions requestFunctions, CommunicationWithTheUser communicationWithTheUser) {
        this.checkFunctions = checkFunctions;
        this.requestFunctions = requestFunctions;
        this.communicationWithTheUser = communicationWithTheUser;
    }

    public void showDictionary() throws IOException{
        String dictionariType = requestFunctions.promptDictionaryType();
        try(BufferedReader br = new BufferedReader(new FileReader(defineDictionaryType(dictionariType)))){
            String line;
            while ((line = br.readLine()) != null) {
                communicationWithTheUser.getMessege(line);//System.out.println(line);// SRP?
            }
        }
    }

    public File defineDictionaryType(String dictionaryType){// переменнjq fileType присваивается значение взависимости от того какой словарь выбран
        //File fileType;// = checkFunctions.file1;
        if("1".equals(dictionaryType)){
            return checkFunctions.file1;//fileType = checkFunctions.file1;
        } else return checkFunctions.file2;//fileType = checkFunctions.file2; //if("2".equals(dictionaryType))
    }

    public Path definePathToFile(String numDict){// переменной path присваивается значение взависимости от того каой словарь выбран
        //Path path = checkFunctions.path1;
        if("1".equals(numDict)){
            return checkFunctions.path1;//path = checkFunctions.path1;
        } else return checkFunctions.path2;//path = checkFunctions.path2;//if("2".equals(numDict))
    }

    public void  findEntryInDictionary() throws IOException{
        String dictionariType = requestFunctions.promptDictionaryType();
        File fileType = defineDictionaryType(dictionariType);
        String searchString = communicationWithTheUser.promptLine();
        String searchStringResult = null;
        try(BufferedReader br = new BufferedReader(new FileReader(fileType))){
            String line;
            while ((line = br.readLine()) != null ) {
                if( line.contains(searchString)){
                    searchStringResult = line; break;
                }
            }
            if(searchStringResult != null){
                communicationWithTheUser.getMessege(searchStringResult);
            } else communicationWithTheUser.getMessege(MessegesForUser.STRING_NOT_FOUND.getMessege());
        }
    }


    public void  makeEntryInDictionary(){
        RequestFunctions requestFunctions = new RequestFunctions(communicationWithTheUser, checkFunctions);
        String numDict = requestFunctions.promptDictionaryType();
        Path pathToFile = definePathToFile(numDict);
        String expression = requestFunctions.requestExpressiont(numDict);
        communicationWithTheUser.getMessege(MessegesForUser.REQUEST_VALUE.getMessege());//promptValue();
        String expressionValue = requestFunctions.requestExpressionValue(numDict);
        String checkedString = expression + "\t" + expressionValue;
        try {
            Files.writeString(pathToFile, "\n" + checkedString, StandardOpenOption.APPEND);
        } catch (IOException e) {
            communicationWithTheUser.getExeptionMessege(MessegesForUser.FILE_WRITING_ERROR.getMessege(),e);
        }
    }

    private boolean chekRowExistensBeforeDeleting(String searchString, File fileType) throws IOException{
        try(BufferedReader br = new BufferedReader(new FileReader(fileType))) {
            String line;
            while ((line = br.readLine()) != null) {
                if (line.startsWith(searchString)) {
                    return true;
                }
            }
        }
        return false;
    }

    public void  deleteEntryInDictionary(){
        String dictionaryType = requestFunctions.promptDictionaryType();
        File fileType = defineDictionaryType(dictionaryType);
        Path path = definePathToFile(dictionaryType);
        File temporaryFile = new File(main.Constants.Files.DIRECTORY.getFilesInfo() + checkFunctions.getSeparator() + main.Constants.Files.TEMP_FILE.getFilesInfo());
        String searchString = requestFunctions.requestExpressiont(dictionaryType);
        try{
            if(chekRowExistensBeforeDeleting(searchString,fileType)){
                try ( BufferedWriter bw = new BufferedWriter(new FileWriter(temporaryFile));
                      BufferedReader br = new BufferedReader(new FileReader(fileType))){
                        String line;
                        while ((line = br.readLine()) != null) {
                            if (!line.contains(searchString)) {
                                bw.write(line);
                                bw.newLine();
                            }
                        }
                    }
                    if (Files.deleteIfExists(path)) {
                        communicationWithTheUser.getMessege(MessegesForUser.DELETE_OK.getMessege());//deleteOKMessage();//System.out.println("delete");
                    } else communicationWithTheUser.getErrorMessege(MessegesForUser.ERROR.getMessege(), MessegesForUser.DELETE_FAILED.getMessege());//deleteFailedMessage();//System.out.println("not delete");
                    if (temporaryFile.renameTo(fileType)) {
                        communicationWithTheUser.getMessege(MessegesForUser.RENAME_OK.getMessege());//renameOKMessage();//System.out.println("successfully");
                    } else communicationWithTheUser.getErrorMessege(MessegesForUser.ERROR.getMessege(), MessegesForUser.RENAME_FAILED.getMessege());//renameFailedMessage();//System.out.println("not rename");
                } else communicationWithTheUser.getErrorMessege(MessegesForUser.ERROR.getMessege(), MessegesForUser.STRING_NOT_FOUND.getMessege());//System.out.println("нет такой строки");

            }catch (IOException e){
            communicationWithTheUser.getExeptionMessege(MessegesForUser.FILE_READ_ERROR.getMessege(), e);
        }
    }
}