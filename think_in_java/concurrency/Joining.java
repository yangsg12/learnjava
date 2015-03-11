package think_in_java.concurrency;

import sun.awt.windows.ThemeReader;

/**
 * Created by Yang on 2015/3/11.
 */
class Sleeper extends Thread{
    private int duration;

    public Sleeper(String name, int sleepTime) {
        super(name);
        duration = sleepTime;
        start();
    }

    @Override
    public void run() {
        try {
            sleep(duration);
        } catch (InterruptedException e) {

            System.out.println(getName()+ " was interrupted. isInterrupted() "+ isInterrupted());
        }
        System.out.println(getName()+" 大爷醒了--");
    }
}
class Joiner extends Thread{
    private Sleeper sleeper;

    public Joiner(String name, Sleeper sleeper) {
        super(name);
        this.sleeper = sleeper;
        start();
    }

    @Override
    public void run() {
        try {
            sleeper.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(getName()+ " join 完成了");
    }
}
public class Joining {
    public static void main(String[] args) {
        Sleeper sleeper = new Sleeper("Sleepy",1500),
                grumpy = new Sleeper("Grumpy", 1500);
        Joiner dopey = new Joiner("Dopey", sleeper),
                doc = new Joiner("Doc", grumpy);
        grumpy.interrupt();

    }
}
