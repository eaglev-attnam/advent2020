package days;

import java.util.Arrays;
import java.util.List;
import java.util.function.Function;

public class Day11 extends Day {

	@Override
	protected int getChallengeNumber() {
		return 11;
	}
	
	@Override
	protected Object part1(List<String> input) {
		return getAnswer(input, a -> b -> c -> getNewState1(a,b,c));
	}

	@Override
	protected Object part2(List<String> input) {
		return getAnswer(input, a -> b -> c -> getNewState2(a,b,c));
	}

	private int getAnswer(List<String> input, Function<char[][], Function<Integer, Function<Integer, Character>>> newStateFunction) {
		char[][] state = new char[input.size()][];
		for(int i = 0; i < input.size(); i++) {
			state[i] = input.get(i).toCharArray();
		}
		char[][] lastState = new char[0][];
		while(!Arrays.deepEquals(lastState, state)) {
			lastState = new char[state.length][];
			for(int x = 0; x < state.length; x++) {
				lastState[x] = Arrays.copyOf(state[x], state[x].length);
			}
			for(int x = 0; x < state.length; x++) {
				for(int y = 0; y < state[0].length; y++) {
					state[x][y] = newStateFunction.apply(lastState).apply(x).apply(y);
				}
			}
		}
		int amount = 0;
		for(char[] row : state) {
			for(char c : row) {
				if(c == '#') {
					amount++;
				}
			}
		}
		return amount;
	}

	private char getNewState1(char[][] lastState, int x, int y) {
		if(lastState[x][y] == '.') {
			return '.';
		}
		boolean empty = lastState[x][y] == 'L';
		int neighbours = 0;
		for(int dx = -1; dx <= 1; dx++) {
			for(int dy = -1; dy <= 1; dy++) {
				if(dx != 0 || dy != 0) {
					int nx = x + dx;
					int ny = y + dy;
					if(nx >= 0 && ny >= 0 && nx < lastState.length && ny < lastState[0].length) {
						if(lastState[nx][ny] == '#') {
							if(empty) {
								return 'L';
							}
							neighbours++;
							if(neighbours >= 4) {
								return 'L';
							}
						}
					}
				}
			}
		}
		return '#';
	}

	private char getNewState2(char[][] lastState, int x, int y) {
		if(lastState[x][y] == '.') {
			return '.';
		}
		boolean empty = lastState[x][y] == 'L';
		int neighbours = 0;
		for(int dx = -1; dx <= 1; dx++) {
			for(int dy = -1; dy <= 1; dy++) {
				if (dx != 0 || dy != 0) {
					int nx = x+dx;
					int ny = y+dy;
					while(nx >= 0 && ny >= 0 && nx < lastState.length && ny < lastState[0].length && lastState[nx][ny] == '.') {
						nx += dx;
						ny += dy;
					}
					if(nx >= 0 && ny >= 0 && nx < lastState.length && ny < lastState[0].length) {
						if(lastState[nx][ny] == '#') {
							if(empty) {
								return 'L';
							}
							neighbours++;
							if(neighbours >= 5) {
								return 'L';
							}
						}
					}
				}
			}
		}
		return '#';
	}
}
