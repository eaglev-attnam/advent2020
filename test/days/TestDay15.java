package days;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class TestDay15 {
	private Day15 day15 = new Day15();
	
	@Test
	public void testPart1() {
		List<String> input = Arrays.asList(
				"0,3,6");
		assert Objects.equals(day15.part1(input), 436);
		input = Arrays.asList(
				"1,3,2");
		assert Objects.equals(day15.part1(input), 1);
		input = Arrays.asList(
				"2,1,3");
		assert Objects.equals(day15.part1(input), 10);
		input = Arrays.asList(
				"1,2,3");
		assert Objects.equals(day15.part1(input), 27);
		input = Arrays.asList(
				"2,3,1");
		assert Objects.equals(day15.part1(input), 78);
		input = Arrays.asList(
				"3,2,1");
		assert Objects.equals(day15.part1(input), 438);
		input = Arrays.asList(
				"3,1,2");
		assert Objects.equals(day15.part1(input), 1836);
	}
	
	@Test
	public void testPart2() {
		List<String> input = Arrays.asList(
				"0,3,6");
		assert Objects.equals(day15.part2(input), 175594);
		input = Arrays.asList(
				"1,3,2");
		assert Objects.equals(day15.part2(input), 2578);
		input = Arrays.asList(
				"2,1,3");
		assert Objects.equals(day15.part2(input), 3544142);
		input = Arrays.asList(
				"1,2,3");
		assert Objects.equals(day15.part2(input), 261214);
		input = Arrays.asList(
				"2,3,1");
		assert Objects.equals(day15.part2(input), 6895259);
		input = Arrays.asList(
				"3,2,1");
		assert Objects.equals(day15.part2(input), 18);
		input = Arrays.asList(
				"3,1,2");
		assert Objects.equals(day15.part2(input), 362);
	}
}
