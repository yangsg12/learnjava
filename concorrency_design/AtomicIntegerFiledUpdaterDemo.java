package concorrency_design;

import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;

/**
 * Created by Yang on 2017/7/17.
 */
public class AtomicIntegerFiledUpdaterDemo {
    public static class Candidate{
        int id;
        volatile int score;
    }

   // public final static AtomicIntegerFieldUpdater<Candidate> scoreUpdater
    //        = new AtomicIntegerFieldUpdater.newUpdater(Candidate.class,"score");
}
