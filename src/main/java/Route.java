import java.util.ArrayList;
import java.util.Collections;

public class Route {
    private final ArrayList<City> cities = new ArrayList<>();
    private final double distance;

    public Route(City[] cities) {
        for (int i = 0; i < cities.length; i++){
            this.cities.add(cities[i]);
        }
        this.distance = this.calcTotalDistance();
    }

    public Route(Route route) {
        cities.addAll(route.cities);
        this.distance = route.getTotalDistance();
    }

    public ArrayList<City> getCities() {
        return cities;
    }

    private double calcTotalDistance() {
        double totalDistance = 0;

        for (int i = 0; i < cities.size(); i++) {
            if (i + 1 == cities.size()) {
                totalDistance += cities.get(i - 1).distanceBetweenTwoCities(cities.get(i));
            } else {
                totalDistance += cities.get(i).distanceBetweenTwoCities(cities.get(i + 1));
            }
        }

        totalDistance += cities.get(cities.size() - 1).distanceBetweenTwoCities(cities.get(0));
        return totalDistance;
    }

    public double getTotalDistance(){
        return distance;
    }

    public String printRoute() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("{ ");

        for (City city : cities) {
            stringBuilder.append(city.getName()).append(" ");
        }

        stringBuilder.append("}");
        return stringBuilder.toString();
    }
}