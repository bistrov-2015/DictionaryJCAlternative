/*
 * класс запукает приложение при помощи метода public void runApplication(), в котором у объекта simpleConsoleApplication вызывается метод runApplication() переопределённый в классе simpleConsoleApplication*/
package main.Classes;

import main.Interfaces.ApplicationInterface;

public class ApplicationLogic {
    //ApplicationInterface simpleConsoleApplication = new SimpleConsoleApplication(s);
    ApplicationInterface simpleConsoleApplication;
    public ApplicationLogic(ApplicationInterface simpleConsoleApplication){
        this.simpleConsoleApplication = simpleConsoleApplication;
    }
    public void runApplication() {

        try {
            simpleConsoleApplication.runApplication();
        } catch (Exception e){
            System.out.println(e);
        }

    }
}