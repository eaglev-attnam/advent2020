package days;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.stream.Collectors;

public class Day21 extends Day {
	
	@Override
	protected int getChallengeNumber() {
		return 21;
	}
	
	@Override
	protected Object part1(List<String> input) {
		Map<String, Set<String>> possibleAllergens = getPossibleAllergens(input);
		Set<String> unsafeIngredients = new HashSet<>();
		for(Set<String> possibleIngredients : possibleAllergens.values()) {
			unsafeIngredients.addAll(possibleIngredients);
		}
		int total = 0;
		for(String food : input) {
			for(String ingredient : food.split(" \\(")[0].split(" ")) {
				if(!unsafeIngredients .contains(ingredient)) {
					total++;
				}
			}
		}
		return total;
	}

	@Override
	protected Object part2(List<String> input) {
		Map<String, Set<String>> possibleAllergens = getPossibleAllergens(input);
		Set<String> found = new HashSet<>();
		boolean done = false;
		while(!done) {
			done = true;
			for(Entry<String, Set<String>> entry : possibleAllergens.entrySet()) {
				if(entry.getValue().size() == 1) {
					found.addAll(entry.getValue());
				} else {
					done = false;
					entry.getValue().removeAll(found);
					possibleAllergens.put(entry.getKey(), entry.getValue());
				}
			}
		}
		return possibleAllergens.entrySet().stream()
			.sorted((a,b) -> a.getKey().compareTo(b.getKey()))
			.flatMap(a -> a.getValue().stream())
			.collect(Collectors.joining(","));
	}
	
	private Map<String, Set<String>> getPossibleAllergens(List<String> input) {
		Map<String, Set<String>> possibleAllergens = new HashMap<>();
		for(String food : input) {
			String[] splitfood = food.split(" \\(");
			List<String> foodIngredients = Arrays.asList(splitfood[0].split(" "));
			String[] contains = splitfood[1].substring(9, splitfood[1].length() - 1).split(", ");
			for(String allergen : contains) {
				if(!possibleAllergens.containsKey(allergen)) {
					possibleAllergens.put(allergen, new HashSet<>(foodIngredients));
				} else {
					possibleAllergens.get(allergen).retainAll(foodIngredients);
				}
			}
		}
		return possibleAllergens;
	}
}
