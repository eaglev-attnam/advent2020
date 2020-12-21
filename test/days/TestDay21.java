package days;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class TestDay21 {
	private Day21 day21 = new Day21();
	
	@Test
	public void testPart1() throws InterruptedException {
		List<String> input = Arrays.asList(
				"mxmxvkd kfcds sqjhc nhms (contains dairy, fish)",
				"trh fvjkl sbzzf mxmxvkd (contains dairy)",
				"sqjhc fvjkl (contains soy)",
				"sqjhc mxmxvkd sbzzf (contains fish)");
		assert Objects.equals(day21.part1(input), 5);
	}
	
	@Test
	public void testPart2() {
		List<String> input = Arrays.asList(
				"mxmxvkd kfcds sqjhc nhms (contains dairy, fish)",
				"trh fvjkl sbzzf mxmxvkd (contains dairy)",
				"sqjhc fvjkl (contains soy)",
				"sqjhc mxmxvkd sbzzf (contains fish)");
		assert Objects.equals(day21.part2(input), "mxmxvkd,sqjhc,fvjkl");
	}
}
