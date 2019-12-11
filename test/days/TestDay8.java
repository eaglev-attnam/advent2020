package days;

import java.util.Arrays;
import java.util.Objects;

import org.junit.jupiter.api.Test;

public class TestDay8 {
	private Day8 day8 = new Day8();

	// No test cases given for part 1
	
	@Test
	public void testPart2() {
		day8.HEIGHT = 2;
		day8.WIDTH = 3;
		Character[][] result = (Character[][]) day8.part2(Arrays.asList("022222112222222122000000"));
		for(int y = 0; y < day8.HEIGHT; y++) {
			for(int x = 0; x < day8.WIDTH; x++) {
				System.out.print(result[x][y]);
			}
			System.out.println();
		}
		assert Objects.deepEquals(day8.part2(Arrays.asList("022222112222222122000000")), new Character[][] {{'0','1'},{'1','0'},{'0','0'}});
	}
}
