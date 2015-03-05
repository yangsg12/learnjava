package concorrency_cookbook.syn_utilities.cyclicBarrier;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.*;

/**
 * Created by Yang on 2015/2/27.
 */
public class HorseRace {
    static final int FINISH_LINE = 75;
    private List<Horse> horses = new ArrayList<Horse>();
    private ExecutorService exec = Executors.newCachedThreadPool();
    private CyclicBarrier cyclicBarrier;

    public HorseRace(int nHorses, final int pause){
        cyclicBarrier = new CyclicBarrier(nHorses, new Runnable() {
            @Override
            public void run() {
                StringBuilder s = new StringBuilder();
                for (int i = 0; i < FINISH_LINE; i++) {
                    s.append("=");
                }
                System.out.println(s);
                for (Horse horse:horses){
                    System.out.println(horse.tracks());
                }
                for (Horse horse : horses){
                    if (horse.getStride()>= FINISH_LINE){
                        System.out.println(horse +" win");
                        exec.shutdownNow();
                    }
                }
                try {
                    TimeUnit.MICROSECONDS.sleep(pause);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                    System.out.println("barrier action sleep interrupted.");
                }

            }
        });

        for (int i = 0; i < nHorses; i++) {
            Horse horse = new Horse(cyclicBarrier);
            horses.add(horse);
            exec.execute(horse);
        }
    }

    public static void main(String[] args) {
        int nHorses = 7;
        int pause = 200;
        if (args.length>0){
            int n = new Integer(args[0]);
            nHorses = n >0?n:nHorses;
        }
        if (args.length >1) {
            int p = new Integer(args[1]);
            pause = p > -1? p: pause;
        }

        new HorseRace(nHorses, pause);
    }
}


class Horse implements Runnable {
    private static int counter = 0 ;
    private final int id = counter++;
    private int stride = 0;
    private static Random random = new Random(47);
    private static CyclicBarrier cyclicBarrier;

    public Horse(CyclicBarrier b){
        cyclicBarrier = b;
    }

    public synchronized int getStride() {
        return stride;
    }

    @Override
    public void run() {
        while (!Thread.interrupted()){
            synchronized (this){
                stride +=random.nextInt(3); //produce 0,1,2
            }
            try {
                cyclicBarrier.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (BrokenBarrierException e) {
                e.printStackTrace();
            }
        }

    }

    public String toString() {
        return "horse " + id + " ";
    }
    public String tracks() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < getStride(); i++) {
            sb.append("*");
        }
        sb.append(id);
        return sb.toString();
    }
}
