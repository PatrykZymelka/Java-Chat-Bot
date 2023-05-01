package GUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import java.io.IOException;

public class MainWindow extends JFrame{
    private static MainWindow instance = null;
    public JPanel MainPanel;
    public JButton ConnectButton;
    public JList<String> Users;
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
    private JLabel MSG_STATUS;

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
                int P = Integer.parseInt(serverIPPortTextField.getText());
                User.SetPort(P);

                try {
                    C.Connect(P);
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
                try{
                    C.Send("Connect-User1-User1-S");
                }catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
                serverIPPortTextField.setText(" ");
            }
        });

        RefreshButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DefaultListModel<String> model = new DefaultListModel<>();
                String[] u = ClientCommandHandler.getConnectedUsers();
                int i;
                for(i=0;i < u.length; i++){
                    model.addElement(u[i]);
                }
                Users.setModel(model);
               }
        });

        GetMessagesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String[] Mes = ClientCommandHandler.getMessagesByUser(Users.getSelectedValue());
                DefaultListModel model = new DefaultListModel();
                ChatPanel.setModel(model);
                int i;
                for(i = 0; i< Mes.length;i++){
                    model.addElement(Mes[i]);
                }
                typeMessageHereTextField.setText("");
            }
        });

        SendMsgButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String Sender = User.GetSender();
                String Receiver = Users.getSelectedValue();
                if(Receiver != null) {
                    try {
                        C.Send("SendM-" + Sender + "-" + Receiver + "-" + typeMessageHereTextField.getText());
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
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

