/*
 * класс запукает приложение при помощи метода public void runApplication(), в котором у объекта simpleConsoleApplication вызывается метод runApplication() переопределённый в классе simpleConsoleApplication*/
package main.Classes;

import main.Constants.MessegesForUser;
import main.Interfaces.ApplicationInterface;

public class ApplicationLogic {
    ApplicationInterface simpleConsoleApplication;
    CommunicationWithTheUser communicationWithTheUser;
    protected ApplicationLogic(ApplicationInterface simpleConsoleApplication, CommunicationWithTheUser communicationWithTheUser){
        this.simpleConsoleApplication = simpleConsoleApplication;
        this.communicationWithTheUser = communicationWithTheUser;
    }
    public void runApplication() {

        try {
            simpleConsoleApplication.runApplication();
        } catch (Exception e){
            communicationWithTheUser.getExeptionMessege(MessegesForUser.APPLICATION_LOGIC_EXEPTION.getMessege(),e);//applicationLogicExeption(e);
        }

    }
}