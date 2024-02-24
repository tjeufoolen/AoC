package dev.foolen.aoc2022.solutions;

import dev.foolen.aoc2022.commons.utils.IOUtil;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Day03 {
    private static List<String> input;
    private static final String PRIORITY_INDEX = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private static final int ELF_GROUP_SIZE = 3;

    public static void main(String[] args) throws IOException {
        input = IOUtil.readFileAsListOfStrings( "input/day03.txt");

        solvePartOne();
        solvePartTwo();
    }

    private static void solvePartOne() {
        int sum = input.stream().mapToInt(line -> {
            String left = line.substring(0, line.length() / 2);
            String right = line.substring(line.length() / 2);
            char item = getOverlappingItemFromRucksacks(left, right);
            return getPriorityFromItem(item);
        }).sum();
        System.out.printf("[PartOne] The sum is: %d%n", sum);
        // - 7553
    }

    private static void solvePartTwo() {
        int sum = getElfGroups(input).stream().mapToInt(group -> {
            char item = getOverlappingItemFromRucksacks(group.stream().toArray(String[]::new));
            return getPriorityFromItem(item);
        }).sum();
        System.out.printf("[PartTwo] The sum is: %s%n", sum);
        // - 2758
    }

    private static List<List<String>> getElfGroups(List<String> input) {
        List<List<String>> groups = new ArrayList<>();
        List<String> group = new ArrayList<>();

        for (String line : input) {
            group.add(line);

            if (group.size() == ELF_GROUP_SIZE) {
                groups.add(group);
                group = new ArrayList<>();
            }
        }

        return groups;
    }

    private static char getOverlappingItemFromRucksacks(String ...rucksacks) {
        for (String rucksack : rucksacks) {
            for (String c : rucksack.split("")) {
                String[] filtered = Arrays.stream(rucksacks)
                        .filter(rs -> rs.contains(c))
                        .toArray(String[]::new);
                if (filtered.length == rucksacks.length) {
                    return c.charAt(0);
                }
            }
        }
        return 0;
    }

    private static int getPriorityFromItem(char item) {
        return PRIORITY_INDEX.indexOf(item) + 1;
    }
}
