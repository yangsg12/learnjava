package think_in_java.concurrency;

/**
 * Created by Yang on 2015/3/11.
 */
public class SimpleThread extends Thread {
    private int countDown =5;
    private static int threadCount = 0;

    public SimpleThread() {
        super(Integer.toString(++threadCount)); //改名字
        start();
    }

    public String toString() {
        return "# "+getName() +" ( "+countDown+" ) ";
    }

    @Override
    public void run() {
        while (true) {
            System.out.println(this);
            if (--countDown == 0) {
                return;
            }
        }
    }

    public static void main(String[] args) {
        for (int i = 0; i < 5; i++) {
            new SimpleThread();
        }
    }
}
