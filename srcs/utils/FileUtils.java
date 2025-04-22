package utils;

import java.io.IOException;
import java.io.PrintStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.List;

public class FileUtils {
    public static List<String> readFile(String fileName) throws IOException {
        Path filePath = Paths.get(fileName);
        List<String> lines;
        try {
            lines = Files.readAllLines(filePath);
            if (lines.isEmpty()) {
                throw new IOException(fileName + " is empty");
            }
        } catch (IOException e) {
            System.err.format("IOException while reading scenario.txt: %s%n", e);
            throw e;
        }
        return lines;
    }

    public static PrintStream redirectOutputToFile() throws IOException {
        Path newFilePath = Paths.get("./simulation.txt");

        PrintStream fileOut = new PrintStream(
            Files.newOutputStream(
                newFilePath,
                StandardOpenOption.CREATE,
                StandardOpenOption.TRUNCATE_EXISTING
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