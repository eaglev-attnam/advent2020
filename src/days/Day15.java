package days;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Day15 extends Day {

	@Override
	protected int getChallengeNumber() {
		return 15;
	}
	
	@Override
	protected Object part1(List<String> input) {
		return getResultAfterXTurns(input, 2020);
	}

	@Override
	protected Object part2(List<String> input) {
		return getResultAfterXTurns(input, 30_000_000);
	}

	private int getResultAfterXTurns(List<String> input, int turns) {
		String[] initial = input.get(0).split(",");
		Map<Integer, Integer> lastTurn = new HashMap<>();
		int number = -1;
		int nextNumber = -1;
		for(int turn = 0; turn < turns; turn++) {
			if(turn < initial.length) {
				number = Integer.parseInt(initial[turn]);
			} else {
				number = nextNumber;
			}
			if(lastTurn.containsKey(number)) {
				nextNumber = turn - lastTurn.get(number);
			} else {
				nextNumber = 0;
			}
			lastTurn.put(number, turn);
		}
		return number;
	}
}
