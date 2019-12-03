package days;

import java.util.List;

public class Day1 extends Day {

	@Override
	protected int getChallengeNumber() {
		return 1;
	}
	
	@Override
	protected Object part1(List<String> input) {
		return input.stream()
			.map(Integer::parseInt)
			.map(this::getFuelNeeded)
			.reduce(0, (a,b) -> a + b);
	}

	@Override
	protected Object part2(List<String> input) {
		return input.stream()
				.map(Integer::parseInt)
				.map(this::getMoreFuelNeeded)
				.reduce(0, (a,b) -> a + b);
	}

	private int getFuelNeeded(int moduleMass) {
		int thirdModule = moduleMass / 3;
		return thirdModule - 2;
	}
	
	private int getMoreFuelNeeded(int moduleMass) {
		int newFuel = getFuelNeeded(moduleMass);
		int totalFuel = newFuel;
		while(newFuel >= 9) {
			newFuel = getFuelNeeded(newFuel);
			totalFuel += newFuel;
		}
		return totalFuel;
	}
}
