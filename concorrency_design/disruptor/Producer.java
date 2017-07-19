package concorrency_design.disruptor;

import com.lmax.disruptor.RingBuffer;

import java.nio.ByteBuffer;

/**
 * Created by Yang on 2017/7/19.
 */
public class Producer {
    private final RingBuffer<PCData> ringBuffer;

    public Producer(RingBuffer<PCData> ringBuffer) {
        this.ringBuffer = ringBuffer;
    }

    public void pushData(ByteBuffer byteBuffer) {
        long sequnce = ringBuffer.next();
        try {
            PCData event = ringBuffer.get(sequnce);
            event.setValue(byteBuffer.getLong());
        } finally {
            ringBuffer.publish(sequnce);
        }
    }
}
