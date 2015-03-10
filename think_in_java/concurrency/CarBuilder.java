package think_in_java.concurrency;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.*;

/**
 * Created by Yang on 2015/3/10.
 * 创建 底盘 , 安装 发动机, 车厢, 轮子
 */

class Car{
    private final int id;
    private boolean engine = false, driveTrain = false,wheels = false;

    public Car(int id) {
        this.id = id;
    }

    public Car() {
        id = -1;
    }

    public synchronized int getId() {
        return id;
    }

    public synchronized void addEngine() {
        engine = true;
    }

    public synchronized void addDriveTrain() {
        driveTrain = true;
    }

    public synchronized void addWheels() {
        wheels = true;
    }

    public synchronized String toString() {
        return "Car " + id + "[ " + " engine: " + engine
                + " driveTrain: " + driveTrain
                + "wheels: " + wheels + " ]";
    }
}

class CarQueue extends LinkedBlockingQueue<Car>{}

class ChassisBuilder implements Runnable{
    private CarQueue carQueue;
    private int counter = 0;

    public ChassisBuilder(CarQueue carQueue) {
        this.carQueue = carQueue;
    }

    @Override
    public void run() {
        try {
            while (!Thread.interrupted()) {
                TimeUnit.MILLISECONDS.sleep(500);
                Car car = new Car(counter++);
                System.out.println(" Chassis builder 建造了 " + car);
                carQueue.put(car);
            }
        } catch (InterruptedException e){
            System.out.println("Interrupted: ChassisBuilder");
        }
        System.out.println("Chassis builder OFF");
    }
}

class Assembler implements Runnable{
    private CarQueue chassisQueue, finishingQueue;
    private Car car;
    private CyclicBarrier barrier = new CyclicBarrier(4);
    private RobotPool robotPool;

    public Assembler(CarQueue chassisQueue, CarQueue finishingQueue, RobotPool robotPool) {
        this.chassisQueue = chassisQueue;
        this.finishingQueue = finishingQueue;
        this.robotPool = robotPool;
    }

    public Car car() {
        return car;
    }

    public CyclicBarrier barrier() {
        return barrier;
    }

    @Override
    public void run() {
        try {
            while (!Thread.interrupted()) {
                car = chassisQueue.take();
                // hire robots to perform work.
                robotPool.hire(EngineRobot.class, this);
                robotPool.hire(DriveTrainRobot.class, this);
                robotPool.hire(WheelRobot.class, this);
                barrier.await();
                finishingQueue.put(car);
            }
        } catch (InterruptedException e) {
            System.out.println("interrupted: Assembler");
        } catch (BrokenBarrierException e) {
            e.printStackTrace();
        }
        System.out.println(" 组装 完成啦");
    }
}

class Reporter implements Runnable{
    private CarQueue carQueue;

    public Reporter(CarQueue carQueue) {
        this.carQueue = carQueue;
    }

    @Override
    public void run() {
        try {
            while (!Thread.interrupted()) {
                System.out.println(carQueue.take());
            }
        } catch (InterruptedException e) {
            System.out.println(" interrupted : Reporter");
        }
        System.out.println(" Reporter 好了----------");
    }
}

abstract class Robot implements Runnable {
    private RobotPool pool;

    public Robot(RobotPool pool) {
        this.pool = pool;
    }

    protected Assembler assembler;

    public Robot assignAssembler(Assembler assembler) {
        this.assembler = assembler;
        return this;
    }

    private boolean engage = false;

    public synchronized void engage() {
        engage = true;
        notifyAll();
    }

    abstract protected void performService();

    @Override
    public void run() {
        try {
            powerDown();
            while (!Thread.interrupted()) {
                performService();
                assembler.barrier().await();
                powerDown();
            }
        } catch (InterruptedException e) {
            System.out.println("exiting " + this + " via interrupt");
        } catch (BrokenBarrierException e) {
            e.printStackTrace();
        }
        System.out.println(this+ " OFF");
    }

    private synchronized void powerDown() throws InterruptedException{
        engage = false;
        assembler = null;
        pool.release(this);
        while (engage == false)
            wait();
    }

    public String toString() {
        return getClass().getName();
    }
}

class EngineRobot extends Robot {
    public EngineRobot(RobotPool pool) {
        super(pool);
    }

    protected void performService() {
        System.out.println(this + " installing engine");
        assembler.car().addEngine();
    }
}

class DriveTrainRobot extends Robot {
    public DriveTrainRobot(RobotPool pool) {
        super(pool);
    }

    protected void performService() {
        System.out.println(this + " installing driveTrain");
        assembler.car().addDriveTrain();
    }
}

class WheelRobot extends Robot {
    public WheelRobot(RobotPool pool) {
        super(pool);
    }

    protected void performService() {
        System.out.println(this + " installing wheels");
        assembler.car().addWheels();
    }
}

class RobotPool{
    private Set<Robot> pool = new HashSet<Robot>();

    public synchronized void add(Robot robot) {
        pool.add(robot);
        notifyAll();
    }

    public synchronized void hire(Class<? extends Robot> robotType, Assembler d) throws InterruptedException {
        for (Robot robot : pool) {
            if (robot.getClass().equals(robotType)) {
                pool.remove(robot);
                robot.assignAssembler(d);
                robot.engage();
                return;
            }
        }
        wait();
        hire(robotType, d); // try again, recursively
    }

    public synchronized void release(Robot robot) {
        add(robot);
    }
}
public class CarBuilder {
    public static void main(String[] args) throws Exception{
        CarQueue chassisQueue = new CarQueue(),
                finishingQueue = new CarQueue();
        ExecutorService exec = Executors.newCachedThreadPool();
        RobotPool robotPool = new RobotPool();
        exec.execute(new EngineRobot(robotPool));
        exec.execute(new DriveTrainRobot(robotPool));
        exec.execute(new WheelRobot(robotPool));
        exec.execute(new Assembler(chassisQueue, finishingQueue, robotPool));
        exec.execute(new Reporter(finishingQueue));
        // start producing chassis
        exec.execute(new ChassisBuilder(chassisQueue));
        TimeUnit.SECONDS.sleep(10);
        exec.shutdownNow();
    }
}
