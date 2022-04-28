/*
 * класс реализует логику работы приложения имплементируя ApplicationInterface;
 * public void runApplication() - вызывает методы до тех пор пока переменная runAp = true;
 * public void showUserMenu() - метод реализует показ пользователю пунктов меню приложения;
 * public void  handleUserSelection() - метод реализует запрос действия от пользователя;
 * public void performSelectedAction() - метод обрабатывет выбор пользователя в зависимости от выбранного пункта меню;
 * */
package main.Classes;

import main.Interfaces.ApplicationInterface;
import main.Interfaces.DictionaryInterface;

public class SimpleConsoleApplication implements ApplicationInterface {
    String selectedItem;
    DictionaryInterface simpleConsoleApplicationIOMethods;
    CommunicationWithTheUser communicationWithTheUser;
    CheckFunctions checkFunctions;
    RequestFunctions requestFunctions;
    private boolean runAp = true;

    public SimpleConsoleApplication(DictionaryInterface simpleConsoleApplicationIOMethods, CommunicationWithTheUser communicationWithTheUser, CheckFunctions checkFunctions, RequestFunctions requestFunctions) {
        this.simpleConsoleApplicationIOMethods = simpleConsoleApplicationIOMethods;
        this.communicationWithTheUser = communicationWithTheUser;
        this.checkFunctions = checkFunctions;
        this.requestFunctions = requestFunctions;
    }

    public void setSelectedItem(String selectedItem) {
        this.selectedItem = selectedItem;
    }



    public void runApplication(){
        checkFunctions.checkFileExistence();
        while (runAp) {
            showUserMenu();
            handleUserSelection();
            performSelectedAction();
        }
    }

    public void showUserMenu(){
        communicationWithTheUser.showMenu();
    };

    public void  handleUserSelection(){
        setSelectedItem(requestFunctions.promptUserSelection());
    };

    public void performSelectedAction(){
        if(selectedItem.equals("1")){
            simpleConsoleApplicationIOMethods.showDictionary();
        } else if(selectedItem.equals("2")){
            simpleConsoleApplicationIOMethods.findEntryInDictionary();
        } else if(selectedItem.equals("3")){
            simpleConsoleApplicationIOMethods.makeEntryInDictionary();
        } else if(selectedItem.equals("4")){
            simpleConsoleApplicationIOMethods.deleteEntryInDictionary();
        } else if (selectedItem.equals("5")){
            runAp = false;
        }
    }
}