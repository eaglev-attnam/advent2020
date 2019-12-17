package common;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

public class IntComputer {
	private static final int READ_POSITIONAL = 0;
	private static final int READ_DIRECT     = 1;
	private static final int READ_RELATIVE   = 2;

	private static final int OP_ADD = 1;
	private static final int OP_MUL = 2;
	private static final int OP_IN  = 3;
	private static final int OP_OUT = 4;
	private static final int OP_JNZ = 5;
	private static final int OP_JZ  = 6;
	private static final int OP_LT  = 7;
	private static final int OP_EQ  = 8;
	private static final int OP_REL = 9;
	private static final int OP_END = 99;
	
	private LinkedList<Integer> inputs = new LinkedList<>();
	private List<Long> memory;
	private List<Long> output = new ArrayList<>();
	private boolean paused = false;
	private int pointer = 0;
	private int relativePointer = 0;
	
	public void runProgram(List<Integer> program) {
		memory = program.stream().map(Number::longValue).collect(Collectors.toList());
		output = new ArrayList<>();
		doRun();
	}

	public void runLongProgram(List<Long> program) {
		memory = new ArrayList<>();
		memory.addAll(program);
		output = new ArrayList<>();
		doRun();
	}

	public void runCommaSeperatedStringProgram(String program) {
		List<Long> longProgram = Arrays.asList(program.split(",")).stream().map(Long::parseLong).collect(Collectors.toList());
		runLongProgram(longProgram);
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
	
	public Long getMemoryAt(int pos) {
		return memory.get(pos);
	}
	
	public List<Long> getOutput() {
		return output;
	}
	
	private void doRun() {
		paused = false;
		long opcode = memory.get(pointer);
		while(opcode % 100 != OP_END) {
			int pos1 = (int) ((opcode / 100) % 10);
			int pos2 = (int) ((opcode / 1000) % 10);
			int pos3 = (int) ((opcode / 10000) % 10);
			opcode %= 100;
			if(opcode == OP_ADD) {
				setBoundSafe(getPos(pointer + 3, pos3), get(pointer + 1, pos1) + get(pointer + 2, pos2));
				pointer += 4;
			} else if(opcode == OP_MUL){
				setBoundSafe(getPos(pointer + 3, pos3), get(pointer + 1, pos1) * get(pointer + 2, pos2));
				pointer += 4;
			} else if(opcode == OP_IN){
				if(inputs.isEmpty()) {
					paused = true;
					return;
				}
				setBoundSafe(getPos(pointer + 1, pos1), inputs.pop().longValue());
				pointer += 2;
			} else if(opcode == OP_OUT){
				output.add(get(pointer + 1, pos1));
				pointer += 2;
			} else if(opcode == OP_JNZ){
				if(get(pointer + 1, pos1) == 0) {
					pointer += 3;
				} else {
					pointer = get(pointer + 2, pos2).intValue();
				}
			} else if(opcode == OP_JZ){
				if(get(pointer + 1, pos1) == 0) {
					pointer = get(pointer + 2, pos2).intValue();
				} else {
					pointer += 3;
				}
			} else if(opcode == OP_LT){
				boolean lt = get(pointer + 1, pos1) < get(pointer + 2, pos2);
				setBoundSafe(getPos(pointer + 3, pos3), lt ? 1L : 0L);
				pointer += 4;
			} else if(opcode == OP_EQ){
				boolean eq = get(pointer + 1, pos1).equals(get(pointer + 2, pos2));
				setBoundSafe(getPos(pointer + 3, pos3), eq ? 1L : 0L);
				pointer += 4;
			} else if(opcode == OP_REL){
				relativePointer += get(pointer + 1, pos1);
				pointer += 2;
			} else {
				throw new IllegalArgumentException("Illegal opcode " + opcode);
			}
			opcode = memory.get(pointer);
		}
		inputs.clear();
		pointer = 0;
	}

	private Long get(int i, int readMode) {
		return getBoundSafe(getPos(i, readMode));
	}
	
	private int getPos(int i, int readMode) {
		if (readMode == READ_POSITIONAL) {
			return getBoundSafe(i).intValue();
		} else if (readMode == READ_RELATIVE) {
			return getBoundSafe(i).intValue() + relativePointer;
		} else if(readMode == READ_DIRECT) {
			return i;
		}
		throw new IllegalArgumentException("Unsupported read mode " + readMode);
	}
	
	private Long getBoundSafe(int i) {
		if(i >= memory.size()) {
			return 0L;
		}
		return memory.get(i);
	}
	
	private void setBoundSafe(Integer i, Long value) {
		if(i > Integer.MAX_VALUE) {
			throw new IllegalStateException("Exceeds memory limits: trying to set memory " + i + " of max " + Integer.MAX_VALUE);
		} else if(i >= memory.size()){
			for(int j = memory.size(); j <= i; j++) {
				memory.add(0L);
			}
		}
		memory.set(i.intValue(), value);
	}

	public void clearOutput() {
		output.clear();
	}
}
