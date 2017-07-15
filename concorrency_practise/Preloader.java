package concorrency_practise;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * Created by Yang on 2016/8/4.
 */
public class Preloader {
    private final FutureTask<ProductInfo> future = new FutureTask<ProductInfo>(new Callable<ProductInfo>() {
        @Override
        public ProductInfo call() throws Exception {
            ProductInfo loadProductInfo = new ProductInfo();
            return loadProductInfo;
        }
    });
    private final Thread thread = new Thread(future);
    public void start(){
        thread.start();
    }
//    public ProductInfo get() throws InterruptedException{
//        try {
//            return future.get();
//
//        } catch (ExecutionException e) {
//            e.printStackTrace();
//        }
//
//    }
}
