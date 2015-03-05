package concorrency_cookbook.syn_utilities.phaser.myphaser;

import java.util.Date;
import java.util.concurrent.Phaser;
import java.util.concurrent.TimeUnit;

/**
 * Created by Yang on 2015/2/5.
 */
public class Student implements Runnable {
    private Phaser phaser;

    public Student(Phaser phaser) {
        this.phaser = phaser;
    }

    @Override
    public void run() {
        System.out.printf(" %s 已经到了,时间 : %s \n",Thread.currentThread().getName(),new Date());
        phaser.arriveAndAwaitAdvance();

        System.out.printf(" %s 开始做 exercise 1 了,时间 : %s \n",Thread.currentThread().getName(),new Date());
        doExercise1();
        phaser.arriveAndAwaitAdvance();

        System.out.printf(" %s 开始做 exercise 2 了,时间 : %s \n",Thread.currentThread().getName(),new Date());
        doExercise2();
        phaser.arriveAndAwaitAdvance();

        System.out.printf(" %s 开始做 exercise 3 了,时间 : %s \n",Thread.currentThread().getName(),new Date());
        doExercise3();
        phaser.arriveAndAwaitAdvance();

        System.out.printf(" %s 开始做 exercise 4 了,时间 : %s \n",Thread.currentThread().getName(),new Date());
        doExercise4();
        phaser.arriveAndAwaitAdvance();

    }

    private void doExercise1() {
        long duration = (long) (Math.random() * 10);
        try {
            TimeUnit.SECONDS.sleep(duration);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void doExercise2() {
        long duration = (long) (Math.random() * 10);
        try {
            TimeUnit.SECONDS.sleep(duration);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    private void doExercise3() {
        long duration = (long) (Math.random() * 10);
        try {
            TimeUnit.SECONDS.sleep(duration);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    private void doExercise4() {
        long duration = (long) (Math.random() * 10);
        try {
            TimeUnit.SECONDS.sleep(duration);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
