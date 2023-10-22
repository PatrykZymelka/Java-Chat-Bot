package org.GUI;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainWindow extends JFrame {
    private static final Logger LOGGER = LogManager.getLogger(MainWindow.class);
    private static MainWindow instance = null;
    public JPanel MainPanel1;
    public JButton ConnectButton;
    public JList<String> Users;
    public JList<String> ChatPanel;
    public JButton RefreshButton;
    private JButton GetMessagesButton;
    private JButton SendMsgButton;
    private JButton SearchButton;
    public JTextField Search;
    private JList<String> list1;
    private JTextField typeMessageHereTextField;
    public JTextField serverIPPortTextField;
    public JLabel StatusLabel;
    private JLabel MSG_STATUS;
    private JLabel NameText;

    private MainWindow() {
        Client C = new Client();
        setContentPane(MainPanel1);
        setTitle("Application-Alpha");
        setSize(650, 600);
        LOGGER.debug("MainWindow setup complete.");

        setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);setVisible(true);


        String Username = JOptionPane.showInputDialog("Input Your Username:");
        User.SetSender(Username);
        NameText.setText(User.GetSender());
        LOGGER.info("Username Set");

        ConnectButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int P = 0;
                try {
                    P = Integer.parseInt(serverIPPortTextField.getText());
                    User.SetPort(P);
                } catch (NumberFormatException ex) {
                    LOGGER.error("No IP was provided");
                }

                try {
                    C.Connect(P);
                    C.Send("Connect-" + User.GetSender() + "-User1-S-" + User.GetPort());
                } catch (Exception exc) {
                    LOGGER.error("User couldn't connect to Server.");
                }
                serverIPPortTextField.setText(" ");

            }
        });



        RefreshButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DefaultListModel<String> model = new DefaultListModel<>();
                try {
                    C.Send("Refresh-User1-User1-S-" + User.GetPort());
                } catch (Exception exc) {
                    LOGGER.error("Messages couldn't be refreshed.");
                }
                try {
                    String[] u = ClientCommandHandler.getConnectedUsers();
                    int i;
                    for (i = 0; i < u.length; i++) {
                        model.addElement(u[i]);
                    }
                    Users.setModel(model);
                } catch (NullPointerException ex) {
                    LOGGER.error("No Users detected");
                }
            }
        });

        GetMessagesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    C.Send("GetM-" + User.GetSender() + "-" + User.GetSender() + "-S-" + User.GetPort());
                } catch (Exception exc) {
                    LOGGER.error("Messages couldn't be loaded.");
                }
                try {
                    String[] Mes = ClientCommandHandler.GetStoredM(User.GetSender());
                    DefaultListModel<String> model = new DefaultListModel<>();
                    ChatPanel.setModel(model);
                    int i;
                    for (i = 0; i < Mes.length; i++) {
                        model.addElement(Mes[i]);
                    }
                    typeMessageHereTextField.setText("");
                } catch (NullPointerException ne) {
                    LOGGER.error("No messages to be loaded.");
                }
            }
        });

        SendMsgButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String Sender = User.GetSender();
                String Receiver = Users.getSelectedValue();
                if (Receiver != null) {
                    try {
                        C.Send("SendM-" + Sender + "-" + Receiver + "-" + typeMessageHereTextField.getText() + "-" + User.GetPort());
                    } catch (Exception exc) {
                        LOGGER.error("Message couldn't be sent.");
                    }
                    MSG_STATUS.setText("Message Status: Sent!");
                    typeMessageHereTextField.setText("");
                }
            }
        });


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

    public JList<String> getList() {
        return Users;
    }
}
