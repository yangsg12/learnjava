package concorrency_cookbook;

import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * Created by Yang on 2015/1/29.
 */
public class DateSourceLoader implements Runnable {
    @Override
    public void run() {
        System.out.printf("Begin data source loading %s\n", new Date());
        try {
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.printf("Data source loading has finished %s \n", new Date());

    }


    public static void main(String[] args) {
        DateSourceLoader dateSourceLoader = new DateSourceLoader();
        Thread thread = new Thread(dateSourceLoader);
        Thread thread1 = new Thread(dateSourceLoader);
        thread.start();
        thread1.start();
        try{
        thread.join();
        thread1.join();}
        catch (InterruptedException e){
            e.printStackTrace();
        }

        System.out.printf("Configuration is loaded %s\n",new Date());
    }
}
