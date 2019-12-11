package days;

import common.Coordinate;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Day10 extends Day {

	@Override
	protected int getChallengeNumber() {
		return 9;
	}
	
	@Override
	protected Object part1(List<String> input) {
		Set<Coordinate> coords = getCoords(input);
		int max = 0;
		Coordinate best = null;
		for(Coordinate c : coords) {
			int visible = 0;
			for(Coordinate other : coords) {
				if(!c.equals(other) && isVisibleFrom(c, other, coords)) {
					visible++;
				}
			}
			if(visible > max) {
				max = visible;
				best = c;
			}
		}
		return best.getX() + " " + best.getY() + " " + max;
	}

	boolean isVisibleFrom(Coordinate c, Coordinate other, Collection<Coordinate> field) {
		int xDiff = c.getX() - other.getX();
		int yDiff = c.getY() - other.getY();
		int gcd = gcd(Math.abs(xDiff), Math.abs(yDiff));
		int xDiv = xDiff/gcd;
		int yDiv = yDiff/gcd;
		for(int i = 1; i < gcd; i++) {
			if(field.contains(new Coordinate(c.getX() - (i * xDiv), c.getY() - (i * yDiv)))) {
				return false;
			}
		}
		return true;
	}

	int gcd(int xDiff, int yDiff) {
		int biggest = xDiff;
		int smallest = yDiff;
		if(biggest < smallest) {
			int tmp = biggest;
			biggest = smallest;
			smallest = tmp;
		}
		if(smallest == 0) {
			return biggest;
		}
		while(biggest % smallest > 0) {
			int tmp = biggest % smallest;
			biggest = smallest;
			smallest = tmp;
		}
		return smallest;
	}

	@Override
	protected Object part2(List<String> input) {
		String start = "" + part1(input);
		String[] split = start.split(" ");
		Coordinate asteroid = new Coordinate(Integer.parseInt(split[0]), Integer.parseInt(split[1]));
		Set<Coordinate> coords = getCoords(input);
		coords.remove(asteroid);
		List<Coordinate> destroyed = new ArrayList<>();
		while(!coords.isEmpty()) {
			Set<Coordinate> toDestroy = new HashSet<>();
			for(Coordinate c : coords) {
				if(isVisibleFrom(asteroid, c, coords)) {
					toDestroy.add(c);
				}
			}
			toDestroy.stream().sorted((a, b) -> compareAngle(asteroid, a, b)).forEach(debris -> {
				destroyed.add(debris);
				coords.remove(debris);
			});
		}
		return destroyed.get(199).getX() * 100 + destroyed.get(199).getY();
	}

	private int compareAngle(Coordinate origin, Coordinate a, Coordinate b) {
		Coordinate relativeA = new Coordinate(a.getX() - origin.getX(), a.getY() - origin.getY());
		Coordinate relativeB = new Coordinate(b.getX() - origin.getX(), b.getY() - origin.getY());
		int qa = getQuadrant(relativeA);
		int qb = getQuadrant(relativeB);
		int result = Integer.compare(qa, qb);
		if(result == 0) {
			result = Double.compare(getAngle(relativeA, qa), getAngle(relativeB, qb));
		}
		return result;
	}

	private double getAngle(Coordinate a, int quadrant) {
		if(a.getY() == 0 || a.getX() == 0) {
			// These are the first lines in the quadrant, no need to calculate
			// In each quadrant, only one of these can be true so no need to check
			return Integer.MIN_VALUE;
		}
		if(quadrant % 2 == 0) {
			return Math.abs((double) a.getX()) / Math.abs((double) a.getY());
		} else {
			return Math.abs((double) a.getY()) / Math.abs((double) a.getX());
		}
	}

	private int getQuadrant(Coordinate a) {
		if(a.getX() >= 0 && a.getY() < 0) { // If in upper right, including due north, excluding due east
			return 0;
		} else if (a.getX() > 0) { // Else if in right (so bottom right), including due east, excluding due south
			return 1;
		} else if (a.getY() > 0) { // Else if in bottom (so bottom left), including due south, excluding due west
			return 2;
		} else { // Else (so top left), including due west, excluding due north
			return 3;
		}
	}

	private Set<Coordinate> getCoords(List<String> input) { new HashSet<>();
		Set<Coordinate> coords = new HashSet<>();
		for(int y = 0; y < input.size(); y++) {
			for(int x = 0; x < input.get(y).length(); x++) {
				if(input.get(y).charAt(x) == '#') {
					coords.add(new Coordinate(x, y));
				}
			}
		}
		return coords;
	}
}
