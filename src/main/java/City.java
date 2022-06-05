public class City {
    private final double latitude;
    private final double longitude;
    private final String name;

    public City(double latitude, double longitude, String name) {
        this.latitude = latitude;
        this.longitude = longitude;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public double degree2rad(double degree) {
        return degree * (Math.PI / 180D);
    }

    public double distanceBetweenTwoCities(City city) {
        double distance = Math.sqrt(Math.pow(city.latitude - this.latitude, 2) + Math.pow(city.longitude - this.latitude, 2));
        return distance;
    }

    public String toString() {
        return "name: " + name + ", latitude: " + latitude + ", longitude: " + longitude;
    }
}