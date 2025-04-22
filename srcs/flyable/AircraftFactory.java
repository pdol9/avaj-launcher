package flyable;

import nav.Coordinates;

public class AircraftFactory {
    private static AircraftFactory instance;
    private AircraftFactory() {}

    static {
        try {
            instance = new AircraftFactory();
        } catch (Exception e) {
            throw new RuntimeException("Exception occurred in creating singleton instance");
        }
    }

    public static AircraftFactory getInstance() {
        return instance;
    }
    public Flyable newAircraft(String p_type, String p_name, Coordinates p_coordinates) {
        switch (p_type.toLowerCase()) {
            case "helicopter":
                return new Helicopter(System.currentTimeMillis(), p_name, p_coordinates);
            case "jetplane":
                return new JetPlane(System.currentTimeMillis(), p_name, p_coordinates);
            case "baloon":
                return new Baloon(System.currentTimeMillis(), p_name, p_coordinates);
            default:
                throw new IllegalArgumentException("Unknown aircraft type: " + p_type);
        }
    }
}
