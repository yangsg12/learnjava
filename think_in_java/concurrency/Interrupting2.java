package think_in_java.concurrency;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by Yang on 2015/3/12.
 *
 * interrupt() 打断 互斥 阻塞 的调用.
 */
 class BlockMutex {
    private Lock lock = new ReentrantLock();

    public BlockMutex() {
        lock.lock();
    }

    public void f() {
        try {
            lock.lockInterruptibly();
            System.out.println("lock acquired in f()");
        } catch (InterruptedException e) {
            System.out.println("Interrupted from lock acquire in f()");
        }
    }
}

class Blocked2 implements Runnable {
    BlockMutex blocked = new BlockMutex();

    @Override
    public void run() {
        System.out.println(" waiting for f() in BlockMutex");
        blocked.f();
        System.out.println("broken out of blocked call");
    }
}

public class Interrupting2 {
    public static void main(String[] args) throws Exception {
        Thread t = new Thread(new Blocked2());
        t.start();
        TimeUnit.SECONDS.sleep(1);
        System.out.println("开始 t.interrupt()");
        t.interrupt();
    }
}
