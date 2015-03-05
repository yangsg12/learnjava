package head_first;

/**
 * Created by Yang on 2015/2/12.
 */
public abstract class Duck {
    public Duck(){

    }
    FlyBehavior flyBehavior;
    QuackBehavior quackBehavior;
    public abstract void display();

    public void performFly() {
        flyBehavior.fly();
    }

    public void performQuack() {
        quackBehavior.quack();
    }

    public void swim() {
        System.out.printf("在游泳啊");
    }

    public void setFlyBehavior(FlyBehavior fb){
        flyBehavior = fb;
    }

    public void setQuackBehavior(QuackBehavior qb){
        quackBehavior = qb;
    }
}
