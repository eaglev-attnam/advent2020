package days;

import common.IntComputer;

import java.util.Arrays;
import java.util.List;

public class Day17 extends Day {

	@Override
	protected int getChallengeNumber() {
		return 17;
	}
	
	@Override
	protected Object part1(List<String> input) {
		IntComputer computer = new IntComputer();
		computer.runCommaSeperatedStringProgram(input.get(0));
		String[] array = computer.getOutput().stream().map(code -> ((char) code.intValue()) + "").reduce("", (a,b) -> a + b).split("\n");
		int sum = 0;
		for(int x = 1; x < array[0].length() - 1; x++) {
			for(int y = 1; y < array.length - 1; y++) {
				if(array[y].charAt(x) == '#'
						&& array[y + 1].charAt(x) == '#'
						&& array[y - 1].charAt(x) == '#'
						&& array[y].charAt(x + 1) == '#'
						&& array[y].charAt(x - 1) == '#') {
					sum += x * y;
				}
			}
		}
		return sum;
	}

	@Override
	protected Object part2(List<String> input) {
		IntComputer computer = new IntComputer();
		String program = input.get(0);
		program = "2" + program.substring(1);
		computer.runCommaSeperatedStringProgram(program);

		// MAX LENGTH: 01234567890123456789
		String main = "A,B,A,C,B,A,C,B,A,C";
		String prgA = "L,12,L,12,L,6,L,6";
		String prgB = "R,8,R,4,L,12";
		String prgC = "L,12,L,6,R,12,R,8";

		String movement = main + "\n" + prgA + "\n" + prgB + "\n" + prgC + "\nn\n";
		for(char c : movement.toCharArray()) {
			computer.addInput(c);
		}
		return computer.getOutput().get(computer.getOutput().size() - 1);
	}
}
