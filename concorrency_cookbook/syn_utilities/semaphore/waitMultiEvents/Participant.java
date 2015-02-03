package concorrency_cookbook.syn_utilities.semaphore.waitMultiEvents;

import java.util.concurrent.TimeUnit;

/**
 * Created by Yang on 2015/2/3.
 */
public class Participant implements Runnable {
    private VideoConference videoConference;
    private String name;
    public Participant(VideoConference videoConference,String name){
        this.videoConference = videoConference;
        this.name = name;
    }

    @Override
    public void run() {
        long duration = (long) (Math.random() * 10);
        try {
            TimeUnit.SECONDS.sleep(duration);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        videoConference.arrive(name);
        videoConference.getCount();

    }
}
