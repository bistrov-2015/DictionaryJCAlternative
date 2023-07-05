package main.java.consoleApplication.implementation.dictionary;

import main.java.consoleApplication.logic.BeanFactory;
import main.java.userInterface.MessegesForUser;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;

/**
 * класс реализует методы ввода/вывода:
 * public void showDictionary() - метод выводит в консоль выбранный пользователем словарь;
 * public File defineDictionaryType(String dictionaryType) - метод определят с каким файлом необходимо работать, взависимости от того какой словарь выбрал пользователь;
 * public Path definePathtoFile(String numDict) - метод возвращает путь до выбранного файла;
 * public void  findEntryInDictionary() - метод поиска в словаре по ключу и по значению;
 * public void  makeEntryInDictionary() - метод реализующий запись в словарь;
 * public boolean chekRowExistensBeforeDeleting(String searchString, File fileType) - метод проверяющий существование записи перед её удалением;
 * public void  deleteEntryInDictionary() - метод реализует удаление записи из словаря;
 */

public class SimpleConsoleApplicationIOMethods implements DictionaryInterface {
    private final BeanFactory beanFactory;

    public SimpleConsoleApplicationIOMethods(BeanFactory beanFactory) {
        this.beanFactory = beanFactory;
    }

    public void showDictionary() {
        String dictionaryType = beanFactory.getObjectRequestFunctions().promptDictionaryType();
        try (BufferedReader br = new BufferedReader(new FileReader(defineDictionaryType(dictionaryType)))) {
            String line;
            while ((line = br.readLine()) != null) {
                beanFactory.getObjectCommunicationWithTheUser().showMessege(line);
            }
        } catch (IOException e) {
            beanFactory.getObjectCommunicationWithTheUser().showExeptionMessege(MessegesForUser.FILE_READ_ERROR.getMessege(), e);
        }
    }

    private File defineDictionaryType(String dictionaryType) {
        if ("1".equals(dictionaryType)) {
            return beanFactory.getObjectFilesFactory().getFileLangDict();
        } else return beanFactory.getObjectFilesFactory().getFileNumDict();
    }

    private Path definePathToFile(String numDict) {
        if ("1".equals(numDict)) {
            return beanFactory.getObjectFilesFactory().getPathToLangFile();
        } else return beanFactory.getObjectFilesFactory().getPathToNumFile();
    }

    public void findEntryInDictionary() {
        String dictionaryType = beanFactory.getObjectRequestFunctions().promptDictionaryType();
        File fileType = defineDictionaryType(dictionaryType);
        String searchString = beanFactory.getObjectCommunicationWithTheUser().promptLine();
        String searchStringResult = null;
        try (BufferedReader br = new BufferedReader(new FileReader(fileType))) {
            String line;
            while ((line = br.readLine()) != null) {
                if (line.contains(searchString)) {
                    searchStringResult = line;
                    break;
                }
            }
            if (searchStringResult != null) {
                beanFactory.getObjectCommunicationWithTheUser().showMessege(searchStringResult);
            } else
                beanFactory.getObjectCommunicationWithTheUser().showMessege(MessegesForUser.STRING_NOT_FOUND.getMessege());
        } catch (FileNotFoundException e) {
            beanFactory.getObjectCommunicationWithTheUser().showExeptionMessege(MessegesForUser.FILE_NOT_FOUND.getMessege(), fileType, e);
        } catch (IOException e) {
            beanFactory.getObjectCommunicationWithTheUser().showExeptionMessege(MessegesForUser.FILE_READ_ERROR.getMessege(), e);
        }
    }


    public void makeEntryInDictionary() {
        String numDict = beanFactory.getObjectRequestFunctions().promptDictionaryType();
        Path pathToFile = definePathToFile(numDict);
        String expression = beanFactory.getObjectRequestFunctions().requestExpressiont(numDict);
        beanFactory.getObjectCommunicationWithTheUser().showMessege(MessegesForUser.REQUEST_VALUE.getMessege());
        String expressionValue = beanFactory.getObjectRequestFunctions().requestExpressionValue(numDict);
        String checkedString = expression + "\t" + expressionValue;
        try {
            Files.writeString(pathToFile, "\n" + checkedString, StandardOpenOption.APPEND);
        } catch (IOException e) {
            beanFactory.getObjectCommunicationWithTheUser().showExeptionMessege(MessegesForUser.FILE_WRITING_ERROR.getMessege(), e);
        }
    }

    private boolean chekRowExistensBeforeDeleting(String searchString, File fileType) {
        try (BufferedReader br = new BufferedReader(new FileReader(fileType))) {
            String line;
            while ((line = br.readLine()) != null) {
                if (line.startsWith(searchString)) {
                    return true;
                }
            }
        } catch (FileNotFoundException e) {
            beanFactory.getObjectCommunicationWithTheUser().showExeptionMessege(MessegesForUser.FILE_NOT_FOUND.getMessege(), fileType, e);
        } catch (IOException e) {
            beanFactory.getObjectCommunicationWithTheUser().showExeptionMessege(MessegesForUser.FILE_READ_ERROR.getMessege(), e);
        }
        return false;
    }

    public void deleteEntryInDictionary() {
        String dictionaryType = beanFactory.getObjectRequestFunctions().promptDictionaryType();
        File fileType = defineDictionaryType(dictionaryType);
        Path path = definePathToFile(dictionaryType);
        File temporaryFile = new File(FilesInfo.DIRECTORY.getFilesInfo() + beanFactory.getObjectFilesFactory().getSeparator() + FilesInfo.TEMP_FILE.getFilesInfo());
        String searchString = beanFactory.getObjectRequestFunctions().requestExpressiont(dictionaryType);
        try {
            if (chekRowExistensBeforeDeleting(searchString, fileType)) {
                try (BufferedWriter bw = new BufferedWriter(new FileWriter(temporaryFile));
                     BufferedReader br = new BufferedReader(new FileReader(fileType))) {
                    String line;
                    while ((line = br.readLine()) != null) {
                        if (!line.contains(searchString)) {
                            bw.write(line);
                            bw.newLine();
                        }
                    }
                } catch (FileNotFoundException e) {
                    beanFactory.getObjectCommunicationWithTheUser().showExeptionMessege(MessegesForUser.FILE_NOT_FOUND.getMessege(), fileType, e);
                }
                if (Files.deleteIfExists(path)) {
                    beanFactory.getObjectCommunicationWithTheUser().showMessege(MessegesForUser.DELETE_OK.getMessege());
                } else
                    beanFactory.getObjectCommunicationWithTheUser().showErrorMessege(MessegesForUser.DELETE_FAILED.getMessege());
                if (temporaryFile.renameTo(fileType)) {
                    beanFactory.getObjectCommunicationWithTheUser().showMessege(MessegesForUser.RENAME_OK.getMessege());
                } else
                    beanFactory.getObjectCommunicationWithTheUser().showErrorMessege(MessegesForUser.RENAME_FAILED.getMessege());
            } else
                beanFactory.getObjectCommunicationWithTheUser().showErrorMessege(MessegesForUser.STRING_NOT_FOUND.getMessege());

        } catch (IOException e) {
            beanFactory.getObjectCommunicationWithTheUser().showExeptionMessege(MessegesForUser.FILE_READ_ERROR.getMessege(), e);
        }
    }
}