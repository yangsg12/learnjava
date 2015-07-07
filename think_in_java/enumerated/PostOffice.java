package think_in_java.enumerated;

import think_in_java.net.mindview.util.Enums;

import java.util.Iterator;

/**
 * Created by Yang on 2015/3/30.
 */

class Mail{
    enum GeneralDelivery{YES,NO1,NO2,NO3,NO4,NO5}
    enum Scannability{UNSCANNABLE,YES1,YES2,YES3,YES4,YES5}
    enum Readability{ILLEGIBLE,YES1,YES2,YES3,YES4,YES5}
    enum Address{INCORRECT,OK1,OK2,OK3,OK4,OK5}
    enum ReturnAddress{ILLEGIBLE,OK1,OK2,OK3,OK4,OK5}

    GeneralDelivery generalDelivery;
    Scannability scannability;
    Readability readability;
    Address address;
    ReturnAddress returnAddress;

    static long counter =0;
    long id = counter++;

    @Override
    public String toString() {
        return "Mail " +id;
    }

    public String detail(){
        return toString()+", General Delivery: "+generalDelivery+
                ", address scanability: "+ scannability+
                ", address readablility: "+ readability+
                ", address "+address+
                ", return address "+ returnAddress;
    }

    public static Mail randomMail(){
        Mail m = new Mail();
        m.generalDelivery = Enums.random(GeneralDelivery.class);
        m.scannability = Enums.random(Scannability.class);
        m.address = Enums.random(Address.class);
        m.returnAddress = Enums.random(ReturnAddress.class);
        return m;
    }

    public static Iterable<Mail> generator(final int count) {

        return new Iterable<Mail>() {
            int n = count;

            @Override
            public Iterator<Mail> iterator() {
                return new Iterator<Mail>(){
                    public boolean hasNext(){
                        return n-- >0;
                    }

                    public Mail next() {
                        return randomMail();
                    }

                    public void remove() {
                        throw new UnsupportedOperationException();
                    }
                };
            }
        };
    }
}


public class PostOffice {
    enum MailHandler{
        GENERAL_DELIVERY{
            boolean handle(Mail mail) {
                switch (mail.generalDelivery) {
                    case YES:
                        System.out.println("using general delivery for "+mail);
                     return true;

                    default:return false;
                }
            }
        },

        MACHINE_SCAN{
            boolean handle(Mail mail) {
                switch (mail.scannability) {
                    case UNSCANNABLE:return false;
                    default:
                        switch (mail.address) {
                            case INCORRECT:return false;
                            default:
                                System.out.println("delivering " + mail + " automatically");
                                return true;
                        }
                }
            }
        };

        abstract boolean handle(Mail mail);
    }

    static void handle(Mail mail) {
        for (MailHandler handler : MailHandler.values()) {
            if (handler.handle(mail)) {
                return;
            }

        }
        System.out.println(mail + " is a dead letter ");
    }

    public static void main(String[] args) {
        for (Mail mail : Mail.generator(10)) {
            System.out.println(mail.detail());
            handle(mail);
            System.out.println("==================");
        }
    }
}
