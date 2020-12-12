package days;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import org.junit.jupiter.api.Test;

public class TestDay12 {
	private Day12 day12 = new Day12();
	
	@Test
	public void testPart1() {
		List<String> input = Arrays.asList(
				"F10",
				"N3",
				"F7",
				"R90",
				"F11");
		assert Objects.equals(day12.part1(input), 25);
	}
	
	@Test
	public void testPart2() {
		List<String> input = Arrays.asList(
				"F10",
				"N3",
				"F7",
				"R90",
				"F11");
		assert Objects.equals(day12.part2(input), 286);
	}
}
