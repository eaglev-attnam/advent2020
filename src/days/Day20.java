package days;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.function.Predicate;

import visuals.Visual;
import visuals.Visual20Part1;
import visuals.Visual20Part2;

public class Day20 extends Day {

	private Optional<Visual> visual1 = Optional.empty();
	private Optional<Visual> visual2 = Optional.empty();
	
	@Override
	protected int getChallengeNumber() {
		return 20;
	}
	
	@Override
	protected Object part1(List<String> input) {
		int size = (int) Math.sqrt((input.size() + 2) / 12);
		Map<Integer, String[]> pieces = getPieces(input, size);
		int[][] up = new int[size][size];
		int[][] solution = solve(pieces.keySet(), pieces, new int[size][size], up).orElseThrow(() -> new IllegalStateException("No solution found"));
		char[][] sea = getSea(size, 10, solution, up, getFullBlocks(input, size));
		for(int x = 0; x < solution.length; x++) {
			for(int y = 0; y < solution.length; y++) {
				System.out.print(solution[x][y] + " " + up[x][y] + "  ");
			}
			System.out.println();
			System.out.println();
		}
		visual1 = Optional.of(new Visual20Part1(sea));
		return 1L * solution[0][0] * solution[0][size - 1] *  solution[size - 1][0] *  solution[size - 1][size - 1];
	}

	@Override
	protected Object part2(List<String> input) {
		int size = (int) Math.sqrt((input.size() + 2) / 12);
		Map<Integer, String[]> blocks = getPieces(input, size);
		Map<Integer, String[]> borderlessBlocks = getBorderlessBlocks(input, size);
		int[][] up = new int[size][size];
		int[][] solution = solve(blocks.keySet(), blocks, new int[size][size], up).orElseThrow(() -> new IllegalStateException("No solution found"));
		char[][] sea = getSea(size, 8, solution, up, borderlessBlocks);
		boolean[][] pattern = getPattern();
		for(int orientation = 0; orientation < 8; orientation++) {
			sea = markMonsters(pattern, sea, orientation);
		}
		int sum = 0;
		System.out.println(sea.length + " squared. " + size + " blocks of length 8.");
		for(int x = 0; x < sea.length; x++) {
			for(int y = 0; y < sea.length; y++) {
				if(sea[x][y] == '#') {
					sum++;
				}
			}
		}

		visual2 = Optional.of(new Visual20Part2(sea));
		
		// 2417 too high
		return sum;
	}

	private char[][] getSea(int size, int blocksize, int[][] solution, int[][] up, Map<Integer, String[]> blocks) {
		char[][] sea = new char[size * blocksize][size * blocksize];
		for(int solY = 0; solY < size; solY++) {
			for(int y = 0; y < blocksize; y++) {
				for(int solX = 0; solX < size; solX++) {
					for(int x = 0; x < blocksize; x++) {
						int orientation = up[solX][solY];
						int realX = x;
						int realY = y ;
						if(orientation == 1 || orientation == 2 || orientation == 5 || orientation == 6) {
							realY = blocksize - realY - 1;
						}
						if(orientation == 2 || orientation == 3 || orientation == 6 || orientation == 7) {
							realX = blocksize - realX - 1;
						}
						if(orientation == 1 || orientation == 3 || orientation == 4 || orientation == 6) {
							int tmp = realX;
							realX = realY;
							realY = tmp;
						}
						sea[blocksize * solY + y][blocksize * solX + x] = blocks.get(solution[solX][solY])[realY].charAt(realX);
					}
				}
			}
		}
		return sea;
	}

	private Map<Integer, String[]> getPieces(List<String> input, int size) {
		Map<Integer, String[]> blocks = new HashMap<Integer, String[]>();
		for(int block = 0; block < size*size; block++) {
			int line = block * 12;
			int key = Integer.parseInt(input.get(line).substring(5, 9));
			line++;								//	 0>		 7>
			String[] sides = new String[8];		//	^  1	^  4
			sides[0] = input.get(line);			//	3  v	6  v
			sides[7] = reverse(sides[0]);		//	 <2		 <5
			StringBuilder left = new StringBuilder();
			StringBuilder right = new StringBuilder();
			while(line < ((block + 1) * 12) - 1) {
				left.append(input.get(line).charAt(0));
				right.append(input.get(line).charAt(9));
				line++;
			}
			line--;
			sides[1] = right.toString();
			sides[6] = right.reverse().toString();
			sides[4] = left.toString();
			sides[3] = left.reverse().toString();
			sides[5] = input.get(line);
			sides[2] = reverse(sides[5]);
			blocks.put(key, sides);
		}
		return blocks;
	}
	
	private Map<Integer, String[]> getBorderlessBlocks(List<String> input, int size) {
		Map<Integer, String[]> blocks = new HashMap<Integer, String[]>();
		for(int block = 0; block < size*size; block++) {
			int line = block * 12;
			int key = Integer.parseInt(input.get(line).substring(5, 9));
			String[] lines = new String[8];
			for(int i = 0; i < 8; i++) {
				lines[i] = input.get(line + i + 2).substring(1, 9);
				lines[i] = input.get(line + i + 2).substring(1, 9);
			}
			blocks.put(key, lines);
		}
		return blocks;
	}
	
