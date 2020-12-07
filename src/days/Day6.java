package days;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Day6 extends Day {

	@Override
	protected int getChallengeNumber() {
		return 6;
	}
	
	@Override
	protected Object part1(List<String> input) {
		int total = 0;
		for(int i = 0; i < input.size(); i++) {
			Set<Character> yesses = new HashSet<>();
			while(input.size() > i && !input.get(i).isEmpty()) {
				for(char c : input.get(i).toCharArray()) {
					yesses.add(c);
				}
				i++;
			}
			total += yesses.size();
		}
		return total;
	}

	@Override
	protected Object part2(List<String> input) {
		int total = 0;
		for(int i = 0; i < input.size(); i++) {
			Set<Character> yesses = new HashSet<>();
			for(char c : input.get(i).toCharArray()) {
				yesses.add(c);
			}
			while(input.size() > i && !input.get(i).isEmpty()) {
				Set<Character> newYesses = new HashSet<>();
				for(char c : input.get(i).toCharArray()) {
					newYesses.add(c);
				}
				yesses.retainAll(newYesses);
				i++;
			}
			total += yesses.size();
		}
		return total;
	}
}
