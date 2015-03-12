package think_in_java.concurrency;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousCloseException;
import java.nio.channels.ClosedByInterruptException;
import java.nio.channels.SocketChannel;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

/**
 * Created by Yang on 2015/3/12.
 * 运行不成功 ???
 */
 class NIOBlocked implements Runnable {
    private final SocketChannel sc;

    public NIOBlocked(SocketChannel sc) {
        this.sc = sc;
    }

    @Override
    public void run() {
        System.out.println("waiting for read() in " + this);
        try {
            sc.read(ByteBuffer.allocate(1));

        } catch (ClosedByInterruptException e){
            System.out.println("Closed by Interrupted exception");
        } catch (AsynchronousCloseException e){
            System.out.println("AsynchronousCloseException");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        System.out.println("Exiting NIOBlocked.run() "+this);
    }
}


public class NIOInterruption{
    public static void main(String[] args) throws Exception {
        ExecutorService exec = Executors.newCachedThreadPool();
        ServerSocket server = new ServerSocket(8080);
        InetSocketAddress inetSocketAddress = new InetSocketAddress("local host", 8080);
        SocketChannel sc1 = SocketChannel.open(inetSocketAddress);
        SocketChannel sc2 = SocketChannel.open(inetSocketAddress);
        Future<?> f = exec.submit(new NIOBlocked(sc1));
        exec.execute(new NIOBlocked(sc2));
        exec.shutdown();
        TimeUnit.SECONDS.sleep(1);
        f.cancel(true);
        TimeUnit.SECONDS.sleep(1);
        sc2.close();
    }
}
