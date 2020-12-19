package days;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Predicate;

public class Day19 extends Day {
	
	private static final int MAX_LENGTH = 90;
	
	@Override
	protected int getChallengeNumber() {
		return 19;
	}
	
	@Override
	protected Object part1(List<String> input) {
		return getMatches(input, false);
	}

	@Override
	protected Object part2(List<String> input) {
		return getMatches(input, true);
	}
	
	private int getMatches(List<String> input, boolean replace8and11) {
		Map<Integer, String> ruleLines = new HashMap<>();
		int matches = 0;
		int i = 0;
		String line = input.get(i);
		while(!line.isEmpty()) {
			String[] split = line.split(": ");
			ruleLines.put(Integer.parseInt(split[0]), split[1]);
			i++;
			line = input.get(i);
		}
		Map<Integer, PredWithLength> rules = parseRuleLines(ruleLines, replace8and11);
		i++;
		while(i < input.size()) {
			if(rules.get(0).test(input.get(i))) {
				matches++;
			}
			i++;
		}
		return matches;
	}

	private Map<Integer, PredWithLength> parseRuleLines(Map<Integer, String> ruleLines, boolean replace8and11) {
		Map<Integer, PredWithLength> rules = new HashMap<>();
		return parseRule(rules, ruleLines, 0, replace8and11);
	}

	private Map<Integer, PredWithLength> parseRule(Map<Integer, PredWithLength> rules, Map<Integer, String> ruleLines, int rule, boolean replace8and11) {
		if(!rules.containsKey(rule)) {
			if(replace8and11 && rule == 8) {
				rules.put(8, getRule8(rules, ruleLines));
			} else if(replace8and11 && rule == 11) {
				rules.put(11, getRule11(rules, ruleLines));
			} else {
				String line = ruleLines.get(rule);
				rules.put(rule, parseRule(rules, ruleLines, line, replace8and11));
			}
		}
		return rules;
	}
	
	private PredWithLength parseRule(Map<Integer, PredWithLength> rules, Map<Integer, String> ruleLines, String rule, boolean replace8and11) {
		if(rule.contains("|")) {
			int orSpot = rule.indexOf('|');
			return parseRule(rules, ruleLines, rule.substring(0, orSpot - 1), replace8and11).pwlOr(parseRule(rules, ruleLines, rule.substring(orSpot + 2), replace8and11));
		} else if (rule.contains(" ")) {
			String[] split = rule.split(" ");
			PredWithLength p = parseRule(rules, ruleLines, split[0], replace8and11);
			for(int i = 1; i < split.length; i++) {
				p = p.pwlAndThen(parseRule(rules, ruleLines, split[i], replace8and11));
			}
			return p;
		} else if(rule.contains("\"")) {
			Set<Integer> lengths = new HashSet<>();
			lengths.add(1);
			return new PredWithLength(lengths, s -> s.equals(rule.charAt(1) + ""));
		} else {
			int ruleNumber = Integer.parseInt(rule);
			parseRule(rules, ruleLines, ruleNumber, replace8and11);
			return rules.get(ruleNumber);
		}
	}
	
	private PredWithLength getRule8(Map<Integer, PredWithLength> rules, Map<Integer, String> ruleLines) {
		// 8: 42 | 42 8
		parseRule(rules, ruleLines, 42, true);
		int min42size = rules.get(42).lengths.stream().min((a,b) -> a.compareTo(b)).get();
		StringBuilder newRule = new StringBuilder();
		for(int i = 0; i < MAX_LENGTH / min42size; i++) {
			newRule.append("42");
			for(int j = 0; j < i; j++) {
				newRule.append(" 42");
			}
			newRule.append(" | ");
 		}
		String rule = newRule.substring(0, newRule.length() - 3);
		return parseRule(rules, ruleLines, rule, true);
	}
	
	private PredWithLength getRule11(Map<Integer, PredWithLength> rules, Map<Integer, String> ruleLines) {
		// 8: 42 | 42 8
		parseRule(rules, ruleLines, 42, true);
		parseRule(rules, ruleLines, 31, true);
		int min4231size = rules.get(42).lengths.stream().min((a,b) -> a.compareTo(b)).get() + rules.get(31).lengths.stream().min((a,b) -> a.compareTo(b)).get();
		StringBuilder newRule = new StringBuilder();
		for(int i = 0; i < MAX_LENGTH / min4231size; i++) {
			newRule.append("42");
			for(int j = 0; j < i; j++) {
				newRule.append(" 42");
			}
			newRule.append(" 31");
			for(int j = 0; j < i; j++) {
				newRule.append(" 31");
			}
			newRule.append(" | ");
 		}
		String rule = newRule.substring(0, newRule.length() - 3);
		return parseRule(rules, ruleLines, rule, true);
	}
	
	public class PredWithLength implements Predicate<String> {

		private Set<Integer> lengths;
		private Predicate<String> base;
		
		public PredWithLength(Set<Integer> lengths, Predicate<String> base) {
			this.lengths = lengths;
			this.base = base;
		}
		
		public PredWithLength pwlOr(PredWithLength other) {
			Set<Integer> newLengths = new HashSet<>();
			newLengths.addAll(lengths);
			newLengths.addAll(other.lengths);
			Predicate<String> newBase = base.or(other.base);
			return new PredWithLength(newLengths, newBase);
		}
		
		public PredWithLength pwlAndThen(PredWithLength other) {
			Set<Integer> newLengths = new HashSet<>();
			for(Integer a : lengths) {
				for(Integer b : other.lengths) {
					newLengths.add(a+b);
				}
			}
			Predicate<String> newBase = s -> {
				for(int a : lengths) {
					if(a < s.length() && base.test(s.substring(0, a)) && other.test(s.substring(a))) {
						return true;
					}
				}
				return false;
			};
			return new PredWithLength(newLengths, newBase);
		}
		
		@Override
		public boolean test(String t) {
			return lengths.contains(t.length()) && base.test(t);
		}
		
		public Set<Integer> getLengths() {
			return lengths;
		}
	}
}
