package days;

import java.util.List;

public class Day3 extends Day {

	@Override
	protected int getChallengeNumber() {
		return 3;
	}
	
	@Override
	protected Object part1(List<String> input) {
		return checkSlope(3, false, input);
	}

	@Override
	protected Object part2(List<String> input) {
		long totalTrees = 1;
		for(int slope : new int[]{1, 3, 5, 7}) {
			totalTrees *= checkSlope(slope, false, input);
		}
		totalTrees *= checkSlope(1, true, input);
		return totalTrees;
	}
	
	private int checkSlope(int slope, boolean steep, List<String> input) {
		int x = 0;
		int trees = 0;
		int steepness = (steep ? 2 : 1);
		for(int y = 0; y < input.size(); y += steepness) {
			String row = input.get(y);
			if(row.charAt(x) == '#') {
				trees++;
			}
			x += slope;
			x %= row.length();
		}
		return trees;
	}
}
