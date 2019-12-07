package days;

import java.util.Arrays;
import java.util.Collection;
import java.util.Objects;

import org.junit.jupiter.api.Test;

public class TestDay4 {
	private Day4 day4 = new Day4();
	
	@Test
	public void testPart1() {
		Collection<Integer> testrange = day4.getEligible(100_000, 250_000);
		assert testrange.contains(111111);
		assert !testrange.contains(223450);
		assert !testrange.contains(123789);
	}
	
	@Test
	public void testPart2() {
		assert Objects.equals(day4.part2(Arrays.asList("112233", "112233")), 1L);
		assert Objects.equals(day4.part2(Arrays.asList("123444", "123444")), 0L);
		assert Objects.equals(day4.part2(Arrays.asList("111122", "111122")), 1L);
	}
}
