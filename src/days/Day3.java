package days;

import common.Coordinate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class Day3 extends Day {

	@Override
	protected int getChallengeNumber() {
		return 3;
	}
	
	@Override
	protected Object part1(List<String> input) {
		List<SortableCoord> wire0 = parseWireSortOnDistance(input.get(0));
		List<SortableCoord> wire1 = parseWireSortOnDistance(input.get(1));
		int w0current = 0;
		int w1current = 0;
		while(!wire0.get(w0current).equals(wire1.get(w1current))) {
			if(wire0.get(w0current).compareTo(wire1.get(w1current)) < 0) {
				w0current++;
			} else {
				w1current++;
			}
		}
		return wire0.get(w0current).dist;
	}

	@Override
	protected Object part2(List<String> input) {
		Map<SortableCoord, Integer> wire0 = parseWireWithDelays(input.get(0));
		Map<SortableCoord, Integer> wire1 = parseWireWithDelays(input.get(1));
		Set<SortableCoord> junctions = wire0.keySet();
		junctions.retainAll(wire1.keySet());
		int closest = Integer.MAX_VALUE;
		for(SortableCoord c : junctions) {
			int delay = wire0.get(c) + wire1.get(c);
			if(delay < closest) {
				closest = delay;
			}
		}
		return closest;
	}
	
	private List<SortableCoord> parseWireSortOnDistance(String wire) {
		return parseWireWithDelays(wire)
				.keySet()
				.stream()
				.sorted()
				.collect(Collectors.toList());
	}
	
	private Map<SortableCoord, Integer> parseWireWithDelays(String wire) {
		Map<SortableCoord, Integer> coords = new HashMap<>();
		int x = 0;
		int y = 0;
		int delay = 0;
		for(String move : wire.split(",")) {
			int xDir = 0;
			int yDir = 0;
			char dir = move.charAt(0);
			if(dir == 'U') {
				yDir = 1;
			} else if(dir == 'D') {
				yDir = -1;
			} else if(dir == 'L') {
				xDir = 1;
			} else {
				xDir = -1;
			}
			int steps = Integer.parseInt(move.substring(1));
			for(int i = 0; i < steps; i++) {
				x += xDir;
				y += yDir;
				delay++;
				SortableCoord c = new SortableCoord(x, y);
				if(!coords.containsKey(c)) {
					coords.put(c, delay);
				}
			}
		}
		return coords;
	}
	
	private class SortableCoord extends Coordinate implements Comparable<SortableCoord> {
		private int dist;
		
		private SortableCoord(int x, int y) {
			super(x, y);
			this.dist = Math.abs(x) + Math.abs(y);
		}

		@Override
		public int compareTo(SortableCoord other) {
			int result = Integer.compare(dist, other.dist);
			if(result == 0) {
				result = Integer.compare(x, other.x);
			}
			if(result == 0) {
				result = Integer.compare(y, other.y);
			}
			return result;
		}
	}
}
