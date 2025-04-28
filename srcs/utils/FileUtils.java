package utils;

import java.io.IOException;
import java.io.PrintStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.List;

public class FileUtils {
    static String inputFile = "scenario.txt";
    public static List<String> readFile(String fileName) {
        // Check if fileName is null or empty
        if (fileName == null || fileName.trim().isEmpty()) {
            System.err.println("Error: No input file provided.");
            System.exit(1);
        }

        if (!fileName.equals(inputFile)) {
            System.err.println("Error: Input file must be named " + inputFile);
            System.exit(1);
        }

        Path filePath = Paths.get(fileName);
        List<String> lines = null;

        // Check if file exists
        if (!Files.exists(filePath)) {
            System.err.println("Error: File '" + inputFile + "' does not exist.");
            System.exit(1);
        }

        try {
            lines = Files.readAllLines(filePath);
            if (lines.isEmpty()) {
                System.err.println("Error: File '" + inputFile + "' is empty.");
                System.exit(1);
            }
        } catch (IOException e) {
            System.err.println("Error: Unable to read " + inputFile);
            System.exit(1);
        }

        return lines;
    }

    public static PrintStream redirectOutputToFile() throws IOException {
        Path newFilePath = Paths.get("simulation.txt");

        PrintStream fileOut = new PrintStream(
            Files.newOutputStream(
                newFilePath,
                StandardOpenOption.CREATE
            ),
            true,
            "UTF-8"
        );
        // Redirect System.out to simulation.txt
        System.setOut(fileOut);
        // Return the PrintStream to close it later
        return fileOut;
    }
}