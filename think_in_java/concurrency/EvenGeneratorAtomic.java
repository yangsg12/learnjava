package think_in_java.concurrency;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by Yang on 2015/3/11.
 */
public class EvenGeneratorAtomic extends IntGenerator {
    private AtomicInteger currentEvenValue = new AtomicInteger(0);

    @Override
    public int next() {
        return currentEvenValue.addAndGet(2);
    }

    public static void main(String[] args) {
        EvenChecker.test(new EvenGeneratorAtomic());
    }
}
