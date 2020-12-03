package days;

import java.util.List;

public class Day2 extends Day {

	@Override
	protected int getChallengeNumber() {
		return 2;
	}
	
	@Override
	protected Object part1(List<String> input) {
		int total = 0;
		for(String line : input) {
			String[] parts = line.split("[-: ]");
			int min = Integer.parseInt(parts[0]);
			int max = Integer.parseInt(parts[1]);
			String target = parts[2];
			int length = parts[4].split(target, -1).length - 1;
			if(length >= min && length <= max) {
				total++;
			}
		}
		return total;
	}

	@Override
	protected Object part2(List<String> input) {
		int total = 0;
		for(String line : input) {
			String[] parts = line.split("[-: ]");
			int first = Integer.parseInt(parts[0]) - 1;
			int second = Integer.parseInt(parts[1]) - 1;
			Character target = parts[2].charAt(0);
			if(target.equals(parts[4].charAt(first)) != target.equals(parts[4].charAt(second))) {
				total++;
			}
		}
		return total;
	}
}
