package days;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class TestDay17 {
	private Day17 day17 = new Day17();
	
	@Test
	public void testPart1() {
		List<String> input = Arrays.asList(
				".#.",
				"..#",
				"###");
		assert Objects.equals(day17.part1(input), 112);
	}
	
	@Test
	public void testPart2() {
		List<String> input = Arrays.asList(
				".#.",
				"..#",
				"###");
		assert Objects.equals(day17.part2(input), 848);
	}
}
