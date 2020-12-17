package days;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Predicate;

public class Day16 extends Day {

	@Override
	protected int getChallengeNumber() {
		return 16;
	}
	
	@Override
	protected Object part1(List<String> input) {
		List<Predicate<Integer>> tests = new ArrayList<>();
		int i = 0;
		while(!input.get(i).isEmpty()) {
			String line = input.get(i);
			line = line.split(": ")[1];
			String[] parts = line.split("[ -]");
			int min1 = Integer.parseInt(parts[0]);
			int max1 = Integer.parseInt(parts[1]);
			int min2 = Integer.parseInt(parts[3]);
			int max2 = Integer.parseInt(parts[4]);
			tests.add(isInRange(min1, max1, min2, max2));
			i++;
		}
		Predicate<Integer> testAll = tests.get(0);
		for(int t = 0; t < tests.size(); t++) {
			testAll = testAll.or(tests.get(t));
		}
		i += 5;
		int error = 0;
		while(i < input.size()) {
			String[] parts = input.get(i).split(",");
			for(String part : parts) {
				int value = Integer.parseInt(part);
				if(!testAll.test(value)) {
					error += value;
				}
			}
			i++;
		}
		return error;
	}

	@Override
	protected Object part2(List<String> input) {
		Map<String, Predicate<Integer>> tests = new HashMap<>();
		int i = 0;
		while(!input.get(i).isEmpty()) {
			String[] line = input.get(i).split(": ");
			String[] parts = line[1].split("[ -]");
			int min1 = Integer.parseInt(parts[0]);
			int max1 = Integer.parseInt(parts[1]);
			int min2 = Integer.parseInt(parts[3]);
			int max2 = Integer.parseInt(parts[4]);
			tests.put(line[0], isInRange(min1, max1, min2, max2));
			i++;
		}
		Predicate<Integer> testAll = a -> false;
		for(Predicate<Integer> test : tests.values()) {
			testAll = testAll.or(test);
		}
		i += 2;
		String[] myTicket = input.get(i).split(",");
		List<Set<String>> possibleFields = new ArrayList<>(myTicket.length);
		for(int j = 0; j < myTicket.length; j++) {
			possibleFields.add(new HashSet<>(tests.keySet()));
			for(Map.Entry<String, Predicate<Integer>> entry : tests.entrySet()) {
				if(!entry.getValue().test(Integer.parseInt(myTicket[j]))) {
					possibleFields.get(j).remove(entry.getKey());
				}
			}
		}
		i += 3;
		while(i < input.size()) {
			String[] parts = input.get(i).split(",");
			boolean isValid = Arrays.stream(parts).map(Integer::parseInt).allMatch(testAll);
			if(isValid) {
				for (int j = 0; j < parts.length; j++) {
					int value = Integer.parseInt(parts[j]);
					for (Map.Entry<String, Predicate<Integer>> entry : tests.entrySet()) {
						if (!entry.getValue().test(value)) {
							possibleFields.get(j).remove(entry.getKey());
						}
					}
				}
			}
			i++;
		}
		boolean done = false;
		Set<String> locked = new HashSet<>();
		while(!done) {
			done = true;
			for(int j = 0; j < possibleFields.size(); j++){
				if(possibleFields.get(j).size() == 1) {
					locked.add(possibleFields.get(j).iterator().next());
				} else {
					done = false;
				}
			}
			for(int j = 0; j < possibleFields.size(); j++){
				if(possibleFields.get(j).size() > 1) {
					possibleFields.get(j).removeAll(locked);
				}
			}
		}
		long product = 1L;
		for(int j = 0; j < possibleFields.size(); j++) {
			String singleField = possibleFields.get(j).iterator().next();
			if(singleField.startsWith("departure ")) {
				product *= Long.parseLong(myTicket[j]);
			}
		}
		return product;
	}

	private Predicate<Integer> isInRange(int min1, int max1, int min2, int max2) {
		return x ->
				(x >= min1 && x <= max1) ||
				(x >= min2 && x <= max2);
	}
}
