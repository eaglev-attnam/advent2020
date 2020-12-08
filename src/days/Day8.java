package days;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Day8 extends Day {

	@Override
	protected int getChallengeNumber() {
		return 8;
	}
	
	@Override
	protected Object part1(List<String> input) {
		return run(input).acc;
	}

	private State run(List<String> input) {
		Set<Integer> done = new HashSet<>();
		int pointer = 0;
		long acc = 0;
		while(!done.contains(pointer) && pointer < input.size()) {
			done.add(pointer);
			String[] op = input.get(pointer).split(" ");
			String opcode = op[0];
			int value = Integer.parseInt(op[1]);
			if ("jmp".equals(opcode)) {
				pointer += value;
			} else if ("acc".equals(opcode)) {
				acc += value;
				pointer++;
			} else {
				pointer++;
			}
		}
		return new State(acc, pointer);
	}

	@Override
	protected Object part2(List<String> input) {
		for(int i = 0; i < input.size(); i++) {
			if(!input.get(i).startsWith("acc")) {
				List<String> modified = new ArrayList<>(input);
				String op = input.get(i);
				if(op.startsWith("jmp")) {
					op = op.replace("jmp", "nop");
				} else {
					op = op.replace("nop", "jmp");
				}
				modified.set(i, op);
				State finished = run(modified);
				if(finished.pointer >= input.size()) {
					return finished.acc;
				}
			}
		}
		return null;
	}

	private class State {
		long acc;
		int pointer;

		private State(long acc, int pointer) {
			this.acc = acc;
			this.pointer = pointer;
		}
	}
}
