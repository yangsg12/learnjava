package think_in_java.concurrency;

import java.util.*;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * Created by Yang on 2015/3/26.
 */
public class GreenhouseScheduler {
    private volatile boolean light = false;
    private volatile boolean water = false;
    private String thermostat = "Day";

    public synchronized String getThermostat() {
        return thermostat;
    }

    public synchronized void setThermostat(String value) {
        thermostat = value;
    }

    ScheduledThreadPoolExecutor scheduler = new ScheduledThreadPoolExecutor(10);

    public void schedule(Runnable event, long delay) {
        scheduler.schedule(event,delay, TimeUnit.MILLISECONDS);
    }

    public void repeat(Runnable event, long initialDelay, long period) {
        scheduler.scheduleAtFixedRate(event, initialDelay, period, TimeUnit.MILLISECONDS);

    }

    class LightOn implements Runnable{
        @Override
        public void run() {
            System.out.println("打开灯泡了 +++");
            light = true;
        }
    }

    class LightOff implements Runnable {
        @Override
        public void run() {
            System.out.println("灯泡 关了 ---");
            water = true;
        }
    }

    class WaterOn implements Runnable {
        @Override
        public void run() {
            System.out.println("水 来了 +++");
            water = true;
        }
    }

    class WaterOff implements Runnable {
        @Override
        public void run() {
            System.out.println("水 关了 ---");
            water = false;
        }
    }

    class ThermostatNight implements Runnable {
        @Override
        public void run() {
            System.out.println("温度 设置 到 晚上");
            setThermostat("大晚上");
        }
    }

    class ThermostatDay implements Runnable {
        @Override
        public void run() {
            System.out.println(" 温度 设置 到 白天");
            setThermostat("白天了 许多事情干不了啊");
        }
    }

    class Bell implements Runnable {
        @Override
        public void run() {
            System.out.println(" 打铃了!!!--------------");
            System.out.println();
        }
    }

    class Terminate implements Runnable {
        @Override
        public void run() {
            System.out.println(" 结束 ...");
            scheduler.shutdownNow();
            // scheduler has been shut down , start a separate task to do
            new Thread() {
                @Override
                public void run() {
                    for (DataPoint dataPoint : data) {
                        System.out.println(dataPoint);
                    }
                }
            }.start();
        }
    }

        class DataPoint {
            final Calendar time;
            final float temperature;
            final float humidity;

            public DataPoint(Calendar time, float temperature, float humidity) {
                this.time = time;
                this.temperature = temperature;
                this.humidity = humidity;
            }

            @Override
            public String toString() {
                return time.getTime() + String.format(" 温度: %1$.1f 湿度: %2$.2f", temperature, humidity);
            }
        }

        private Calendar lastTime = Calendar.getInstance();

        {
            // adjust date to the half hour
            lastTime.set(Calendar.MINUTE, 30);
            lastTime.set(Calendar.SECOND, 00);
        }

        private float lastTemp = 65.0f;
        private int tempDirection = +1;
        private float lastHumidity = 50.0f;
        private int humidityDirection = +1;
        private Random random = new Random(47);
        List<DataPoint> data = Collections.synchronizedList(new ArrayList<DataPoint>());

        class CollectData implements Runnable {
            @Override
            public void run() {
                System.out.println(" 收集 数据");
                synchronized (GreenhouseScheduler.this){
                   lastTime.set(Calendar.MINUTE, lastTime.get(Calendar.MINUTE+30));
                    if (random.nextInt(5) == 4) {
                        tempDirection = -tempDirection;
                    }
                    lastTemp = lastTemp + tempDirection * (1.0f + random.nextFloat());

                    if (random.nextInt(5) == 4) {
                        humidityDirection = -humidityDirection;
                    }
                    lastHumidity = lastHumidity + humidityDirection * random.nextFloat();

                    data.add(new DataPoint((Calendar) lastTime.clone(), lastTemp, lastHumidity));
                }
            }
        }


    public static void main(String[] args) {
        GreenhouseScheduler gh = new GreenhouseScheduler();
        gh.schedule(gh.new Terminate(),5000);

        gh.repeat(gh.new Bell(), 0, 1000);
        gh.repeat(gh.new ThermostatNight(), 0, 200);

        gh.repeat(gh.new LightOn(), 0, 200);
        gh.repeat(gh.new LightOff(), 0, 400);
        gh.repeat(gh.new WaterOn(), 0 , 600);
        gh.repeat(gh.new WaterOff(), 0 , 800);

        gh.repeat(gh.new ThermostatDay(), 0, 1400);
        gh.repeat(gh.new CollectData(), 500, 500);

    }


}



