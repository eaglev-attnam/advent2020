package advent;

import java.io.IOException;

import days.Day;

public class AdventRunner {
	
	public static void main(String[] args) throws IOException {
		if(args.length > 0) {
			try {
				new AdventRunner().run(Integer.parseInt(args[0]));
			} catch (NumberFormatException ex) {
				new AdventRunner().run(null);
			}
		} else {
			new AdventRunner().run(null);
		}
	}

	private void run(Integer dayNum) throws IOException {
		Day day;
		if(dayNum == null) {
			day = Day.getLastDay();
		} else {
			day = Day.getDay(dayNum);
		}
		long start = System.currentTimeMillis();
		Object out1 = day.part1();
		System.out.println("Part 1 took " + (System.currentTimeMillis() - start) + "ms");
		System.out.println(out1);
		start = System.currentTimeMillis();
		Object out2 = day.part2();
		System.out.println("Part 2 took " + (System.currentTimeMillis() - start) + "ms");
		System.out.println(out2);
		day.get1Visual().ifPresent(v -> v.show(out1));
		day.get2Visual().ifPresent(v -> v.show(out2));
	}
}