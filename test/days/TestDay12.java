package days;

import days.Day12;
import org.junit.jupiter.api.Test;

import common.AdventMath;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class TestDay12 {
	Day12 day12 = new Day12();

    @Test
    public void testPart1() {
        Day12.Moon m1 = new Day12.Moon(-1, 0, 2);
        Day12.Moon m2 = new Day12.Moon(2, -10, -7);
        Day12.Moon m3 = new Day12.Moon(4, -8, 8);
        Day12.Moon m4 = new Day12.Moon(3, 5, -1);

        assert day12.energyAfter(Arrays.asList(m1, m2, m3, m4), 10).equals(179);
        
        m1 = new Day12.Moon(-8, -10, 0);
        m2 = new Day12.Moon(5, 5, 10);
        m3 = new Day12.Moon(2, -7, 3);
        m4 = new Day12.Moon(9, -8, -3);

        assert day12.energyAfter(Arrays.asList(m1, m2, m3, m4), 100).equals(1940);
    }

    @Test
    public void testPart2() {
    	assert AdventMath.lcm(18, 28) == 252;
    	
        List<String> input = Arrays.asList(
            "<x=-1, y=0, z=2>",
            "<x=2, y=-10, z=-7>",
            "<x=4, y=-8, z=8>",
            "<x=3, y=5, z=-1>"
        );

        assert Objects.equals(day12.part2(input), 2772L);
    	
        input = Arrays.asList(
            "<x=-8, y=-10, z=0>",
            "<x=5, y=5, z=10>",
            "<x=2, y=-7, z=3>",
            "<x=9, y=-8, z=-3>"
        );

        assert Objects.equals(day12.part2(input), 4686774924L);
    }
}