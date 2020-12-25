package days;

import java.util.List;

public class Day25 extends Day {
	
	private static final int SUBJECT = 7;
	private static final int DIVIDER = 20201227;
	
	@Override
	protected int getChallengeNumber() {
		return 25;
	}
	
	@Override
	protected Object part1(List<String> input) {
		int card = Integer.parseInt(input.get(0));
		int door = Integer.parseInt(input.get(1));
		int s = SUBJECT;
		int turns = 0;
		while(s != card && s != door) {
			s *= SUBJECT;
			s %= DIVIDER;
			turns++;
		}
		long multiplicand = card;
		if(s == card) {
			multiplicand = door;
		}
		long newSubject = multiplicand;
		for(int i = 0; i < turns; i++) {
			multiplicand *= newSubject;
			multiplicand %= DIVIDER;
		}
		return multiplicand;
	}

	@Override
	protected Object part2(List<String> input) {
		return "Merry christmas!";
	}
}
