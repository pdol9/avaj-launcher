package weather;

import nav.Coordinates;
import java.lang.String;
import java.util.concurrent.ThreadLocalRandom;

public class WeatherProvider {

    private final int indexOne;
    private final int indexTwo;
    private final int indexThree;
    private final int indexFour;

    private static WeatherProvider instance;
    private WeatherProvider() {
        indexOne = ThreadLocalRandom.current().nextInt(4);
        indexTwo = ThreadLocalRandom.current().nextInt(4);
        indexThree = ThreadLocalRandom.current().nextInt(4);
        indexFour = ThreadLocalRandom.current().nextInt(4);
    }

    private String[] weather = {"FOG", "SNOW", "RAIN", "SUN"};

    // static block initialization for exception handling
    static {
        try {
            instance = new WeatherProvider();
        } catch (Exception e) {
            throw new RuntimeException("Exception occurred in creating singleton instance");
        }
    }

    public static WeatherProvider getInstance() {
        return instance;
    }

    public String getCurrentWeather(Coordinates p_coordinates) {
        final int lon = p_coordinates.getLongitude();
        final int lat = p_coordinates.getLatitude();
        final int height = p_coordinates.getHeight();

        // weather ranges:
        if (lon < 100 &&
            lat < 100 &&
            height <= 30) {
            System.out.printf("WeatherProvider says: weather update: %s.\n", weather[indexOne]);
            return weather[this.indexOne];
            
        }

        if (lon > 100 && lon <= 500 &&
            lat > 0 && lat <= 300 &&
            height <= 60 ) {
            System.out.printf("WeatherProvider says: weather update: %s.\n", weather[indexTwo]);
            return weather[this.indexTwo];
        }

        if (lon >= 250 && lon <= 750 &&
            lat >= 200 && lat <= 1000 &&
            height > 60 && height <= 80) {
            System.out.printf("WeatherProvider says: weather update: %s.\n", weather[indexThree]);
            return weather[this.indexThree];
        }

        // Default
        System.out.printf("WeatherProvider says: weather update: %s.\n", weather[indexFour]);
        return weather[this.indexFour];
    }
}