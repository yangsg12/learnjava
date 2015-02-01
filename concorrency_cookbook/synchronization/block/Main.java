package concorrency_cookbook.synchronization.block;

/**
 * Created by Yang on 2015/1/30.
 */
public class Main {
    public static void main(String[] args) {
        Account account = new Account();
        account.setBalance(1000);

        Company company = new Company(account);
        Thread companyThread = new Thread(company);

        Bank bank = new Bank(account);
        Thread bankThread = new Thread(bank);

        System.out.printf("刚开始有 %f 钱\n",account.getBalance());
        companyThread.start();
        bankThread.start();


        try {
            companyThread.join();
            bankThread.join();
            System.out.printf("最终  有    %f 钱", account.getBalance());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}
