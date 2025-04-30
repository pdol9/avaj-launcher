package app;

import weather.WeatherTower;
import flyable.AircraftFactory;
import flyable.Flyable;
import utils.FileUtils;
import nav.Coordinates;

import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException {

        if (args.length != 1) {
            System.err.println("Usage: java -cp srcs app.Main scenario");
            System.exit(1);
        }
        String fileName = args[0];
        List<String> lines = FileUtils.readFile(fileName);
        PrintStream fileOut = FileUtils.redirectOutputToFile();

        try {
            // Parse the file
            int numSimulations;
            try {
                numSimulations = Integer.parseInt(lines.get(0));
                if (numSimulations <= 0)
                    throw new NumberFormatException("Simulation count must be positive.");
            } catch (NumberFormatException e) {
                throw new IOException("First line of " + fileName + " must be a positive integer", e);
            }
            
            List<Flyable> aircrafts = new ArrayList<>();
            AircraftFactory factory = AircraftFactory.getInstance();
            WeatherTower tower = new WeatherTower();

            for (int i = 1; i < lines.size(); i++) {
                String[] parts = lines.get(i).split("\\s+");
                if (parts.length != 5) {
                    throw new IOException("Invalid line in " + fileName + ": " + lines.get(i));
                }
                String type = parts[0];
                String name = parts[1];
                int longitude, latitude, height;
                try {
                    longitude = Integer.parseInt(parts[2]);
                    latitude = Integer.parseInt(parts[3]);
                    height = Integer.parseInt(parts[4]);
                } catch (NumberFormatException e) {
                    throw new IOException("Invalid coordinates in " + fileName + ": " + lines.get(i), e);
                }
                Flyable aircraft = factory.newAircraft(type, name, new Coordinates(longitude, latitude, height));
                aircraft.registerTower(tower);
                aircrafts.add(aircraft);
            }

            // run simulations
            for (int i = 0; i < numSimulations; i++) {
                tower.changeWeather();
            }
        } finally {
            fileOut.close();
            System.setOut(new PrintStream(new java.io.FileOutputStream(java.io.FileDescriptor.out)));
        }
    }
}