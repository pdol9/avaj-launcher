package flyable;

import weather.*;
import nav.Coordinates;

class Baloon extends Aircraft {
    public Baloon(long p_id, String p_name, Coordinates p_coordinate) {
        super(p_id, p_name, p_coordinate);
    }
    @Override
    public void updateConditions() {
        String weather = weatherTower.getWeather(coordinates);
        System.out.println("Baloon " + name + ": Weather is " + weather + ", Coordinates: (" +
            coordinates.getLongitude() + ", " + coordinates.getLatitude() + ", " + coordinates.getHeight() + ")");
    }

    @Override
    public void registerTower(WeatherTower tower) {
        this.weatherTower = tower;
        tower.register(this);
    }
}
