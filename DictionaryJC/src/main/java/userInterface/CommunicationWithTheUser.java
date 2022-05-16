/**
 * Калсс реализует методы для общения с пользователем.
 * метод showMenu() - метод выводит в консоль главное меню приложения.
 * метод promptLine() - запрашивает у пользователя ввод строки.
 * метод showMessege() - выводит в консоль строку (сообщение).
 * метод showErrorMessege() - вывод сообщения об ошибке в консоль. Принимает в качестве параметра константу со строкой из перечисления MessegesForUser.
 * метод showExeptionMessege - вывод в консоль сообщения об исключении. Принимает два параметра:
 * первый - константа со строкой из перечисления MessegesForUser, второй - конкретный Exception.
 * */

package main.java.userInterface;

import java.io.File;
import java.util.Scanner;

public class CommunicationWithTheUser implements UserInterface {

    public void showMenu(){
        System.out.println(MessegesForUser.MAINMENU.getMessege());
    }

    public String promptLine(){
        showMessege(MessegesForUser.KEY_REQUEST.getMessege());
        return  readLine();
    }

    public void showMessege(String messege){
        System.out.println(messege);
    }

    public void showErrorMessege(String text){
        System.out.println(MessegesForUser.ERROR.getMessege() + text);
    }

    public void showExeptionMessege(String messege, Exception e){
        System.out.println(messege + e);
    }
    public void showExeptionMessege(String messege, File file, Exception e){
        System.out.println(messege + file + e);
    }
    public String readLine(){
        String result;
        try {
            result = System.console().readLine();
        }catch (NullPointerException e){
            Scanner scanner = new Scanner(System.in);
            result = scanner.nextLine();
        }
         return result;
    }
}