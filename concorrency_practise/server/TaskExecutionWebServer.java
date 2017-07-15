package concorrency_practise.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

/**
 * Created by Yang on 2016/8/5.
 */
public class TaskExecutionWebServer {
    private static final int nTHREADS = 100;
    private static final Executor exec = Executors.newFixedThreadPool(nTHREADS);

    public static void main(String[] args) throws IOException{
        ServerSocket socket = new ServerSocket(80);
        while (true) {
            final Socket connection = socket.accept();
            Runnable task = new Runnable() {
                @Override
                public void run() {
                    handleRequest(connection);
                }


            };

            exec.execute(task);



        }


    }

    private static void handleRequest(Socket connection) {
        System.out.println("handle request "+ connection.toString());
    }


}
