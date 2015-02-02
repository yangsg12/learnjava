package concorrency_cookbook.synchronization.conditions.multi_conditions;

/**
 * Created by Yang on 2015/2/2.
 */
public class Producer implements Runnable {
    private FileMock fileMock;
    private Buffer buffer;

    public Producer(FileMock fileMock, Buffer buffer) {
        this.fileMock = fileMock;
        this.buffer = buffer;
    }

    @Override
    public void run() {
        buffer.setPendingLines(true);
        while (fileMock.hasMoreLines()){
            String line = fileMock.getLine();
            buffer.insert(line);
        }
        buffer.setPendingLines(false);
    }
}
