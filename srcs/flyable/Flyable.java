package flyable;

import nav.Coordinates;
import weather.WeatherTower;

public abstract class Flyable {
    protected WeatherTower weatherTower;
    public abstract Coordinates getCoordinates();
    public abstract void updateConditions();
    public abstract void registerTower(WeatherTower tower);
    public abstract String getType();
    public abstract String getID();
}