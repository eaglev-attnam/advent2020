package days;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import org.junit.jupiter.api.Test;

public class TestDay3 {
	private Day3 day3 = new Day3();
	
	@Test
	public void testPart1() {
		List<String> input = Arrays.asList(
				"..##.......", 
				"#...#...#..", 
				".#....#..#.", 
				"..#.#...#.#", 
				".#...##..#.", 
				"..#.##.....", 
				".#.#.#....#", 
				".#........#", 
				"#.##...#...", 
				"#...##....#", 
				".#..#...#.#");
		assert Objects.equals(day3.part1(input), 7);
	}
	
	@Test
	public void testPart2() {
		List<String> input = Arrays.asList(
				"..##.......", 
				"#...#...#..", 
				".#....#..#.", 
				"..#.#...#.#", 
				".#...##..#.", 
				"..#.##.....", 
				".#.#.#....#", 
				".#........#", 
				"#.##...#...", 
				"#...##....#", 
				".#..#...#.#");
		assert Objects.equals(day3.part2(input), 336);
	}
}
