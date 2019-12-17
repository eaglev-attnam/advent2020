package days;

import common.Coordinate;
import common.IntComputer;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.stream.Collectors;

public class Day13 extends Day {

	@Override
	protected int getChallengeNumber() {
		return 13;
	}
	
	@Override
	protected Object part1(List<String> input) {
		IntComputer computer = new IntComputer();
		List<Long> program = Arrays.asList(input.get(0).split(",")).stream().map(Long::parseLong).collect(Collectors.toList());
		computer.runLongProgram(program);
		List<Long> output = computer.getOutput();
		Set<Coordinate> blocks = new HashSet<>();
		for(int i = 0; i < output.size(); i += 3) {
			Coordinate c = new Coordinate(output.get(i).intValue(), output.get(i + 1).intValue());
			if(output.get(i + 2).equals(2L)) {
				blocks.add(c);
			} else {
				blocks.remove(c);
			}
		}
		return blocks.size();
	}

	@Override
	protected Object part2(List<String> input) {
		IntComputer computer = new IntComputer();
		List<Long> program = Arrays.asList(input.get(0).split(",")).stream().map(Long::parseLong).collect(Collectors.toList());
		program.set(0, 2L);
		computer.runLongProgram(program);
		List<Long> output = computer.getOutput();
		int maxX = 0;
		int maxY = 0;
		Map<Coordinate, Long> state = new HashMap<>();
		Coordinate ball = new Coordinate(0, 0);
		Coordinate paddle = new Coordinate(0, 0);
		for(int i = 0; i < output.size(); i += 3) {
			int x = output.get(i).intValue();
			int y = output.get(i+1).intValue();
			if(x > maxX) {
				maxX = x;
			}
			if(y > maxY) {
				maxY = y;
			}
			long value = output.get(i+2);
			if(value == 3L) {
				paddle = new Coordinate(x, y);
			} else if (value == 4L) {
				ball = new Coordinate(x, y);
			}
			state.put(new Coordinate(x, y), value);
		}
		computer.clearOutput();
		
		while(computer.isPaused() && state.values().stream().anyMatch(l -> l.equals(2L))) {
			if(paddle.getX() > ball.getX()) {
				computer.addInput(-1);
			} else if(paddle.getX() < ball.getX()) {
				computer.addInput(1);
			} else {
				computer.addInput(0);
			}
			output = computer.getOutput();
			for(int i = 0; i < output.size(); i += 3) {
				int x = output.get(i).intValue();
				int y = output.get(i+1).intValue();
				long value = output.get(i+2);
				if(value == 3L) {
					paddle = new Coordinate(x, y);
				} else if (value == 4L) {
					ball = new Coordinate(x, y);
				}
				state.put(new Coordinate(x, y), value);
			}
			computer.clearOutput();
		}

		return state.get(new Coordinate(-1, 0));
	}

	private class BoardState {
		private Coordinate ball;
		private Coordinate paddle;
		private long[][] board;
	}

	private void drawState(Map<Coordinate, Long> state, int maxX, int maxY) {
		for(int y = 0; y <= maxY; y++) {
			for(int x = 0; x <= maxX; x++) {
				Long l = state.get(new Coordinate(x, y));
				switch (l.intValue()) {
					case 1:
						System.out.print('#');
						break;
					case 2:
						System.out.print('*');
						break;
					case 3:
						System.out.print('=');
						break;
					case 4:
						System.out.print('o');
						break;
					default:
						System.out.print(' ');
						break;
				}
			}
			System.out.println();
		}
	}
}
