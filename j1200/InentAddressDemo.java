package j1200;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * Created by Yang on 2015/3/6.
 */
public class InentAddressDemo {
    public static void main(String[] args) {
        InetAddress inetAddress;
        try {
            inetAddress = InetAddress.getLocalHost();
            String localName= inetAddress.getHostName();
            String localIp = inetAddress.getHostAddress();
            System.out.println("local name :"+localName);
            System.out.println("local ip :"+localIp);
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
    }
}
