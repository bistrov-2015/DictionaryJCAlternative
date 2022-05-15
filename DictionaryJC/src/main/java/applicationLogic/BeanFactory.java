package main.java.applicationLogic;

import main.java.logicImplementation.*;
import main.java.userInterface.CommunicationWithTheUser;

public class BeanFactory {
    /*CommunicationWithTheUser communicationWithTheUser = new CommunicationWithTheUser();
    CheckFunctions checkFunctions = new CheckFunctions(communicationWithTheUser);
    RequestFunctions requestFunctions = new RequestFunctions(communicationWithTheUser, checkFunctions);
    DictionaryInterface simpleConsoleApplicationIOMethods = new SimpleConsoleApplicationIOMethods(checkFunctions, requestFunctions, communicationWithTheUser);
    ApplicationInterface simpleConsoleApplication = new SimpleConsoleApplication(simpleConsoleApplicationIOMethods,communicationWithTheUser,checkFunctions,requestFunctions);
    ApplicationLogic applicationLogic = new ApplicationLogic(simpleConsoleApplication, communicationWithTheUser);*/
    private CommunicationWithTheUser communicationWithTheUser;
    private FilesFactory filesFactory;
    private CheckFunctions checkFunctions;
    private RequestFunctions requestFunctions;
    private DictionaryInterface simpleConsoleApplicationIOMethods;
    private ApplicationInterface simpleConsoleApplication;
    private ApplicationLogic applicationLogic;
    protected void createObjects(){
        communicationWithTheUser = new CommunicationWithTheUser();
        filesFactory = new FilesFactory();
        checkFunctions = new CheckFunctions(communicationWithTheUser, filesFactory);
        requestFunctions = new RequestFunctions(communicationWithTheUser, checkFunctions);
        simpleConsoleApplicationIOMethods = new SimpleConsoleApplicationIOMethods(checkFunctions, requestFunctions, communicationWithTheUser, filesFactory);
        simpleConsoleApplication = new SimpleConsoleApplication(simpleConsoleApplicationIOMethods,communicationWithTheUser,checkFunctions,requestFunctions);
        applicationLogic = new ApplicationLogic(simpleConsoleApplication, communicationWithTheUser);
    }

    public CommunicationWithTheUser getCommunicationWithTheUser() {
        return communicationWithTheUser;
    }

    protected void start(){
        applicationLogic.runApplication();
    }

}
