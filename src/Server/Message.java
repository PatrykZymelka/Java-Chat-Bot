package Server;
import GUI.User;

public class Message extends User {
    User u = new User();
  public String Content;
  public long Timestamp;
  public int Port = GetPort();
  public String Content() {
        return Content;
  }
  public long GetTimestamp() {
      return 1111111;
  }
  public String GetMessage(){
      return "Message";
  }








}
