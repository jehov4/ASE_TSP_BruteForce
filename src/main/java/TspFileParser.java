import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Pattern;

public class TspFileParser implements IFileParser {

    private static final Pattern pattern = Pattern.compile("^\\s*(?<name>\\d{1,3})\\s+(?<latitude>\\d{1,3})\\s+(?<longitude>\\d{1,3})");
    private static final int NUMBER_OF_GROUPS = 3;
    private static final String GROUP_NAME = "name";
    private static final String GROUP_LATITUDE = "latitude";
    private static final String GROUP_LONGITUDE = "longitude";

    @Override
    public ArrayList<City> parse(String filepath) throws Exception {

        var cities = new ArrayList<City>();

        try {
            final var file = new File(filepath);
            final var input = new Scanner(file);

            while (input.hasNextLine()) {
                final var line = input.nextLine();

                final var matcher = pattern.matcher(line);

                if(matcher.find() && matcher.groupCount() == NUMBER_OF_GROUPS) {
                    final String name = matcher.group(GROUP_NAME);
                    final double latitude = Double.parseDouble(matcher.group(GROUP_LATITUDE));
                    final double longitude = Double.parseDouble(matcher.group(GROUP_LONGITUDE));

                    cities.add(new City(latitude, longitude, name));
                }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw new Exception("Could not parse file: " + filepath);
        }

        return cities;
    }
}
