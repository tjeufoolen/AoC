package dev.foolen.aoc2022.solutions;

import dev.foolen.aoc2022.commons.utils.IOUtil;

import java.io.FileNotFoundException;
import java.util.List;

public abstract class AbstractDay {
    protected List<String> input;

    public AbstractDay(int nbOfDay) {
        String fileName = String.format("day%02d.txt", nbOfDay);
        String filePath = String.format("input/%s", fileName);

        try {
            input = IOUtil.readFileAsListOfStrings(filePath);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            System.exit(-1);
        }
    }

    public void solve() {
        long start = System.currentTimeMillis();
        String answer = solvePartOne();
        System.out.printf("[Part1] Took %dms, answer: %s%n", (System.currentTimeMillis() - start), answer);

        start = System.currentTimeMillis();
        answer = solvePartTwo();
        System.out.printf("[Part2] Took %dms, answer: %s%n", (System.currentTimeMillis() - start), answer);
    }

    public abstract String solvePartOne();
    public abstract String solvePartTwo();
}
