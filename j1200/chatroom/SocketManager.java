package j1200.chatroom;

import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;

/**
 * Created by Yang on 2015/3/6.
 * UDP
 * 发数据包
 * DatagramSocket()
 * DatagramPacket() 要发送的 数据包
 * DatagramSocket send()
 * <p/>
 * 接收
 * DatagramSocket(int port)
 * DatagramPacket(byte[] buf, int length) 要接受的 数据包
 * DatagramSocket receive()
 *
 * 聊天室
 */
public class SocketManager extends ArrayList {
    synchronized void add(Socket socket) {
        super.add(socket);
    }
    synchronized void del(Socket socket) {
        super.remove(socket);
    }
    synchronized void sendClientCont(){
        String info = "当前 聊天 人数 : "+size();
        System.out.println(info);
        writeAll(info);
    }

    synchronized void writeAll(String str){
        PrintWriter writer = null;
        Socket socket;
        for (int i = 0; i < size(); i++) {
            socket = (Socket) get(i);
            try {
                writer = new PrintWriter(socket.getOutputStream(), true);
                if (writer != null){
                    writer.println(str);
                }
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }
}
