import java.util.ArrayList;
import java.util.Arrays;

public class TSP {
    private final ArrayList<City> cities;
    private double currentBest = Double.MAX_VALUE;
    private Route currentBestRoute;

    private int iteration = 0;

    public TSP(ArrayList<City> cities) {
        this.cities = cities;
    }

    private void analyzePermutation(Route permutation){
        if (iteration % 10000 == 0){
            System.out.printf("\b\b\b\b\b\b\b\b\b\b%10d", iteration);
        }
        if (permutation.getTotalDistance() < currentBest){
            currentBest = permutation.getTotalDistance();
            currentBestRoute = permutation;
            System.out.printf("\nFound new Best Value: %f\n → New Best Route: %s\n → Iteration: %d\nIteration:           ", currentBest, permutation.printRoute(), iteration);
        }
        iteration++;
    }

    public static <T> void swap(T[] array, int first, int second) {
        T temp = array[first];
        array[first] = array[second];
        array[second] = temp;
    }

    private void allPermutationsHelper(City[] permutation, int n) {
        if (n <= 0) {
            analyzePermutation(new Route(permutation));
            return;
        }
        City[] tempPermutation = Arrays.copyOf(permutation, permutation.length);
        for (int i = 0; i < n; i++) {
            swap(tempPermutation, i, n - 1);
            allPermutationsHelper(tempPermutation, n - 1);
            swap(tempPermutation, i, n - 1);
        }
    }

    private void permutations(City[] original) {
        ArrayList<Route> permutations = new ArrayList<>();
        allPermutationsHelper(original, original.length);
    }

    public void findShortestPath() {
        City[] cities = new City[this.cities.size()];
        for (int i = 0; i < cities.length; i++) {
            cities[i] = this.cities.get(i);
        }
        permutations(cities);
    }

    public static void main(String[] args) throws Exception {
        TspFileParser parser = new TspFileParser();
        ArrayList<City> cities = parser.parse("data/a280.tsp");
        TSP tsp = new TSP(cities);
        System.out.print("Iteration:           ");
        tsp.findShortestPath();
        double distance = tsp.currentBest;
        System.out.println("The shortest path is " + tsp.currentBestRoute.printRoute() + " in " +
                distance + " miles.");
    }
}