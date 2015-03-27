package think_in_java.enumerated;

/**
 * Created by Yang on 2015/3/27.
 */
public enum OzWitch{
    WEST("Miss Gulch, aka the wicked witch of the 西"),
    NORTH("Miss Gulch, aka the wicked witch of the 北"),
    EAST("Miss Gulch, aka the wicked witch of the 东"),
    SOUTH("Miss Gulch, aka the wicked witch of the 南");
    private String description;

    private OzWitch(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public static void main(String[] args) {
        for (OzWitch ozWitch : OzWitch.values()) {
            System.out.println(ozWitch +" : "+ozWitch.getDescription() );
        }
    }
}

