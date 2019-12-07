package days;

import java.util.List;
import java.util.stream.Collectors;

import common.IntComputer;

public class Day5 extends Day {

	@Override
	protected int getChallengeNumber() {
		return 5;
	}
	
	@Override
	protected Object part1(List<String> input) {
		return runProgram(input.stream()
				.map(Integer::parseInt)
				.collect(Collectors.toList()), 1);
	}

	@Override
	protected Object part2(List<String> input) {
		return runProgram(input.stream()
				.map(Integer::parseInt)
				.collect(Collectors.toList()), 5);
	}

	String runProgram(List<Integer> program, int input) {
		IntComputer computer = new IntComputer();
		computer.addInput(input);
		computer.runProgram(program);
		return "START\n" + String.join("\n", computer.getOutput().stream().map(i -> i.toString()).collect(Collectors.toList())) + "\nEND\n";
	}
}
