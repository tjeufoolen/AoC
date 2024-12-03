package lib;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;

public class InputUtil {
    public static List<String> readLines(String filename) throws IOException {
        return Files.readAllLines(new File(filename).toPath());
    }
}
