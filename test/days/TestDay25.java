package days;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class TestDay25 {
	private Day25 day25 = new Day25();
	
	@Test
	public void testPart1() {
		List<String> input = Arrays.asList(
				"5764801",
				"17807724");
		assert Objects.equals(day25.part1(input), 14897079L);
	}
}
