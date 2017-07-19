package concorrency_design.disruptor;


import com.lmax.disruptor.WorkHandler;

/**
 * Created by Yang on 2017/7/19.
 */
public class Consumer implements WorkHandler<PCData> {
    @Override
    public void onEvent(PCData event) throws Exception {
        System.out.println(Thread.currentThread().getId() + " : Event: --" + event.getValue() * event.getValue() + "--");


    }
}
