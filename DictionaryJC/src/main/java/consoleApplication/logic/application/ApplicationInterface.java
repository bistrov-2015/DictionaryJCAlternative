/**
 * В этом интерфейсе объявлен метеод runApplication() который запускает всё консольное приложение. Метод определяется в классе ApplicationLogic.
 * Интерфейс наследуется от UserInteractionInterface получая все методы определённые в нём.
 * Это сделано для того, чтобы класс SimpleConsoleApplication мог имплементируя один интерфейс получить все необходимые для его работы методы.
 * */
package main.java.consoleApplication.logic.application;

import main.java.consoleApplication.implementation.functions.UserInteractionInterface;

public interface ApplicationInterface extends UserInteractionInterface {
    void runApplication();
}