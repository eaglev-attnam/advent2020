package advent;

import java.io.IOException;

import days.Day;

public class AdventRunner {
	
	public static void main(String[] args) throws IOException {
		new AdventRunner().run();
	}

	private void run() throws IOException {
		Day day = Day.getLastDay();
		Object out1 = day.part1();
		Object out2 = day.part2();
		System.out.println(out1);
		System.out.println(out2);
		day.get1Visual().ifPresent(v -> v.show(out1));
		day.get2Visual().ifPresent(v -> v.show(out2));
	}
}