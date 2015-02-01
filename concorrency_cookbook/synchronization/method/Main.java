package concorrency_cookbook.synchronization.method;

/**
 * Created by Yang on 2015/2/1.
 */
public class Main {
    public static void main(String[] args) {
        Cinema cinema = new Cinema();
        TicketsOffice1 ticketsOffice1 = new TicketsOffice1(cinema);
        Thread thread1 = new Thread(ticketsOffice1,"TicketsOffice1");
        TicketsOffice2 ticketsOffice2 = new TicketsOffice2(cinema);
        Thread thread2 = new Thread(ticketsOffice2,"TicketsOffice2");
        thread1.start();
        thread2.start();


        try {
            thread1.join();
            thread2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.printf("Room 1 cinema1 %d \n", cinema.getCinema1());
        System.out.printf("Room 2 cinema1 %d \n", cinema.getCinema2());
    }
}
