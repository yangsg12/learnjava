package concorrency_cookbook.thread_excutors;

import javax.swing.*;
import java.awt.*;
import java.text.DateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Random;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

/**
 * Created by Yang on 2015/3/2.
 * ScheduledExecutorService
 */
public class VirusScanner {
    private static JFrame appFrame;
    private static JLabel statusString;
    private int scanNumber = 0;
    private static final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(5);
    private GregorianCalendar calendar = new GregorianCalendar();
    private static VirusScanner app = new VirusScanner();

    public void scanDisk(){
        final Runnable scanner = new Runnable() {
            @Override
            public void run() {
                try {
                    appFrame.setVisible(true);
                    scanNumber++;
                    Calendar cal = Calendar.getInstance();
                    DateFormat df = DateFormat.getDateTimeInstance(DateFormat.FULL, DateFormat.MEDIUM);
                    statusString.setText("Scan "+scanNumber+" started at "+df.format(cal.getTime()));
                    Thread.sleep(1000+new Random().nextInt(10000));
                    appFrame.setVisible(false);
                }catch (InterruptedException e){
                    e.printStackTrace();
                }
            }
        };

        final ScheduledFuture<?> scanManager = scheduler.scheduleAtFixedRate(scanner, 1, 5, TimeUnit.SECONDS);
        scheduler.schedule(new Runnable() {
            @Override
            public void run() {
                scanManager.cancel(true);
                scheduler.shutdown();
                appFrame.dispose();
            }
        },60,TimeUnit.SECONDS);

    }

    public static void main(String[] args) {
        appFrame = new JFrame();
        Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
        appFrame.setSize(400, 70);
        appFrame.setLocation(dimension.width/2 - appFrame.getWidth()/2,
                dimension.height/2 -appFrame.getHeight()/2);
        statusString = new JLabel();
        appFrame.add(statusString);
        appFrame.setVisible(false);
        app.scanDisk();
    }
}
