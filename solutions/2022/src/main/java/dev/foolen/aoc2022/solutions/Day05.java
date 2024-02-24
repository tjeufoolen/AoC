package dev.foolen.aoc2022.solutions;

import dev.foolen.aoc2022.commons.utils.IOUtil;
import dev.foolen.aoc2022.commons.utils.StringUtil;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import static dev.foolen.aoc2022.solutions.Day05.CRANE_TYPE.*;

public class Day05 {
    private static List<String> input;
    private static int splitIdx;

    public static void main(String[] args) throws IOException {
        input = IOUtil.readFileAsListOfStrings( "input/day05.txt");
        splitIdx = input.indexOf("");

        solvePartOne();
        solvePartTwo();
    }

    private static void solvePartOne() {
        List<Stack<String>> stacks = getStacksAfterMovementOperations(getInitialStacks(), getMovementOperations(), CRATE_MOVER_9000);
        System.out.printf("[PartOne] message: %s%n", getMessageFromStacks(stacks));
        // - DHBJQJCCW
    }

    private static void solvePartTwo() {
        List<Stack<String>> stacks = getStacksAfterMovementOperations(getInitialStacks(), getMovementOperations(), CRATE_MOVER_9001);
        System.out.printf("[PartTwo] message: %s%n", getMessageFromStacks(stacks));
        // - WJVRLSJJT
    }

    private static String getMessageFromStacks(List<Stack<String>> stacks) {
        return stacks.stream()
            .map(s -> s.isEmpty() ? "" : s.peek())
            .collect(Collectors.joining());
    }

    private static List<String> getMovementOperations() {
        return input.subList(splitIdx+1, input.size());
    }

    private static List<Stack<String>> getInitialStacks() {
        List<Stack<String>> stacks = new ArrayList<>();
        List<String> lines = input.subList(0, input.indexOf(""));

        int nbOfStacks = Integer.parseInt(StringUtil.getLastFromString(lines.get(lines.size()-1)));
        for (int i=0; i<nbOfStacks; i++) stacks.add(new Stack<>());

        for (int i=lines.size()-2; i>=0; i--) {
            String line = lines.get(i);
            for (int j=0; j<line.length(); j++) {
                char c = line.charAt(j);
                if (!Character.isLetter(c)) continue;
                stacks.get(j/4).add(String.valueOf(c));
            }
        }

        return stacks;
    }

    private static List<Stack<String>> getStacksAfterMovementOperations(List<Stack<String>> stacks, List<String> operations, CRANE_TYPE craneType) {
        Pattern pattern = Pattern.compile("move\\s(\\d{1,2})\\sfrom\\s(\\d{1,2})\\sto\\s(\\d{1,2})");

        for (String operation : operations) {
            Matcher matcher = pattern.matcher(operation);

            if (matcher.find()) {
                int nbOfCrates = Integer.parseInt(matcher.group(1));
                Stack<String> from = stacks.get(Integer.parseInt(matcher.group(2)) - 1);
                Stack<String> to = stacks.get(Integer.parseInt(matcher.group(3)) - 1);

                switch (craneType) {
                    case CRATE_MOVER_9000 -> {
                        for (int j = 0; j < nbOfCrates; j++) {
                            to.push(from.pop());
                        }
                    }
                    case CRATE_MOVER_9001 -> {
                        Stack<String> crates = new Stack<>();
                        for (int j = 0; j < nbOfCrates; j++) {
                            crates.push(from.pop());
                        }

                        while (!crates.isEmpty()) {
                            to.push(crates.pop());
                        }
                    }
                }
            }
        }

        return stacks;
    }

    protected enum CRANE_TYPE {
        CRATE_MOVER_9000,
        CRATE_MOVER_9001
    }
}
