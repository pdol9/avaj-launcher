package weather;

import java.util.*;
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

    // Stores current weather per zone
    private final Map<String, String> zoneWeather = new HashMap<>();

    // Called once per simulation tick to update zone weather
    public void updateWeather() {
        zoneWeather.put("Zone 1", randomWeather());
        zoneWeather.put("Zone 2", randomWeather());
        zoneWeather.put("Zone 3", randomWeather());
        zoneWeather.put("Zone 4", randomWeather());

        System.out.println("WeatherProvider: Weather updated for all zones.");
    }

    /*
    * Return the current weather for the provided coordinates.
    * The world is divided into four deterministic “zones”.
    */
    public String getCurrentWeather(Coordinates p_coordinates) {
        int lon = p_coordinates.getLongitude();
        int lat = p_coordinates.getLatitude();
        int height = p_coordinates.getHeight();

        String zone;

        if (lon < 100 && lat < 100 && height <= 30) {
            zone = "Zone 1";
        } else if (lon > 100 && lon <= 500 &&
                   lat > 0   && lat <= 300 &&
                   height <= 60) {
            zone = "Zone 2";
        } else if (lon >= 250 && lon <= 750 &&
                   lat >= 200 && lat <= 1000 &&
                   height > 60 && height <= 80) {
            zone = "Zone 3";
        } else {
            zone = "Zone 4";
        }

        String w = zoneWeather.get(zone);
        System.out.printf("WeatherProvider (%s) says: weather update: %s.%n", zone, w);
        return w;
    }

    // ────────────────────────────────────────────────────────────
    // Helper
    // ────────────────────────────────────────────────────────────
    /** Pick one of the four weather strings at random. */
    private String randomWeather() {
        int idx = ThreadLocalRandom.current().nextInt(weather.length);
        return weather[idx];
    }
}