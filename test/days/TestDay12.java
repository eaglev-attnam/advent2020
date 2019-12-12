import days.Day12;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

public class TestDay12 {
	Day12 day12 = new Day12();

    @Test
    public void testPart1() {
        Day12.Moon m1 = new Day12.Moon(-1,0,2);
        Day12.Moon m2 = new Day12.Moon(2, -10, -7);
        Day12.Moon m3 = new Day12.Moon(4, -8, 8);
        Day12.Moon m4 = new Day12.Moon(3, 5, -1);

        assert day12.energyAfter(Arrays.asList(m1, m2, m3, m4), 10).equals(179);
    }

    @Test
    public void testPart2() {
        List<String> input = Arrays.asList(
            "<x=-1, y=0, z=2>",
            "<x=2, y=-10, z=-7>",
            "<x=4, y=-8, z=8>",
            "<x=3, y=5, z=-1>"
        );

        System.out.println(day12.part2(input));
    }
}