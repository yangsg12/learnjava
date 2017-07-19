package concorrency_design.disruptor;

import com.lmax.disruptor.EventFactory;

/**
 * Created by Yang on 2017/7/19.
 */
public class PCDataFacotry implements EventFactory {
    @Override
    public Object newInstance() {
        return new PCData();
    }
}
