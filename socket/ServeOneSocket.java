package socket;

import java.io.*;
import java.net.Socket;

/**
 * Created by Yang on 2015/1/27.
 */
public class ServeOneSocket extends Thread {
    private Socket socket;
    private BufferedReader in;
    private PrintWriter out;
    private String content;

    public ServeOneSocket(Socket s, String c) throws IOException {
        socket = s;
        content = c;
        in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(socket.getOutputStream())));
        start(); // call run()

    }

    @Override
    public void run() {
        try {
            while (true) {

                String str = null;
                str = in.readLine();
                if (str.equals("END")) {
                    break;
                }
                System.out.println("Echoing  " + str);
                out.println(str);
                out.println(content);
            }
            System.out.println("Closing  ");
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                socket.close();
            } catch (IOException e) {

            }
        }
    }
}
