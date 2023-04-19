package Server;

import GUI.CommandHandler;

import java.awt.event.ActionEvent;
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
        DataInputStream socketReader = new DataInputStream(server.getInputStream());
        String line;
        while (!(line = socketReader.readUTF()).equals("exit")) {
            String[] parts = line.split("-");
            String Operation = parts[0];
            String Sender = parts[1];
            String Receiver = parts[2];
            String Message = parts[3];
            CommandHandler CH = new CommandHandler();

            switch (Operation) {
                case "Connect" -> {
                    System.out.println("Got Here");
                    CH.connect(Sender, Port);
                    break;
                }
                case "GetM" -> CH.getMessagesByUser(Sender, Receiver, Message);

            }
        }


    }
}
