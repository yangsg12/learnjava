package think_in_java.concurrency;

import java.util.Random;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by Yang on 2015/3/10.
 */

abstract class Accumulator {
    public static long cycles = 5000L;
    private static final int N = 4;
    public static ExecutorService exec = Executors.newFixedThreadPool(N * 2);
    private static CyclicBarrier barrier = new CyclicBarrier(N * 2 + 1);
    protected volatile int index = 0;
    protected volatile long value = 0;
    protected String id = " error";
    protected long duration = 0;
    protected final static int SIZE = 10000;
    protected static int[] preLoaded = new int [SIZE+1];
    static {
        Random random = new Random(47);
        for (int i = 0; i < SIZE; i++) {
            preLoaded[i] = random.nextInt();
        }
    }

    public abstract void accumulate();
    public abstract long read();
    private class Modifier implements Runnable{
        @Override
        public void run() {
            for (long i = 0; i < cycles; i++) {
                accumulate();
            }
            try {
                barrier.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (BrokenBarrierException e) {
                e.printStackTrace();
            }
        }
    }

    private class Reader implements Runnable{
        private volatile long value;

        @Override
        public void run() {
            for (long i = 0; i < cycles; i++) {
                value = read();
            }
            try {
                barrier.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (BrokenBarrierException e) {
                e.printStackTrace();
            }
        }
    }

    public void timedTest(){
        long start = System.nanoTime();
        for (int i = 0; i < N; i++) {
            exec.execute(new Modifier());
            exec.execute(new Reader());
        }
        try {
            barrier.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (BrokenBarrierException e) {
            e.printStackTrace();
        }
        long duration = System.nanoTime() - start;
        System.out.printf("%-13s : %13d\n",id,duration);
    }

    public static void report(Accumulator accumulator1, Accumulator accumulator2){
        System.out.printf("%22s: %.2f\n",accumulator1.id+" /" +accumulator2.id,
                (double)accumulator1.duration/(double)accumulator2.duration);
    }
}

class BaseLine extends Accumulator{
    {
        id = "BaseLine";
    }

    @Override
    public void accumulate() {
        if (index >= SIZE) {
            index = 0;
        }
        value += preLoaded[index++];

    }

    @Override
    public long read() {
        return value;
    }
}

class SynchronizedTest extends Accumulator{
    {
        id = "Synchronized";
    }

    @Override
    public void accumulate() {
        if (index >= SIZE) {
            index = 0;
        }
        value += preLoaded[index++];


    }

    @Override
    public long read() {
        return value;
    }
}

class LockTest extends Accumulator{
    {id = "Lock";}

    private Lock lock = new ReentrantLock();

    @Override
    public void accumulate() {
        lock.lock();
        try {
            value += preLoaded[index++];
            if (index >= SIZE) {
                index = 0;
            }
        } finally {
            lock.unlock();
        }
    }

    @Override
    public long read() {
        lock.lock();
        try{
            return value;
        }finally {
            lock.unlock();
        }
    }
}

class AtomicTest extends Accumulator{
    {id = "Atomic";}

    private AtomicInteger index = new AtomicInteger(0);
    private AtomicLong value = new AtomicLong(0);

    @Override
    public void accumulate() {
        int i = index.getAndIncrement();
        value.getAndAdd(preLoaded[i]);
        if (++i >= SIZE) {
            index.set(0);
        }
    }

    @Override
    public long read() {
        return value.get();
    }
}

public class SynchronizationComparisons {
    static BaseLine baseLine = new BaseLine();
    static SynchronizedTest synchronizedTest = new SynchronizedTest();
    static LockTest lockTest = new LockTest();
    static AtomicTest atomicTest = new AtomicTest();

    static void test() {
        System.out.println("=======================");
        System.out.printf("%-12s : % 13d\n", "Cycles", Accumulator.cycles);
        baseLine.timedTest();
        synchronizedTest.timedTest();
        lockTest.timedTest();
        atomicTest.timedTest();
        Accumulator.report(synchronizedTest, baseLine);
        Accumulator.report(lockTest, baseLine);
        Accumulator.report(atomicTest, baseLine);
        Accumulator.report(synchronizedTest, lockTest);
        Accumulator.report(synchronizedTest, atomicTest);
        Accumulator.report(lockTest,atomicTest);

    }

    public static void main(String[] args) {
        int interations = 5;
        System.out.println("warm up");
        baseLine.timedTest();
        for (int i = 0; i < interations; i++) {
            test();
            Accumulator.cycles *=2;
        }

        Accumulator.exec.shutdown();
    }
}
