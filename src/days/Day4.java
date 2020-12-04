package days;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class Day4 extends Day {

	private static Map<String, Predicate<String>> REQUIRED = new HashMap<>();
	static {
		REQUIRED.put("byr", getNumberPredicate(1920, 2002));
		REQUIRED.put("iyr", getNumberPredicate(2010, 2020));
		REQUIRED.put("eyr", getNumberPredicate(2020, 2030));
		REQUIRED.put("hgt", getHeightPredicate());
		REQUIRED.put("hcl", s -> s.matches("^#[0-9a-f]{6}$"));
		REQUIRED.put("ecl", s -> s.matches("^(amb|blu|brn|gry|grn|hzl|oth)$"));
		REQUIRED.put("pid", s -> s.matches("^[0-9]{9}$"));
	}
	
	@Override
	protected int getChallengeNumber() {
		return 4;
	}

	private static Predicate<String> getHeightPredicate() {
		return s -> {
			if(s.endsWith("cm")) {
				if(s.length() != 5) {
					return false;
				}
				return getNumberPredicate(150, 193).test(s.substring(0, 3));
			} else if(s.endsWith("in")) {
				if(s.length() != 4) {
					return false;
				}
				return getNumberPredicate(59, 76).test(s.substring(0, 2));
			} else {
				return false;
			}
		};
	}

	private static Predicate<String> getNumberPredicate(int min, int max) {
		return s -> {
			try {
				int i = Integer.parseInt(s);
				return min <= i && max >= i;
			} catch (NumberFormatException e) {
				return false;
			}
		};
	}

	@Override
	protected Object part1(List<String> input) {
		int total = 0;
		for(int i = 0; i < input.size(); i++) {
			Set<String> presentFields = new HashSet<>();
			while(input.size() > i && !input.get(i).isEmpty()) {
				presentFields.addAll(
						Arrays.stream(input.get(i).split(" "))
						.map(s -> s.split(":")[0])
						.collect(Collectors.toSet()));
				i++;
			}
			if(REQUIRED.keySet().stream().allMatch(presentFields::contains)) {
				total++;
			}
		}
		return total;
	}

	@Override
	protected Object part2(List<String> input) {
		int total = 0;
		for(int i = 0; i < input.size(); i++) {
			Map<String, String> presentFields = new HashMap<>();
			while(input.size() > i && !input.get(i).isEmpty()) {
				Arrays.stream(input.get(i).split(" "))
						.map(s -> s.split(":"))
						.forEach(p -> presentFields.put(p[0], p[1]));
				i++;
			}
			if(REQUIRED.keySet().stream().allMatch(k -> presentFields.containsKey(k) && REQUIRED.get(k).test(presentFields.get(k)))) {
				total++;
			}
		}
		return total;
	}
}
