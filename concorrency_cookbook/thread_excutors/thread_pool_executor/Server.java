package concorrency_cookbook.thread_excutors.thread_pool_executor;

import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * Created by Yang on 2015/2/12.
 */
public class Server {
    private ThreadPoolExecutor executor;
    public Server(){
//        executor = (ThreadPoolExecutor) Executors.newCachedThreadPool();
        executor = (ThreadPoolExecutor) Executors.newFixedThreadPool(5);
    }

    public void executorTask(Task task){
        System.out.println("----------------------");
        System.out.printf("Server : a new task arrived.\n");
        executor.execute(task);
        System.out.printf("Server : PoolSize %d \n", executor.getPoolSize());
        System.out.printf("Server : Active Count %d \n", executor.getActiveCount());
        System.out.printf("Server : complete tasks %d \n",executor.getCompletedTaskCount());
        System.out.printf("Server : task count %d \n",executor.getTaskCount());
    }

    public void endServer() {
        executor.shutdown();
    }
}
