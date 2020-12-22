package days;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class TestDay22 {
	private Day22 day22 = new Day22();
	
	@Test
	public void testPart1() throws InterruptedException {
		List<String> input = Arrays.asList(
				"Player 1:",
				"9",
				"2",
				"6",
				"3",
				"1",
				"",
				"Player 2:",
				"5",
				"8",
				"4",
				"7",
				"10");
		assert Objects.equals(day22.part1(input), 306);
	}
	
	@Test
	public void testPart2() {
		List<String> input = Arrays.asList(
				"Player 1:",
				"9",
				"2",
				"6",
				"3",
				"1",
				"",
				"Player 2:",
				"5",
				"8",
				"4",
				"7",
				"10");
		assert Objects.equals(day22.part2(input), 291);
	}
}
