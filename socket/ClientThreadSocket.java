package socket;

import java.io.*;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.Socket;

/**
 * Created by Yang on 2015/1/27.
 */
public class ClientThreadSocket extends Thread {
    private Socket socket;
    private BufferedReader in;
    private PrintWriter out;
    private static int counter=0;
    private int id = counter++;
    private static int threadCounter = 0;
    final int port = 8110;

    public static int getThreadCounter(){
        return threadCounter;
    }

    public ClientThreadSocket(InetAddress address){
        System.out.println("Making client : "+ id);
        threadCounter++;
        try {
            socket = new Socket(address,port);
        } catch (IOException e) {
            e.printStackTrace();
        }
    try {
        in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(socket.getOutputStream())));
        start();
    }catch (IOException e){
        try {
            socket.close();
        } catch (IOException e1) {
            e1.printStackTrace();
        }
    }
    }

    @Override
    public void run() {
        try {

            for (int i = 0; i < 25; i++) {
                out.println("Client : " + id + ":" + i);
                String str = in.readLine();
                System.out.println(str);

            }
            out.println("END");
        }catch (IOException e){

        }finally {
            try {
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            threadCounter--;
        }
    }
}
