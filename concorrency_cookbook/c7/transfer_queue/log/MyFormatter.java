package concorrency_cookbook.c7.transfer_queue.log;






import java.io.IOException;

import java.text.SimpleDateFormat;

import java.util.Date;
import java.util.concurrent.TimeUnit;
import java.util.logging.*;

/**
 * Created by Yang on 2016/3/3.
 */
public class MyFormatter extends Formatter {

    @Override
    public String format(LogRecord record) {
        StringBuilder sb = new StringBuilder();
        sb.append("["+record.getLevel()+"] - ");
        sb.append(new Date(record.getMillis()) +" : ");
        sb.append(record.getSourceClassName()+"."+record.getSourceMethodName()+" : ");
        sb.append(record.getMessage()+"\n");

        return sb.toString();
    }


}

class MyLogger {
    private static Handler handler;
    public static Logger getLogger(String name){
        Logger logger = Logger.getLogger(name);
        logger.setLevel(Level.ALL);
        try {
            if (handler == null){

                handler = new FileHandler("myLog.log");
                Formatter formatter = new MyFormatter();
                handler.setFormatter(formatter);

            }
            if (logger.getHandlers().length == 0){
                logger.addHandler(handler);
            }
        }


        catch (IOException e) {
            e.printStackTrace();
        }

        return logger;
    }
}


class Task implements Runnable{
    @Override
    public void run() {
        Logger logger = MyLogger.getLogger(this.getClass().getName());
        logger.entering(Thread.currentThread().getName(),"run()");

        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        logger.exiting(Thread.currentThread().getName(),"run()",Thread.currentThread());

    }
}

class CustomLog{
    public static void main(String[] args) {
        Logger logger = MyLogger.getLogger("core");
        logger.entering("core","main()",args);
        Thread[] threads = new Thread[5];
        for (int i = 0; i < threads.length; i++) {
            logger.log(Level.INFO,"launching thread "+i);
            Task task = new Task();
            threads[i] = new Thread(task);
            logger.log(Level.INFO,"thread created : "+ threads[i].getName());
            threads[i].start();
        }

        logger.log(Level.INFO," 10 threads created. ");

        for (int i = 0; i < threads.length; i++) {
            try {
                threads[i].join();
                logger.log(Level.INFO,"Thread finished ",threads[i]);
            } catch (InterruptedException e) {
                //e.printStackTrace();
                logger.log(Level.SEVERE," 这不对啊",e);
            }
        }

        logger.exiting("core","main()");
    }
}