/**
 * Класс реализует методы запроса данных у пользователя:
 * public String promptUserSelection() - запрос пункта меню;
 * public String promptDictionaryType() - запрос типа словаря с которым хочет работать пользователь;
 * public String requestExpressiont(String numDict) - метод запроса слова(ключа) для словаря
 * и проверка его на удовлетвореня условиям ТЗ для конкретного словаря, в зависимости от того какой словарь выбран;
 * public String requestExpressionValue(String numDict) - метод запроса слова(значения) для словаря и проверка его на удовлетвореня условиям ТЗ для конкретного словаря, в зависимости от того какой словарь выбран;
 *  */
package main.java.logicImplementation;

import main.java.applicationLogic.BeanFactory;
import main.java.userInterface.MessegesForUser;
import main.java.userInterface.CommunicationWithTheUser;

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
        Scanner scanner = new Scanner(System.in);
        beanFactory.getCommunicationWithTheUser().showMessege(MessegesForUser.REQUEST_ACTION.getMessege());
        setSelectItem(scanner.nextLine());
        if (!beanFactory.getCheckFunctions().checkUserSelection(selectItem)) {
            promptUserSelection();
        }
        return selectItem;
    }

    public String promptDictionaryType(){
        Scanner scanner = new Scanner(System.in);
        beanFactory.getCommunicationWithTheUser().showMessege(MessegesForUser.PROMPT_DICTIOARY_TYPE.getMessege());
        setNumDict(scanner.nextLine());
        if (!beanFactory.getCheckFunctions().checkDictionaryTypeSelection(numDict)) {
            promptDictionaryType();
        }
        return numDict;
    }

    public String requestExpressiont(String numDict){
        String stringToFile = beanFactory.getCommunicationWithTheUser().promptLine();
        if("1".equals(numDict)){
            while (!beanFactory.getCheckFunctions().checkSymbolString(stringToFile)){
                beanFactory.getCommunicationWithTheUser().showMessege(MessegesForUser.SHOW_STRING_FORMAT_FOR_EXPRESSION_MESSEGE.getMessege());//showStringFormatForExpression();
                stringToFile = beanFactory.getCommunicationWithTheUser().promptLine();
            }
        }else if ("2".equals(numDict)){
            while (!beanFactory.getCheckFunctions().checkNumericString(stringToFile)){
                beanFactory.getCommunicationWithTheUser().showMessege(MessegesForUser.SHOW_NUMBER_FORMAT_FOR_EXPRESSION_MESSEGE.getMessege());//showNumberFormatForExpression();
                stringToFile = beanFactory.getCommunicationWithTheUser().promptLine();
            }
        }
        return stringToFile;
    }

    public String requestExpressionValue(String numDict){
        String stringToFile = beanFactory.getCommunicationWithTheUser().promptLine();
        if("1".equals(numDict)){
            while (!beanFactory.getCheckFunctions().checkSymbolExpressionValue(stringToFile)){
                beanFactory.getCommunicationWithTheUser().showMessege(MessegesForUser.SHOW_STRING_FORMAT_FOR_VALUE_MESSEGE.getMessege());//showStringFormatForExpressionValue();
                stringToFile = beanFactory.getCommunicationWithTheUser().promptLine();
            }
        }else if ("2".equals(numDict)){
            while (!beanFactory.getCheckFunctions().checkNumericExpressionValue(stringToFile)){
                beanFactory.getCommunicationWithTheUser().showMessege(MessegesForUser.SHOW_NUMBER_FORMAT_FOR_VALUE_MESSEGE.getMessege());//showNumberFormatForExpressionValue();
                stringToFile = beanFactory.getCommunicationWithTheUser().promptLine();
            }
        }
        return stringToFile;
    }

}