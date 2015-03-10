package j1200;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by Yang on 2015/3/6.
 */
public class ServerSocketDemo {
    public static void main(String[] args) {
        try {
//            ServerSocket serverSocket = new ServerSocket(1212);
//            System.out.println("服务器创建套字成功!!!");
//            while (true) {
//                System.out.println("等待 客户机 连接");
//                Socket socket = serverSocket.accept();
//                System.out.println("客户机连接成功!!!");
//            }

            ServerSocket serverSocket = null;
            Socket client = null;
            String str = null;
            DataOutputStream out = null;
            DataInputStream in = null;

            serverSocket = new ServerSocket(1213);
            System.out.println("等等 客户机 连接");
            client = serverSocket.accept();

            in = new DataInputStream(client.getInputStream());
            out = new DataOutputStream(client.getOutputStream());
            while (true){
                str = in.readUTF();
                out.writeUTF(str);
                System.out.println("服务器 收到 " + str);
                Thread.sleep(100);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e){
            e.printStackTrace();
        }
    }
}


