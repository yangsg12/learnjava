package concorrency_cookbook;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by Yang on 2015/1/29.
 */
public class MainSetPriority {
    public static void main(String[] args) {
        Thread [] threads = new Thread[10];
        Thread.State status [] = new Thread.State[10];
        for (int i=0 ; i<10 ; i++){
            threads [i] = new Thread(new Calculator(i));
            if ((i%2)==0){
                threads[i].setPriority(Thread.MAX_PRIORITY);
            }else {
                threads[i].setPriority(Thread.MIN_PRIORITY);
            }

            threads[i].setName("Thread "+i);

        }
//        try {
//        FileWriter file = new FileWriter(".\\data\\log.txt");
//        PrintWriter printWriter = new PrintWriter(file);
//            for (int i =0 ; i<10;i++){
//                printWriter.println("Main : status of thread "+i+": "+ threads[i].getState());
//            }
//        }
//
//
//        catch (IOException e){
//            e.printStackTrace();
//        }

        for (int i =0;i<10 ;i++){
            System.out.println("status of thread"+i+":"+threads[i].getState());
        }

        for (int i=0;i<10;i++){
            threads[i].start();
        }

        for (int i =0;i<10 ;i++){
            System.out.println("status of thread"+i+":"+threads[i].getState());
        }
    }
}
