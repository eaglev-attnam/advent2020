package days;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import common.Coordinate;
import common.IntComputer;
import visuals.Day11Visual2;
import visuals.Visual;

public class Day11 extends Day {
	private static final int BLACK = 0;
	private static final int WHITE = 1;
	
	@Override
	protected int getChallengeNumber() {
		return 11;
	}
	
	@Override
	protected Object part1(List<String> input) {
		return painting(input, BLACK).size();
	}

	@Override
	protected Object part2(List<String> input) {
		return painting(input, WHITE);
	}
	
	@Override
	public Optional<Visual> get2Visual() {
		return Optional.of(new Day11Visual2());
	}
	
	private Map<Coordinate, Integer> painting(List<String> input, int startingColor) {
		List<Long> program = Arrays.asList(input.get(0).split(",")).stream().map(Long::parseLong).collect(Collectors.toList());
		Robot robot = new Robot();
		robot.runProgram(program);
		Map<Coordinate, Integer> painted = new HashMap<>();
		painted.put(new Coordinate(0, 0), startingColor);
		while(!robot.finished()) {
			Coordinate pos = robot.getPosition();
			Integer color = painted.get(pos);
			if(color == null) {
				color = BLACK;
			}
			int newColor = robot.getNewColor(color);
			painted.put(pos, newColor);
		}
		return painted;
	}
	
	private class Robot {
		private IntComputer brain = new IntComputer();
		private DirectionalCoord position = new DirectionalCoord(0, 0, DirectionalCoord.NORTH);
		
		public void runProgram(List<Long> program) {
			brain.runLongProgram(program);
		}
		
		public int getNewColor(int color) {
			brain.addInput(color);
			List<Long> output = brain.getOutput();
			if(output.get(output.size() - 1).equals(0L)) {
				position.turnLeft();
			} else {
				position.turnRight();
			}
			position.moveForward();
			return output.get(output.size() - 2).intValue();
		}
		
		public boolean finished() {
			return !brain.isPaused();
		}
		
		public Coordinate getPosition() {
			return new Coordinate(position.getX(), position.getY());
		}
	}
	
	private class DirectionalCoord extends Coordinate {
		static final int NORTH = 0;
		static final int EAST = 1;
		static final int SOUTH = 2;
		static final int WEST = 3;
		private int direction;
		
		public DirectionalCoord(int x, int y, int direction) {
			super(x, y);
			this.direction = direction % 4;
		}
		
		public void turnLeft() {
			direction--;
			direction += 4;
			direction %= 4;
		}
		
		public void turnRight() {
			direction++;
			direction %= 4;
		}
		
		public void moveForward() {
			switch (direction) {
			case NORTH:
				y--;
				break;
			case EAST:
				x++;
				break;
			case SOUTH:
				y++;
				break;
			case WEST:
				x--;
				break;
			}
		}
	}
}
