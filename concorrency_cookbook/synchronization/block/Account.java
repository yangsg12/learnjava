package concorrency_cookbook.synchronization.block;

/**
 * Created by Yang on 2015/1/30.
 */
public class Account {
    private double balance;

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public synchronized void addAmount(double amount){
        double tmp = balance;
        try {
            Thread.sleep(30);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        tmp += amount;
        balance= tmp;
    }

    public synchronized void subAmount(double amount){
        double tmp = balance;
        try {
            Thread.sleep(300);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        tmp -= amount;
        balance= tmp;
    }
}
