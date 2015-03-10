package j1200.chatroom;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by Yang on 2015/3/6.
 */
public class ServerProcess {
    private SocketManager socketManager = new SocketManager();
    void getServer(){
        try {
            ServerSocket serverSocket = new ServerSocket(7777);
            System.out.println("服务器 套字 已创建 ");
            while (true){
                Socket socket = serverSocket.accept();
                new write_Thread(socket).start();
                socketManager.add(socket);
                socketManager.sendClientCont();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    class write_Thread extends Thread{
        Socket socket = null;
        private BufferedReader reader;
        private PrintWriter writer;
        public write_Thread(Socket socket){
            this.socket = socket;
        }

        @Override
        public void run() {
            try {
                reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                writer = new PrintWriter(socket.getOutputStream(), true);
                String msg;
                while ((msg = reader.readLine()) !=null) {
                    System.out.println(msg);
                    socketManager.writeAll(msg);
                }
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        ServerProcess serverProcess = new ServerProcess();
        serverProcess.getServer();
    }
}
