package concorrency_cookbook.c7.transfer_queue;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.PriorityBlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TransferQueue;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by Yang on 2016/3/2.
 */
public class MyPriorityTransferQueue<E> extends PriorityBlockingQueue<E> implements TransferQueue<E>{
    private AtomicInteger counter;
    private LinkedBlockingQueue<E> transferred;
    private ReentrantLock lock;
    public MyPriorityTransferQueue(){
        counter = new AtomicInteger(0);
        lock = new ReentrantLock();
        transferred = new LinkedBlockingQueue<E>();
    }

    @Override
    public void transfer(E e) throws InterruptedException {
        lock.lock();
        if (counter.get() !=0){
            put(e);
            lock.unlock();
        } else {
            transferred.add(e);
            lock.unlock();
            synchronized (e){
                e.wait();
            }
        }
    }

    @Override
    public boolean tryTransfer(E e, long timeout, TimeUnit unit) throws InterruptedException {
        lock.lock();
        if (counter.get() !=0){
            put(e);
            lock.unlock();
            return true;
        }else {
            transferred.add(e);
            long newTimeout = TimeUnit.MILLISECONDS.convert(timeout,unit);
            lock.unlock();
            e.wait(newTimeout);


            lock.lock();
            if (transferred.contains(e)){
                transferred.remove(e);
                lock.unlock();
                return false;
            } else {
                lock.unlock();
                return true;
            }
        }
    }

    @Override
    public boolean tryTransfer(E e) {
        return false;
    }

    @Override
    public boolean hasWaitingConsumer() {
        return (counter.get()!=0);
    }

    @Override
    public int getWaitingConsumerCount() {
        return counter.get();
    }

    @Override
    public E take() throws InterruptedException {
        lock.lock();
        counter.incrementAndGet();
        E value = transferred.poll();
        if (value == null){
            lock.unlock();
            value = super.take();
            lock.lock();
        } else {
            synchronized (value){
                value.notify();
            }
        }
        counter.decrementAndGet();
        lock.unlock();
        return value;
    }
}

// class event

class Event implements Comparable<Event>{
    private String thread;
    private int priority;

    public Event(String thread, int priority) {
        this.thread = thread;
        this.priority = priority;
    }

    public String getThread() {
        return thread;
    }

    public int getPriority() {
        return priority;
    }

    @Override
    public int compareTo(Event o) {
        if (this.priority > o.priority){
            return -1;
        } else if(this.priority < o.priority){
            return 1;

        }else {
            return 0;
        }
    }
}


// producer

class Producer implements Runnable{
    private MyPriorityTransferQueue<Event> buffer;

    public Producer(MyPriorityTransferQueue<Event> buffer) {
        this.buffer = buffer;
    }

    @Override
    public void run() {
        for (int i = 0; i < 100; i++) {
            Event event = new Event(Thread.currentThread().getName(),i);
            buffer.put(event);
        }
    }
}


// Consumer

class Consumer implements Runnable{
    private MyPriorityTransferQueue<Event> buffer;

    public Consumer(MyPriorityTransferQueue<Event> buffer) {
        this.buffer = buffer;
    }

    @Override
    public void run() {
        for (int i = 0; i < 1002; i++) {
            try {
                Event value = buffer.take();
                System.out.printf("Consumer : %s  --- %d\n",value.getThread(),value.getPriority());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}


class Main {
    public static void main(String[] args) throws Exception{
        MyPriorityTransferQueue<Event> buffer = new MyPriorityTransferQueue<Event>();
        Producer producer = new Producer(buffer);
        Thread[] producerThreads = new Thread[10];
        for (int i = 0; i < producerThreads.length; i++) {
            producerThreads[i] = new Thread(producer);
            producerThreads[i].start();
        }

        Consumer consumer = new Consumer(buffer);
        Thread consumerThread = new Thread(consumer);
        consumerThread.start();

        System.out.printf("Main: Buffer -- Consumer counter : %d \n", buffer.getWaitingConsumerCount());

        Event myEvent = new Event("core event ", 0);

        buffer.transfer(myEvent);
        System.out.printf("Main : My event has been transfered \n");

        for (int i = 0; i < producerThreads.length; i++) {
            producerThreads[i].join();
        }

        TimeUnit.SECONDS.sleep(1);
        System.out.printf("Main : Buffer consumer counter %d",buffer.getWaitingConsumerCount());

        myEvent = new Event("event core 2",0);
        buffer.transfer(myEvent);

        consumerThread.join();
        System.out.printf("这是啥意思 \n");

    }
}