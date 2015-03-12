package think_in_java.concurrency;

import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * Created by Yang on 2015/3/12.
 */
public class CloseResource {
    public static void main(String[] args) throws Exception {
        ExecutorService executorService = Executors.newCachedThreadPool();
        ServerSocket server = new ServerSocket(8086);
        InputStream socketInput = new Socket("local host",8086).getInputStream();
        executorService.execute(new IOBlocked(socketInput));
        executorService.execute(new IOBlocked(System.in));

        TimeUnit.MILLISECONDS.sleep(1);
        System.out.println("关闭 所有 threads");
        executorService.shutdownNow();

        TimeUnit.SECONDS.sleep(1);
        System.out.println("Closing " + socketInput.getClass().getName());

        TimeUnit.SECONDS.sleep(1);
        System.out.println("Closing "+ System.in.getClass().getName());
        System.in.close();
    }
}
