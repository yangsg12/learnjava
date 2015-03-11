package think_in_java.concurrency;

import java.util.concurrent.TimeUnit;

/**
 * Created by Yang on 2015/3/11.
 */
class ADaemon implements Runnable{
    @Override
    public void run() {
        System.out.println(" Starting ADaemon...");
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        finally {
            System.out.println("这个一直在跑??");
        }
    }
}
public class DaemonsDontRunFinally {
    public static void main(String[] args) throws Exception {
        Thread thread = new Thread(new ADaemon());
        //thread.setDaemon(true);  finally 执行了
        thread.start();
    }
}
