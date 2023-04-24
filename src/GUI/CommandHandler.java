package GUI;

import GUI.MainWindow;

import javax.swing.*;

public class CommandHandler {

    public static void connect(String Sender, int P) {
        MainWindow.getInstance().setStatusLabelText("Your IP is: " + Integer.toString(P));

    }

    public static String getMessagesByUser(String sender, String Rece, String Message) {
        return "Message";
    }
    public String[] List(String s){
        return new String[]{"User1", "User2", "User3"};
    }
    public boolean Send() {
        return true;
    }
}
interface Commands{
    public String connect(String Sender, int P);
    public String getMessagesByUser(String sender, String Rece, String Message);
    public String[] List(String names);
    public boolean Send();


};
