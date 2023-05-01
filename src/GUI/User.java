package GUI;
public class User {
    public String Sender;
    public String Receiver;
    public static int Port;
    public static String GetSender() {
        return "User1";
    }
    public static String GetReceiver() {
        return "User1";
    }
    public static int GetPort(){return Port;}
    public static void SetPort(int P){Port = P;

    }
}
