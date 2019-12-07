package days;

import java.util.ArrayList;
import java.util.List;

public class Day4 extends Day {

	@Override
	protected int getChallengeNumber() {
		return 4;
	}
	
	@Override
	protected Object part1(List<String> input) {
		int min = Integer.parseInt(input.get(0));
		int max = Integer.parseInt(input.get(1));
		return getEligible(min, max).size();
	}

	@Override
	protected Object part2(List<String> input) {
		int min = Integer.parseInt(input.get(0));
		int max = Integer.parseInt(input.get(1));
		return getEligible(min, max).stream()
			.filter(a ->
				("" + a).replaceAll("([0-9])\\1\\1+", "")
					.matches(".*([0-9])\\1.*"))
			.count();
				
	}

	List<Integer> getEligible(int min, int max) {
		List<Integer> ints = new ArrayList<>();
		for(int ht = 1; ht < 10; ht++) {
			for(int tt = ht; tt < 10; tt++) {
				for(int th = tt; th < 10; th++) {
					for(int h = th; h < 10; h++) {
						for(int t = h; t < 10; t++) {
							for(int o = t; o < 10; o++) {
								int result = 100000*ht + 10000*tt + 1000*th + 100*h + 10*t + o;
								if(result >= min) {
									if(result > max) {
										return ints;
									} else if (ht == tt || tt == th || th == h || h == t || t == o) {
										ints.add(result);
									}
								}
							}
						}
					}
				}
			}
		}
		throw new IllegalStateException();
	}
}
