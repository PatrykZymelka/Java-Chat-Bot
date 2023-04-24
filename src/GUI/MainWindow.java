package GUI;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import java.awt.event.ContainerAdapter;
import java.awt.event.ContainerEvent;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class MainWindow extends JFrame{
    private static MainWindow instance = null;
    public JPanel MainPanel;
    public JButton ConnectButton;
    public JList Users;
    public JList ChatPanel;
    public JButton RefreshButton;
    private JButton GetMessagesButton;
    private JButton SendMsgButton;
    private JButton SearchButton;
    public JTextField Search;
    private JList list1;
    private JTextField typeMessageHereTextField;
    public JTextField serverIPPortTextField;
    public JLabel StatusLabel;
    private MainWindow() {
        Client C = new Client();
        setContentPane(MainPanel);
        setTitle("Application-Alpha");
        setSize(650, 600);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);
        ConnectButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                StatusLabel.setText("Connected to Port: " + serverIPPortTextField.getText());
                int P = Integer.parseInt(serverIPPortTextField.getText());
                try {
                    C.Connect(P, "Connect-User1-User1-S");
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
                serverIPPortTextField.setText(" ");
            }
        });
        serverIPPortTextField.addContainerListener(new ContainerAdapter() {
            @Override
            public void componentAdded(ContainerEvent e) {
                super.componentAdded(e);
            }
        });

        RefreshButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                System.out.println(e.getActionCommand() + " " + e.getModifiers());
                JOptionPane.showMessageDialog(MainPanel, "Messages from " + (String) Users.getSelectedValue() + " have been refreshed!");
            }
        });

        GetMessagesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //TODO
            }
        });
        SendMsgButton.addActionListener(new ActionListener() {
            DefaultListModel model = new DefaultListModel();

            @Override
            public void actionPerformed(ActionEvent e) {
                /*if (Connected) {
                    SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
                    Date date = new Date();
                    ChatPanel.setModel(model);
                    String txt = formatter.format(date) + " " + typeMessageHereTextField.getText();
                    model.addElement(txt);
                    typeMessageHereTextField.setText("");
                }

                 */
            }
        });

        /*SearchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });

         */

    }
    public static MainWindow getInstance() {
        if (instance == null) {
            instance = new MainWindow();
        }
        return instance;
    }
    public void setStatusLabelText(String text) {
        StatusLabel.setText(text);
    }


}

