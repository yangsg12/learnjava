package think_in_java.enumerated;

/**
 * Created by Yang on 2015/3/27.
 */
public enum SpaceShip {
    SCOUT, CARGO,TRANSPORT,CRUISER, BATTLESHIP,MOTHERSHIP;

    @Override
    public String toString() {
        String id = name();
        String lower = id.substring(1).toLowerCase();
        return id.charAt(0) +lower;
    }

    public static void main(String[] args) {
        for (SpaceShip spaceShip : values()) {
            System.out.println(spaceShip);
        }
    }
}
