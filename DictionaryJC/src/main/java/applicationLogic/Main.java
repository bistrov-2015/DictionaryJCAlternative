package main.java.applicationLogic;

import main.java.logicImplementation.CheckFunctions;
import main.java.logicImplementation.DictionaryInterface;
import main.java.logicImplementation.RequestFunctions;
import main.java.logicImplementation.SimpleConsoleApplicationIOMethods;
import main.java.userInterface.CommunicationWithTheUser;

public class Main {

    public static void main(String[] args) {
        CommunicationWithTheUser communicationWithTheUser = new CommunicationWithTheUser();
        CheckFunctions checkFunctions = new CheckFunctions(communicationWithTheUser);
        RequestFunctions requestFunctions = new RequestFunctions(communicationWithTheUser, checkFunctions);
        DictionaryInterface simpleConsoleApplicationIOMethods = new SimpleConsoleApplicationIOMethods(checkFunctions, requestFunctions, communicationWithTheUser);
        ApplicationInterface simpleConsoleApplication = new SimpleConsoleApplication(simpleConsoleApplicationIOMethods,communicationWithTheUser,checkFunctions,requestFunctions);
        ApplicationLogic applicationLogic = new ApplicationLogic(simpleConsoleApplication, communicationWithTheUser);
        applicationLogic.runApplication();
    }
}