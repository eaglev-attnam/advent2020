package days;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import org.junit.jupiter.api.Test;

public class TestDay5 {
	private Day5 day5 = new Day5();
	
	@Test
	public void testPart1() {
		List<String> input = Arrays.asList(
				"FBFBBFFRLR",
				"BFFFBBFRRR",
				"FFFBBBFRRR",
				"BBFFBBFRLL");
		assert Objects.equals(day5.part1(input), 820);
	}
}
