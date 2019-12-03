package advent;

import java.io.IOException;

import days.Day;

public class AdventRunner {
	
	public static void main(String[] args) throws IOException {
		new AdventRunner().run();
	}

	private void run() throws IOException {
		System.out.println(Day.getLastDay().part1());
		System.out.println(Day.getLastDay().part2());
	}
}