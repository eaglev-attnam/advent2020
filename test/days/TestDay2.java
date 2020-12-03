package days;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import org.junit.jupiter.api.Test;

public class TestDay2 {
	private Day2 day2 = new Day2();
	
	@Test
	public void testPart1() {
		List<String> input = Arrays.asList(
				"1-3 a: abcde", 
				"1-3 b: cdefg", 
				"2-9 c: ccccccccc");
		assert Objects.equals(day2.part1(input), 2);
	}
	
	@Test
	public void testPart2() {
		List<String> input = Arrays.asList(
				"1-3 a: abcde", 
				"1-3 b: cdefg", 
				"2-9 c: ccccccccc");
		assert Objects.equals(day2.part2(input), 1);
	}
}
