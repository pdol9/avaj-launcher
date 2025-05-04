package weather;

import nav.Coordinates;

public class WeatherTower extends Tower {

    public String getWeather(Coordinates p_coordinates) {
        int seed = (p_coordinates.getLongitude() + p_coordinates.getLatitude() + p_coordinates.getHeight()) % 4;
        return switch (seed) {
            case 0 -> "RAIN";
            case 1 -> "FOG";
            case 2 -> "SUN";
            case 3 -> "SNOW";
            default -> "SUN";
        };
    }

    public void changeWeather() {
        conditionChanged();
    }
}