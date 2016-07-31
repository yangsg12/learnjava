package concorrency_practise;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Yang on 2016/7/31.
 */
public class MonitorVehicleTracker {
    private final Map<String ,MutablePoint> locations;

    public MonitorVehicleTracker(Map<String, MutablePoint> locations) {
        this.locations = deepCopy(locations);
    }



    private static Map<String,MutablePoint> deepCopy(Map<String, MutablePoint> locations) {
        Map<String ,MutablePoint> result = new HashMap<String, MutablePoint>();
        for (String id : locations.keySet()) {
            result.put(id,new MutablePoint(locations.get(id)));
        }
        return Collections.unmodifiableMap(result);
    }

    public synchronized Map<String ,MutablePoint> getLocations(){
        return deepCopy(locations);
    }

    public synchronized MutablePoint getLocations(String id){
        MutablePoint loc = locations.get(id);
        return loc == null ? null : new MutablePoint(loc);

    }

    public synchronized void setLocations(String id, int x, int y){
        MutablePoint loc = locations.get(id);
        if (loc == null){
            throw new IllegalArgumentException("No such ID : " + id);
        }
//        loc.x = x;
//        loc.y = y;
    }

}
