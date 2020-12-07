package days;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import org.junit.jupiter.api.Test;

public class TestDay6 {
	private Day6 day6 = new Day6();
	
	@Test
	public void testPart1() {
		List<String> input = Arrays.asList(
				"abc",
				"",
				"a",
				"b",
				"c",
				"",
				"ab",
				"ac",
				"",
				"a",
				"a",
				"a",
				"a",
				"",
				"b");
		assert Objects.equals(day6.part1(input), 11);
	}
	
	@Test
	public void testPart2() {
		List<String> input = Arrays.asList(
				"abc",
				"",
				"a",
				"b",
				"c",
				"",
				"ab",
				"ac",
				"",
				"a",
				"a",
				"a",
				"a",
				"",
				"b");
		assert Objects.equals(day6.part2(input), 6);
	}
}
