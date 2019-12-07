package days;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public abstract class Day {
	
	public static final Day[] DAYS = new Day[] {
			new Day1(),
			new Day2(),
			new Day3(),
			new Day4(),
			new Day5(),
			new Day6(),
			new Day7()/*,
			new Day8(),
			new Day9(),
			new Day10(),
			new Day11(),
			new Day12(),
			new Day13(),
			new Day14(),
			new Day15(),
			new Day16(),
			new Day17(),
			new Day18(),
			new Day19(),
			new Day20(),
			new Day21(),
			new Day22(),
			new Day23(),
			new Day24(),
			new Day25()*/
		};
	
	protected abstract int getChallengeNumber();
	protected abstract Object part1(List<String> input);
	protected abstract Object part2(List<String> input);

	public String part1() throws IOException {
		return part1(getInputFile(1)) + "";
	}
	public String part2() throws IOException {
		return part2(getInputFile(sameInputFile() ? 1 : 2)) + "";
	}
	
	protected boolean sameInputFile() {
		return true;
	}
	
	protected List<String> getInputFile(int part) throws IOException {
		try (Stream<String> stream = Files.lines(Paths.get("resources/input" + getChallengeNumber() + "." + part))) {
			return stream.collect(Collectors.toList());
		}
	}
	
	public static Day getDay(int num) {
		return DAYS[num];
	}
	
	public static Day getLastDay() {
		return DAYS[DAYS.length - 1];
	}
}
