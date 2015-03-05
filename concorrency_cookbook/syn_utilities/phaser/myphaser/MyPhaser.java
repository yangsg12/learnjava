package concorrency_cookbook.syn_utilities.phaser.myphaser;

import java.util.concurrent.Phaser;

/**
 * Created by Yang on 2015/2/5.
 */
public class MyPhaser extends Phaser {
    @Override
    protected boolean onAdvance(int phase, int registeredParties) {
        switch (phase){
            case 0:
                return studentsArrived();
            case 1:
                return finishExercise1();
            case 2:
                return finishExercise2();
            case 3:
                return finishExam();
            default:
                return true;
        }
    }

    private boolean studentsArrived() {
        System.out.printf("Phaser : 学生准备好了 \n");
        System.out.printf("来了 %d 个学生 \n", getRegisteredParties());
        return false;
    }

    private boolean finishExercise1(){
        System.out.printf("所有学生完成了 exercise 1 . \n");
        System.out.printf("准备          exercise 2 . \n");
        return false;
    }

    private boolean finishExercise2(){
        System.out.printf("所有学生完成了 exercise 2 . \n");
        System.out.printf("准备考试了啊  . \n");
        return false;
    }

    private boolean finishExam(){
        System.out.printf("学生老爷们完成考试了!!!\n");
        System.out.printf("射射~~~!!!\n");
        return true;  //这个!!!
    }
}
