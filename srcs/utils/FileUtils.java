package utils;

import java.io.IOException;
import java.io.PrintStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.List;

public class FileUtils {
    private static final String EXPECTED_INPUT_FILE = "scenario.txt";

    public static List<String> readFile(String fileName) {
        validateFileName(fileName);
        validateFileExists(fileName);
        List<String> lines = readLines(fileName);
        validateNotEmpty(lines, fileName);
        return lines;
    }

    private static void validateFileName(String fileName) {
        if (fileName == null || fileName.trim().isEmpty()) {
            exitWithError("Error: No input file provided.");
        }
        if (!fileName.equals(EXPECTED_INPUT_FILE)) {
            exitWithError("Error: Input file must be named " + EXPECTED_INPUT_FILE);
        }
    }

    private static void validateFileExists(String fileName) {
        if (!Files.exists(Paths.get(fileName))) {
            exitWithError("Error: File '" + fileName + "' does not exist.");
        }
    }

    private static List<String> readLines(String fileName) {
        try {
            return Files.readAllLines(Paths.get(fileName));
        } catch (IOException e) {
            exitWithError("Error: Unable to read " + fileName);
            return null; // unreachable, but required by compiler
        }
    }

    private static void validateNotEmpty(List<String> lines, String fileName) {
        if (lines.isEmpty()) {
            exitWithError("Error: File '" + fileName + "' is empty.");
        }
    }

    private static void exitWithError(String message) {
        System.err.println(message);
        System.exit(1);
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