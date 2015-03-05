package concorrency_cookbook.syn_utilities.phaser.myphaser;

/**
 * Created by Yang on 2015/2/5.
 */
public class Main {
    public static void main(String[] args) {
        MyPhaser myPhaser = new MyPhaser();
        Student student[] = new Student[5];
        for (int i = 0; i < 5; i++) {
            student[i] = new Student(myPhaser);
            myPhaser.register();
        }

        Thread threads[] = new Thread[student.length];
        for (int i = 0; i < student.length; i++) {
            threads[i] = new Thread(student[i], "学生 " + i);
            threads[i].start();
        }

        for (int i = 0; i < threads.length; i++) {
            try {
                threads[i].join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        System.out.printf("Main : phaser 结束了! : %s.",myPhaser.isTerminated());
    }
}
