package days;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Day5 extends Day {

	@Override
	protected int getChallengeNumber() {
		return 5;
	}
	
	@Override
	protected Object part1(List<String> input) {
		int max = 0;
		for(String pass : input) {
			int seat = getSeatNumber(pass);
			if(seat > max) {
				max = seat;
			}
		}
		return max;
	}

	@Override
	protected Object part2(List<String> input) {
		Set<Integer> seats = new HashSet<>();
		for(int i = 0; i < 1024; i++) {
			seats.add(i);
		}
		int max = 0;
		int min = 1023;
		for(String pass : input) {
			int seat = getSeatNumber(pass);
			if(seat > max) {
				max = seat;
			}
			if(seat < min) {
				min = seat;
			}
			seats.remove(seat);
		}
		int minFinal = min;
		int maxFinal = max;
		return seats.stream()
				.filter(s -> s > minFinal && s < maxFinal)
				.findAny().get();
	}
	
	private int getSeatNumber(String pass) {
		int i = 0;
		int max = 1023;
		int min = 0;
		while(max > min) {
			if(pass.charAt(i) == 'F' || pass.charAt(i) == 'L') {
				max = (min + max - 1) / 2;
			} else {
				min = (min + max + 1) / 2;
			}
			i++;
		}
		return min;
	}
}
