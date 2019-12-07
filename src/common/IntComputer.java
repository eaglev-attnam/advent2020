package common;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class IntComputer {
	private LinkedList<Integer> inputs = new LinkedList<>();
	private List<Integer> memory;
	private List<Integer> output = new ArrayList<>();
	private boolean paused = false;
	private int pointer = 0;
	
	public void runProgram(List<Integer> program) {
		memory = new ArrayList<>();
		memory.addAll(program);
		output = new ArrayList<>();
		doRun();
	}
	
	public void addInput(int input) {
		inputs.add(input);
		if(paused) {
			doRun();
		}
	}
	
	public boolean isPaused() {
		return paused;
	}
	
	public Integer getMemoryAt(int pos) {
		return memory.get(pos);
	}
	
	public List<Integer> getOutput() {
		return output;
	}
	
	private void doRun() {
		paused = false;
		int opcode = memory.get(pointer);
		while(opcode % 100 != 99) {
			boolean pos1 = (opcode / 100) % 2 == 0;
			boolean pos2 = (opcode / 1000) % 2 == 0;
			opcode %= 100;
			if(opcode == 1) {
				memory.set(memory.get(pointer + 3), get(pointer + 1, pos1) + get(pointer + 2, pos2));
				pointer += 4;
			} else if(opcode == 2){
				memory.set(memory.get(pointer + 3), get(pointer + 1, pos1) * get(pointer + 2, pos2));
				pointer += 4;
			} else if(opcode == 3){
				if(inputs.isEmpty()) {
					paused = true;
					return;
				}
				memory.set(memory.get(pointer + 1), inputs.pop());
				pointer += 2;
			} else if(opcode == 4){
				output.add(get(pointer + 1, pos1));
				pointer += 2;
			} else if(opcode == 5){
				if(get(pointer+1, pos1) == 0) {
					pointer += 3;
				} else {
					pointer = get(pointer+2, pos2);
				}
			} else if(opcode == 6){
				if(get(pointer+1, pos1) == 0) {
					pointer = get(pointer+2, pos2);
				} else {
					pointer += 3;
				}
			} else if(opcode == 7){
				boolean lt = get(pointer+1, pos1) < get(pointer+2, pos2);
				memory.set(memory.get(pointer + 3), lt ? 1 : 0);
				pointer += 4;
			} else if(opcode == 8){
				boolean eq = get(pointer+1, pos1).equals(get(pointer+2, pos2));
				memory.set(memory.get(pointer + 3), eq ? 1 : 0);
				pointer += 4;
			} else {
				throw new IllegalArgumentException("Illegal opcode " + opcode);
			}
			opcode = memory.get(pointer);
		}
		inputs.clear();
		pointer = 0;
	}

	private Integer get(int i, boolean positional) {
		if(positional) {
			i = memory.get(i);
		}
		return memory.get(i);
	}
}
