/**
 *  Данный класс является
 * */
package main.java.applicationLogic;

import main.java.logicImplementation.*;
import main.java.userInterface.CommunicationWithTheUser;

public class BeanFactory {
    private CommunicationWithTheUser communicationWithTheUser;
    private FilesFactory filesFactory;
    private CheckFunctions checkFunctions;
    private RequestFunctions requestFunctions;
    private DictionaryInterface simpleConsoleApplicationIOMethods;
    private ApplicationInterface simpleConsoleApplication;
    private ApplicationLogic applicationLogic;

    protected void createObjects(BeanFactory beanFactory){
        communicationWithTheUser = new CommunicationWithTheUser();
        filesFactory = new FilesFactory();
        checkFunctions = new CheckFunctions(beanFactory);
        requestFunctions = new RequestFunctions(beanFactory);
        simpleConsoleApplicationIOMethods = new SimpleConsoleApplicationIOMethods(beanFactory);
        simpleConsoleApplication = new SimpleConsoleApplication(beanFactory);
        applicationLogic = new ApplicationLogic(beanFactory);
    }

    public FilesFactory getFilesFactory() {
        return filesFactory;
    }

    public CheckFunctions getCheckFunctions() {
        return checkFunctions;
    }

    public RequestFunctions getRequestFunctions() {
        return requestFunctions;
    }

    public DictionaryInterface getSimpleConsoleApplicationIOMethods() {
        return simpleConsoleApplicationIOMethods;
    }

    public ApplicationInterface getSimpleConsoleApplication() {
        return simpleConsoleApplication;
    }

    public CommunicationWithTheUser getCommunicationWithTheUser() {
        return communicationWithTheUser;
    }

    protected void start(){
        applicationLogic.runApplication();
    }

}
