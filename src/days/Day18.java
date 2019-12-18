package days;

import common.Coordinate;
import javafx.util.Pair;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;
import java.util.stream.Collectors;

public class Day18 extends Day {

	private String LC_LETTERS = "abcdefghijklmnopqrstuvwxyz";
	private String UC_LETTERS;

	@Override
	protected int getChallengeNumber() {
		return 18;
	}



	@Override
	protected Object part1(List<String> input) {
		return part1(input, LC_LETTERS);
	}

	public Object part1(List<String> input, String newLetters) {
		LC_LETTERS = newLetters;
		UC_LETTERS = LC_LETTERS.toUpperCase();
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
		Map<Character, Map<Character, List<Character>>> neededKeys = new HashMap<>();

		for(Character c : (LC_LETTERS + "@").toCharArray()) {
			distances.put(c, new HashMap<>());
			neededKeys.put(c, new HashMap<>());
		}
		for(int i = 0; i < LC_LETTERS.length(); i++) {
			char key = LC_LETTERS.charAt(i);
			Pair<Integer, List<Character>> distAndDoors = getDistAndDoors(map, items.get('@'), items.get(key));
			distances.get('@').put(key, distAndDoors.getKey());
			neededKeys.get('@').put(key, distAndDoors.getValue());
			for(int j = i + 1; j < LC_LETTERS.length(); j++) {
				char key2 = LC_LETTERS.charAt(j);
				Pair<Integer, List<Character>> distAndDoors2 = getDistAndDoors(map, items.get(key), items.get(key2));
				distances.get(key).put(key2, distAndDoors2.getKey());
				neededKeys.get(key).put(key2, distAndDoors2.getValue());
				distances.get(key2).put(key, distAndDoors2.getKey());
				neededKeys.get(key2).put(key, distAndDoors2.getValue());
			}
		}

		return shortestDist(distances, neededKeys, '@', new HashSet<>(), new HashMap<>());
		// 2147483647 too high
		// 6012 too low
	}

	private int shortestDist(Map<Character, Map<Character, Integer>> distances, Map<Character, Map<Character, List<Character>>> blockingDoors,
							 Character current, Set<Character> done, Map<String, Integer> calculated) {
		String key = done.stream().sorted().map(a -> a + "").collect(Collectors.joining()) + ":" + current;
		if(done.size() == LC_LETTERS.length()) {
			return 0;
		} else if (calculated.containsKey(key)){
			return calculated.get(key);
		}
		int best = Integer.MAX_VALUE;
		for(Character c : distances.get(current).keySet()) {
			if(current != c && !done.contains(c) && done.containsAll(blockingDoors.get(current).get(c))) {
				int dist = distances.get(current).get(c);
				Set<Character> newDone = new HashSet<>(done);
				newDone.add(c);
				dist += shortestDist(distances, blockingDoors, c, newDone, calculated);
				if(dist < best) {
					best = dist;
				}
			}
		}
		if(done.size() < 10) {
			// System.out.println("Resolved level " + key.length() + " round " + key + ".");
		}
		calculated.put(key, best);
		return best;
	}

