package think_in_java.concurrency;

/**
 * Created by Yang on 2015/3/11.
 */
public class EvenGeneratorSyn extends IntGenerator {
    private int currentEvenValue = 0;

    @Override
    public synchronized int next() {
        ++currentEvenValue;
        Thread.yield();
        ++currentEvenValue;
        return currentEvenValue;
    }

    public static void main(String[] args) {
        EvenChecker.test(new EvenGeneratorSyn());
    }
}
