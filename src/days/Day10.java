package days;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Day10 extends Day {

	@Override
	protected int getChallengeNumber() {
		return 10;
	}
	
	@Override
	protected Object part1(List<String> input) {
		int[] diff = new int[3];
		List<Integer> sorted = input.stream().map(Integer::parseInt)
				.sorted()
				.collect(Collectors.toList());
		for(int i = 0; i < sorted.size() - 1; i++) {
			diff[sorted.get(i+1) - sorted.get(i) - 1]++;
		}
		diff[sorted.get(0) - 1]++;
		diff[2]++;
		return diff[0] * diff[2];
	}

	@Override
	protected Object part2(List<String> input) {
		Map<Integer, Long> ways = new HashMap<>();
		List<Integer> sorted = input.stream().map(Integer::parseInt)
				.sorted()
				.collect(Collectors.toList());
		sorted.add(0,0);
		return getWays(sorted, ways, 0).get(0);
	}

	private Map<Integer, Long> getWays(List<Integer> sorted, Map<Integer, Long> ways, int currentPos) {
		if(ways.containsKey(currentPos)) {
			return ways;
		}
		if(currentPos == sorted.size() - 1) {
			ways.put(currentPos, 1L);
			return ways;
		}
		long myWays = 0;
		for(int i = 1; i <= 3; i++) {
			if(currentPos+i < sorted.size() && sorted.get(currentPos+i) - sorted.get(currentPos) <= 3) {
				getWays(sorted, ways, currentPos+i);
				myWays += ways.get(currentPos+i);
			}
		}
		ways.put(currentPos, myWays);
		return ways;
	}
}
