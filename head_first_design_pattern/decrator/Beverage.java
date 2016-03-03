package head_first_design_pattern.decrator;

/**
 * Created by Yang on 2016/1/25.
 */
public abstract class Beverage {
    String description = "Unknown Beverage";
    double milkCost,soyCost,mochaCost;
    String milk,soy,mocha;
    public String getDescription(){
        return description;
    }

    int size;
    public int getSize(){
        return size;
    }


    public abstract double cost();
}


// condiment 调料
 abstract class CondimentDecorator extends Beverage{
    public abstract String getDescription();
}

// 浓缩咖啡
class Espresso extends Beverage{
    public Espresso() {
        description = "Espresso";
    }
    public double cost(){
        return 1.99;
    }
}

class HouseBlend extends Beverage{
    public HouseBlend() {
        description = "House Blend Coffee";
    }
    public double cost(){
        return .89;
    }
}


class Mocha extends CondimentDecorator{
    Beverage beverage;

    public Mocha(Beverage beverage) {
        this.beverage = beverage;
    }
    public String getDescription(){
        return beverage.getDescription()+", Mocha";
    }
    public double cost(){
        return .20 + beverage.cost();
    }
}


class Soy extends CondimentDecorator{
    Beverage beverage;

    public Soy(Beverage beverage) {
        this.beverage = beverage;
    }
    public int getSize(){
        return beverage.getSize();
    }
    public String getDescription() {
        return beverage.getDescription() + " Soy";
    }
    public double cost(){
        double cost = beverage.cost();
        return cost;
    }
}