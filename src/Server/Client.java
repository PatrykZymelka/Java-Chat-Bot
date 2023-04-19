package Server;



import java.net.*;
import java.io.*;

public class Client {
    final static String User = "User1";
    public void Connect(int Port,String s) throws IOException {
        Socket client = new Socket((String)null,Port);

        InputStreamReader inputStreamReader = new InputStreamReader(client.getInputStream());
        OutputStreamWriter outputStreamWriter = new OutputStreamWriter(client.getOutputStream());


        BufferedReader reader = new BufferedReader(inputStreamReader);
        DataOutputStream writer = new DataOutputStream(client.getOutputStream());String line = "";
        while (!line.equals("exit")) {
            line = s;
            writer.writeUTF(line);
            line = reader.readLine();
        }
    }
}
