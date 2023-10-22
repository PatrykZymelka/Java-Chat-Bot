package org.GUI;

import java.io.IOException;

public class MainClient {

    public static void main(String[] args) throws IOException {
        try {
            MainWindow.getInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
