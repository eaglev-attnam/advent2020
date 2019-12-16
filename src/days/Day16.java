package days;

import java.util.Arrays;
import java.util.List;

public class Day16 extends Day {

	@Override
	protected int getChallengeNumber() {
		return 16;
	}
	
	@Override
	protected Object part1(List<String> input) {
		return null;
	}

	@Override
	protected Object part2(List<String> input) {
		int[] number = new int[input.get(0).length() * 10_000];
		for(int c = 0; c < input.get(0).length(); c++) {
			for(int i = 0; i < 10_000; i++) {
				number[c + (i * input.get(0).length())] = Integer.parseInt("" + input.get(0).charAt(c));
			}
		}
		int offset = 0;
		for(int i = 0; i < 7; i++) {
			offset *= 10;
			offset += number[i];
		}
		number = Arrays.copyOfRange(number, offset, number.length);
		for(int i = 0; i < 100; i++) {
			int[] next = new int[number.length];
			for(int j = 0; j < number.length; j++) {
				next[0] += number[j];
				next[0] %= 10;
			}
			for(int j = 1; j < number.length; j++) {
				next[j] = next[j-1] - number[j-1];
				next[j] += 10;
				next[j] %= 10;
			}
			number = next;
		}
		return Arrays.toString(number);
	}
}
