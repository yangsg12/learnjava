package think_in_java.concurrency;

import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.PriorityBlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * Created by Yang on 2015/3/26.
 */
class PrioritizeTask implements Runnable,Comparable<PrioritizeTask>{
    private Random random = new Random(47);
    private static int counter = 0;
    private final int id = counter++;
    private final int priority;
    protected static List<PrioritizeTask> sequence = new ArrayList<PrioritizeTask>();

    public PrioritizeTask(int priority) {
        this.priority = priority;
        sequence.add(this);
    }

    @Override
    public int compareTo(PrioritizeTask o) {
        return priority<o.priority?1:(priority > o.priority? -1:0);
    }

    @Override
    public void run() {
        try {
            TimeUnit.MILLISECONDS.sleep(random.nextInt(250));
        } catch (InterruptedException e) {

        }
        System.out.println(this);
    }

    @Override
    public String toString() {
        return String.format("[%1$-3d]",priority)+" Task "+id;
    }

    public String summary() {
        return "( " + id + " : " + priority + " )";
    }
}

 class EndSentinel extends PrioritizeTask{
    private ExecutorService executorService;
    public EndSentinel(ExecutorService executorService) {
        super(-1); // lowest priority
        this.executorService = executorService;
    }

    @Override
    public void run() {
        int count = 0;
        for (PrioritizeTask prioritizeTask : sequence) {
            System.out.println(prioritizeTask.summary());
            if (++count % 5 == 0) {
                System.out.println();
            }
        }
        System.out.println();
        System.out.println(this + " calling shutdownNow()");
        executorService.shutdownNow();
    }
}

class PrioritizeTaskProducer implements Runnable{
    private Random random = new Random(47);
    private Queue<Runnable> queue;
    private ExecutorService exec;
    public PrioritizeTaskProducer(Queue<Runnable> queue, ExecutorService exec){
        this.queue = queue;
        this.exec = exec;
    }

    @Override
    public void run() {
        for (int i = 0; i < 20; i++) {
            queue.add(new PrioritizeTask(random.nextInt(10)));
            Thread.yield();
        }
        try {
            for (int i = 0; i < 10; i++) {
                TimeUnit.MILLISECONDS.sleep(250);
                queue.add(new PrioritizeTask(10));
            }
            for (int i = 0; i < 10; i++) {
                queue.add(new PrioritizeTask(i));
            }

            queue.add(new EndSentinel(exec));
        } catch (InterruptedException e) {

        }
        System.out.println("finished TaskProducer ++++ ");
    }
}

class PrioritizedTaskConsumer implements Runnable{
    private PriorityBlockingQueue<Runnable> q;

    public PrioritizedTaskConsumer(PriorityBlockingQueue<Runnable> q) {
        this.q = q;
    }

    @Override
    public void run() {
        try {
            while (!Thread.interrupted()) {
                q.take().run();
            }
        } catch (InterruptedException e) {

        }

        System.out.println("finish task consumer ----");
    }
}

public class PriorityBlockingQueueDemo {
    public static void main(String[] args) throws Exception {
        Random random = new Random(47);
        ExecutorService executorService = Executors.newCachedThreadPool();
        PriorityBlockingQueue<Runnable> queue = new PriorityBlockingQueue<Runnable>();
        executorService.execute(new PrioritizeTaskProducer(queue, executorService));
        executorService.execute(new PrioritizedTaskConsumer(queue));
    }
}
