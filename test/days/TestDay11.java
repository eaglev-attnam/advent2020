package days;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class TestDay11 {
	private Day11 day11 = new Day11();
	
	@Test
	public void testPart1() {
		List<String> input = Arrays.asList(
				"L.LL.LL.LL",
				"LLLLLLL.LL",
				"L.L.L..L..",
				"LLLL.LL.LL",
				"L.LL.LL.LL",
				"L.LLLLL.LL",
				"..L.L.....",
				"LLLLLLLLLL",
				"L.LLLLLL.L",
				"L.LLLLL.LL");
		assert Objects.equals(day11.part1(input), 37);
	}
	
	@Test
	public void testPart2() {
		List<String> input = Arrays.asList(
				"L.LL.LL.LL",
				"LLLLLLL.LL",
				"L.L.L..L..",
				"LLLL.LL.LL",
				"L.LL.LL.LL",
				"L.LLLLL.LL",
				"..L.L.....",
				"LLLLLLLLLL",
				"L.LLLLLL.L",
				"L.LLLLL.LL");
		assert Objects.equals(day11.part2(input), 26);
	}
}
