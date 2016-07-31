package concorrency_practise;

import java.util.Collections;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * Created by Yang on 2016/7/31.
 */
public class DelegatingVehicleTracker {
    private final ConcurrentMap<String ,Point> locations;
    private final Map<String ,Point> unmodifiableMap;

    public DelegatingVehicleTracker( Map<String, Point> points) {
        locations = new ConcurrentHashMap<String, Point>(points);
        unmodifiableMap = Collections.unmodifiableMap(locations);
    }

    public Map<String ,Point> getLocations(){
        return unmodifiableMap;
    }
    public Point getLocation(String id){
        return locations.get(id);
    }

    public void setLocations(String id, int x, int y) {
        if (locations.replace(id,new Point(x,y))==null){
            throw new IllegalArgumentException("invalid vehicle name : " + id);
        }
    }
}
