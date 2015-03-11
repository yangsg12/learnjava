package think_in_java.concurrency;

import java.util.concurrent.TimeUnit;

/**
 * Created by Yang on 2015/3/11.
 */
class InnerThread1{
    private int countDown = 5;
    private Inner inner;
    private class Inner extends Thread{
        Inner(String name) {
            super(name);
            start();
        }

        @Override
        public void run() {
            while (true) {
                System.out.println(this);
                if (--countDown == 0) return;

                    try {
                        sleep(10);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

            }
        }

        @Override
        public String toString() {
            return getName()+" : "+countDown;
        }
    }

    public InnerThread1(String name) {
        inner = new Inner(name);
    }
}

// an anonymous inner class
class InnerThread2{
    private int countDown = 5;
    private Thread thread;

    public InnerThread2(String name) {
        thread = new Thread(name){
            @Override
            public void run() {
                while (true) {
                    System.out.println(this);
                    if (--countDown == 0) {
                        return;
                    }
                    try {
                        sleep(10);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

            }

            @Override
            public String toString() {
                return getName()+" : "+countDown;
            }
        };
        thread.start();
    }
}

// a named Runnable implementation
class InnerRunnable1{
    private int countDown = 5;
    private Inner inner;
    private class Inner implements Runnable{
        Thread thread;

        Inner(String name) {
            thread = new Thread(this, name);
            thread.start();
        }

        @Override
        public void run() {
            while (true) {
                System.out.println(this);
                if (-- countDown == 0) return;
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }

        @Override
        public String toString() {
            return thread.getName()+" : " +countDown;
        }
    }

    public InnerRunnable1(String name) {
        inner = new Inner(name);
    }
}

// anonymous Runnable
class InnerRunnable2{
    private int countDown = 5;
    private Thread thread;

    public InnerRunnable2(String name) {
        thread = new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    System.out.println(this);
                    if (--countDown == 0) return;
                    try {
                        TimeUnit.MILLISECONDS.sleep(10);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                }
            }

            @Override
            public String toString() {
                return Thread.currentThread().getName()+" : "+countDown;
            }
        },name);
        thread.start();
    }
}

class ThreadMethod{
    private int countDown = 5;
    private Thread thread;
    private String name;

    public ThreadMethod(String name) {
        this.name = name;
    }

    public void runTask() {
        if (thread == null) {
            thread = new Thread(name){
                @Override
                public void run() {
                    while (true) {
                        System.out.println(this);
                        if (-- countDown ==0) return;
                        try {
                            sleep(10);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }

                @Override
                public String toString() {
                    return getName()+" :  "+countDown;
                }
            };
            thread.start();
        }
    }
}
public class ThreadVariations {
    public static void main(String[] args) {
        new InnerThread1("InnerThread1");
        new InnerThread2("InnerThread2");
        new InnerRunnable1("InnerRunnable1");
        new InnerRunnable2("InnerRunnable2");

        new ThreadMethod("ThreadMethod").runTask();
    }
}
