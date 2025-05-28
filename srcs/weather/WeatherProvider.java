package weather;

import java.lang.String;
import java.util.concurrent.ThreadLocalRandom;

import simulator.Coordinates;

public class WeatherProvider {

    // ────────────────────────────────────────────────────────────
    // Singleton boilerplate
    // ────────────────────────────────────────────────────────────
    private static final WeatherProvider instance = new WeatherProvider();

    private WeatherProvider() {}

    public static WeatherProvider getInstance() {
        return instance;
    }

    private static final String[] weather = {"FOG", "SNOW", "RAIN", "SUN"};

    /*
    * Return the current weather for the provided coordinates.
    * The world is divided into four deterministic “zones”; *within*
    * a zone the weather is random on every call.
    */
    public String getCurrentWeather(Coordinates p_coordinates) {
            int lon = p_coordinates.getLongitude();
            int lat = p_coordinates.getLatitude();
            int height = p_coordinates.getHeight();

        // Zone 1 ────────────────────────────────────────────────
        if (lon < 100 && lat < 100 && height <= 30) {
            return randomWeather("Zone 1");
        }

        // Zone 2 ────────────────────────────────────────────────
        if (lon > 100 && lon <= 500 &&
            lat > 0   && lat <= 300 &&
            height <= 60) {
            return randomWeather("Zone 2");
        }

        // Zone 3 ────────────────────────────────────────────────
        if (lon >= 250 && lon <= 750 &&
            lat >= 200 && lat <= 1000 &&
            height > 60 && height <= 80) {
            return randomWeather("Zone 3");
        }

        // Default zone ──────────────────────────────────────────
        return randomWeather("Zone 4");
    }

    // ────────────────────────────────────────────────────────────
    // Helper
    // ────────────────────────────────────────────────────────────
    /** Pick one of the four weather strings at random and print it. */
    private String randomWeather(String zoneLabel) {
        int idx = ThreadLocalRandom.current().nextInt(weather.length);
        String w = weather[idx];
        System.out.printf("WeatherProvider (%s) says: weather update: %s.%n", zoneLabel, w);
        return w;
    }
}