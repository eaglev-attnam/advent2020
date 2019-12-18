package days;

import common.Coordinate;
import javafx.util.Pair;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;

public class Day18 extends Day {

	private static final String LC_LETTERS = "abcdefghijklmnopqrstuvwxyz";
	private static final String UC_LETTERS = LC_LETTERS.toUpperCase();

	@Override
	protected int getChallengeNumber() {
		return 18;
	}



	@Override
	protected Object part1(List<String> input) {
		char[][] map = new char[input.get(0).length()][input.size()];
		Map<Character, Coordinate> items = new HashMap<>();
		for(int y = 0; y < input.size(); y++) {
			String row = input.get(y);
			for(int x = 0; x < input.get(0).length(); x++) {
				char c = row.charAt(x);
				map[x][y] = c;
				if(c != '#' && c != '.') {
					items.put(c, new Coordinate(x, y));
				}
			}
		}

		Map<Character, Map<Character, Integer>> distances = new HashMap<>();
		Map<Character, Map<Character, String>> blockingDoors = new HashMap<>();

		for(Character c : (LC_LETTERS + "@").toCharArray()) {
			distances.put(c, new HashMap<>());
			blockingDoors.put(c, new HashMap<>());
		}
		for(int i = 0; i < LC_LETTERS.length(); i++) {
			char key = LC_LETTERS.charAt(i);
			Pair<Integer, String> distAndDoors = getDistAndDoors(map, items.get('@'), items.get(key));
			distances.get('@').put(key, distAndDoors.getKey());
			blockingDoors.get('@').put(key, distAndDoors.getValue());
			for(int j = i + 1; j < LC_LETTERS.length(); j++) {
				char key2 = LC_LETTERS.charAt(j);
				Pair<Integer, String> distAndDoors2 = getDistAndDoors(map, items.get(key), items.get(key2));
				distances.get(key).put(key2, distAndDoors2.getKey());
				blockingDoors.get(key).put(key2, distAndDoors2.getValue());
				distances.get(key2).put(key, distAndDoors2.getKey());
				blockingDoors.get(key2).put(key, distAndDoors2.getValue());
			}
		}

		return shortestDist(distances, blockingDoors, '@', new HashSet<>());
	}

	private int shortestDist(Map<Character, Map<Character, Integer>> distances, Map<Character, Map<Character, String>> blockingDoors,
							 Character current, Set<Character> done) {
		if(done.size() == 26) {
			return 0;
		}
		int best = Integer.MAX_VALUE;
		for(Character c : distances.get(current).keySet()) {
			if(done.containsAll(Arrays.asList(blockingDoors.get(current).get(c).toLowerCase().toCharArray()))) {
				int dist = distances.get(current).get(c);
				Set<Character> newDone = new HashSet<>(done);
				newDone.add(c);
				dist += shortestDist(distances, blockingDoors, c, newDone);
				if(dist < best) {
					best = dist;
				}
			}
		}
		return best;
	}

	private Pair<Integer, String> getDistAndDoors(char[][] map, Coordinate start, Coordinate end) {
		SortedSet<AStarCoordinate> toCheck = new TreeSet<>();
		Set<Coordinate> done = new HashSet<>();
		AStarCoordinate c = new AStarCoordinate();
		c.coordinate = start;
		c.distanceDone = 0;
		c.minDistToDo = Math.abs(start.getX() - end.getX()) + Math.abs(start.getY() - end.getY());
		c.parent = null;
		while(!c.coordinate.equals(end)) {
			done.add(c.coordinate);
			for(Coordinate n : getNeighbours(c.coordinate)) {
				if(!done.contains(n) && map[n.getX()][n.getY()] != '#') {
					AStarCoordinate next = new AStarCoordinate();
					next.coordinate = n;
					next.parent = c;
					next.distanceDone = c.distanceDone + 1;
					next.minDistToDo = Math.abs(n.getX() - end.getX()) + Math.abs(n.getY() - end.getY());
					toCheck.add(next);
				}
			}
			c = toCheck.first();
			toCheck.remove(c);
		}
		int dist = c.distanceDone;
		String doors = "";
		while(c.parent != null) {
			if(UC_LETTERS.contains(map[c.coordinate.getX()][c.coordinate.getY()] + "")) {
				doors += map[c.coordinate.getX()][c.coordinate.getY()];
			}
		}
		return new Pair<>(dist, doors);
	}

	private Set<Coordinate> getNeighbours(Coordinate c) {
		Set<Coordinate> n = new HashSet<>();
		n.add(new Coordinate(c.getX() + 1, c.getY()));
		n.add(new Coordinate(c.getX() - 1, c.getY()));
		n.add(new Coordinate(c.getX(), c.getY() + 1));
		n.add(new Coordinate(c.getX(), c.getY() - 1));
		return n;
	}

	private class AStarCoordinate implements Comparable<AStarCoordinate> {
		Coordinate coordinate;
		AStarCoordinate parent;
		int distanceDone;
		int minDistToDo;

		@Override
		public int compareTo(AStarCoordinate o) {
			return Integer.compare(distanceDone + minDistToDo, o.distanceDone + o.minDistToDo);
		}
	}

	@Override
	protected Object part2(List<String> input) {
		return null;
	}
}
