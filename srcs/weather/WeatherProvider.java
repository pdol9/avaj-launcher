package weather;

import nav.Coordinates;
import java.lang.String;

public class WeatherProvider {
    private static WeatherProvider instance;
    private WeatherProvider() {}

    private String[] weather = {"RAIN", "FOG", "SUN", "SNOW"};

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
        return weather[(int) (Math.random() * weather.length + p_coordinates.getLatitude() + p_coordinates.getLongitude() + p_coordinates.getHeight())];
    }
}