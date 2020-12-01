package days;

import java.util.List;
import java.util.stream.Collectors;

public class Day1 extends Day {

	@Override
	protected int getChallengeNumber() {
		return 1;
	}
	
	@Override
	protected Object part1(List<String> input) {
		List<Integer> parsed = input.stream()
				.map(Integer::parseInt)
				.collect(Collectors.toList());
		for(int i = 0; i < parsed.size(); i++) {
			for(int j = i+1; j < parsed.size(); j++) {
				if(parsed.get(i) + parsed.get(j) == 2020) {
					return parsed.get(i) * parsed.get(j);
				}
			}
		}
		return 0;
	}

	@Override
	protected Object part2(List<String> input) {
		List<Integer> parsed = input.stream()
				.map(Integer::parseInt)
				.collect(Collectors.toList());
		for(int i = 0; i < parsed.size(); i++) {
			for(int j = i+1; j < parsed.size(); j++) {
				for(int k = j+1; k < parsed.size(); k++) {
					if(parsed.get(i) + parsed.get(j) + parsed.get(k) == 2020) {
						return parsed.get(i) * parsed.get(j) * parsed.get(k);
					}
				}
			}
		}
		return 0;
	}
}
