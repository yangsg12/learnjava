package think_in_java.concurrency;

/**
 * Created by Yang on 2015/3/11.
 */
public class MoreBasicThreads {
    public static void main(String[] args) {
        for (int i = 0; i < 5; i++) {
            new Thread(new LiftOff()).start();
        }
        System.out.println("Waiting for LiftOff!!!");
    }
}
