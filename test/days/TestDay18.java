import days.Day16;
import days.Day18;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class TestDay18 {
	Day18 day18 = new Day18();

    @Test
    public void testPart1() {
        List<String> input = Arrays.asList(
                "#########",
                "#b.A.@.a#",
                "#########");
        assert day18.part1(input, "ab").equals(8);
        input = Arrays.asList(
                "########################",
                "#f.D.E.e.C.b.A.@.a.B.c.#",
                "######################.#",
                "#d.....................#",
                "########################");
        System.out.println(day18.part1(input, "abcdef"));
        assert day18.part1(input, "abcdef").equals(86);
    }

    @Test
    public void testPart2() {
    }
}