package concorrency_cookbook.synchronization.conditions.multi_conditions;

/**
 * Created by Yang on 2015/2/2.
 */
public class Main {
    public static void main(String[] args) {
        FileMock fileMock = new FileMock(100, 10);
        Buffer buffer = new Buffer(20);
        Producer producer = new Producer(fileMock, buffer);
        Thread threadProducer = new Thread(producer, " 生产者来啦++++");
        Consumer consumer[] = new Consumer[3];
        Thread threadConsumer[] = new Thread[3];
        for (int i =0;i<3;i++) {
            consumer[i] = new Consumer(buffer);
            threadConsumer[i] = new Thread(consumer[i],"消费者" +i+ "驾到-----");
        }

        threadProducer.start();
        for (int i =0 ;i<3;i++){
            threadConsumer[i].start();
        }
    }
}
