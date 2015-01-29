package concorrency_cookbook;

import java.awt.*;
import java.util.Deque;

/**
 * Created by Yang on 2015/1/29.
 */
public class WriterTask implements Runnable {
    private Deque<Event> deque;
    public WriterTask(Deque<Event> deque){
        this.deque= deque;
    }
    @Override
    public void run() {
        for (int i=0; i<100;i++){
            System.out.printf("running %s\n",Thread.currentThread().getName());

        }

    }
}
