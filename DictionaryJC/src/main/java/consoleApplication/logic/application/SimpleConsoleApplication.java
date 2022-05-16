package main.java.consoleApplication.logic.application;

import main.java.consoleApplication.logic.BeanFactory;
import main.java.userInterface.MenuItems;

/**
 * Класс реализует логику работы приложения имплементируя ApplicationInterface;
 * public void runApplication() - вызывает методы до тех пор пока переменная runAp = true;
 * public void showUserMenu() - метод реализует показ пользователю пунктов меню приложения;
 * public void  handleUserSelection() - метод реализует запрос действия от пользователя;
 * public void performSelectedAction() - метод обрабатывет выбор пользователя в зависимости от выбранного пункта меню
 */

public class SimpleConsoleApplication implements ApplicationInterface {
    private String selectedItem;
    private final BeanFactory beanFactory;
    private boolean runAp;

    public SimpleConsoleApplication(BeanFactory beanFactory) {
        this.beanFactory = beanFactory;
    }

    public void setSelectedItem(String selectedItem) {
        this.selectedItem = selectedItem;
    }

    private void setRunUp(boolean value) {
        this.runAp = value;
    }

    private boolean getRunAp() {
        return runAp;
    }


    public void runApplication() {
        beanFactory.getObjectCheckFunctions().checkFileExistence();
        setRunUp(true);
        while (getRunAp()) {
            showUserMenu();
            handleUserSelection();
            performSelectedAction();
        }
    }

    public void showUserMenu() {
        beanFactory.getObjectCommunicationWithTheUser().showMenu();
    }

    public void handleUserSelection() {
        setSelectedItem(beanFactory.getObjectRequestFunctions().promptUserSelection());
    }

    public void performSelectedAction() {
        if (selectedItem.equals(MenuItems.SHOW_ALL.getMenuItem())) {
            beanFactory.getObjectSimpleConsoleApplicationIOMethods().showDictionary();
        } else if (selectedItem.equals(MenuItems.FIND.getMenuItem())) {
            beanFactory.getObjectSimpleConsoleApplicationIOMethods().findEntryInDictionary();
        } else if (selectedItem.equals(MenuItems.ADD.getMenuItem())) {
            beanFactory.getObjectSimpleConsoleApplicationIOMethods().makeEntryInDictionary();
        } else if (selectedItem.equals(MenuItems.DELETE.getMenuItem())) {
            beanFactory.getObjectSimpleConsoleApplicationIOMethods().deleteEntryInDictionary();
        } else if (selectedItem.equals(MenuItems.EXIT.getMenuItem())) {
            setRunUp(false);
        }
    }
}