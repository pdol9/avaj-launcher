package app;

import java.util.ArrayList;
import java.util.List;

import nav.Coordinates;
import weather.WeatherTower;
import aviation.Aircraft;
import aviation.AircraftFactory;
import aviation.Flyable;

public class Simulation {

    private static final List<Flyable> aircrafts = new ArrayList<>();

    public static void runSimulation(List<String> lines, String fileName) {
        aircrafts.clear();
        WeatherTower tower = new WeatherTower();
        int numSimulations = parseSimIteration(lines, fileName);
        
        for (int i = 1; i < lines.size(); i++) {
            String line = lines.get(i);
            processScenarioFile(tower, line, i + 1, fileName);
        }

        for (int i = 0; i < numSimulations; i++) {
            tower.changeWeather();
        }
    }

    private static int parseSimIteration(List<String> lines, String fileName) {
        if (lines.size() < 2) {
            System.err.println("Error: File '" + fileName + "' must have at least 2 lines (simulation count and at least one aircraft).");
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

    private static void processScenarioFile(WeatherTower tower, String line, int lineNumber, String fileName) {
        AircraftFactory factory = AircraftFactory.getInstance();
        String[] parts = line.split("\\s+");
        if (parts.length != 5) {
            System.err.println("Error: Invalid line in '" + fileName + "' at line " + lineNumber + ": " + line);
            System.exit(1);
        }

        String type = parts[0];
        String name = parts[1];
        int longitude = parseIntWithValidation(parts[2], "Longitude", lineNumber, fileName, line, 0, 1000);
        int latitude = parseIntWithValidation(parts[3], "Latitude", lineNumber, fileName, line, 0, 1000);
        int height = parseIntWithValidation(parts[4], "Height", lineNumber, fileName, line, 0, 100);

        try {
            Flyable aircraft = factory.newAircraft(type, name, new Coordinates(longitude, latitude, height));
            aircrafts.add(aircraft);
            markIfDuplicateName(aircrafts, aircraft, name);
            aircraft.registerTower(tower);
            tower.addTowersWatchList(aircraft);
        } catch (Exception e) {
            System.err.println("Error: Failed to process aircraft in '" + fileName + "' at line " + lineNumber + ": " + line);
            System.exit(1);
        }
    }

    private static int parseIntWithValidation(String value, String fieldName, int lineNumber, String fileName, String line, int min, int max) {
        int parsedValue = 0;
        try {
            parsedValue = Integer.parseInt(value);
        } catch (NumberFormatException e) {
            System.err.println("Error: Invalid " + fieldName.toLowerCase() + " in '" + fileName + "' at line " + lineNumber + ": " + line);
            System.exit(1);
        }

        if (parsedValue < min || parsedValue > max) {
            System.err.println("Error: " + fieldName + " must be between " + min + " and " + max + " in '" + fileName + "' at line " + lineNumber + ": " + line);
            System.exit(1);
        }

        return parsedValue;
    }

    private static void markIfDuplicateName(List<Flyable> aircrafts, Flyable currentAircraft, String name) {
        for (Flyable existingAircraft : aircrafts) {
            if (existingAircraft != currentAircraft && ((Aircraft) existingAircraft).getName().equals(name)) {
                currentAircraft.setRegistered(true);
                existingAircraft.setRegistered(true);
                break;
            }
        }
    }
}
