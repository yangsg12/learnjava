package j1200;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

/**
 * Created by Yang on 2015/3/6.
 */
public class SocketDemo {
    public static void main(String[] args) {
        String str = null;
        Socket client;
        DataInputStream in = null;
        DataOutputStream out = null;


        try {
            client = new Socket("127.0.0.1",1213);
            in = new DataInputStream(client.getInputStream());
            out = new DataOutputStream(client.getOutputStream());
            out.writeUTF("我是客户机!");
            int i = 0;
            while (true){
                str = in.readUTF();
                out.writeUTF(i++ + "");
                System.out.println("客户机 收到 " + str);
                Thread.sleep(1000);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }catch (InterruptedException e){
            e.printStackTrace();
        }

    }
}
