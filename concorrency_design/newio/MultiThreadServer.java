package concorrency_design.newio;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by Yang on 2017/7/20.
 */
public class MultiThreadServer {
    private static ExecutorService tp = Executors.newCachedThreadPool();
    static class HandleMsg implements Runnable{
        Socket client;

        public HandleMsg(Socket client) {
            this.client = client;
        }

        @Override
        public void run() {
            BufferedReader is = null;
            PrintWriter os = null;
            try {
                is = new BufferedReader(new InputStreamReader(client.getInputStream()));
                os = new PrintWriter(client.getOutputStream(), true);
                String inputLine = null;
                long b = System.currentTimeMillis();
                while ((inputLine = is.readLine()) != null) {
                    os.println(inputLine);

                }
                long e = System.currentTimeMillis();
                System.out.println("spend " + (e - b) + " ms");




            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                try {
                    if (is != null) {
                        is.close();
                    }
                    if (os != null) {
                        os.close();
                    }
                    client.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void main(String[] args) {
        ServerSocket server = null;
        Socket client = null;
        try  {
              server = new ServerSocket(8000);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

        while (true) {
            try  {
                client = server.accept();
                System.out.println(client.getRemoteSocketAddress() + " connect!");
                tp.execute(new HandleMsg(client));

            }catch (IOException e){
                e.printStackTrace();
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }
}
