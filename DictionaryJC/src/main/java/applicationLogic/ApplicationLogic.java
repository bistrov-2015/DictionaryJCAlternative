/**
 * Класс запукает приложение при помощи метода public void runApplication(), в котором у объекта simpleConsoleApplication вызывается метод runApplication() переопределённый в классе SimpleConsoleApplication.
 * */
package main.java.applicationLogic;

import main.java.userInterface.MessegesForUser;
import main.java.userInterface.CommunicationWithTheUser;

public class ApplicationLogic {
    private BeanFactory beanFactory;
    public ApplicationLogic(BeanFactory beanFactory){
        this.beanFactory = beanFactory;
    }
    public void runApplication() {

        try {
            beanFactory.getSimpleConsoleApplication().runApplication();
        } catch (Exception e){
            beanFactory.getCommunicationWithTheUser().showExeptionMessege(MessegesForUser.APPLICATION_LOGIC_EXEPTION.getMessege(),e);//applicationLogicExeption(e);
        }

    }
}