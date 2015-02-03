package concorrency_cookbook.syn_utilities.semaphore.phaser;

import java.util.concurrent.Phaser;

/**
 * Created by Yang on 2015/2/3.
 */
public class Main {
    public static void main(String[] args) {
        Phaser phaser = new Phaser(3);
        FileSearch system = new FileSearch("C:\\Users\\Yang\\Pictures\\Camera Roll", "jpg", phaser);
        FileSearch apps = new FileSearch("C:\\Users\\Yang\\Pictures\\本机照片", "jpg", phaser);
        FileSearch document = new FileSearch("C:\\Users\\Yang\\Music", "mp3", phaser);

        Thread sysThread = new Thread(system, "system");
        sysThread.start();
        Thread appThread = new Thread(apps, " apps");
        appThread.start();
        Thread docThread = new Thread(document, "document");
        docThread.start();

        try {
            sysThread.join();
            appThread.join();
            docThread.join();
        }catch (InterruptedException e){
            e.printStackTrace();
        }

        System.out.println("Terminated :  "+phaser.isTerminated());

    }
}
