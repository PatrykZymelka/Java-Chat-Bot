package Server;

import GUI.CommandHandler;

import java.io.IOException;
import java.net.*;
import java.io.*;

public class MainServer{

    public void run(int Port) throws IOException {
        ServerSocket serverSocket = new ServerSocket(Port);
        serverSocket.setSoTimeout(60000);
        System.out.printf("Waiting for connection on port %d%n", Port);

        Socket server = serverSocket.accept();
        System.out.println("Client connected");
        InputStream inputStream = server.getInputStream();
        InputStreamReader reader = new InputStreamReader(inputStream);
        BufferedReader bufferedReader = new BufferedReader(reader);
        String line;
        while ((line = bufferedReader.readLine()) != null) {
            String[] parts = line.split("-");
            String Operation = parts[0];
            String Sender = parts[1];
            String Receiver = parts[2];
            String Message = parts[3];

            switch (Operation) {
                case "Connect" -> {
                    System.out.println("Got Here");
                    CommandHandler.connect(Sender, Port);
                }
                case "GetM" -> CommandHandler.getMessagesByUser(Sender, Receiver, Message);

            }
        }


    }
}
