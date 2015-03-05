package concorrency_cookbook.syn_utilities.waitMultiEvents;

/**
 * Created by Yang on 2015/2/3.
 */
public class Main {
    public static void main(String[] args) {
        VideoConference videoConference = new VideoConference(10);
        Thread threadConference = new Thread(videoConference);
        threadConference.start();
        for (int i =0;i<10;i++) {
            Participant p = new Participant(videoConference, "大爷 " + i);
            new Thread(p).start();

        }
    }
}
