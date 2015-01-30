package concorrency_cookbook.thread_factory;


/**
 * Created by Yang on 2015/1/30.
 */
public class Main {
    public static void main(String[] args) {
        MyThreadFactory myThreadFactory = new MyThreadFactory("线程工厂");
        Task task = new Task();

        Thread thread ;
        System.out.printf("Starting the threads \n");
        for (int i=0 ;i<10;i++){
            thread = myThreadFactory.newThread(task);
            thread.start();
        }

        System.out.printf("Factory stats: \n");
        System.out.printf("%s \n",myThreadFactory.getStats());

    }
}
