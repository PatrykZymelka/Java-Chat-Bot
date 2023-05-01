package Server;

import GUI.ClientCommandHandler;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainServer {

    public void run(int Port) throws IOException {
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
    }

    private static class ClientHandler implements Runnable {
        private Socket client;

        public ClientHandler(Socket client) {
            this.client = client;
        }

        public void run() {
            try {
                InputStream inputStream = client.getInputStream();
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
                        case "Connect":
                            ClientCommandHandler.connect(Sender);
                            break;

                        case "GetM":
                            ClientCommandHandler.getMessagesByUser(Sender);
                            break;

                        case "Refresh":
                            ClientCommandHandler.refresh(Sender);
                            break;

                        case "SendM":
                            ClientCommandHandler.SendMessage(Sender, Receiver, Message);
                            break;
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static boolean isUserConnected(String username) {
        List<String> connectedUsers = Arrays.asList(ClientCommandHandler.getConnectedUsers());
        return connectedUsers.contains(username);
    }
}