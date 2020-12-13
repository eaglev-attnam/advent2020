package days;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import org.junit.jupiter.api.Test;

public class TestDay13 {
	private Day13 day13 = new Day13();
	
	@Test
	public void testPart1() {
		List<String> input = Arrays.asList(
				"939",
				"7,13,x,x,59,x,31,19");
		assert Objects.equals(day13.part1(input), 295);
	}
	
	@Test
	public void testPart2() {
		List<String> input = Arrays.asList(
				"939",
				"3,4,x,x,x,x,5");
		assert Objects.equals(day13.part2(input), BigInteger.valueOf(39L));
		input = Arrays.asList(
				"939",
				"7,13,x,x,59,x,31,19");
		assert Objects.equals(day13.part2(input), BigInteger.valueOf(1068781L));
		input = Arrays.asList(
				"939",
				"17,x,13,19");
		assert Objects.equals(day13.part2(input), BigInteger.valueOf(3417L));
		input = Arrays.asList(
				"939",
				"67,7,59,61");
		assert Objects.equals(day13.part2(input), BigInteger.valueOf(754018L));
		input = Arrays.asList(
				"939",
				"67,x,7,59,61");
		assert Objects.equals(day13.part2(input), BigInteger.valueOf(779210L));
		input = Arrays.asList(
				"939",
				"67,7,x,59,61");
		assert Objects.equals(day13.part2(input), BigInteger.valueOf(1261476L));
		input = Arrays.asList(
				"939",
				"1789,37,47,1889");
		assert Objects.equals(day13.part2(input), BigInteger.valueOf(1202161486L));
	}
}
