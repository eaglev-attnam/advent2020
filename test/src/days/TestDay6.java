package days;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Objects;

public class TestDay6 {
    private Day6 day6 = new Day6();

    @Test
    public void testPart1() {
        Object result = day6.part1(Arrays.asList("COM)B","B)C","C)D","D)E","E)F","B)G","G)H","D)I","E)J","J)K","K)L"));
        assert Objects.equals(result, 42);
    }

    @Test
    public void testPart2() {
        Object result = day6.part2(Arrays.asList("COM)B","B)C","C)D","D)E","E)F","B)G","G)H","D)I","E)J","J)K","K)L", "K)YOU", "I)SAN"));
        assert Objects.equals(result, 4);
    }
}
