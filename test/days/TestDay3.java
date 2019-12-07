package days;

import java.io.IOException;
import java.util.Arrays;
import java.util.Objects;

import org.junit.jupiter.api.Test;

public class TestDay3 {
	private Day3 day3 = new Day3();
	
	@Test
	public void testPart1() throws IOException {
		assert Objects.equals(day3.part1(Arrays.asList("R8,U5,L5,D3","U7,R6,D4,L4")), 6);
		assert Objects.equals(day3.part1(Arrays.asList("R75,D30,R83,U83,L12,D49,R71,U7,L72","U62,R66,U55,R34,D71,R55,D58,R83")), 159);
		assert Objects.equals(day3.part1(Arrays.asList("R98,U47,R26,D63,R33,U87,L62,D20,R33,U53,R51","U98,R91,D20,R16,D67,R40,U7,R15,U6,R7")), 135);
	}
	
	@Test
	public void testPart2() {
		assert Objects.equals(day3.part2(Arrays.asList("R8,U5,L5,D3","U7,R6,D4,L4")), 30);
		assert Objects.equals(day3.part2(Arrays.asList("R75,D30,R83,U83,L12,D49,R71,U7,L72","U62,R66,U55,R34,D71,R55,D58,R83")), 610);
		assert Objects.equals(day3.part2(Arrays.asList("R98,U47,R26,D63,R33,U87,L62,D20,R33,U53,R51","U98,R91,D20,R16,D67,R40,U7,R15,U6,R7")), 410);
	}
}
