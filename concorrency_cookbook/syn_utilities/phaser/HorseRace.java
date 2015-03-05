package concorrency_cookbook.syn_utilities.phaser;

import java.util.ArrayList;
import java.util.concurrent.Phaser;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by Yang on 2015/3/2.
 * 可变数目, 集合点.
 * 所有的马 到 起跑栏
 */
public class HorseRace {
    private final int NUMBER_OF_HORSE =12;
    private  final static int INIT_PARTIES = 1 ;
    private final static Phaser manager = new Phaser(INIT_PARTIES);

    public static void main(String[] args) {
        Thread raceMonitor = new Thread(new RaceMonitor());
        raceMonitor.start();
        new HorseRace().manageRace();

    }

    public void manageRace(){
        ArrayList<Horse> horseArray = new ArrayList<Horse>();
        for (int i = 0; i < NUMBER_OF_HORSE; i++) {
            horseArray.add(new Horse());
        }
        runRace(horseArray);
    }

    private void runRace(Iterable<Horse> team) {
        log(" Assign all horses, then start race");
        for (final Horse horse : team){
            final String dev = horse.toString();
            log("安排 "+ dev +" 到赛场");
            manager.register();

            new Thread(){
                @Override
                public void run() {
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    log(dev + " 要等所有的马");
                    manager.arriveAndAwaitAdvance();
                    horse.run();
                }
            }.start();
        }
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        log("所有的马 都 到齐了, 开始比赛!!!");
        manager.arriveAndDeregister();

    }

    private static void log(String msg){
        System.out.println(msg);
    }
    private static class Horse implements Runnable{
        private final static AtomicInteger idSource = new AtomicInteger();
        private final int id = idSource.incrementAndGet();
        public void run() {
            log(toString()+" : running ");
        }

        @Override
        public String toString() {
            return "horse "+ id;
        }
    }

    private static class RaceMonitor implements Runnable{
        @Override
        public void run() {
            while (true){
                System.out.printf(" 现在到了 %d 匹马 \n ", HorseRace.manager.getArrivedParties());
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                if (HorseRace.manager.getArrivedParties() == 0){
                    break;
                }
            }
        }
    }
}
