package flyable;

import nav.Coordinates;
import weather.WeatherTower;

public abstract class Flyable {
    protected WeatherTower weatherTower;
    public abstract Coordinates getCoordinates();
    public abstract void updateConditions();

    public void registerTower(WeatherTower tower) {
        this.weatherTower = tower;
        tower.register(this);
    }
}