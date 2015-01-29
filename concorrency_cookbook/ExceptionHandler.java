package concorrency_cookbook;

/**
 * Created by Yang on 2015/1/29.
 */
public class ExceptionHandler implements Thread.UncaughtExceptionHandler {
    public void uncaughtException(Thread t,Throwable e) {
        System.out.printf("An exception has captured\n");
        System.out.printf("Thread : %s\n", t.getId());
        System.out.printf("Exception %s: %s\n",e.getClass(),e.getMessage());
        System.out.printf("StackTrace: \n");
        e.printStackTrace();
        System.out.printf("Thread status : %s \n", t.getState());
    }










}