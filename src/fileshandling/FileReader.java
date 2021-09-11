package fileshandling;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 *
 */
public class FileReader {

    /**
     * Reads a file and store the lines in a List.
     *
     * @param filePath: a path to the file.
     * @throws IOException: if something is bad with the given file.
     * @return List<String> the lines of the file.
     */
    public static List<String> readFile(String filePath) throws IOException {
        List<String> fileLines = new ArrayList<>();
        // Try reading file (closes file automatically if error occurs):
        try (FileInputStream fstream = new FileInputStream(filePath);
             BufferedReader buffer = new BufferedReader(new InputStreamReader(fstream))) {
            String line;
            while ((line = buffer.readLine()) != null) {
                fileLines.add(line);
            }
        }
        // Error occurred:
        catch (IOException e) {
            // Propagate it upwards:
            throw new IOException();
        }
        return fileLines;
    }
}
