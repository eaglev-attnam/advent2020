package days;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class TestDay23 {
	private Day23 day23 = new Day23();
	
	@Test
	public void testPart1() throws InterruptedException {
		List<String> input = Arrays.asList(
				"389125467",
				"10");
		assert Objects.equals(day23.part1(input), "92658374");
		input = Arrays.asList(
				"389125467",
				"100");
		assert Objects.equals(day23.part1(input), "67384529");
	}
	
	@Test
	public void testPart2() {
		List<String> input = Arrays.asList(
				"389125467",
				"100");
		assert Objects.equals(day23.part2(input), 149245887792L);
	}
}
