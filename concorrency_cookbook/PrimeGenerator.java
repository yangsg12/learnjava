package concorrency_cookbook;

/**
 * Created by Yang on 2015/1/29.
 */
public class PrimeGenerator extends Thread {
    @Override
    public void run() {
        long number = 1L;
        while (true){
            if (isPrime(number)){
                System.out.printf("Number %d is prime number ", number);
                System.out.println();
            }

            if (isInterrupted()){
                System.out.println(" Interrupted !!!");
                return;

            }
            number++;
        }
    }

    private boolean isPrime(long number){
        if (number<=2){
            return true;
        }
        for (long i=2;i<number;i++){
            if ((number%i) == 0){
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        Thread task = new PrimeGenerator();
        task.start();
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        task.interrupt();
    }
}
