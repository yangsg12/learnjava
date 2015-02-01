package concorrency_cookbook.synchronization.method;

/**
 * Created by Yang on 2015/2/1.
 */
public class Cinema {
    private long cinema1;
    private long cinema2;
    private final Object controlCinema1, controlCinema2;
    public Cinema(){
        controlCinema1 = new Object();
        controlCinema2 = new Object();
        cinema1 = 20L;
        cinema2 = 20;
    }

    public boolean sellTicket1(int number){
        synchronized (controlCinema1){
            if (number < cinema1){
                cinema1 -= number;
                return true;
            }else {
                return false;
            }
        }
    }

    public boolean sellTicket2(int number){
        synchronized (controlCinema2){
            if (number < cinema2){
                cinema2 -= number;
                return true;
            }else {
                return false;
            }
        }
    }

    public boolean returnTickets1(int number){
        synchronized (controlCinema1){
            cinema1 += number;
            return true;
        }
    }

    public boolean returnTickets2(int number){
        synchronized (controlCinema2){
            cinema2 += number;
            return true;
        }
    }

    public long getCinema1(){
        return cinema1;
    }

    public long getCinema2(){
        return cinema2;
    }


}
