package socket;

import java.io.IOException;
import java.net.Inet4Address;
import java.net.InetAddress;

/**
 * Created by Yang on 2015/1/27.
 */
public class MultiSocketClient {
    static final int MAX_THREADS = 10;

    public static void main(String[] args) throws IOException, InterruptedException{
        InetAddress address = InetAddress.getByName(null);
        while (true){
            if (ClientThreadSocket.getThreadCounter() < MAX_THREADS){
                new ClientThreadSocket(address);
                Thread.currentThread().sleep(300);
            }
        }

    }
}
