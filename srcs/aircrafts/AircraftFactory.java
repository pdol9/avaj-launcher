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

    public Flyable newAircraft(String p_type, String p_name, Coordinates p_coordinates) {
        long id = nextId();
        return switch (p_type) {
            case "Baloon", "Balloon" -> new Balloon(id, p_name, p_coordinates);
            case "Helicopter" -> new Helicopter(id, p_name, p_coordinates);
            case "JetPlane" -> new JetPlane(id, p_name, p_coordinates);
            default -> throw new IllegalArgumentException("Invalid aircraft type: " + p_type);
        };
    }
}