package concorrency_design.disruptor;

import com.lmax.disruptor.dsl.Disruptor;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;

/**
 * Created by Yang on 2017/7/19.
 */
public class TestMain {
    public static void main(String[] args) {
        Executor executor = Executors.newCachedThreadPool();
        PCDataFacotry facotry = new PCDataFacotry();
        int bufferSize = 1024;
        ThreadFactory threadFactory = new ThreadFactory() {
            @Override
            public Thread newThread(Runnable r) {
                return null;
            }
        };
        Disruptor<PCData> disruptor = new Disruptor<PCData>(facotry,bufferSize,threadFactory);
    }
}
