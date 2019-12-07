package days;

import org.junit.jupiter.api.Test;

import common.IntComputer;

import java.util.Arrays;
import java.util.List;

public class TestDay5 {
    private Day5 day5 = new Day5();
	private IntComputer computer = new IntComputer();

    @Test
    public void testPart1() {
        List<Integer> program = Arrays.asList(1002,4,3,4,33);
        computer.addInput(1);
        computer.runProgram(program);
        assert computer.getMemoryAt(4).equals(99);

        program = Arrays.asList(1101,100,-1,4,0);
        computer.addInput(1);
        computer.runProgram(program);
        assert computer.getMemoryAt(4).equals(99);
    }

    @Test
    public void testPart2() {
        List<Integer> program = Arrays.asList(3,9,8,9,10,9,4,9,99,-1,8);
        assert day5.runProgram(program, 8).endsWith("1\nEND\n");
        program = Arrays.asList(3,9,8,9,10,9,4,9,99,-1,8);
        assert day5.runProgram(program, 7).endsWith("0\nEND\n");

        program = Arrays.asList(3,9,7,9,10,9,4,9,99,-1,8);
        assert day5.runProgram(program, 8).endsWith("0\nEND\n");
        program = Arrays.asList(3,9,7,9,10,9,4,9,99,-1,8);
        assert day5.runProgram(program, 7).endsWith("1\nEND\n");

        program = Arrays.asList(3,3,1108,-1,8,3,4,3,99);
        assert day5.runProgram(program, 8).endsWith("1\nEND\n");
        program = Arrays.asList(3,3,1108,-1,8,3,4,3,99);
        assert day5.runProgram(program, 7).endsWith("0\nEND\n");

        program = Arrays.asList(3,3,1107,-1,8,3,4,3,99);
        assert day5.runProgram(program, 8).endsWith("0\nEND\n");
        program = Arrays.asList(3,3,1107,-1,8,3,4,3,99);
        assert day5.runProgram(program, 7).endsWith("1\nEND\n");

        program = Arrays.asList(3,12,6,12,15,1,13,14,13,4,13,99,-1,0,1,9);
        assert day5.runProgram(program, 0).endsWith("0\nEND\n");
        program = Arrays.asList(3,12,6,12,15,1,13,14,13,4,13,99,-1,0,1,9);
        assert day5.runProgram(program, 2).endsWith("1\nEND\n");

        program = Arrays.asList(3,3,1105,-1,9,1101,0,0,12,4,12,99,1);
        assert day5.runProgram(program, 0).endsWith("0\nEND\n");
        program = Arrays.asList(3,3,1105,-1,9,1101,0,0,12,4,12,99,1);
        assert day5.runProgram(program, 2).endsWith("1\nEND\n");

        program = Arrays.asList(3,21,1008,21,8,20,1005,20,22,107,8,21,20,1006,20,31,
                1106,0,36,98,0,0,1002,21,125,20,4,20,1105,1,46,104,
                999,1105,1,46,1101,1000,1,20,4,20,1105,1,46,98,99);
        assert day5.runProgram(program, 7).endsWith("999\nEND\n");
        program = Arrays.asList(3,21,1008,21,8,20,1005,20,22,107,8,21,20,1006,20,31,
                1106,0,36,98,0,0,1002,21,125,20,4,20,1105,1,46,104,
                999,1105,1,46,1101,1000,1,20,4,20,1105,1,46,98,99);
        assert day5.runProgram(program, 8).endsWith("1000\nEND\n");
        program = Arrays.asList(3,21,1008,21,8,20,1005,20,22,107,8,21,20,1006,20,31,
                1106,0,36,98,0,0,1002,21,125,20,4,20,1105,1,46,104,
                999,1105,1,46,1101,1000,1,20,4,20,1105,1,46,98,99);
        assert day5.runProgram(program, 9).endsWith("1001\nEND\n");
    }
}
