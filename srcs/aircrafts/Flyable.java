package aircrafts;

import simulator.Coordinates;
import weather.WeatherTower;

public abstract class Flyable {
    protected WeatherTower weatherTower;

    public void registerTower(WeatherTower p_tower) {
        this.weatherTower = p_tower;
        p_tower.register(this);
    }
    public abstract void updateConditions();
    public abstract Coordinates getCoordinates();
    public abstract String getType();
    public abstract String getID();
    public abstract void setIdNum(Long existingId);
    public abstract Long getIdNum();
    public abstract void markDuplicate(boolean flag);
    public abstract boolean isDuplicate();
    public abstract void setAirborneReference();
    public abstract void linkAirborneStatus(Aircraft primary);
    public abstract void setAirStatus(boolean flag);
    public abstract boolean getAirStatus();
}