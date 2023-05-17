import java.text.SimpleDateFormat;
import java.util.*;

public class ServerCommandHandler {
    public static List<String> connectedUsers = new ArrayList<>();
    static Map<String, List<String>> messageMap = new HashMap<>();


    public static void removeUser(String Sender) {
        connectedUsers.remove(Sender);
    }
    public static void AddUser(String Sender) {
        connectedUsers.add(Sender);
    }
    public static void SendMessage(String Sender, String Receiver, String Message){
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        Date date = new Date();
        String txt = formatter.format(date) + " |" + Sender + "|> " + Message ;
        List<String> messages = messageMap.getOrDefault(Receiver, new ArrayList<>());
        messages.add(txt);
        messageMap.put(Receiver, messages);
    }


    public static String[] getConnectedUsers() {
        return connectedUsers.toArray(new String[0]);
    }

}
