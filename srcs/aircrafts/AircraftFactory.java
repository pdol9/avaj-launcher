package aircrafts;

import simulator.Coordinates;

public class AircraftFactory {
    private static AircraftFactory instance;
    private static long idCounter = 0;

    private AircraftFactory() {}

    public static AircraftFactory getInstance() {
        if (instance == null) {
            instance = new AircraftFactory();
        }
        return instance;
    }

    private long nextId() {
        return ++idCounter;
    }

    public Flyable newAircraft(String type, String name, Coordinates coordinates) {
        long id = nextId();
        return switch (type) {
            case "Baloon", "Balloon" -> new Balloon(id, name, coordinates);
            case "Helicopter" -> new Helicopter(id, name, coordinates);
            case "JetPlane" -> new JetPlane(id, name, coordinates);
            default -> throw new IllegalArgumentException("Invalid aircraft type: " + type);
        };
    }
}