package app;

import java.io.PrintStream;
import java.util.List;

import utils.FileUtils;

public class Main {
    public static void main(String[] args) {
        if (args.length != 1) {
            System.err.println("Error: Usage -> java -cp srcs app.Main scenario");
            System.exit(1);
        }
        String fileName = args[0];
        List<String> lines = FileUtils.readFile(fileName);
        PrintStream fileOut = null;

        try {
            fileOut = FileUtils.redirectOutputToFile();
            Simulation.runSimulation(lines, fileName);
        } catch (Exception e) {
            System.err.println("Error: Failed to run simulation: " + e.getMessage());
            System.exit(1);
        } finally {
            if (fileOut != null) {
                fileOut.close();
            }
            System.setOut(new PrintStream(new java.io.FileOutputStream(java.io.FileDescriptor.out)));
        }
    }
}