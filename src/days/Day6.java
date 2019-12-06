package days;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class Day6 extends Day {

	@Override
	protected int getChallengeNumber() {
		return 6;
	}
	
	@Override
	protected Object part1(List<String> input) {
		Map<String, String> orbits = parseOrbits(input);
		Map<String, Integer> orbitSums = calculateOrbitSums(orbits);
		return orbitSums.values().stream().reduce(0, (a,b) -> a + b);
	}

	private Map<String, Integer> calculateOrbitSums(Map<String, String> orbits) {
		Map<String, Integer> orbitSums = new HashMap<>();
		orbitSums.put("COM", 0);
		orbits.keySet().forEach(orbiter -> {
			calculateOrbitSum(orbits, orbitSums, orbiter);
		});
		return orbitSums;
	}

	private Map<String, String> parseOrbits(List<String> input) {
		Map<String, String> orbits = new HashMap<>();
		input.forEach(orbit -> {
			String[] split = orbit.split("\\)");
			orbits.put(split[1], split[0]);
		});
		return orbits;
	}

	private void calculateOrbitSum(Map<String, String> orbits, Map<String, Integer> orbitSums, String orbiter) {
		if(orbitSums.containsKey(orbiter)) {
			return; // Already calculated as part of another sum
		} else {
			String orbitee = orbits.get(orbiter);
			calculateOrbitSum(orbits, orbitSums, orbitee);
			orbitSums.put(orbiter, orbitSums.get(orbitee) + 1);
		}
	}

	@Override
	protected Object part2(List<String> input) {
		Map<String, String> orbits = parseOrbits(input);
		Map<String, Integer> orbitSums = calculateOrbitSums(orbits);
		Set<String> youOrbits = new HashSet<>();
		String current = "YOU";
		while(current != null) {
			youOrbits.add(current);
			current = orbits.get(current);
		}
		Set<String> sanOrbits = new HashSet<>();
		current = "SAN";
		while(current != null) {
			sanOrbits.add(current);
			current = orbits.get(current);
		}
		Set<String> commonOrbits = new HashSet<>();
		commonOrbits.addAll(youOrbits);
		commonOrbits.retainAll(sanOrbits);
		Set<String> superOrbits = commonOrbits.stream().map(orbits::get).collect(Collectors.toSet());
		commonOrbits.removeAll(superOrbits);
		String firstCommonOrbit = commonOrbits.iterator().next();
		return orbitSums.get("YOU") + orbitSums.get("SAN") - (2 * orbitSums.get(firstCommonOrbit)) - 2;
		// You to common is YOU - common - 1; common to san = SAN - common - 1.
	}
}
