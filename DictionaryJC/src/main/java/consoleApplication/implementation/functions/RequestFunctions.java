/**
 * Класс реализует методы запроса данных у пользователя:
 * public String promptUserSelection() - запрос пункта меню;
 * public String promptDictionaryType() - запрос типа словаря с которым хочет работать пользователь;
 * public String requestExpressiont(String numDict) - метод запроса слова(ключа) для словаря
 * и проверка его на удовлетвореня условиям ТЗ для конкретного словаря, в зависимости от того какой словарь выбран;
 * public String requestExpressionValue(String numDict) - метод запроса слова(значения) для словаря и проверка его на удовлетвореня условиям ТЗ для конкретного словаря, в зависимости от того какой словарь выбран;
 *  */
package main.java.consoleApplication.implementation.functions;

import main.java.consoleApplication.logic.BeanFactory;
import main.java.userInterface.MessegesForUser;

import java.util.Scanner;

public class RequestFunctions {
    private String numDict;
    private String selectItem;
    private BeanFactory beanFactory;

    public RequestFunctions(BeanFactory beanFactory) {
        this.beanFactory = beanFactory;
    }

    public void setNumDict(String numDict) {
        this.numDict = numDict;
    }

    public void setSelectItem(String selectItem) {
        this.selectItem = selectItem;
    }

    public String promptUserSelection(){
        beanFactory.getObjectCommunicationWithTheUser().showMessege(MessegesForUser.REQUEST_ACTION.getMessege());
        setSelectItem(beanFactory.getObjectCommunicationWithTheUser().readLine());
        if (!beanFactory.getObjectCheckFunctions().checkUserSelection(selectItem)) {
            promptUserSelection();
        }
        return selectItem;
    }

    public String promptDictionaryType(){
        beanFactory.getObjectCommunicationWithTheUser().showMessege(MessegesForUser.PROMPT_DICTIOARY_TYPE.getMessege());
        setNumDict(beanFactory.getObjectCommunicationWithTheUser().readLine());
        if (!beanFactory.getObjectCheckFunctions().checkDictionaryTypeSelection(numDict)) {
            promptDictionaryType();
        }
        return numDict;
    }

    public String requestExpressiont(String numDict){
        String stringToFile = beanFactory.getObjectCommunicationWithTheUser().promptLine();

        if("1".equals(numDict)){
            while (!beanFactory.getObjectCheckFunctions().checkSymbolString(stringToFile)){
                beanFactory.getObjectCommunicationWithTheUser().showMessege(MessegesForUser.SHOW_STRING_FORMAT_FOR_EXPRESSION_MESSEGE.getMessege());//showStringFormatForExpression();
                stringToFile = beanFactory.getObjectCommunicationWithTheUser().promptLine();
            }
        }else if ("2".equals(numDict)){
            while (!beanFactory.getObjectCheckFunctions().checkNumericString(stringToFile)){
                beanFactory.getObjectCommunicationWithTheUser().showMessege(MessegesForUser.SHOW_NUMBER_FORMAT_FOR_EXPRESSION_MESSEGE.getMessege());//showNumberFormatForExpression();
                stringToFile = beanFactory.getObjectCommunicationWithTheUser().promptLine();
            }
        }
        return stringToFile;
    }

    public String requestExpressionValue(String numDict){
        String stringToFile = beanFactory.getObjectCommunicationWithTheUser().promptLine();

        if("1".equals(numDict)){
            while (!beanFactory.getObjectCheckFunctions().checkSymbolExpressionValue(stringToFile)){
                beanFactory.getObjectCommunicationWithTheUser().showMessege(MessegesForUser.SHOW_STRING_FORMAT_FOR_VALUE_MESSEGE.getMessege());//showStringFormatForExpressionValue();
                stringToFile = beanFactory.getObjectCommunicationWithTheUser().promptLine();
            }
        }else if ("2".equals(numDict)){
            while (!beanFactory.getObjectCheckFunctions().checkNumericExpressionValue(stringToFile)){
                beanFactory.getObjectCommunicationWithTheUser().showMessege(MessegesForUser.SHOW_NUMBER_FORMAT_FOR_VALUE_MESSEGE.getMessege());//showNumberFormatForExpressionValue();
                stringToFile = beanFactory.getObjectCommunicationWithTheUser().promptLine();
            }
        }
        return stringToFile;
    }

}