package concorrency_cookbook.synchronization.block;

import concorrency_cookbook.synchronization.block.Account;

/**
 * Created by Yang on 2015/1/30.
 */
public class Bank implements Runnable {
    private Account account;
    public Bank(Account account){
        this.account = account;
    }

    @Override
    public void run() {
        for (int i=0;i<100;i++){
            System.out.printf(" ------------银行扣了 %d 钱了！！！\n",i);
            account.subAmount(1000);
        }
    }
}
