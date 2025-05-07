package aviation;

import nav.Coordinates;
import weather.WeatherTower;

public abstract class Aircraft extends Flyable {

    protected long id;
    protected String name;
    protected Coordinates coordinates;
    protected boolean alreadyRegistered;

    protected Aircraft(long p_id, String p_name, Coordinates p_coordinate) {
        this.id = p_id;
        this.name = p_name;
        this.coordinates = p_coordinate;
        this.alreadyRegistered = false;
    }

    @Override
    public void registerTower(WeatherTower p_tower) {
        this.weatherTower = p_tower;
        p_tower.register(this);
    }

    public Coordinates getCoordinates() {
        return coordinates;
    }
    protected void status_check() {
        if (this.coordinates.getHeight() < 0) {
            System.out.println("Attention! " + this.getID() + " crashed!.");
            weatherTower.unregister(this);
        } else if (this.coordinates.getHeight() == 0) {
            System.out.println("Attention! " + this.getID() + " landed.");
            weatherTower.unregister(this);
        }
    }
    @Override
    public String getID() {
        return getType() + "#" + name + "(" + id + ")";
    }

    public String getName() {
        return this.name;
    }
    public boolean isAlreadyRegistered() {
        return this.alreadyRegistered;
    }
    public void setRegistered(boolean flag) {
        this.alreadyRegistered = flag;
    }
}