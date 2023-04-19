package GUI;

import javax.swing.*;

public class CommandHandler {
    MainWindow MW = new MainWindow();
    public void connect(String Sender, int P) {
        DefaultListModel<String> mod = new DefaultListModel<>();

        mod.addElement("User1");
    }

    public String getMessagesByUser(String sender, String Rece, String Message) {
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
