package think_in_java.concurrency;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by Yang on 2015/3/10.
 */

abstract class Incrementable{
    protected long counter = 0;

    public abstract void increment();
}

class SynchronizingTest extends Incrementable {
    @Override
    public void increment() {
        ++counter;
    }
}

class LockingTest extends Incrementable {
    private Lock lock = new ReentrantLock();

    @Override
    public void increment() {
        lock.lock();
        try{
            ++counter;
        }finally {
            lock.unlock();
        }
    }
}


public class SimpleMicroBenchmark {
    static long test(Incrementable incrementable) {
        long start = System.nanoTime();
        for (long i = 0; i < 1000000L; i++) {
            incrementable.increment();
        }
        return System.nanoTime()-start;
    }

    public static void main(String[] args) {
        long synchTime = test(new SynchronizingTest());
        long lockTime = test(new LockingTest());
        System.out.printf("synchronized: %1$10d\n", synchTime);
        System.out.printf("Lock :        %1$10d\n", lockTime);
        System.out.printf("lock / synchronized = %1$.3f",(double)lockTime/(double)synchTime);
    }
}
