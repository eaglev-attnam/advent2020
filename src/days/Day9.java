package days;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import common.IntComputer;

public class Day9 extends Day {

	@Override
	protected int getChallengeNumber() {
		return 9;
	}
	
	@Override
	protected Object part1(List<String> input) {
		List<Long> program = Arrays.asList(input.get(0).split(",")).stream().map(Long::parseLong).collect(Collectors.toList());
		IntComputer comp = new IntComputer();
		comp.addInput(1);
		comp.runLongProgram(program);
		comp.getOutput().forEach(o -> System.out.println(o));
		return comp.getOutput().size() == 1;
	}

	@Override
	protected Object part2(List<String> input) {
		List<Long> program = Arrays.asList(input.get(0).split(",")).stream().map(Long::parseLong).collect(Collectors.toList());
		IntComputer comp = new IntComputer();
		comp.addInput(2);
		comp.runLongProgram(program);
		return comp.getOutput().get(0);
	}
}
