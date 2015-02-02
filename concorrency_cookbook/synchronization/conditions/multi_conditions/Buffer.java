package concorrency_cookbook.synchronization.conditions.multi_conditions;

import java.util.Date;
import java.util.LinkedList;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by Yang on 2015/2/2.
 */
public class Buffer {
    private LinkedList<String> buffer;
    private int maxSize;
    private ReentrantLock lock;
    private Condition lines;
    private Condition spaces;
    private boolean pendingLines;

    public Buffer(int maxSize) {
        this.maxSize = maxSize;
        buffer = new LinkedList<String>();
        lock = new ReentrantLock();
        lines = lock.newCondition();  // key
        spaces = lock.newCondition();
        pendingLines = true;
    }

    public void insert(String line) {

        lock.lock();

        while (buffer.size() == maxSize) {
            try {
                spaces.await();
                buffer.offer(line);
                System.out.printf(" %s : insert line: %d \n",
                        Thread.currentThread().getName(), buffer.size());
                lines.signalAll();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        }
    }

    public String get(){
        String line= null;
        lock.lock();
        try {
            while ((buffer.size() == 0) && hasPendingLines()) {
                lines.await();
            }

            if (hasPendingLines()){
                line = buffer.poll();
                System.out.printf("%s 读取了 %d 行",Thread.currentThread().getName(),buffer.size());
                spaces.signalAll();
            }

        }catch (InterruptedException e){
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
        return line;
    }

    public void setPendingLines(boolean pendingLines){
        this.pendingLines = pendingLines;
    }

    public boolean hasPendingLines() {
        return pendingLines||buffer.size()>0;
    }

}
