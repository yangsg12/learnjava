package think_in_java.concurrency;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by Yang on 2015/3/11.
 */

class Pair{
    private int x,y;

    public Pair(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public Pair(){ this(0,0);}

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void incrementX(){x++;}
    public void incrementY(){y++;}

    @Override
    public String toString() {
        return "x: "+ x + ", y: "+y;
    }



    public class PairValueNotEqualException extends RuntimeException{
        public PairValueNotEqualException(){
            super("pair values not equal : "+ Pair.this);
        }
    }

    public void checkState() {
        if (x != y) {
            throw new PairValueNotEqualException();
        }
    }

}

// thread safe

abstract class PairManager{
    AtomicInteger checkCounter = new AtomicInteger(0);
    protected Pair p = new Pair();
    private List<Pair> storage = Collections.synchronizedList(new ArrayList<Pair>());
    public synchronized Pair getPair() {
        return new Pair(p.getX(), p.getY());
    }
    protected void store(Pair p) {
        storage.add(p);
        try {
            TimeUnit.MILLISECONDS.sleep(50);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public abstract void increment();
}

class PairManager1 extends PairManager {
    @Override
    public synchronized void increment() {
        p.incrementX();
        p.incrementY();
        store(getPair());
        System.out.println(" 同步 方法");
    }
}

// use critical section

class PairManager2 extends PairManager {
    @Override
    public void increment() {
        Pair temp;
        synchronized (this){
            p.incrementY();
            p.incrementX();
            temp = getPair();
        }
        System.out.println(" use critical seciton");

        store(temp);
    }
}

class PairManipulator implements Runnable {
    private PairManager pm;

    public PairManipulator(PairManager pm) {
        this.pm = pm;
    }

    @Override
    public void run() {
        while (true) {
            pm.increment();
        }
    }

    @Override
    public String toString() {
        return "Pair: "+ pm.getPair()+" checkCounter = "+ pm.checkCounter.get();
    }
}

class PairCheck implements Runnable {
    private PairManager pm;

    public PairCheck(PairManager pm) {
        this.pm = pm;
    }

    @Override
    public void run() {
        while (true) {
            pm.checkCounter.incrementAndGet();
            pm.getPair().checkState();
        }
    }
}


public class CriticalSection {
    static void testApproaches(PairManager pman1, PairManager pman2){
        ExecutorService executorService = Executors.newCachedThreadPool();
        PairManipulator pm1 = new PairManipulator(pman1),
                        pm2 = new PairManipulator(pman2);
        PairCheck pairCheck1 = new PairCheck(pman1),
                    pairCheck2 = new PairCheck(pman2);
        executorService.execute(pm1);
        executorService.execute(pm2);
        executorService.execute(pairCheck1);
        executorService.execute(pairCheck2);

        try {
            TimeUnit.MILLISECONDS.sleep(500);
        } catch (InterruptedException e) {
            System.out.println("sleep interrupted ");
        }
        System.out.println("pm1 "+ pm1 + "\npm2 "+pm2);
        System.exit(0);
    }

    public static void main(String[] args) {
        PairManager
                pman1 = new PairManager1(),
                pman2 = new PairManager2();
        testApproaches(pman1,pman2);
    }
}
