package concorrency_practise.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by Yang on 2016/8/5.
 */
public class SingleThreadServer {
    public static void main(String[] args) throws IOException {
        ServerSocket server = new ServerSocket(80);
        while (true) {
            Socket connection = server.accept();
            handRequest (connection);
        }
    }

    private static void handRequest (Socket connection) {

    }
}
