import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class Day01Test {

    @Test
    void part1() {
        var left = Arrays.asList(3,4,2,1,3,3);
        var right = Arrays.asList(4,3,5,3,9,3);

        var sum = Day01.Part1(left, right);

        assertEquals(11, sum);
    }

    @Test
    void part2() {
        var left = Arrays.asList(3,4,2,1,3,3);
        var right = Arrays.asList(4,3,5,3,9,3);

        var sum = Day01.Part2(left, right);

        assertEquals(31, sum);
    }
}