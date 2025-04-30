package flyable;

import nav.Coordinates;
import weather.WeatherTower;

public abstract class Aircraft extends Flyable {

    protected long id;
    protected String name;
    protected Coordinates coordinates;
    protected WeatherTower weatherTower;
    protected boolean landed = false;

    protected Aircraft(long p_id, String p_name, Coordinates p_coordinate) {
        this.id = p_id;
        this.name = p_name;
        this.coordinates = p_coordinate;
    }
    @Override
    public void registerTower(WeatherTower p_tower) {
        this.weatherTower = p_tower;
        p_tower.register(this);
    }

    public Coordinates getCoordinates() {
        return coordinates;
    }
    protected void land() {
        if (!landed) {
            landed = true;
            System.out.println(this + " landing.");
            weatherTower.unregister(this);
        }
    }
    @Override
    public String getID() {
        return getType() + "#" + name + "(" + id + ")";
    }
}