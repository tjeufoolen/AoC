import lib.InputUtil;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Day01 {
    public void main() throws IOException {
        var input = InputUtil.readLines("input/day01a.txt");

        List<Integer> left = new ArrayList<>();
        List<Integer> right = new ArrayList<>();

        for (var line : input) {
            var locations = line.split(" {3}");
            left.add(Integer.parseInt(locations[0]));
            right.add(Integer.parseInt(locations[1]));
        }

        System.out.println(STR."The answer for part 1 is: \{Part1(left, right)}");
        System.out.println(STR."The answer for part 2 is: \{Part2(left, right)}");
    }

    public static Integer Part1(List<Integer> left, List<Integer> right) {
        left.sort(null);
        right.sort(null);

        var sum = 0;
        for (var i=0; i<left.size(); i++) {
            sum += Math.abs(left.get(i) - right.get(i));
        }
        return sum;
    }

    public static Integer Part2(List<Integer> left, List<Integer> right) {
        var sum = 0;
        for (Integer leftValue : left) {
            var occurrences = right.stream().filter(x -> Objects.equals(x, leftValue));
            sum += leftValue * Math.toIntExact(occurrences.count());
        }
        return sum;
    }
}