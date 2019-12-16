package days;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import common.Coordinate;
import common.IntComputer;

public class Day15 extends Day {

	private static final int NORTH = 1;
	private static final int SOUTH = 2;
	private static final int EAST = 4;
	private static final int WEST = 3;

	private static final Map<Integer, Integer> TURN_RIGHT = new HashMap<>();
	private static final Map<Integer, Integer> TURN_LEFT = new HashMap<>();
	
	static {
		TURN_RIGHT.put(NORTH, EAST);
		TURN_RIGHT.put(EAST, SOUTH);
		TURN_RIGHT.put(SOUTH, WEST);
		TURN_RIGHT.put(WEST, NORTH);
		TURN_LEFT.put(NORTH, WEST);
		TURN_LEFT.put(WEST, SOUTH);
		TURN_LEFT.put(SOUTH, EAST);
		TURN_LEFT.put(EAST, NORTH);
	}
	
	@Override
	protected int getChallengeNumber() {
		return 15;
	}
	
	@Override
	protected Object part1(List<String> input) {
		int[][] map = getMap(input);
		Coordinate start = getFromMap(map, -1);
		Coordinate oxygen = getFromMap(map, 2);
		
		int steps = 0;
		Set<Coordinate> done = new HashSet<>();
		Set<Coordinate> toCheck = new HashSet<>();
		Set<Coordinate> toCheckNext = new HashSet<>();
		toCheckNext.add(start);
		while(!toCheckNext.contains(oxygen)) {
			steps++;
			toCheck.clear();
			toCheck.addAll(toCheckNext);
			toCheckNext.clear();
			toCheck.forEach(c -> toCheckNext.addAll(neighbours(c)));
			done.addAll(toCheck);
			toCheckNext.removeAll(done);
			toCheckNext.removeIf(c -> map[c.getX()][c.getY()] == 0);
		}
		
		return steps;
	}

	private Coordinate getFromMap(int[][] map, int target) {
		for(int y = 0; y < map[0].length; y++) {
			for(int x = 0; x < map.length; x++) {
				if(map[x][y] == target) {
					return new Coordinate(x, y);
				}
			}
		}
		return null;
	}

	private Collection<? extends Coordinate> neighbours(Coordinate c) {
		Set<Coordinate> n = new HashSet<>();
		for(int i = 1; i <= 4; i++) {
			n.add(updateCoordinate(c, i));
		}
		return n;
	}

	@Override
	protected Object part2(List<String> input) {
		int[][] map = getMap(input);
		Coordinate oxygen = getFromMap(map, 2);
		
		int steps = 0;
		Set<Coordinate> done = new HashSet<>();
		Set<Coordinate> toCheck = new HashSet<>();
		Set<Coordinate> toCheckNext = new HashSet<>();
		toCheckNext.add(oxygen);
		while(!toCheckNext.isEmpty()) {
			steps++;
			toCheck.clear();
			toCheck.addAll(toCheckNext);
			toCheckNext.clear();
			toCheck.forEach(c -> toCheckNext.addAll(neighbours(c)));
			done.addAll(toCheck);
			toCheckNext.removeAll(done);
			toCheckNext.removeIf(c -> map[c.getX()][c.getY()] == 0);
		}
		// 395 too high
		return steps - 1;
	}
	
	private int[][] getMap(List<String> input) {
		IntComputer computer = new IntComputer();
		List<Long> program = Arrays.asList(input.get(0).split(",")).stream().map(Long::parseLong).collect(Collectors.toList());
		computer.runLongProgram(program);
		Set<Coordinate> walls = new HashSet<>();
		Coordinate oxygen = null;
		Coordinate current = new Coordinate(0, 0);
		Coordinate start = new Coordinate(0, 0);
		int dir = EAST;
		do {
			// Try to turn into the wall we're following
			computer.clearOutput();
			computer.addInput(TURN_LEFT.get(dir));
			if(computer.getOutput().get(0) > 0) {
				dir = TURN_LEFT.get(dir);
				current = updateCoordinate(current, dir);
			} else {
				walls.add(updateCoordinate(current, TURN_LEFT.get(dir)));
				dir = TURN_RIGHT.get(dir);
			}
			if(computer.getOutput().get(0).equals(2L)) {
				oxygen = current;
			}
		} while(!current.equals(start) || dir != EAST);

		int minX = 0;
		int minY = 0;
		int maxX = 0;
		int maxY = 0;
		for(Coordinate c : walls) {
			if(minX > c.getX()) {
				minX = c.getX();
			}
			if(minY > c.getY()) {
				minY = c.getY();
			}
			if(maxX < c.getX()) {
				maxX = c.getX();
			}
			if(maxY < c.getY()) {
				maxY = c.getY();
			}
		}
		
		int[][] map = new int[maxX - minX + 1][maxY - minY + 1];
		for(int y = minY; y <= maxY; y++) {
			for(int x = minX; x <= maxX; x++) {
				if(new Coordinate(x, y).equals(oxygen)) {
					map[x - minX][y - minY] = 2;
				} else if(new Coordinate(x, y).equals(start)) {
					map[x - minX][y - minY] = -1;
				} else {
					map[x - minX][y - minY] = walls.contains(new Coordinate(x, y)) ? 0 : 1;
				}
			}
		}
		return map;	
	}

	private Coordinate updateCoordinate(Coordinate current, int dir) {
		int xChange = 0;
		int yChange = 0;
		if(dir <= 2) {
			yChange = (2 * dir) - 3;
		} else {
			xChange = (2 * dir) - 7;
		}
		return new Coordinate(current.getX() + xChange, current.getY() + yChange);
	}
}
