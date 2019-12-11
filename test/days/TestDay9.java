package days;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

import common.IntComputer;

public class TestDay9 {
	
	@Test
	public void testPart1() {
		IntComputer c = new IntComputer();
		c.runProgram(Arrays.asList(109,1,204,-1,1001,100,1,100,1008,100,16,101,1006,101,0,99));
		assert c.getOutput().size() == 16;
		assert c.getOutput().get(0) == 109L;
		assert c.getOutput().get(1) == 1L;
		assert c.getOutput().get(2) == 204L;
		assert c.getOutput().get(3) == -1L;
		assert c.getOutput().get(4) == 1001L;
		assert c.getOutput().get(5) == 100L;
		assert c.getOutput().get(6) == 1L;
		assert c.getOutput().get(7) == 100L;
		assert c.getOutput().get(8) == 1008L;
		assert c.getOutput().get(9) == 100L;
		assert c.getOutput().get(10) == 16L;
		assert c.getOutput().get(11) == 101L;
		assert c.getOutput().get(12) == 1006L;
		assert c.getOutput().get(13) == 101L;
		assert c.getOutput().get(14) == 0L;
		assert c.getOutput().get(15) == 99L;
		
		c.runProgram(Arrays.asList(1102,34915192,34915192,7,4,7,99,0));
		assert c.getOutput().size() == 1;
		assert c.getOutput().get(0) >= 1_000_000_000_000_000L;
		assert c.getOutput().get(0) < 10_000_000_000_000_000L;
		
		c.runLongProgram(Arrays.asList(104L,1_125_899_906_842_624L,99L));
		assert c.getOutput().size() == 1;
		assert c.getOutput().get(0).equals(1_125_899_906_842_624L);
	}
	
	@Test
	public void testPart2() {
		
	}
}
