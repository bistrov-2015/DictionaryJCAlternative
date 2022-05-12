/*
 * класс реализующий методы проверки:
 * public void checkFileExistence() - проверка существования файла по заданному пути, если его нет файл будет создан;
 * public boolean chekUserSelection(String userSelection) - проверка выбранного пользователем пунта меню программы на соответствие существующим пунктам;
 * public boolean checkDictionaryTypeSelection(String Dict) - функция проверяет введённую пользователем цифру(которая обозначает тип словаря);
 * public boolean checkNumericString(String chekedStr) - функция проверяет соответствие введённой пользователем строки для слолваря хранящего числа;
 * public boolean checkSymbolString(String chekedStr) - функция проверяет соответствие введённой пользователем строки для слолваря хранящего слова из латинских букв;
 * public boolean checkSymbolExpressionValue(String chekedStr) - функция проверяет соответствие введённого пользователем значения для слолваря хранящего слова из латинских букв;
 * public boolean checkNumericExpressionValue(String chekedStr) - функция проверяет соответствие введённого пользователем значения для слолваря хранящего чвисла;
 * */
package main.Classes;

import main.Constants.Files;
import main.Constants.MessegesForUser;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.regex.Pattern;

public class CheckFunctions {
    String separator = File.separator;
    CommunicationWithTheUser communicationWithTheUser;

    protected CheckFunctions(CommunicationWithTheUser communicationWithTheUser) {
        this.communicationWithTheUser = communicationWithTheUser;
    }
    public String getSeparator(){
        return separator;
    }
    private String getDirectory(){
        return Files.DIRECTORY.getFilesInfo() + getSeparator();
    }

    Path path1 = Paths.get(getDirectory() + Files.LANG_FILE.getFilesInfo());
    Path path2 = Paths.get(getDirectory() + Files.NUM_FILE.getFilesInfo());
    File file1 = new File(getDirectory() + Files.LANG_FILE.getFilesInfo());
    File file2 = new File(getDirectory() + Files.NUM_FILE.getFilesInfo());

    public void checkFileExistence() {
        try {
            File file1 = new File(String.valueOf(path1));
            if (file1.createNewFile())
                communicationWithTheUser.getMessege(MessegesForUser.REPORT_LANGUAGE_DICTIONARY_FILE_CREATION.getMessege());//reportLanguageDictionaryFileCreation();
        } catch (IOException e) {
            communicationWithTheUser.getErrorMessege(MessegesForUser.ERROR.getMessege(), MessegesForUser.REPORT_LANGUAGE_DICTIONARY_FILE_CREATION_FAILED.getMessege());//reportLanguageDictionaryFileCreationFailed();
        }
        try {
            File file2 = new File(String.valueOf(path2));
            if (file2.createNewFile())
                communicationWithTheUser.getMessege(MessegesForUser.REPORT_NUMERIC_DICTIONARY_FILE_CREATION.getMessege());//reportNumericDictionaryFileCreation();

        } catch (IOException e) {
            communicationWithTheUser.getErrorMessege(MessegesForUser.ERROR.getMessege(), MessegesForUser.REPORT_NUMERIC_DICTIONARY_FILE_CREATION_FAILED.getMessege());//reportNumericDictionaryFileCreationFailed();
        }
    }

    public boolean chekUserSelection(String userSelection){
        String regex = "[12345]";
        return Pattern.matches(regex, userSelection);
    }

    public boolean checkDictionaryTypeSelection(String Dict){
        String regex = "[12]";
        return Pattern.matches(regex, Dict);
    }

    public boolean checkNumericString(String chekedStr) {
        return chekedStr.matches("^[0-9]+$") & chekedStr.length() == 5;
    }

    public boolean checkSymbolString(String chekedStr) {
        return chekedStr.matches("^[a-z]+$") & chekedStr.length() == 4;
    }

    public boolean checkSymbolExpressionValue(String chekedStr) {
        return chekedStr.matches("^[а-яА-Я]+$");
    }

    public boolean checkNumericExpressionValue(String chekedStr) {
        return chekedStr.matches("^[0-9]+$");
    }
}