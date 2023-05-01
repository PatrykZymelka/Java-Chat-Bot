package Server;

import GUI.MainWindow;

import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        MainServer MS = new MainServer();
        MS.run(1234);
    }

}