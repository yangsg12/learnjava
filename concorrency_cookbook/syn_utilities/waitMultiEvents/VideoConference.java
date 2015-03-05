package concorrency_cookbook.syn_utilities.waitMultiEvents;

import java.util.concurrent.CountDownLatch;

/**
 * Created by Yang on 2015/2/3.
 *
 * 视频会议 等 参加者 齐了再行动
 */
public class VideoConference implements Runnable{
    private final CountDownLatch controller;
    public VideoConference(int number) {
        controller = new CountDownLatch(number);

    }

    public void arrive(String name) {
        System.out.printf("%s 大爷来了 \n", name);
        controller.countDown();
    }

    public void getCount(){
        System.out.printf("还要等 %d 位大爷 \n", controller.getCount());
    }

    @Override
    public void run() {
        System.out.printf("视频会议开始 有 %d 位 大爷要来。\n", controller.getCount());
        try {
            controller.await();
            System.out.printf("大爷们都到了！！！\n");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
