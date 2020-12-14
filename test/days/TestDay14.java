package days;

import org.junit.jupiter.api.Test;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class TestDay14 {
	private Day14 day14 = new Day14();
	
	@Test
	public void testPart1() {
		List<String> input = Arrays.asList(
				"mask = XXXXXXXXXXXXXXXXXXXXXXXXXXXXX1XXXX0X",
				"mem[8] = 11",
				"mem[7] = 101",
				"mem[8] = 0");
		assert Objects.equals(day14.part1(input), 165L);
	}
	
	@Test
	public void testPart2() {
		List<String> input = Arrays.asList(
				"mask = 000000000000000000000000000000X1001X",
				"mem[42] = 100",
				"mask = 00000000000000000000000000000000X0XX",
				"mem[26] = 1");
		assert Objects.equals(day14.part2(input), 208L);
	}
}
