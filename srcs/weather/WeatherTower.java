package weather;

import java.lang.String;
import nav.Coordinates;
import java.util.Random;

public class WeatherTower extends Tower {

    private String currentWeather;
    private static final String[] WEATHER_STATES = {"RAIN", "FOG", "SUN", "SNOW"};
    private static final Random random = new Random();

    public WeatherTower() {
        // Initialize with a random weather
        currentWeather = WEATHER_STATES[random.nextInt(WEATHER_STATES.length)];
    }

    public String getWeather(Coordinates p_coordinates) {
        // Ignore coordinates for now, return the randomly generated weather
        return currentWeather;
    }

    public void changeWeather() {
        // Generate a new random weather
        currentWeather = WEATHER_STATES[random.nextInt(WEATHER_STATES.length)];
        conditionChanged();
    }
}