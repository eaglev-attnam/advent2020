package days;

import java.util.Arrays;
import java.util.Objects;

import org.junit.jupiter.api.Test;

public class TestDay1 {
	private Day1 day1 = new Day1();
	
	@Test
	public void testPart1() {
		assert Objects.equals(day1.part1(Arrays.asList("12")), 2);
		assert Objects.equals(day1.part1(Arrays.asList("14")), 2);
		assert Objects.equals(day1.part1(Arrays.asList("1969")), 654);
		assert Objects.equals(day1.part1(Arrays.asList("100756")), 33583);
	}
	
	@Test
	public void testPart2() {
		assert Objects.equals(day1.part2(Arrays.asList("14")), 2);
		assert Objects.equals(day1.part2(Arrays.asList("1969")), 966);
		assert Objects.equals(day1.part2(Arrays.asList("100756")), 50346);
	}
}
