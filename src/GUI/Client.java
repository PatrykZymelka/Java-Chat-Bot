package GUI;

import Server.MainServer;

import java.io.*;
import java.net.Socket;

public class Client {
    private Socket client;
    private BufferedReader reader;
    private BufferedWriter writer;

    public void Connect(int port) throws IOException {

        client = new Socket((String) null, port);
        InputStreamReader inputStreamReader = new InputStreamReader(client.getInputStream());
        OutputStreamWriter outputStreamWriter = new OutputStreamWriter(client.getOutputStream());
        reader = new BufferedReader(inputStreamReader);
        writer = new BufferedWriter(outputStreamWriter);
    }

    public void Send(String s) throws IOException {
        writer.write(s);
        writer.newLine();
        writer.flush();
    }

    public void close() throws IOException {
        reader.close();
        writer.close();
        client.close();
    }
}