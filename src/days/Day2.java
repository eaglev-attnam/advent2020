package days;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import common.IntComputer;

public class Day2 extends Day {

	@Override
	protected int getChallengeNumber() {
		return 2;
	}
	
	@Override
	protected Object part1(List<String> input) {
		List<Integer> program = input
				.stream()
				.map(Integer::parseInt)
				.collect(Collectors.toList());
		program.set(1, 12);
		program.set(2, 2);
		return runProgram(program);
	}

	@Override
	protected Object part2(List<String> input) {
		List<Integer> original = input
				.stream()
				.map(Integer::parseInt)
				.collect(Collectors.toList());
		int target = 19690720;
		for(int i = 0; i < 1000; i++) {
			for(int j = -i; j < i; j++) {
				if(attempt(original, i, j) == target) {
					return 100 * i + j;
				}
				if(attempt(original, -i, j) == target) {
					return 100 * -i + j;
				}
			}
		}
		return "nope";
	}
	
	private int attempt(List<Integer> original, int i, int j) {
		List<Integer> program = new ArrayList<>();
		program.addAll(original);
		program.set(1, i);
		program.set(2, j);
		try {
			return runProgram(program);
		} catch(ArrayIndexOutOfBoundsException | IllegalArgumentException e) {
			return -1;
		}
	}
	
	int runProgram(List<Integer> program) {
		IntComputer computer = new IntComputer();
		computer.runProgram(program);
		return computer.getMemoryAt(0);
	}
}
