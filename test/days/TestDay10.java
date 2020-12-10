package days;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class TestDay10 {
	private Day10 day10 = new Day10();
	
	@Test
	public void testPart1() {
		List<String> input = Arrays.asList(
				"16",
				"10",
				"15",
				"5",
				"1",
				"11",
				"7",
				"19",
				"6",
				"12",
				"4");
		assert Objects.equals(day10.part1(input), 35);
		input = Arrays.asList(
				"28",
				"33",
				"18",
				"42",
				"31",
				"14",
				"46",
				"20",
				"48",
				"47",
				"24",
				"23",
				"49",
				"45",
				"19",
				"38",
				"39",
				"11",
				"1",
				"32",
				"25",
				"35",
				"8",
				"17",
				"7",
				"9",
				"4",
				"2",
				"34",
				"10",
				"3");
		assert Objects.equals(day10.part1(input), 220);
	}
	
	@Test
	public void testPart2() {
		List<String> input = Arrays.asList(
				"16",
				"10",
				"15",
				"5",
				"1",
				"11",
				"7",
				"19",
				"6",
				"12",
				"4");
		assert Objects.equals(day10.part2(input), 8L);
		input = Arrays.asList(
				"28",
				"33",
				"18",
				"42",
				"31",
				"14",
				"46",
				"20",
				"48",
				"47",
				"24",
				"23",
				"49",
				"45",
				"19",
				"38",
				"39",
				"11",
				"1",
				"32",
				"25",
				"35",
				"8",
				"17",
				"7",
				"9",
				"4",
				"2",
				"34",
				"10",
				"3");
		assert Objects.equals(day10.part2(input), 19208L);
	}
}
