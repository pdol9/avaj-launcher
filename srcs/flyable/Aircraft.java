package flyable;

import nav.Coordinates;
import weather.WeatherTower;

public abstract class Aircraft extends Flyable {
    protected long id;
    protected String name;
    protected Coordinates coordinates;
    protected WeatherTower weatherTower;

    protected Aircraft(long id, String name, Coordinates coordinate) {
        this.id = id;
        this.name = name;
        this.coordinates = coordinate;
    }

    @Override
    public void registerTower(WeatherTower tower) {
        this.weatherTower = tower;
        tower.register(this);
    }

    public Coordinates getCoordinates() {
        return coordinates;
    }
    // for now to increment coordinates
    public void incrementCoordinates(int delta) {
        coordinates.incrementCoordinates(delta);
    }
}