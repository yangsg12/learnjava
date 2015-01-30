package concorrency_cookbook.uncaught_exceptions;

/**
 * Created by Yang on 2015/1/30.
 */
public class MyThreadGroup extends ThreadGroup {
    public MyThreadGroup( String name){
        super(name); //注意是括号。
    }

    @Override
    public void uncaughtException(Thread t, Throwable e) {
        System.out.printf("The thread has throw an exception: %s\n", t.getId());
        e.printStackTrace(System.out);
        System.out.printf("Terminate the rest threads \n");
        interrupt();
    }
}
