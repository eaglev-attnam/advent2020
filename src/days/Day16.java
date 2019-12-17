package days;

import java.util.Arrays;
import java.util.List;

public class Day16 extends Day {

	@Override
	protected int getChallengeNumber() {
		return 16;
	}

	private static final int[] PATTERN = new int[]{0, 1, 0, -1};

	@Override
	protected Object part1(List<String> input) {
		int[] number = new int[input.get(0).length()];
		for(int i = 0; i < input.get(0).length(); i++) {
			number[i] = Integer.parseInt(input.get(0).charAt(i) + "");
		}
		for(int i = 0; i < 100; i++) {
			//System.out.println(Arrays.toString(number));
			number = fft(number);

		}
		return Arrays.toString(number);
	}

	public int[] fft(int[] input) {
		int[] newNumber = new int[input.length];
		int startOfZeroes = 0;
		for(int round = 1; round <= input.length; round++) {
			int sum = 0;
			for(int i = round; i <= input.length; i++) {
				int modifier = PATTERN[(i / round) % PATTERN.length];
				if(modifier != 0) {
					sum += modifier * input[i - 1];
				}
			}
			sum = Math.abs(sum);
			sum %= 10;
			if(sum > 0) {
				startOfZeroes = round;
			}
			newNumber[round - 1] = (int) sum;
		}
		newNumber = Arrays.copyOfRange(newNumber, 0, startOfZeroes);
		System.out.println("Dropped " + (input.length - startOfZeroes));
		return newNumber;
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
