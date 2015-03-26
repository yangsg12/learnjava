package think_in_java.concurrency.bank_teller;

import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Random;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * Created by Yang on 2015/3/9.
 */


// read-only
class Customer{
    private final int serviceTime ;

    public Customer(int serviceTime) {
        this.serviceTime = serviceTime;
    }

    public int getServiceTime(){
        return serviceTime;
    }

    public String toString() {
        return " [" + serviceTime + "]";
    }
}

class CustomerLine extends ArrayBlockingQueue<Customer>{
    public CustomerLine(int maxLine) {
        super(maxLine);
    }

    public String toString(){
        if (this.size() == 0){
            return " [ empty ]";
        }
        StringBuilder result = new StringBuilder();
        for (Customer customer : this) {
            result.append(customer);
        }
        return  result.toString();
    }
}

class CustomerGenerator implements Runnable {
    private CustomerLine customers;
    private static Random random = new Random(47);

    public CustomerGenerator(CustomerLine cq){
        customers = cq;
    }


    @Override
    public void run() {
        while (!Thread.interrupted()){
            try {
                TimeUnit.MILLISECONDS.sleep(random.nextInt(300));
                customers.put(new Customer(random.nextInt(1000)));
            } catch (InterruptedException e) {
                e.printStackTrace();
                System.out.println(" customer generator interrupted ");
            }

            System.out.println(" customer generator terminated !!!");
        }
    }
}


class Teller implements Runnable,Comparable<Teller>{
    private static int counter = 0;
    private final int id = counter++;
    private int customersServed = 0;
    private CustomerLine customers;
    private boolean servingCustomerLine = true;

    public Teller(CustomerLine cq) {
        customers = cq;
    }

    @Override
    public void run() {
        try {
            while (!Thread.interrupted()){
                Customer customer = customers.take();
                TimeUnit.MILLISECONDS.sleep(customer.getServiceTime());

                synchronized (this){
                    customersServed++;
                    while (!servingCustomerLine) {
                        wait();
                    }
                }
            }
        } catch (InterruptedException e){
            e.printStackTrace();
            System.out.println(this + " interrupted !!!");
        }

        System.out.println(this+" terminating");

    }

    public synchronized void doSomethingElse(){
        customersServed = 0;
        servingCustomerLine =false;
    }

    public synchronized void serveCustomerLine() {
        assert !servingCustomerLine:"already serving " +this;
        servingCustomerLine = true;
        notifyAll();
    }

    public String toString(){
        return "Teller " + id +" ";
    }

    public String shortString() {
        return "T " +id;
    }

    @Override
    public synchronized int compareTo(Teller o) {
        /*return (customersServed < o.customersServed)?:-1:(customersServed == o.customersServed?:0:1);
        return 1<2?:0:-1;*/
        if (customersServed < o.customersServed){
            return -1;
        }else if (customersServed == o.customersServed){
            return 0;
        } else {
            return 1;
        }
    }
}

class TellerManager implements Runnable {
    private ExecutorService exec;
    private CustomerLine customers;
    private PriorityQueue<Teller> workingTellers = new PriorityQueue<Teller>();
    private Queue<Teller> tellersDoingOtherThings = new LinkedList<Teller>();
    private int adjustmentPeriod;
    private static Random random = new Random(47);

    public TellerManager(ExecutorService e, CustomerLine customers,  int adjustmentPeriod) {
        exec = e;
        this.customers = customers;
        this.adjustmentPeriod = adjustmentPeriod;
        // start with a single teller
        Teller teller = new Teller(customers);
        exec.execute(teller);
        workingTellers.add(teller);
    }

    public void adjustTellerNum(){
        if (customers.size() / workingTellers.size() >2){
            if (tellersDoingOtherThings.size()>0){
                Teller teller = tellersDoingOtherThings.remove();
                teller.serveCustomerLine();
                workingTellers.offer(teller);
                return;
            }
                // line is short enough , remove  a teller.
            if (workingTellers.size()>1 && customers.size() / workingTellers.size() <2) {
                resignOneTeller();
            }
        }
    }

    private void resignOneTeller(){
        Teller teller = workingTellers.poll();
        teller.doSomethingElse();
        tellersDoingOtherThings.offer(teller);
    }

    @Override
    public void run() {
        try{
            while (!Thread.interrupted()){
                TimeUnit.MILLISECONDS.sleep(adjustmentPeriod);
                adjustTellerNum();
                System.out.print(customers + "{");
                for (Teller teller : workingTellers) {
                    System.out.print(teller.toString() + " ");
                }
                System.out.println("}");

            }
        }catch (InterruptedException e){
            e.printStackTrace();
            System.out.println(this + " interrupted!");
        }
        System.out.println(this + " terminating");
    }

    public String toString() {
        return " Teller manager ";
    }
}

public class BankTellerSimulation {
    static final int MAX_LINE_SIZE = 50;
    static final int ADJUSTMENT_PERIOD = 1000;

    public static void main(String[] args) throws Exception{
        ExecutorService exec = Executors.newCachedThreadPool();
        // if line is too long, customer will leave.
        CustomerLine customers = new CustomerLine(MAX_LINE_SIZE);
        exec.execute(new CustomerGenerator(customers));
        // manage teller line
        exec.execute(new TellerManager(exec, customers, ADJUSTMENT_PERIOD));
        if (args.length > 0) {
            TimeUnit.SECONDS.sleep(new Integer(args[0]));
        }else {
            System.out.println("press enter to quit");
            System.in.read();
        }

        exec.shutdown();
    }
}
