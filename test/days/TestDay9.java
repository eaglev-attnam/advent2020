package days;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class TestDay9 {
	private Day9 day9 = new Day9(5);
	
	@Test
	public void testPart1() {
		List<String> input = Arrays.asList(
				"35",
				"20",
				"15",
				"25",
				"47",
				"40",
				"62",
				"55",
				"65",
				"95",
				"102",
				"117",
				"150",
				"182",
				"127",
				"219",
				"299",
				"277",
				"309",
				"576");
		assert Objects.equals(day9.part1(input), 127L);
	}
	
	@Test
	public void testPart2() {
		List<String> input = Arrays.asList(
				"35",
				"20",
				"15",
				"25",
				"47",
				"40",
				"62",
				"55",
				"65",
				"95",
				"102",
				"117",
				"150",
				"182",
				"127",
				"219",
				"299",
				"277",
				"309",
				"576");
		assert Objects.equals(day9.part2(input), 62L);
	}
}
