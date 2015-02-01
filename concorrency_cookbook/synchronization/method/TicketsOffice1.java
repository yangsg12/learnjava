package concorrency_cookbook.synchronization.method;

/**
 * Created by Yang on 2015/2/1.
 */
public class TicketsOffice1 implements Runnable {
    private Cinema cinema;
    public TicketsOffice1(Cinema cinema){
        this.cinema = cinema;
    }
    @Override
    public void run() {
        cinema.sellTicket1(3);
        cinema.sellTicket1(2);
        cinema.sellTicket2(2);
        cinema.returnTickets1(3);
        cinema.sellTicket1(5);
        cinema.sellTicket2(2);
        cinema.sellTicket2(2);
        cinema.sellTicket2(2);
    }
}
