package dev.foolen.aoc2022.commons.utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class IOUtil {
    public static List<String> readFileAsListOfStrings(String filePath) throws FileNotFoundException {
        List<String> lines = new ArrayList<>();
        File inputFile = null;
        try {
            inputFile = getFileFromResource(filePath);
            Scanner sc = new Scanner(inputFile);
            while (sc.hasNextLine()) {
                lines.add(sc.nextLine());
            }
        } catch (URISyntaxException e) {
            throw new FileNotFoundException();
        }
        return lines;
    }

    private static File getFileFromResource(String fileName) throws URISyntaxException {
        ClassLoader classLoader = IOUtil.class.getClassLoader();
        URL resource = classLoader.getResource(fileName);
        if (resource == null) {
            throw new IllegalArgumentException("file not found! " + fileName);
        } else {
            return new File(resource.toURI());
        }
    }
}
