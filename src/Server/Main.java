package Server;

import GUI.MainWindow;

import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {

        Message M = new Message();
        int Port = M.GetPort();

        MainWindow.getInstance();

        MainServer MS = new MainServer();
        MS.run(Port);






    }

}