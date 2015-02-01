package concorrency_cookbook.synchronization.method;

/**
 * Created by Yang on 2015/2/1.
 */
public class TicketsOffice2 implements Runnable {
    private Cinema cinema;
    public TicketsOffice2(Cinema cinema){
        this.cinema = cinema;
    }
    @Override
    public void run() {
        cinema.sellTicket2(2);
        cinema.sellTicket2(4);
        cinema.sellTicket1(2);
        cinema.sellTicket1(1);
        cinema.returnTickets2(3);
        cinema.sellTicket1(3);
        cinema.sellTicket2(2);
        cinema.sellTicket1(1);
    }
}
