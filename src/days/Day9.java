package days;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

public class Day9 extends Day {

	private int preamble = 25;

	@Override
	protected int getChallengeNumber() {
		return 9;
	}

	Day9() {

	}

	Day9(int preamble) {
		this.preamble = preamble;
	}

	@Override
	protected Object part1(List<String> input) {
		// For a preamble of size p and a list of size n, this is O(p log p + p * n) I think
		List<Long> numbers = input.stream().map(Long::parseLong).collect(Collectors.toList());
		LinkedList<Long> last = new LinkedList<>();
		for(int i = 0; i < preamble; i++) {
			last.add(numbers.get(i));
		}
		last.sort(Long::compare);
		for(int i = preamble; i < numbers.size(); i++) {
			if(!canBeSum(last, numbers.get(i))) {
				return numbers.get(i);
			}
			last.remove(numbers.get(i - preamble));
			int j = preamble - 2;
			// CanBeSum assumes last is sorted, so we have to insert at the correct position. Since the new number is
			// likely to be higher than most others, we start checking from the back
			while(j >= 0 && last.get(j) > numbers.get(i)) {
				j--;
			}
			last.add(j+1, numbers.get(i));
		}
		return null;
	}

	private boolean canBeSum(LinkedList<Long> last, Long sum) {
		Iterator<Long> forward = last.iterator();
		Iterator<Long> backward = last.descendingIterator();
		long lower = forward.next();
		long higher = backward.next();
		while(lower != higher) {
			if(sum == lower + higher) {
				return true;
			} else if (sum > lower + higher) {
				lower = forward.next();
			} else {
				higher = backward.next();
			}
		}
		return false;
	}


	@Override
	protected Object part2(List<String> input) {
		long target = (Long) part1(input);
		List<Long> numbers = input.stream().map(Long::parseLong).collect(Collectors.toList());
		int i = 1;
		int j = 2;
		long sum = numbers.get(i) + numbers.get(j);
		while(sum != target) {
			if(sum < target) {
				j++;
				sum += numbers.get(j);
			} else {
				sum -= numbers.get(i);
				i++;
			}
		}

		long min = Long.MAX_VALUE;
		long max = 0;

		for(int k = i; k <= j; k++) {
			if(numbers.get(k) < min) {
				min = numbers.get(k);
			}
			if(numbers.get(k) > max) {
				max = numbers.get(k);
			}
		}

		return min + max;
	}
}