	private Pair<Integer, List<Character>> getDistAndDoors(char[][] map, Coordinate start, Coordinate end) {
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
		List<Character> neededKeys = new ArrayList<>();
		while(c.parent != null) {
			if(UC_LETTERS.contains(map[c.coordinate.getX()][c.coordinate.getY()] + "")) {
				neededKeys.add((map[c.coordinate.getX()][c.coordinate.getY()] + "").toLowerCase().charAt(0));
			}
			c = c.parent;
		}
		return new Pair<>(dist, neededKeys);
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
			int result = Integer.compare(distanceDone + minDistToDo, o.distanceDone + o.minDistToDo);
			if(result == 0) {
				result = Integer.compare(coordinate.getX(), o.coordinate.getX());
			}
			if(result == 0) {
				result = Integer.compare(coordinate.getY(), o.coordinate.getY());
			}
			return result;
		}

		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || getClass() != o.getClass()) return false;
			AStarCoordinate that = (AStarCoordinate) o;
			return distanceDone == that.distanceDone &&
					minDistToDo == that.minDistToDo &&
					coordinate.equals(that.coordinate) &&
					Objects.equals(parent, that.parent);
		}

		@Override
		public int hashCode() {
			return Objects.hash(coordinate, parent, distanceDone, minDistToDo);
		}
	}

	@Override
	protected Object part2(List<String> input) {
		LC_LETTERS = "abcdefghijklmnopqrstuvwxyz";
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

		// @ # %
		// # # #
		// $ # &

		map[39][39] = '@';
		map[39][40] = '#';
		map[39][41] = '$';
		map[40][39] = '#';
		map[40][40] = '#';
		map[40][41] = '#';
		map[41][39] = '%';
		map[41][40] = '#';
		map[41][41] = '&';

		items.put('@', new Coordinate(39, 39));
		items.put('$', new Coordinate(39, 41));
		items.put('%', new Coordinate(41, 39));
		items.put('&', new Coordinate(41, 41));

		Map<Character, Map<Character, Integer>> distances = new HashMap<>();
		Map<Character, Map<Character, List<Character>>> neededKeys = new HashMap<>();

		for(Character c : (LC_LETTERS + "@$%&").toCharArray()) {
			distances.put(c, new HashMap<>());
			neededKeys.put(c, new HashMap<>());
		}
		Set<Character> starts = new HashSet<>();
		starts.add('@');
		starts.add('$');
		starts.add('%');
		starts.add('&');

		for(int i = 0; i < LC_LETTERS.length(); i++) {
			char key = LC_LETTERS.charAt(i);
			for(Character start : starts) {
				Optional<Pair<Integer, List<Character>>> distAndDoors = getDistAndDoorsOpt(map, items.get(start), items.get(key));
				if(distAndDoors.isPresent()) {
					distances.get(start).put(key, distAndDoors.get().getKey());
					neededKeys.get(start).put(key, distAndDoors.get().getValue());
				}
			}
			for(int j = i + 1; j < LC_LETTERS.length(); j++) {
				char key2 = LC_LETTERS.charAt(j);
				Optional<Pair<Integer, List<Character>>> distAndDoors2 = getDistAndDoorsOpt(map, items.get(key), items.get(key2));
				if(distAndDoors2.isPresent()) {
					distances.get(key).put(key2, distAndDoors2.get().getKey());
					neededKeys.get(key).put(key2, distAndDoors2.get().getValue());
					distances.get(key2).put(key, distAndDoors2.get().getKey());
					neededKeys.get(key2).put(key, distAndDoors2.get().getValue());
				}
			}
		}

		return shortestDist(distances, neededKeys, new Character[]{'@', '$', '%', '&'}, new HashSet<>(), new HashMap<>());
	}

	private Optional<Pair<Integer, List<Character>>> getDistAndDoorsOpt(char[][] map, Coordinate start, Coordinate end) {
		if(((start.getX() < 40) != (end.getX() < 40)) || ((start.getY()) < 40 != (end.getY() < 40))) {
			return Optional.empty();
		}
		return Optional.of(getDistAndDoors(map, start, end));
	}

	private int shortestDist(Map<Character, Map<Character, Integer>> distances, Map<Character, Map<Character, List<Character>>> blockingDoors,
							 Character[] current, Set<Character> done, Map<String, Integer> calculated) {
		String key = done.stream().sorted().map(a -> a + "").collect(Collectors.joining()) + ":" + Arrays.toString(current);
		if(done.size() == LC_LETTERS.length()) {
			return 0;
		} else if (calculated.containsKey(key)){
			return calculated.get(key);
		}
		int best = Integer.MAX_VALUE;
		for(Character c : LC_LETTERS.toCharArray()) {
			for(int i = 0; i < current.length; i++) {
				char pos = current[i];
				if (distances.get(pos).containsKey(c) && pos != c && !done.contains(c) && done.containsAll(blockingDoors.get(pos).get(c))) {
					int dist = distances.get(pos).get(c);
					Set<Character> newDone = new HashSet<>(done);
					newDone.add(c);
					Character[] newCurrent = Arrays.copyOf(current, 4);
					newCurrent[i] = c;
					dist += shortestDist(distances, blockingDoors, newCurrent, newDone, calculated);
					if (dist < best) {
						best = dist;
					}
				}
			}
		}
		if(done.size() < 10) {
			// System.out.println("Resolved level " + key.length() + " round " + key + ".");
		}
		calculated.put(key, best);
		return best;
	}
}
