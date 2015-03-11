package think_in_java.concurrency;

/**
 * Created by Yang on 2015/3/11.
 */
public class LiftOff implements Runnable {
    protected int countDown =10;
    private static int taskCount = 0;
    private final int id = taskCount++;
    public LiftOff(){}

    public LiftOff(int countDown) {
        this.countDown = countDown;
    }

    public String status() {
        return "# " + id + " ( " + (countDown > 0 ? countDown : "LiftOFF !") + " )";
    }

    @Override
    public void run() {
        while (countDown-- > 0) {
            System.out.println(status());
            Thread.yield();
        }
    }
}


class MainThread{
    public static void main(String[] args) {
        LiftOff launch = new LiftOff();
        launch.run();
    }
}