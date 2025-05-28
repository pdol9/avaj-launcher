package aircrafts;

import simulator.Coordinates;
import weather.WeatherTower;

public abstract class Flyable {
    protected WeatherTower weatherTower;
    public abstract Coordinates getCoordinates();
    public abstract void updateConditions();
    public abstract void registerTower(WeatherTower tower);
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