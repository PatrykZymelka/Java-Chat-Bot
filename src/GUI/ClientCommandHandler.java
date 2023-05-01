package GUI;

import Server.ServerCommandHandler;

import javax.swing.*;
import java.text.SimpleDateFormat;
import java.util.*;

public class ClientCommandHandler {


    public ClientCommandHandler(JList<String> myList) {
        Users = myList;
    }

    public static void connect(String Sender) {
        int P = User.GetPort();
        MainWindow.getInstance().setStatusLabelText("Your IP is: " + Integer.toString(P));
        ServerCommandHandler.connectedUsers.add(Sender);
    }
    public static String[] getMessagesByUser(String sender) {
        List<String> messages = messageMap.get(sender);
        String[] array = messages.toArray(new String[0]);
        return array;
    }
    public static void refresh(String Sender) {
        DefaultListModel<String> model = (DefaultListModel<String>) Users.getModel();
        model.clear();
        String[] u = ServerCommandHandler.getConnectedUsers();
        model.addElement(u[0]);
    }
}