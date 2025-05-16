package aviation;

import nav.Coordinates;
import weather.WeatherTower;

public abstract class Aircraft extends Flyable {
    
    protected long id;
    protected String name;
    protected Coordinates coordinates;
    protected boolean duplicate;

    /***************************************/
    /*              SharedState            */
    /***************************************/

    protected SharedState AirborneState;

public class SharedState {
    private boolean airborneStatus = false;

    public boolean isAirborne() {
        return this.airborneStatus;
    }

    public void setAirborneStatus(boolean flag) {
        this.airborneStatus = flag;
    }
}

    public void setAirborneReference() {
        this.AirborneState = this.new SharedState();
    }

    public void linkAirborneStatus(Aircraft primary) {
        this.AirborneState = primary.AirborneState;
    }

    public void setAirStatus(boolean flag) {
        if (this.AirborneState != null) {
            this.AirborneState.setAirborneStatus(flag);
        }
    }

    public boolean getAirStatus() {
        return AirborneState.isAirborne();
    }

        /************************************/
        /*              Aircraft            */
        /************************************/

    protected Aircraft(long p_id, String p_name, Coordinates p_coordinate) {
        this.id = p_id;
        this.name = p_name;
        this.coordinates = p_coordinate;
        this.duplicate = false;
    }

    @Override
    public void registerTower(WeatherTower p_tower) {
        this.weatherTower = p_tower;
        p_tower.register(this);
    }

    public Coordinates getCoordinates() {
        return coordinates;
    }

    @Override
    public String getID() {
        return getType() + "#" + name + "(" + id + ")";
    }

    @Override
    public Long getIdNum() {
        return this.id;
    }

    @Override
    public void setIdNum(Long existingId) {
        this.id = existingId;
    }

    public String getName() {
        return this.name;
    }
    
    @Override
    public void markDuplicate(boolean flag) {
        this.duplicate = flag;
    }

    @Override
    public boolean isDuplicate() {
        return this.duplicate;
    }
}