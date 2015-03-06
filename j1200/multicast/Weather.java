package j1200.multicast;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;

/**
 * Created by Yang on 2015/3/6.
 * UDP 广播
 */
public class Weather extends Thread {
    String weather = "天气预报,气温下降,注意保暖!!";
    int port = 9898;
    InetAddress inetAddress = null;
    MulticastSocket socket = null;
    Weather() {
        try{
            inetAddress = InetAddress.getByName("224.255.10.0");
            socket = new MulticastSocket(port);
            socket.setTimeToLive(1);
            socket.joinGroup(inetAddress);

        }catch (Exception e){
            e.printStackTrace();
        }

    }

    @Override
    public void run() {
        while (true){
            DatagramPacket packet = null;
            byte[] data = weather.getBytes();
            packet = new DatagramPacket(data, data.length, inetAddress, port);
            System.out.println(new String(data));
            try {
                socket.send(packet);

                sleep(300);

            } catch (IOException e) {
                e.printStackTrace();
            } catch (InterruptedException e){
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        Weather w = new Weather();
        w.start();
    }
}
