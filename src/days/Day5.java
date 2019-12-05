package days;

import java.util.List;
import java.util.stream.Collectors;

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
		int pointer = 0;
		int opcode = program.get(pointer);
		StringBuilder output = new StringBuilder();
		while(opcode % 100 != 99) {
			System.out.println(opcode);
			boolean pos1 = (opcode / 100) % 2 == 0;
			boolean pos2 = (opcode / 1000) % 2 == 0;
			System.out.println(opcode % 100 + " - pos1: " + pos1 + " - pos2: " + pos2);
			opcode %= 100;
			if(opcode == 1) {
				program.set(program.get(pointer + 3), get(program, pointer + 1, pos1) + get(program, pointer + 2, pos2));
				pointer += 4;
			} else if(opcode == 2){
				program.set(program.get(pointer + 3), get(program, pointer + 1, pos1) * get(program, pointer + 2, pos2));
				pointer += 4;
			} else if(opcode == 3){
				program.set(program.get(pointer + 1), input);
				pointer += 2;
			} else if(opcode == 4){
				output.append("At " + pointer + ": " + get(program, pointer + 1, pos1) + "\n");
				pointer += 2;
			} else if(opcode == 5){
				if(get(program, pointer+1, pos1) == 0) {
					pointer += 3;
				} else {
					pointer = get(program, pointer+2, pos2);
				}
			} else if(opcode == 6){
				if(get(program, pointer+1, pos1) == 0) {
					pointer = get(program, pointer+2, pos2);
				} else {
					pointer += 3;
				}
			} else if(opcode == 7){
				boolean lt = get(program, pointer+1, pos1) < get(program, pointer+2, pos2);
				program.set(program.get(pointer + 3), lt ? 1 : 0);
				pointer += 4;
			} else if(opcode == 8){
				boolean eq = get(program, pointer+1, pos1).equals(get(program, pointer+2, pos2));
				program.set(program.get(pointer + 3), eq ? 1 : 0);
				pointer += 4;
			} else {
				throw new IllegalArgumentException("Illegal opcode " + opcode);
			}
			opcode = program.get(pointer);
		}
		return output.toString();
	}

	private Integer get(List<Integer> program, int i, boolean positional) {
		if(positional) {
			i = program.get(i);
		}
		return program.get(i);
	}
}
