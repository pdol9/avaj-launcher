package weather;

import simulator.Coordinates;

public class WeatherTower extends Tower {

    public String getWeather(Coordinates p_coordinates) {
        return WeatherProvider.getInstance().getCurrentWeather(p_coordinates);
    }

    public void changeWeather() {
        // Update all zones once
        WeatherProvider.getInstance().updateWeather();
        // Notify aircraft
        conditionChanged();
    }
}