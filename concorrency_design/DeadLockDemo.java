package concorrency_design;

/**
 * Created by Yang on 2017/7/17.
 */
public class DeadLockDemo extends Thread {
    protected Object tool;
    static Object fork1 = new Object();
    static Object fork2 = new Object();

    public DeadLockDemo(Object object) {
        this.tool = object;
        if (tool == fork1) {
            this.setName("哲学家 A");
        }
        if (tool == fork2) {
            this.setName("哲学家 BBB");
        }
    }

    @Override
    public void run() {
        if (tool == fork1) {
            synchronized (fork1){
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (fork2){
                    System.out.println("哲学 家 A 吃白饭了");
                }
            }
        }

        if (tool == fork2){
            synchronized (fork2){
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                synchronized (fork1){
                    System.out.println("哲学家BBB 吃饭了");
                }
            }
        }
    }

    public static void main(String[] args) throws InterruptedException{
        DeadLockDemo a = new DeadLockDemo(fork1);
        DeadLockDemo b = new DeadLockDemo(fork2);

        a.start();
        b.start();

        Thread.sleep(1000);
    }
}
