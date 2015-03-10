package j1200.chatroom;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * Created by Yang on 2015/3/6.
 */
public class Client extends JFrame implements Runnable {
    private JPanel jPanel = new JPanel();
    private JLabel nameLabel = new JLabel("姓名 ");
    private JTextField nameField = new JTextField();
    private JTextArea msgArea = new JTextArea();
    private JTextField sendField = new JTextField();
    private JScrollPane jScrollPane = new JScrollPane();

    private BufferedReader reader;
    private PrintWriter writer;
    private Socket socket;

    public Client(String title) {
        super(title);
        this.setSize(360, 340);
        this.add(jPanel);
        jPanel.setLayout(null);
        msgArea.setEditable(false);
        jPanel.add(nameLabel);
        nameLabel.setBounds(10, 10, 60, 20);
        jPanel.add(nameField);
        nameField.setBounds(60, 10, 270, 21);
        jPanel.add(sendField);
        sendField.setBounds(10, 270, 320, 21);
        msgArea.setColumns(20);
        msgArea.setRows(5);
        jScrollPane.setViewportView(msgArea);
        jPanel.add(jScrollPane);
        jScrollPane.setBounds(10, 40, 320, 220);
        sendField.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                writer.println(nameField.getText() + ":" + sendField.getText());
                sendField.setText("");
            }
        });
    }
    @Override
    public void run() {
        while (true){
            try {
                msgArea.append(reader.readLine() + "\n");
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }

    void getSocket() {
        msgArea.append("尝试 与 服务器 连接 ");
        try {
            socket = new Socket("127.0.0.1", 7777);
            msgArea.append("聊天 准备 好了啊");
            reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            writer = new PrintWriter(socket.getOutputStream(), true);
            new Thread(this).start();
        }catch (Exception e){
            e.printStackTrace();
        }

    }

    public static void main(String[] args) {
        Client client = new Client("迷你聊天室");
        client.setVisible(true);
        client.getSocket();
    }
}
