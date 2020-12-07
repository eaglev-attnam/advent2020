package days;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Day7 extends Day {

	@Override
	protected int getChallengeNumber() {
		return 7;
	}
	
	@Override
	protected Object part1(List<String> input) {
		Map<String, Set<String>> subs = new HashMap<>();
		for(String rule : input) {
			subs = parseNoNumber(rule, subs);
		}
		Map<String, Boolean> canContainShinyGold = new HashMap<>();
		canContainShinyGold.put("shiny gold", true);
		int total = 0;
		for(String color : subs.keySet()) {
			if(checkColor(color, canContainShinyGold, subs)) {
				total++;
			}
		}
		return total - 1; // Original bag doesn't count
	}

	private boolean checkColor(String color, Map<String, Boolean> canContainShinyGold, Map<String, Set<String>> subs) {
		if(canContainShinyGold.containsKey(color)) {
			return canContainShinyGold.get(color);
		} else {
			for(String sub : subs.get(color)) {
				if(checkColor(sub, canContainShinyGold, subs)) {
					canContainShinyGold.put(color, true);
					return true;
				}
			}
			canContainShinyGold.put(color, false);
			return false;
		}
	}

	private  Map<String, Set<String>> parseNoNumber(String rule, Map<String, Set<String>> subs) {
		String[] words = rule.split(" ");
		String bagColor = words[0] + " " + words[1]; // 5 6, 9 10
		if(rule.endsWith("no other bags.")) {
			subs.put(bagColor, new HashSet<>());
			return subs;
		}
		Set<String> thisSubs = new HashSet<>();
		for(int i = 5; i < words.length; i+= 4) {
			thisSubs.add(words[i] + " " + words[i+1]);
		}
		subs.put(bagColor, thisSubs);
		return subs;
	}

	@Override
	protected Object part2(List<String> input) {
		Map<String, Map<String, Integer>> subs = new HashMap<>();
		for(String rule : input) {
			subs = parse(rule, subs);
		}
		return getNumberIn("shiny gold", subs);
	}

	private Long getNumberIn(String color, Map<String, Map<String, Integer>> subs) {
		long number = 0;
		for(Map.Entry<String, Integer> sub : subs.get(color).entrySet()) {
			number += sub.getValue();
			number += sub.getValue() * getNumberIn(sub.getKey(), subs);
		}
		return number;
	}

	private Map<String, Map<String, Integer>> parse(String rule, Map<String, Map<String, Integer>> subs) {
		String[] words = rule.split(" ");
		String bagColor = words[0] + " " + words[1]; // 5 6, 9 10
		if(rule.endsWith("no other bags.")) {
			subs.put(bagColor, new HashMap<>());
			return subs;
		}
		Map<String, Integer> thiSubs = new HashMap<>();
		for(int i = 5; i < words.length; i+= 4) {
			thiSubs.put(words[i] + " " + words[i+1], Integer.parseInt(words[i-1]));
		}
		subs.put(bagColor, thiSubs);
		return subs;
	}
}
