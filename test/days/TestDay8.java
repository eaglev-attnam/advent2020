package days;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class TestDay8 {
	private Day8 day8 = new Day8();
	
	@Test
	public void testPart1() {
		List<String> input = Arrays.asList(
				"nop +0",
				"acc +1",
				"jmp +4",
				"acc +3",
				"jmp -3",
				"acc -99",
				"acc +1",
				"jmp -4",
				"acc +6");
		assert Objects.equals(day8.part1(input), 5L);
	}
	
	@Test
	public void testPart2() {
		List<String> input = Arrays.asList(
				"nop +0",
				"acc +1",
				"jmp +4",
				"acc +3",
				"jmp -3",
				"acc -99",
				"acc +1",
				"jmp -4",
				"acc +6");
		assert Objects.equals(day8.part2(input), 8L);
	}
}
