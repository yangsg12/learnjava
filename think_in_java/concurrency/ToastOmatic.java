package think_in_java.concurrency;


import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * Created by Yang on 2015/3/12.
 */

class Toast {
    public enum Status {DRY, BUTTERED, JAMMED,}
    private Status status = Status.DRY;
    private final int id;

    public Toast(int id) {
        this.id = id;
    }
    public void butter(){status = Status.BUTTERED;}
    public void jam(){status = Status.JAMMED;}
    public Status getStatus(){return status;}
    public int getId(){return id;}

    @Override
    public String toString() {
        return "Toast " + id +" : "+status;
    }
}

class ToastQueue extends LinkedBlockingQueue<Toast> {

}

class Toaster implements Runnable {
    private ToastQueue toasts;
    private int count = 0;
    private Random random = new Random(47);

    public Toaster(ToastQueue toasts) {
        this.toasts = toasts;
    }

    @Override
    public void run() {
        try {
            while (!Thread.interrupted()) {
                TimeUnit.MILLISECONDS.sleep(100+random.nextInt(500));
                Toast t = new Toast(count++);
                System.out.println(t);
                toasts.put(t);
            }
        } catch (InterruptedException e) {
            System.out.println("Toaster interrupted");
        }

        System.out.println("Toaster off!!");
    }
}

class Butter implements Runnable {
    private ToastQueue dryQueue,butteredQueue;

    public Butter(ToastQueue dryQueue, ToastQueue butteredQueue) {
        this.dryQueue = dryQueue;
        this.butteredQueue = butteredQueue;
    }

    @Override
    public void run() {
        try {
            while (!Thread.interrupted()) {
                Toast t = dryQueue.take();
                t.butter();
                System.out.println(t);
                butteredQueue.put(t);
            }
        } catch (InterruptedException e) {
            System.out.println("butter interrupted");
        }

        System.out.println("Butter off");
    }
}

class Jammer implements Runnable {
    private ToastQueue butteredQueue, finishedQueue;

    public Jammer(ToastQueue butteredQueue, ToastQueue finishedQueue) {
        this.butteredQueue = butteredQueue;
        this.finishedQueue = finishedQueue;
    }

    @Override
    public void run() {
        try {
            while (!Thread.interrupted()) {
                Toast t = butteredQueue.take();
                t.jam();
                System.out.println(t);
                finishedQueue.put(t);

            }
        } catch (InterruptedException e) {
            System.out.println("Jam interrupted");
        }
        System.out.println("Jam off");
    }
}

class Eater implements Runnable {
    private ToastQueue finishedQueue;
    int counter = 0;

    public Eater(ToastQueue finishedQueue) {
        this.finishedQueue = finishedQueue;
    }

    @Override
    public void run() {
        try {
            while (!Thread.interrupted()) {
                Toast t = finishedQueue.take();
                if (t.getId() != counter++ || t.getStatus() != Toast.Status.JAMMED) {
                    System.out.println(" >>> error: "+ t);
                    System.exit(1);
                }else {
                    System.out.println("chomp " +t);
                }
            }
        } catch (InterruptedException e) {
            System.out.println("eater interrupt");
        }
        System.out.println("eater off");
    }
}
public class ToastOMatic {
    public static void main(String[] args) throws Exception {
        ToastQueue dryQueue = new ToastQueue(),
                butterQueue = new ToastQueue(),
                finishedQueue = new ToastQueue();
        ExecutorService exec = Executors.newCachedThreadPool();
        exec.execute(new Toaster(dryQueue));
        exec.execute(new Butter(dryQueue, butterQueue));
        exec.execute(new Jammer(butterQueue, finishedQueue));
        exec.execute(new Eater(finishedQueue));
        TimeUnit.SECONDS.sleep(5);
        exec.shutdownNow();
    }
}