	private Map<Integer, String[]> getFullBlocks(List<String> input, int size) {
		Map<Integer, String[]> blocks = new HashMap<Integer, String[]>();
		for(int block = 0; block < size*size; block++) {
			int line = block * 12;
			int key = Integer.parseInt(input.get(line).substring(5, 9));
			String[] lines = new String[10];
			for(int i = 0; i < 10; i++) {
				lines[i] = input.get(line + i + 1);
				lines[i] = input.get(line + i + 1);
			}
			blocks.put(key, lines);
		}
		return blocks;
	}
	
	private String reverse(String s) {
		StringBuilder sb = new StringBuilder(s);
		return sb.reverse().toString();
	}
	
	private Optional<int[][]> solve(Set<Integer> available, Map<Integer, String[]> pieces, int [][] done, int[][] sideUp) {
		int x = 0;
		int y = 0;
		while(done[x][y] > 0) {
			x++;
			if(x >= done.length) {
				x = 0;
				y++;
				if(y >= done.length) {
					return Optional.of(done);
				}
			}
		}
		Predicate<String> upMatcher = a -> true;
		Predicate<String> leftMatcher = a -> true;
		if(x > 0) {
			String[] leftPiece = pieces.get(done[x-1][y]);
			int up = sideUp[x-1][y];
			int right = (up + 1) % 4;
			if(up >= 4) {
				right += 4;
			}
			int reverseRight = 7 - right;
			leftMatcher = a -> a.equals(leftPiece[reverseRight]);
		}
		if(y > 0) {
			String[] upPiece = pieces.get(done[x][y-1]);
			int up = sideUp[x][y-1];
			int down = (up + 2) % 4;
			if(up >= 4) {
				down += 4;
			}
			int reverseDown = 7 - down;
			upMatcher = a -> a.equals(upPiece[reverseDown]);
		}
		for(Integer piece : available) {
			for(int up = 0; up < 8; up++) {
				int left = (up + 3) % 4;
				if(up >= 4) {
					left += 4;
				}
				String[] sides = pieces.get(piece);
				if(upMatcher.test(sides[up]) && leftMatcher.test(sides[left])) {
					Set<Integer> newAvailable = new HashSet<>();
					newAvailable.addAll(available);
					newAvailable.remove(piece);
					done[x][y] = piece;
					sideUp[x][y] = up;
					Optional<int[][]> maybeSolution = solve(newAvailable, pieces, done, sideUp);
					if(maybeSolution.isPresent()) {
						return maybeSolution;
					}
					done[x][y] = 0;
				}
			}
		}
		return Optional.empty();
	}
	
	private boolean[][] getPattern() {
		boolean[][] pattern = new boolean[20][3];
		for(int i = 0; i <= 12; i += 6) {
			pattern[i][1] = true;
			pattern[i+1][2] = true;
			pattern[i+4][2] = true;
			pattern[i+5][1] = true;
		}
		pattern[18][0] = true;
		pattern[18][1] = true;
		pattern[19][1] = true;
		return pattern;
	}
	
	private char[][] markMonsters(boolean[][] pattern, char[][] sea, int orientation) {
		for(int x = 0; x < sea.length; x++) {
			for(int y = 0; y < sea.length; y++) {
				sea = markMonster(pattern, sea, orientation, x, y);
			}
		}
		return sea;
	}

	private char[][] markMonster(boolean[][] pattern, char[][] sea, int orientation, int x, int y) {
		for(int dx = 0; dx < pattern.length; dx++) {
			for(int dy = 0; dy < pattern[0].length; dy++) {
				if(pattern[dx][dy]) {
					int realDX = dx;
					int realDY = dy ;
					if(orientation == 1 || orientation == 2 || orientation == 6 || orientation == 7) {
						realDY = 2 - realDY;
					}
					if(orientation == 2 || orientation == 3 || orientation == 5 || orientation == 6) {
						realDX = 19 - realDX;
					}
					if((orientation % 2 == 0) != (orientation < 4)) {
						int tmp = realDX;
						realDX = realDY;
						realDY = tmp;
					}
					int newX = x + realDX;
					int newY = y + realDY;
					if(newX < 0 || newX >= sea.length || newY < 0 || newY >= sea.length || sea[newX][newY] == '.') {
						return sea;
					}
				}
			}
		}
		for(int dx = 0; dx < pattern.length; dx++) {
			for(int dy = 0; dy < pattern[0].length; dy++) {
				if(pattern[dx][dy]) {
					int realDX = dx;
					int realDY = dy ;
					if(orientation == 1 || orientation == 2 || orientation == 6 || orientation == 7) {
						realDY = 2 - realDY;
					}
					if(orientation == 2 || orientation == 3 || orientation == 5 || orientation == 6) {
						realDX = 19 - realDX;
					}
					if((orientation % 2 == 0) != (orientation < 4)) {
						int tmp = realDX;
						realDX = realDY;
						realDY = tmp;
					}
					int newX = x + realDX;
					int newY = y + realDY;
					sea[newX][newY] = 'O';
				}
			}
		}
		return sea;
	}
	
	@Override
	public Optional<Visual> get1Visual() {
		return visual1;
	}
	
	@Override
	public Optional<Visual> get2Visual() {
		return visual2;
	}
}
