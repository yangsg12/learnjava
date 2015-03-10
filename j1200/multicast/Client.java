package j1200.multicast;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;

/**
 * Created by Yang on 2015/3/6.
 */
public class Client extends JFrame implements Runnable ,ActionListener{
    int port;
    InetAddress group = null;
    MulticastSocket socket = null;
    JButton ince = new JButton("开始接收");
    JButton stop = new JButton("停止了");
    JTextArea inceAr = new JTextArea(10, 10);
    JTextArea inced = new JTextArea(10, 10);
    Thread thread;
    boolean aBoolean = false;
    public Client() {
        super("广播数据报");
        thread = new Thread(this);
        ince.addActionListener(this);
        stop.addActionListener(this);

        inceAr.setForeground(Color.blue);
        JPanel north = new JPanel();
        north.add(ince);
        north.add(stop);

        add(north, BorderLayout.CENTER);
        validate();// 刷新
        port = 9898;
        try{
            group = InetAddress.getByName("224.255.10.0");
            socket = new MulticastSocket(port);
            socket.joinGroup(group);

        }catch (Exception e){
            e.printStackTrace();
        }

        setBounds(100, 50, 360, 380);
        setVisible(true);
    }

    @Override
    public void run() {
        while (true) {
            byte[] data = new byte[1024];
            DatagramPacket packet = null;
            packet = new DatagramPacket(data, data.length, group, port);
            try {
                socket.receive(packet);
                String msg = new String(packet.getData(), 0, packet.getLength());
                inceAr.setText("正在接收 %n" + msg);
                inced.append(msg +"\n");
            } catch (IOException e) {
                e.printStackTrace();
            }
            if (aBoolean = true){
                break;
            }
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == ince) {
            ince.setBackground(Color.pink);
            stop.setBackground(Color.gray);
            if (!thread.isAlive()) {
                thread = new Thread(this);

            }
            thread.start();
            aBoolean = false;
        }

        if (e.getSource() == stop) {
            ince.setBackground(Color.yellow);
            stop.setBackground(Color.red);
            aBoolean = true;

        }
    }

    public static void main(String[] args) {
        Client rec = new Client();
        rec.setSize(460, 200);
    }
}
