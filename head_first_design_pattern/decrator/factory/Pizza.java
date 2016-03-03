package head_first_design_pattern.decrator.factory;

/**
 * Created by Yang on 2016/1/25.
 */
public abstract class Pizza {
    String name;
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }





    public void prepare(){
        System.out.println("prepare");
    }
    public void bake(){
        System.out.println("baked");
    }

    @Override
    public String toString() {
        return "Pizza{" +
                "name='" + getName() + '\'' +
                '}';
    }
}



class ChicagoStylePizza extends Pizza{
    public ChicagoStylePizza() {
        name = "Chicago Style Pizza";
    }
    public void bake(){
        System.out.println("Chicago style bake");
    }
}