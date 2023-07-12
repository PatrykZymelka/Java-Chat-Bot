package org.Server;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.net.SocketTimeoutException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class MainServer {
    private static final Logger LOGGER = LogManager.getLogger(MainServer.class);
    public void run(int Port) throws IOException {
        try {

            ServerSocket serverSocket = new ServerSocket(Port);
            serverSocket.setSoTimeout(60000);
            System.out.printf("Waiting for connection on port %d%n", Port);
            List<ClientHandler> clients = new ArrayList<>();

            while (true) {
                Socket server = serverSocket.accept();
                System.out.println("Client connected");

                ClientHandler clientHandler = new ClientHandler(server);

                clients.add(clientHandler);

                Thread thread = new Thread(clientHandler);
                thread.start();
            }
        } catch (SocketTimeoutException | SocketException se) {
            LOGGER.error("Server socket connection has timed out");

        }
    }
    private static class ClientHandler implements Runnable {
        private Socket client;
        private BufferedWriter writer;

        public ClientHandler(Socket client) {
            this.client = client;
        }

        public void run() {
            try {
                InputStream inputStream = client.getInputStream();
                InputStreamReader reader = new InputStreamReader(inputStream);
                BufferedReader bufferedReader = new BufferedReader(reader);
                OutputStreamWriter outputStreamWriter = new OutputStreamWriter(client.getOutputStream());
                writer = new BufferedWriter(outputStreamWriter);
                String line;
                while ((line = bufferedReader.readLine()) != null) {
                    String[] parts = line.split("-");
                    String Operation = parts[0];
                    String Sender = parts[1];
                    String Receiver = parts[2];
                    String Message = parts[3];
                    String Port = parts[4];

                    switch (Operation) {
                        case "Connect":
                            ServerCommandHandler.AddUser(Sender);
                            writer.write("Connect-" + Sender + "-" + Receiver + "-" + Message + "-" + Port);
                            writer.newLine();
                            writer.flush();
                            break;

                        case "GetM":
                            String[] M = ServerCommandHandler.messageMap.get(Receiver).toArray(new String[0]);
                            String LM = convertObjectArrayToString(M, ",");
                            writer.write("GetM-" + Sender + "-" + Receiver + "-" + LM + "-" + Port);
                            writer.newLine();
                            writer.flush();
                            break;



                        case "Refresh":
                            String List[] = ServerCommandHandler.getConnectedUsers();
                            String ListOfUsers = convertObjectArrayToString(List, ",");

                            writer.write("Refresh-" + Sender + "-" + Receiver + "-" + ListOfUsers + "-" + Port);
                            writer.newLine();
                            writer.flush();
                            break;

                        case "SendM":
                            ServerCommandHandler.SendMessage(Sender, Receiver, Message);
                            writer.write("SendM-" + Sender + "-" + Receiver + "-" + Message + "-" + Port);
                            writer.newLine();
                            writer.flush();
                            break;

                        case "Close":
                            ServerCommandHandler.removeUser(Sender);
                            break;

                
                    }
                }
            } catch (NullPointerException | IOException e) {
                LOGGER.fatal("Invalid input from the client");
            }
        }
    }

    public static boolean isUserConnected(String username) {
        List<String> connectedUsers = Arrays.asList(ServerCommandHandler.getConnectedUsers());
        return connectedUsers.contains(username);
    }
    private static String convertObjectArrayToString(Object[] arr, String delimiter) {
        StringBuilder sb = new StringBuilder();
        for (Object obj : arr)
            sb.append(obj.toString()).append(delimiter);
        return sb.substring(0, sb.length() - 1);

    }
}