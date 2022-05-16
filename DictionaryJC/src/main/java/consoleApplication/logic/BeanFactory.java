/**
 *  Данный класс отвечает за создание объектов всех классов проекта (в методе createObjects),
 *  методы getClassName (где ClassName - имя конкретного класса)
 *  возщврают объект класса (с именем ClassName).
 *  Метод start вызывает метод createObjects и у объекта класса ApplicationLogic вызывает метод runApplication,
 *  запуская консольное приложение.
 *  Метода getClassName() и start() реализуют открытый интерфейс класса BeanFactory;
 * */
package main.java.consoleApplication.logic;

import main.java.consoleApplication.implementation.dictionary.DictionaryInterface;
import main.java.consoleApplication.implementation.dictionary.FilesFactory;
import main.java.consoleApplication.implementation.dictionary.SimpleConsoleApplicationIOMethods;
import main.java.consoleApplication.implementation.functions.CheckFunctions;
import main.java.consoleApplication.implementation.functions.RequestFunctions;
import main.java.consoleApplication.logic.application.ApplicationInterface;
import main.java.consoleApplication.logic.application.ApplicationLogic;
import main.java.consoleApplication.logic.application.SimpleConsoleApplication;
import main.java.userInterface.CommunicationWithTheUser;

public final class BeanFactory {
    private CommunicationWithTheUser communicationWithTheUser;
    private FilesFactory filesFactory;
    private CheckFunctions checkFunctions;
    private RequestFunctions requestFunctions;
    private DictionaryInterface simpleConsoleApplicationIOMethods;
    private ApplicationInterface simpleConsoleApplication;
    private ApplicationLogic applicationLogic;

    private void createObjects(BeanFactory beanFactory){
        communicationWithTheUser = new CommunicationWithTheUser();
        filesFactory = new FilesFactory();
        checkFunctions = new CheckFunctions(beanFactory);
        requestFunctions = new RequestFunctions(beanFactory);
        simpleConsoleApplicationIOMethods = new SimpleConsoleApplicationIOMethods(beanFactory);
        simpleConsoleApplication = new SimpleConsoleApplication(beanFactory);
        applicationLogic = new ApplicationLogic(beanFactory);
    }

    public FilesFactory getObjectFilesFactory() {
        return filesFactory;
    }

    public CheckFunctions getObjectCheckFunctions() {
        return checkFunctions;
    }

    public RequestFunctions getObjectRequestFunctions() {
        return requestFunctions;
    }

    public DictionaryInterface getObjectSimpleConsoleApplicationIOMethods() {
        return simpleConsoleApplicationIOMethods;
    }

    public ApplicationInterface getObjectSimpleConsoleApplication() {
        return simpleConsoleApplication;
    }

    public CommunicationWithTheUser getObjectCommunicationWithTheUser() {
        return communicationWithTheUser;
    }

    protected void start(BeanFactory beanFactory){
        createObjects(beanFactory);
        applicationLogic.runApplication();
    }

}
