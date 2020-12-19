package days;

import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

public class Day17 extends Day {

	@Override
	protected int getChallengeNumber() {
		return 17;
	}
	
	@Override
	protected Object part1(List<String> input) {
		boolean[][][] grid = new boolean[input.get(0).length() + 12][input.size() + 12][13];
		for(int x = 0; x < input.get(0).length(); x++) {
			for(int y = 0; y < input.size(); y++) {
				if(input.get(y).charAt(x) == '#') {
					grid[x+6][y+6][6] = true;
				}
			}
		}
		for(int i = 0; i < 6; i++) {
			boolean[][][] newGrid = new boolean[input.get(0).length() + 12][input.size() + 12][13];
			for(int x = 0; x < grid.length; x++) {
				for(int y = 0; y < grid[0].length; y++) {
					for(int z = 0; z < grid[0][0].length; z++) {
						int neighbours = getNeighbours(grid, x, y, z);
						if((grid[x][y][z] && neighbours == 2) || neighbours == 3) {
							newGrid[x][y][z] = true;
						}
					}
				}
			}
			grid = newGrid;
		}
		int count = 0;
		for(int x = 0; x < grid.length; x++) {
			for (int y = 0; y < grid[0].length; y++) {
				for (int z = 0; z < grid[0][0].length; z++) {
					if(grid[x][y][z]) {
						count++;
					}
				}
			}
		}
		return count;
	}

	private int getNeighbours(boolean[][][] grid, int x, int y, int z) {
		int n = 0;
		for(int dx = -1; dx <= 1; dx++) {
			for(int dy = -1; dy <= 1; dy++) {
				for(int dz = -1; dz <= 1; dz++) {
					if(dx != 0 || dy != 0 || dz != 0) {
						int nx = x + dx;
						int ny = y + dy;
						int nz = z + dz;
						if(nx > 0 && nx < grid.length &&
							ny > 0 && ny < grid[0].length &&
							nz > 0 && nz < grid[0][0].length &&
							grid[x+dx][y+dy][z+dz]) {
								n++;
						}
					}
				}
			}
		}
		return n;
	}

	@Override
	protected Object part2(List<String> input) {
		Set<Coordinate> active = new HashSet<>();
		Set<Coordinate> toCheck = new HashSet<>();
		for(int x = 0; x < input.get(0).length(); x++) {
			for(int y = 0; y < input.size(); y++) {
				if(input.get(y).charAt(x) == '#') {
					Coordinate c = new Coordinate(x, y, 0, 0);
					active.add(c);
					toCheck.addAll(c.getNeighbours());
					toCheck.add(c);
				}
			}
		}
		for(int i = 0; i < 6; i++) {
			Set<Coordinate> nextActive = new HashSet<>();
			Set<Coordinate> nextToCheck = new HashSet<>();
			for(Coordinate c : toCheck) {
				int neighbours = (int) c.getNeighbours().stream().filter(active::contains).count();
				if(neighbours == 3 || (neighbours == 2 && active.contains(c))) {
					nextActive.add(c);
					nextToCheck.addAll(c.getNeighbours());
					nextToCheck.add(c);
				}
			}
			active = nextActive;
			// print(active);
			toCheck = nextToCheck;
		}
		return active.size();
	}

	private class Coordinate {
		int x,y,z,w;

		private Coordinate(int x, int y, int z, int w) {
			this.x = x;
			this.y = y;
			this.z = z;
			this.w = w;
		}

		private Set<Coordinate> getNeighbours() {
			Set<Coordinate> neighbours = new HashSet<>();
			for(int dx = -1; dx <= 1; dx++) {
				for (int dy = -1; dy <= 1; dy++) {
					for (int dz = -1; dz <= 1; dz++) {
						for (int dw = -1; dw <= 1; dw++) {
							if(dx != 0 || dy != 0 || dz != 0 || dw != 0) {
								neighbours.add(new Coordinate(x + dx, y + dy, z + dz, w + dw));
							}
						}
					}
				}
			}
			return neighbours;
		}

		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || getClass() != o.getClass()) return false;
			Coordinate that = (Coordinate) o;
			return x == that.x &&
					y == that.y &&
					z == that.z &&
					w == that.w;
		}

		@Override
		public int hashCode() {
			return Objects.hash(x, y, z, w);
		}
	}
}
