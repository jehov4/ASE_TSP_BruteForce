import java.util.ArrayList;

public class Location {
    private final ArrayList<Double> locations;

    public Location(ArrayList<Double> locations) {
        this.locations = locations;
    }

    public ArrayList<Double> getLocations() {
        return locations;
    }
}