package days;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class TestDay18 {
	private Day18 day18 = new Day18();
	
	@Test
	public void testPart1() {
		List<String> input = Arrays.asList(
				"1 + 2 * 3 + 4 * 5 + 6",
				"1 + (2 * 3) + (4 * (5 + 6))",
				"2 * 3 + (4 * 5)",
				"5 + (8 * 3 + 9 + 3 * 4 * 3)",
				"5 * 9 * (7 * 3 * 3 + 9 * 3 + (8 + 6 * 4))",
				"((2 + 4 * 9) * (6 + 9 * 8 + 6) + 6) + 2 + 4 * 2");
		assert Objects.equals(day18.part1(input), 71+51+26+437+12240+13632L);
	}
	
	@Test
	public void testPart2() {
		List<String> input = Arrays.asList(
				"1 + 2 * 3 + 4 * 5 + 6",
				"1 + (2 * 3) + (4 * (5 + 6))",
				"2 * 3 + (4 * 5)",
				"5 + (8 * 3 + 9 + 3 * 4 * 3)",
				"5 * 9 * (7 * 3 * 3 + 9 * 3 + (8 + 6 * 4))",
				"((2 + 4 * 9) * (6 + 9 * 8 + 6) + 6) + 2 + 4 * 2");
		assert Objects.equals(day18.part2(input), 231+51+46+1445+669060+23340L);
	}
}
