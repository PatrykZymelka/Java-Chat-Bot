package Server;
public class User {
    public String Sender;
    public String Receiver;
    public int Port;
    public String GetSender() {
        return "You";
    }
    public String GetReceiver() {
        return "Me";
    }
    public int GetPort(){return 1234;}
}
