package days;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import org.junit.jupiter.api.Test;

public class TestDay1 {
	private Day1 day1 = new Day1();
	
	@Test
	public void testPart1() {
		List<String> input = Arrays.asList(
				"1721",
				"979",
				"366",
				"299",
				"675",
				"1456");
		assert Objects.equals(day1.part1(input), 514579);
	}
	
	@Test
	public void testPart2() {
		List<String> input = Arrays.asList(
				"1721",
				"979",
				"366",
				"299",
				"675",
				"1456");
		assert Objects.equals(day1.part2(input), 241861950);
	}
}
