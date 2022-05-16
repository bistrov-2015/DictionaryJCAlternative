/**
 * Класс запукает приложение при помощи метода public void runApplication(),
 *
 * в котором у объекта simpleConsoleApplication вызывается метод runApplication() переопределённый в классе SimpleConsoleApplication.
 * */
package main.java.consoleApplication.logic.application;

import main.java.consoleApplication.logic.BeanFactory;
import main.java.userInterface.MessegesForUser;

public class ApplicationLogic {
    private BeanFactory beanFactory;
    public ApplicationLogic(BeanFactory beanFactory){
        this.beanFactory = beanFactory;
    }
    public void runApplication() {

        try {
            beanFactory.getObjectSimpleConsoleApplication().runApplication();
        } catch (Exception e){
            beanFactory.getObjectCommunicationWithTheUser().showExeptionMessege(MessegesForUser.APPLICATION_LOGIC_EXEPTION.getMessege(),e);
        }

    }
}