package dev.foolen.aoc2022.solutions;

import dev.foolen.aoc2022.commons.utils.IOUtil;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.IOException;
import java.util.*;

public class Day01 {
    private static List<String> input;

    public static void main(String[] args) throws IOException {
        input = IOUtil.readFileAsListOfStrings( "input/day01.txt");

        solvePartOne();
        solvePartTwo();
    }

    private static void solvePartOne() {
        Elf elf = createSortedElvesCarryingCaloriesList().get(0);
        System.out.println("\n=============== PART1 ===============");
        System.out.println(String.format("Elf %s is carrying the most calories. He/She is carrying %s calories.", elf.number, elf.calories));
    }

    private static void solvePartTwo() {
        List<Elf> elves = createSortedElvesCarryingCaloriesList().subList(0, 3);
        System.out.println("\n=============== PART2 ===============");
        System.out.println("Top 3 - Most carrying elves:");
        for (int i=0; i<elves.size(); i++) {
            int place = i+1;
            Elf elf = elves.get(i);
            System.out.println(String.format("%s. Elf %s carrying %s calories.", place, elf.number, elf.calories));
        }
        int total = elves.stream().mapToInt(elf -> elf.calories).sum();
        int amountOfElves = elves.size();
        System.out.println(String.format("\nThis is a total of %s calories carried by only %s elves!", total, amountOfElves));
    }

    private static List<Elf> createSortedElvesCarryingCaloriesList() {
        List<Elf> elves = new ArrayList<>();
        elves.add(new Elf(1, 0));

        for (String line : input) {
            Elf elf = elves.get(elves.size() - 1);

            // Check if new elf
            if (line.isEmpty()) {
                elves.add(new Elf(elf.number + 1, 0));
                continue;
            }

            // Add calories to total carried
            elf.addCalories(Integer.parseInt(line));
        }

        // sort elves by most carrying calories
        elves.sort((o1, o2) -> o2.calories - o1.calories);

        return elves;
    }

    @AllArgsConstructor
    @ToString
    private static class Elf {
        @Getter @Setter
        private final int number;

        @Getter @Setter
        private int calories;

        public void addCalories(int calories) {
            this.calories += calories;
        }
    }
}
