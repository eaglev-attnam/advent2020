package days;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import common.Coordinate;

public class Day24 extends Day {
	
	@Override
	protected int getChallengeNumber() {
		return 24;
	}
	
	@Override
	protected Object part1(List<String> input) {
		return getBlackTiles(input).size();
	}

	private Set<Coordinate> getBlackTiles(List<String> input) {
		Set<Coordinate> blacktiles = new HashSet<>();
		for(String s : input) {
			int x = 0;
			int y = 0;
			int i = 0;
			while(i < s.length()) {
				char c = s.charAt(i);
				switch(c) {
				case 'e' :
					x++;
					break;
				case 'w' :
					x--;
					break;
				case 'n' :
					y--;
					i++;
					if(s.charAt(i) == 'w') {
						x--;
					}
					break;
				case 's' :
					y++;
					i++;
					if(s.charAt(i) == 'e') {
						x++;
					}
					break;
				}
				i++;
			}
			Coordinate c = new Coordinate(x, y);
			if(!blacktiles.remove(c)) {
				blacktiles.add(c);
			}
		}
		return blacktiles;
	}

	@Override
	protected Object part2(List<String> input) {
		Set<Coordinate> blackTilesCurrent = getBlackTiles(input);
		Set<Coordinate> toCheck = new HashSet<>(blackTilesCurrent);
		for(Coordinate c : blackTilesCurrent) {
			toCheck.addAll(getHexNeighbours(c));
		}
		for(int i = 0; i < 100; i++) {
			Set<Coordinate> blackTilesNext = new HashSet<>();
			Set<Coordinate> toCheckNext = new HashSet<>();
			for(Coordinate c : toCheck) {
				Set<Coordinate> neighbours = getHexNeighbours(c);
				int blackNeighbours = (int) neighbours.stream().filter(blackTilesCurrent::contains).count();
				if(blackNeighbours == 2 || (blackNeighbours == 1 && blackTilesCurrent.contains(c))) {
					blackTilesNext.add(c);
					toCheckNext.add(c);
					toCheckNext.addAll(neighbours);
				}
			}
			blackTilesCurrent = blackTilesNext;
			toCheck = toCheckNext;
		}
		return blackTilesCurrent.size();
	}
	
	private Set<Coordinate> getHexNeighbours(Coordinate c) {
		Set<Coordinate> n = new HashSet<>();
		n.add(new Coordinate(c.getX() + 1, c.getY()));
		n.add(new Coordinate(c.getX() - 1, c.getY()));
		n.add(new Coordinate(c.getX(), c.getY() + 1));
		n.add(new Coordinate(c.getX(), c.getY() - 1));
		n.add(new Coordinate(c.getX() + 1, c.getY() + 1));
		n.add(new Coordinate(c.getX() - 1, c.getY() - 1));
		return n;
	}
}
