package dev.foolen.aoc2022.solutions;

import dev.foolen.aoc2022.commons.utils.IOUtil;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

import java.io.IOException;
import java.util.List;
import java.util.function.Predicate;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class Day04 {
    private static List<String> input;

    public static void main(String[] args) throws IOException {
        input = IOUtil.readFileAsListOfStrings( "input/day04.txt");

        solvePartOne();
        solvePartTwo();
    }

    private static void solvePartOne() {
        long pairs = getNumberOfAssignmentPairsBasedOnPredicate(x -> x.fs <= x.ss && x.fe >= x.se || x.fs >= x.ss && x.fe <= x.se);
        System.out.printf("[PartOne] result: %d%n", pairs);
        // - 498
    }

    private static void solvePartTwo() {
        long pairs = getNumberOfAssignmentPairsBasedOnPredicate(x -> x.fs >= x.ss && x.fs <= x.se || x.fs <= x.ss && x.fe >= x.ss);
        System.out.printf("[PartTwo] result: %d%n", pairs);
        // - 859
    }

    private record AssignmentPair(int fs, int fe, int ss, int se) {}

    private static long getNumberOfAssignmentPairsBasedOnPredicate(Predicate<AssignmentPair> predicate) {
        Pattern pattern = Pattern.compile("(\\d{1,3})-(\\d{1,3}),(\\d{1,3})-(\\d{1,3})");
        return input.stream()
            .map(line -> {
                Matcher m = pattern.matcher(line);
                if (m.find()) {
                    int fs = Integer.parseInt(m.group(1)), fe = Integer.parseInt(m.group(2));
                    int ss = Integer.parseInt(m.group(3)), se = Integer.parseInt(m.group(4));
                    return new AssignmentPair(fs, fe, ss, se);
                }
                return null;
            })
            .filter(predicate)
            .count();
    }
}
