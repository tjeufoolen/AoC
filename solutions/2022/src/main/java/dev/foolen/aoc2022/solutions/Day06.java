package dev.foolen.aoc2022.solutions;

import dev.foolen.aoc2022.commons.utils.IOUtil;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class Day06 {
    private static List<String> input;

    public static void main(String[] args) throws IOException {
        input = IOUtil.readFileAsListOfStrings( "input/day06.txt");

        solvePartOne();
        solvePartTwo();
    }

    private static void solvePartOne() {
        int marker = getFirstMarker(input.get(0), 4);
        System.out.printf("[PartOne] first marker after character: %s%n", marker);
        // - 1702
    }

    private static void solvePartTwo() {
        int marker = getFirstMarker(input.get(0), 14);
        System.out.printf("[PartTwo] first marker after character: %s%n", marker);
        // - 3559
    }

    private static int getFirstMarker(String buffer, int nbOfDistinctCharacters) {
        List<String> parts = Arrays.stream(buffer.split("")).toList();
        for (int i=nbOfDistinctCharacters-1; i< parts.size(); i++) {
            List<String> lastFour = parts.subList(i-(nbOfDistinctCharacters-1), i+1);
            if (lastFour.stream().distinct().count() == nbOfDistinctCharacters) return i + 1;
        }
        return -1;
    }
}
