package concorrency_practise.server;

import org.omg.CORBA.Request;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.RejectedExecutionException;

/**
 * Created by Yang on 2016/8/5.
 */
public class LifeCycleWebServer {
    private final ExecutorService executorService = Executors.newSingleThreadExecutor();

    public void start() throws IOException {
        ServerSocket socket = new ServerSocket(80);
        while (!executorService.isShutdown()) {
            try {
                final Socket connection = socket.accept();
                executorService.execute(new Runnable() {
                    @Override
                    public void run() {
                        handleReques(connection);
                    }
                });
            } catch (IOException e) {
                e.printStackTrace();
            } catch (RejectedExecutionException e){
                if (!executorService.isShutdown()) {
                    System.out.println("task submission rejected "+ e);
                }
                e.printStackTrace();
            }
        }
    }

    public void stop() {
        executorService.shutdown();
    }

    void handleReques(Socket connection) {
        System.out.println("handle request" + connection.toString());
    }
}
