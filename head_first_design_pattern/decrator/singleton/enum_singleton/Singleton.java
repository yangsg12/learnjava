package head_first_design_pattern.decrator.singleton.enum_singleton;

/**
 * Created by Yang on 2016/4/1.
 */
public enum  Singleton {
    INSTANCE;
    private String name;
    public String getName(){
        return name;
    }

    public void setName(String name){
        this.name = name;
    }

}
