package main.java.userInterface;

public interface UserInterface {
    void showMenu();
    String promptLine();
    void showMessege(String messege);
    void showErrorMessege(String text);
    void showExeptionMessege(String messege, Exception e);
}
