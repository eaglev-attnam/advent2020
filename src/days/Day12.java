package days;

import java.util.List;

public class Day12 extends Day {

	@Override
	protected int getChallengeNumber() {
		return 12;
	}
	
	@Override
	protected Object part1(List<String> input) {
		int[] facing = new int[]{1,0};
		int x = 0;
		int y = 0;
		for(String instruction : input) {
			char heading = instruction.charAt(0);
			int amount = Integer.parseInt(instruction.substring(1));
			switch (heading) {
			case 'N':
				y -= amount;
				break;
			case 'E':
				x += amount;
				break;
			case 'S':
				y += amount;
				break;
			case 'W':
				x -= amount;
				break;
			case 'L':
				amount = 360 - amount;
			case 'R':
				for(int i = 0; i*90 < amount; i++) {
					int tmp = facing[0];
					facing[0] = -facing[1];
					facing[1] = tmp;
				}
				break;
			case 'F':
				x += amount * facing[0];
				y += amount * facing[1];
				break;
			default:
			}
		}
		return Math.abs(x) + Math.abs(y);
	}

	@Override
	protected Object part2(List<String> input) {
		int[] waypoint = new int[]{10,-1};
		int x = 0;
		int y = 0;
		for(String instruction : input) {
			char heading = instruction.charAt(0);
			int amount = Integer.parseInt(instruction.substring(1));
			switch (heading) {
			case 'N':
				waypoint[1] -= amount;
				break;
			case 'E':
				waypoint[0] += amount;
				break;
			case 'S':
				waypoint[1] += amount;
				break;
			case 'W':
				waypoint[0] -= amount;
				break;
			case 'L':
				amount = 360 - amount;
			case 'R':
				for(int i = 0; i*90 < amount; i++) {
					int tmp = waypoint[0];
					waypoint[0] = -waypoint[1];
					waypoint[1] = tmp;
				}
				break;
			case 'F':
				x += amount * waypoint[0];
				y += amount * waypoint[1];
				break;
			default:
			}
		}
		return Math.abs(x) + Math.abs(y);
	}
}
