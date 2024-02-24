package dev.foolen.aoc2022.solutions;

import dev.foolen.aoc2022.commons.utils.IOUtil;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.io.IOException;
import java.util.*;

import static dev.foolen.aoc2022.solutions.Day02.Outcome.*;
import static dev.foolen.aoc2022.solutions.Day02.Weapon.*;

public class Day02 {
    private static List<String> input;

    public static void main(String[] args) throws IOException {
        input = IOUtil.readFileAsListOfStrings( "input/day02.txt");

        solvePartOne();
        solvePartTwo();
    }

    private static void solvePartOne() {
        int total = 0;
        for (String line : input) {
            Weapon ow = Weapon.getByChar(line.charAt(0));
            Weapon mw = Weapon.getByChar(line.charAt(2));
            Outcome oc = null;

            switch (ow) {
                case ROCK -> oc = mw == ROCK ? DRAW : mw == PAPER ? WIN : LOSE;
                case PAPER -> oc = mw == PAPER ? DRAW : mw == SCISSORS ? WIN : LOSE;
                case SCISSORS -> oc = mw == SCISSORS ? DRAW : mw == ROCK ? WIN : LOSE;
            }

            total += oc.points + mw.value;
        }
        System.out.printf("[PartOne] total: %d%n", total);
        // - 13052
    }

    private static void solvePartTwo() {
        int total = 0;
        for (String line : input) {
            Weapon ow = Weapon.getByChar(line.charAt(0));
            Weapon mw = null;
            Outcome oc = line.charAt(2) == 'Z' ? WIN : line.charAt(2) == 'Y' ? DRAW : LOSE;

            switch (ow) {
                case ROCK -> mw = oc == DRAW ? ROCK : oc == WIN ? PAPER : SCISSORS;
                case PAPER -> mw = oc == DRAW ? PAPER : oc == WIN ? SCISSORS : ROCK;
                case SCISSORS -> mw = oc == DRAW ? SCISSORS : oc == WIN ? ROCK : PAPER;
            }

            total += oc.points + mw.value;
        }
        System.out.printf("[PartTwo] total: %d%n", total);
        // - 13693
    }

    @AllArgsConstructor
    public enum Outcome {
        WIN(6),
        DRAW(3),
        LOSE(0);

        @Getter
        private final int points;
    }

    @AllArgsConstructor
    public enum Weapon {
        ROCK(1),
        PAPER(2),
        SCISSORS(3);

        @Getter
        private final int value;

        public static Weapon getByValue(int value) {
            return Arrays.stream(values())
                .filter(w -> w.value == value)
                .findFirst()
                .orElse(null);
        }

        public static Weapon getByChar(char c) {
            if ("ABC".contains(String.valueOf(c))) {
                return getByValue("ABC".indexOf(c) + 1);
            } else if ("XYZ".contains(String.valueOf(c))) {
                return getByValue("XYZ".indexOf(c) + 1);
            }
            return null;
        }
    }
}
