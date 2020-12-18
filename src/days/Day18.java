package days;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;
import java.util.function.Function;

public class Day18 extends Day {

	@Override
	protected int getChallengeNumber() {
		return 18;
	}
	
	@Override
	protected Object part1(List<String> input) {
		return getTotal(input, this::calc1);
	}

	@Override
	protected Object part2(List<String> input) {
		return getTotal(input, this::calc2);
	}
	
	private long getTotal(List<String> input, Function<String, Long> calcFunction) {
		long sum = 0;
		for(String line : input) {
			while(line.contains("(")) {
				int start = line.indexOf('(');
				int end = start + 1;
				while(line.charAt(end) != ')') {
					if(line.charAt(end) == '(') {
						start = end;
					}
					end++;
				}
				long intermediary = calcFunction.apply(line.substring(start + 1, end));
				line = line.substring(0, start) + intermediary + line.substring(end + 1);
			}
			sum += calcFunction.apply(line);
		}
		return sum;
	}
	
	private long calc1(String input) {
		String[] line = input.split(" ");
		long total = Long.parseLong(line[0]);
		for(int i = 1; i < line.length; i += 2) {
			char operator = line[i].charAt(0);
			long operand = Long.parseLong(line[i+1]);
			if(operator == '+') {
				total += operand;
			} else {
				total *= operand;
			}
		}
		return total;
	}
	
	private long calc2(String input) {
		Long product = 1L;
		String[] line = input.split(" \\* ");
		for(String sum : line) {
			Long total = 0L;
			for(String s : sum.split(" \\+ ")) {
				total += Long.parseLong(s);
			}
			product *= total;
		}
		return product;
	}
}
