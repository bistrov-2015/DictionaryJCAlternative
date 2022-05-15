/**
 * класс реализует методы ввода/вывода:
 * public void showDictionary() - метод выводит в консоль выбранный пользователем словарь;
 * public File defineDictionaryType(String dictionaryType) - метод определят с каким файлом необходимо работать, взависимости от того какой словарь выбрал пользователь;
 * public Path definePathtoFile(String numDict) - метод возвращает путь до выбранного файла;
 * public void  findEntryInDictionary() - метод поиска в словаре по ключу и по значению;
 * public void  makeEntryInDictionary() - метод реализующий запись в словарь;
 * public boolean chekRowExistensBeforeDeleting(String searchString, File fileType) - метод проверяющий существование записи перед её удалением;
 * public void  deleteEntryInDictionary() - метод реализует удаление записи из словаря;
 * */
package main.java.logicImplementation;

import main.java.applicationLogic.BeanFactory;
import main.java.userInterface.MessegesForUser;
import main.java.userInterface.CommunicationWithTheUser;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;


public class SimpleConsoleApplicationIOMethods implements DictionaryInterface {
    private BeanFactory beanFactory;

    public SimpleConsoleApplicationIOMethods(BeanFactory beanFactory) {
        this.beanFactory = beanFactory;
    }

    public void showDictionary(){
        String dictionariType = beanFactory.getRequestFunctions().promptDictionaryType();
        try(BufferedReader br = new BufferedReader(new FileReader(defineDictionaryType(dictionariType)))){
            String line;
            while ((line = br.readLine()) != null) {
                beanFactory.getCommunicationWithTheUser().showMessege(line);
            }
        } catch (IOException e){
            beanFactory.getCommunicationWithTheUser().showExeptionMessege(MessegesForUser.FILE_READ_ERROR.getMessege(), e);
        }
    }

    public File defineDictionaryType(String dictionaryType){// переменнjq fileType присваивается значение взависимости от того какой словарь выбран
        if("1".equals(dictionaryType)){
            return beanFactory.getFilesFactory().getFileLangDict();
        } else return beanFactory.getFilesFactory().getFileNumDict();
    }

    private Path definePathToFile(String numDict){// переменной path присваивается значение взависимости от того каой словарь выбран
        if("1".equals(numDict)){
            return beanFactory.getFilesFactory().getPathToLangFile();
        } else return beanFactory.getFilesFactory().getPathToNumFile();
    }

    public void  findEntryInDictionary(){
        String dictionaryType = beanFactory.getRequestFunctions().promptDictionaryType();
        File fileType = defineDictionaryType(dictionaryType);
        String searchString = beanFactory.getCommunicationWithTheUser().promptLine();
        String searchStringResult = null;
        try(BufferedReader br = new BufferedReader(new FileReader(fileType))){
            String line;
            while ((line = br.readLine()) != null ) {
                if( line.contains(searchString)){
                    searchStringResult = line; break;
                }
            }
            if(searchStringResult != null){
                beanFactory.getCommunicationWithTheUser().showMessege(searchStringResult);
            } else beanFactory.getCommunicationWithTheUser().showMessege(MessegesForUser.STRING_NOT_FOUND.getMessege());
        }catch (IOException e){
            beanFactory.getCommunicationWithTheUser().showExeptionMessege(MessegesForUser.FILE_READ_ERROR.getMessege(), e);
        }
    }


    public void  makeEntryInDictionary(){
        String numDict = beanFactory.getRequestFunctions().promptDictionaryType();
        Path pathToFile = definePathToFile(numDict);
        String expression = beanFactory.getRequestFunctions().requestExpressiont(numDict);
        beanFactory.getCommunicationWithTheUser().showMessege(MessegesForUser.REQUEST_VALUE.getMessege());
        String expressionValue = beanFactory.getRequestFunctions().requestExpressionValue(numDict);
        String checkedString = expression + "\t" + expressionValue;
        try {
            Files.writeString(pathToFile, "\n" + checkedString, StandardOpenOption.APPEND);
        } catch (IOException e) {
            beanFactory.getCommunicationWithTheUser().showExeptionMessege(MessegesForUser.FILE_WRITING_ERROR.getMessege(),e);
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
        String dictionaryType = beanFactory.getRequestFunctions().promptDictionaryType();
        File fileType = defineDictionaryType(dictionaryType);
        Path path = definePathToFile(dictionaryType);
        File temporaryFile = new File(FilesInfo.DIRECTORY.getFilesInfo() + beanFactory.getFilesFactory().getSeparator() + FilesInfo.TEMP_FILE.getFilesInfo());
        String searchString = beanFactory.getRequestFunctions().requestExpressiont(dictionaryType);
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
                        beanFactory.getCommunicationWithTheUser().showMessege(MessegesForUser.DELETE_OK.getMessege());
                    } else beanFactory.getCommunicationWithTheUser().showErrorMessege(MessegesForUser.DELETE_FAILED.getMessege());
                    if (temporaryFile.renameTo(fileType)) {
                        beanFactory.getCommunicationWithTheUser().showMessege(MessegesForUser.RENAME_OK.getMessege());
                    } else beanFactory.getCommunicationWithTheUser().showErrorMessege(MessegesForUser.RENAME_FAILED.getMessege());
                } else beanFactory.getCommunicationWithTheUser().showErrorMessege(MessegesForUser.STRING_NOT_FOUND.getMessege());

            }catch (IOException e){
            beanFactory.getCommunicationWithTheUser().showExeptionMessege(MessegesForUser.FILE_READ_ERROR.getMessege(), e);
        }
    }
}