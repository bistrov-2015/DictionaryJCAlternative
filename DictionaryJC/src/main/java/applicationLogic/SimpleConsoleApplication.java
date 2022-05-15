/**
 * Класс реализует логику работы приложения имплементируя ApplicationInterface;
 * public void runApplication() - вызывает методы до тех пор пока переменная runAp = true;
 * public void showUserMenu() - метод реализует показ пользователю пунктов меню приложения;
 * public void  handleUserSelection() - метод реализует запрос действия от пользователя;
 * public void performSelectedAction() - метод обрабатывет выбор пользователя в зависимости от выбранного пункта меню
 * */
package main.java.applicationLogic;

import main.java.userInterface.MenuItems;
import main.java.logicImplementation.DictionaryInterface;
import main.java.userInterface.CommunicationWithTheUser;
import main.java.logicImplementation.CheckFunctions;
import main.java.logicImplementation.RequestFunctions;

public class SimpleConsoleApplication implements ApplicationInterface {
    private String selectedItem;
    private BeanFactory beanFactory;
    private boolean runAp;

    public SimpleConsoleApplication(BeanFactory beanFactory) {
        this.beanFactory = beanFactory;
    }

    public void setSelectedItem(String selectedItem) {
        this.selectedItem = selectedItem;
    }
    private void setRunUp(boolean value){
        this.runAp = value;
    }
    private boolean getRunAp(){
        return runAp;
    }


    public void runApplication(){
        beanFactory.getCheckFunctions().checkFileExistence();
        setRunUp(true);
        while (getRunAp()) {
            showUserMenu();
            handleUserSelection();
            performSelectedAction();
        }
    }

    public void showUserMenu(){
        beanFactory.getCommunicationWithTheUser().showMenu();
    }

    public void  handleUserSelection(){
        setSelectedItem(beanFactory.getRequestFunctions().promptUserSelection());
    }

    public void performSelectedAction(){
        if(selectedItem.equals(MenuItems.SHOW_ALL.getMenuItem())){
                beanFactory.getSimpleConsoleApplicationIOMethods().showDictionary();
        } else if(selectedItem.equals(MenuItems.FIND.getMenuItem())){
                beanFactory.getSimpleConsoleApplicationIOMethods().findEntryInDictionary();
        } else if(selectedItem.equals(MenuItems.ADD.getMenuItem())){
                beanFactory.getSimpleConsoleApplicationIOMethods().makeEntryInDictionary();
        } else if(selectedItem.equals(MenuItems.DELETE.getMenuItem())){
                beanFactory.getSimpleConsoleApplicationIOMethods().deleteEntryInDictionary();
        } else if (selectedItem.equals(MenuItems.EXIT.getMenuItem())){
            setRunUp(false);
        }
    }
}