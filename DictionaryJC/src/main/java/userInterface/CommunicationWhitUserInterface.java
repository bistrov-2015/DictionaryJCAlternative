/**
 * Данный интерфейс определяет методы для общения с пользователем.
 * showMenu() - показть пользователю главное меню.
 * promptLine() - запросить у пользователя строку.
 * showMessege(String messege) - показать пользователю сообщение.
 * showErrorMessege(String text) - вывести сообщение об ошибке.
 * showExeptionMessege(String messege, Exception e) - сообщить о  том, что произошло исключение.
 * void showExeptionMessege(String messege, File file, Exception e); - сообщить о  том, что произошло исключение(при работе с файлами).
 * String readLine() - считывание строки.
 * */
package main.java.userInterface;

import java.io.File;

public interface CommunicationWhitUserInterface {
    void showMenu();
    String promptLine();
    void showMessege(String messege);
    void showErrorMessege(String text);
    void showExeptionMessege(String messege, Exception e);
    void showExeptionMessege(String messege, File file, Exception e);
    String readLine();
}
