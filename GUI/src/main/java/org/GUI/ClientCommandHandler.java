package org.GUI;

import java.util.*;

public class ClientCommandHandler {
    public static String[] CUsers;
    static Map<String, List<String>> SentMessages = new HashMap<>();
    static int j;
    static Boolean ConnectedStatus = false;


    public static void connect(String Sender, String Port) {
        MainWindow.getInstance().setStatusLabelText("Your IP is: " + Port);
        j = 0;
        ConnectedStatus = true;

    }
    public static boolean isConnected() {
        return ConnectedStatus;


    }
    /*
    public static String[] getMessagesByUser(String sender) {
        List<String> messages = messageMap.get(sender);
        String[] array = messages.toArray(new String[0]);
        return array;
    }
    */
    public static void refresh(String[] U) {
        CUsers = Arrays.copyOf(U,U.length);
    }
    public static String[] getConnectedUsers() {
        try {
            return CUsers;
        }
        catch(NullPointerException e){
            return new String[]{User.GetSender()};
        }
    }
    public static void StoreM(String Receiver,String[] Mess) {

        List<String> messages = SentMessages.getOrDefault(Receiver, new ArrayList<>());
        for(int i = j; i<Mess.length;i++) {
            messages.add(Mess[i]);
            SentMessages.put(Receiver, messages);
            j++;
        }
    }
    public static String[] GetStoredM(String Receiver) {
            return SentMessages.get(Receiver).toArray(new String[0]);
    }
    public static String convertObjectArrayToString(Object[] arr, String delimiter) {
        StringBuilder sb = new StringBuilder();
        for (Object obj : arr)
            sb.append(obj.toString()).append(delimiter);
        return sb.substring(0, sb.length() - 1);

    }
}