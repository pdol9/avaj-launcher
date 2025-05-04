package app;

import nav.Coordinates;
import weather.WeatherTower;
import aviation.AircraftFactory;
import aviation.Flyable;

import java.util.List;

public class Simulation {
    public static int parseSimIteration(List<String> lines, String fileName) {
        if (lines.size() < 2) {
            System.err.println("Error: File '" + fileName + "' must have at least 2 lines (simulation count and one aircraft).");
            System.exit(1);
        }
        int numSimulations = 0;
        try {
            numSimulations = Integer.parseInt(lines.get(0));
            if (numSimulations <= 0) {
                System.err.println("Error: Simulation count must be positive in '" + fileName + "' at line 1: " + lines.get(0));
                System.exit(1);
            }
        } catch (NumberFormatException e) {
            System.err.println("Error: Invalid simulation count in '" + fileName + "' at line 1: " + lines.get(0));
            System.exit(1);
        }
        return numSimulations;
    }

    public static void parseInputFile(List<String> lines, String fileName, List<Flyable> aircrafts) {
        AircraftFactory factory = AircraftFactory.getInstance();
        WeatherTower tower = new WeatherTower();

        for (int i = 1; i < lines.size(); i++) {
            String line = lines.get(i);
            String[] parts = line.split("\\s+");
            if (parts.length != 5) {
                System.err.println("Error: Invalid line in '" + fileName + "' at line " + (i + 1) + ": " + line);
                System.exit(1);
            }
            String type = parts[0];
            String name = parts[1];
            int longitude = 0, latitude = 0, height = 0;
            try {
                longitude = Integer.parseInt(parts[2]);
                latitude = Integer.parseInt(parts[3]);
                height = Integer.parseInt(parts[4]);
            } catch (NumberFormatException e) {
                System.err.println("Error: Invalid coordinates in '" + fileName + "' at line " + (i + 1) + ": " + line);
                System.exit(1);
            }
            // Validate coordinate ranges
            if (longitude < 0) {
                System.err.println("Error: Longitude must be positive in '" + fileName + "' at line " + (i + 1) + ": " + line);
                System.exit(1);
            }
            if (longitude > 1000) {
                System.err.println("Error: Longitude must be between 0 and 1000 in '" + fileName + "' at line " + (i + 1) + ": " + line);
                System.exit(1);
            }
            if (latitude < 0) {
                System.err.println("Error: Latitude must be positive in '" + fileName + "' at line " + (i + 1) + ": " + line);
                System.exit(1);
            }
            if (latitude > 1000) {
                System.err.println("Error: Latitude must be between 0 and 1000 in '" + fileName + "' at line " + (i + 1) + ": " + line);
                System.exit(1);
            }
            if (height < 0) {
                System.err.println("Error: Height must be positive in '" + fileName + "' at line " + (i + 1) + ": " + line);
                System.exit(1);
            }
            if (height > 100) {
                System.err.println("Error: Height must be between 0 and 100 in '" + fileName + "' at line " + (i + 1) + ": " + line);
                System.exit(1);
            }

            try {
                Flyable aircraft = factory.newAircraft(type, name, new Coordinates(longitude, latitude, height));
                aircraft.registerTower(tower);
                aircrafts.add(aircraft);
            } catch (Exception e) {
                System.err.println("Error: Failed to create aircraft in '" + fileName + "' at line " + (i + 1) + ": " + line);
                System.exit(1);
            }
        }
    }

    public static void runSimulation(List<String> lines, String fileName, List<Flyable> aircrafts) {
        int numSimulations = parseSimIteration(lines, fileName);

        // Run simulations
        WeatherTower tower = new WeatherTower();
        for (int i = 0; i < numSimulations; i++) {
            tower.changeWeather();
        }
    }
}