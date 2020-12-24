package days;

import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

public class Day23 extends Day {
	
	@Override
	protected int getChallengeNumber() {
		return 23;
	}
	
	@Override
	protected Object part1(List<String> input) {
		int rounds = Integer.parseInt(input.get(1));
		LinkedList<Integer> state = new LinkedList<>();
		state.addAll(Arrays.stream(input.get(0).split(""))
				.map(Integer::parseInt)
				.collect(Collectors.toList()));
		for(int i = 0; i < rounds; i++) {
			Integer current = state.pop();
			int[] move = new int[3];
			for(int j = 0; j < 3; j++) {
				move[j] = state.pop();
			}
			int next = current;
			do {
				next--;
				if(next < 1) {
					next = 9;
				}
			} while(!state.contains(next));
			int pos = state.indexOf(next);
			for(int j = 2; j >= 0; j--) {
				state.add(pos+1, move[j]);
			}
			state.addLast(current);
		}
		while(!state.peek().equals(1)) {
			state.add(state.pop());
		}
		state.pop();
		return state.stream()
				.map(a -> a + "")
				.collect(Collectors.joining());
	}

	@Override
	protected Object part2(List<String> input) {
		int[] next = new int[1_000_001];
		int last = 1_000_000;
		for(String s : input.get(0).split("")) {
			int current = Integer.parseInt(s);
			next[last] = current;
			last = current;
		}
		for(int i = 10; i <= 1_000_000; i++) {
			next[last] = i;
			last = i;
		}
		int current = next[1_000_000];
		for(int r = 0; r < 10_000_000; r++) {
			// c-1 a  ...  c w x y z => c-1 w x y a ... c z
			int w = next[current];
			int x = next[w];
			int y = next[x];
			int z = next[y];
			int cMinus = current;
			do {
				cMinus--;
				if(cMinus <= 0) {
					cMinus = 1_000_000;
				}
			} while(w == cMinus || x == cMinus || y == cMinus);
			int a = next[cMinus];
			
			next[cMinus] = w;
			next[y] = a;
			next[current] = z;
			current = z;
		}
		return 1L * next[1] * next[next[1]];
	}
}
