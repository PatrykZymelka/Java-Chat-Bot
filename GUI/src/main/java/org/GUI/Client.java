package org.GUI;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.*;
import java.net.Socket;
import java.net.SocketTimeoutException;
public class Client {
    private static final Logger LOGGER = LogManager.getLogger(Client.class);
    private Socket client;
    private BufferedReader reader;
    private BufferedWriter writer;

    public void Connect(int port) throws IOException {
        try {
            client = new Socket((String) null, port);
            InputStreamReader inputStreamReader = new InputStreamReader(client.getInputStream());
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(client.getOutputStream());
            reader = new BufferedReader(inputStreamReader);
            writer = new BufferedWriter(outputStreamWriter);
        }catch(NullPointerException exc){
            LOGGER.fatal("Couldn't connect to server");
        }
    }

    public void Send(String s) throws IOException {
        try {
            client.setSoTimeout(1000);
            writer.write(s);
            writer.newLine();
            writer.flush();
            String Read = reader.readLine();
            read(Read);
        }
        catch(NullPointerException exc){
            LOGGER.fatal("Server connection couldn't be maintained");
        }catch(SocketTimeoutException se){
            LOGGER.fatal("No response in desired time");
        }
    }

    public void close() throws IOException {
        if(ClientCommandHandler.ConnectedStatus) {
            writer.write("Close-" + User.GetSender() + "-User1-Message-1234");
            writer.newLine();
            writer.flush();
            reader.close();
            writer.close();
            client.close();
        }
    }
    public void read(String Read) throws IOException{

        String[] parts = Read.split("-");
        String Operation = parts[0];
        String Sender = parts[1];
        String Receiver = parts[2];
        String Message = parts[3];
        String Port = parts[4];

        switch (Operation) {

            case "Refresh":
                String[] Users = Message.split(",");
                ClientCommandHandler.refresh(Users);
                LOGGER.debug("List Refreshed");
                break;

            case "Connect":
                ClientCommandHandler.connect(Sender, Port);
                LOGGER.debug("User Connected");
                break;

            case "SendM":
                break;

            case "GetM":
                String[] LM = Message.split(",");
                ClientCommandHandler.StoreM(Receiver,LM);
                break;

            case "Close":
                reader.close();
                writer.close();
                client.close();
                LOGGER.debug("Client Closed");
                break;


        }
    }
}