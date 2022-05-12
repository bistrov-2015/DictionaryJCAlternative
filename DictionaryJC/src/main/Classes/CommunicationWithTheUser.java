/*
 * калсс реализует методы для общения с пользователем
 * */

package main.Classes;

import main.Constants.MessegesForUser;

import java.util.Scanner;

public class CommunicationWithTheUser {

    public void showMenu(){
        System.out.println(MessegesForUser.MAINMENU.getMessege());
    }

    public String promptLine(){
        String line;
        Scanner scanner = new Scanner(System.in);
        System.out.print(MessegesForUser.KEY_REQUEST.getMessege());
        line = scanner.nextLine();
        return line;
    }

    public void getMessege(String messege){
        System.out.println(messege);
    }

    public void getErrorMessege(String ERROR, String text){
        System.out.println(ERROR + text);
    }

    public void getExeptionMessege(String messege, Exception e){
        System.out.println(messege + e);
    }
}