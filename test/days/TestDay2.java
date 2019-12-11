package days;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import org.junit.jupiter.api.Test;

import common.IntComputer;

public class TestDay2 {
	private Day2 day2 = new Day2();
	private IntComputer computer = new IntComputer();
	
	@Test
	public void testPart1() {
		assert Objects.equals(day2.runProgram(Arrays.asList(1,9,10,3,2,3,11,0,99,30,40,50)), 3500L);
		assert Objects.equals(day2.runProgram(Arrays.asList(1,1,1,4,99,5,6,0,99)), 30L);
		List<Integer> program = Arrays.asList(2,3,0,3,99);
		computer.runProgram(program);
		assert Objects.equals(computer.getMemoryAt(3), 6L);
		program = Arrays.asList(2,4,4,5,99,0);
		computer.runProgram(program);
		assert Objects.equals(computer.getMemoryAt(5), 9801L);
	}
	
	// No test cases given for part 2
}
