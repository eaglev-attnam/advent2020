package days;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Day14 extends Day {

	@Override
	protected int getChallengeNumber() {
		return 14;
	}
	
	@Override
	protected Object part1(List<String> input) {
		Map<Long, Long> memory = new HashMap<>();
		String mask = "";
		for(String line : input) {
			String value = line.split(" ")[2];
			if(line.startsWith("mask")) {
				mask = value;
			} else {
				String address = line.substring(4, line.indexOf(']'));
				memory.put(Long.parseLong(address), maskValue(mask, Long.parseLong(value)));
			}
		}
		return memory.values().stream()
				.reduce(0L, (a,b) -> a+b);
	}

	private Long maskValue(String mask, long value) {
		char[] binary = String.format("%" + mask.length() + "s", Long.toBinaryString(value)).replaceAll(" ", "0").toCharArray();
		for(int i = 0; i < mask.length(); i++) {
			if(mask.charAt(i) != 'X') {
				binary[i] = mask.charAt(i);
			}
		}
		return Long.parseLong(String.copyValueOf(binary), 2);
	}

	@Override
	protected Object part2(List<String> input) {
		Map<Long, Long> memory = new HashMap<>();
		String mask = "";
		for(String line : input) {
			String value = line.split(" ")[2];
			if(line.startsWith("mask")) {
				mask = value;
			} else {
				String baseAddress = line.substring(4, line.indexOf(']'));
				for(Long address : getAddresses(mask, Long.parseLong(baseAddress))) {
					memory.put(address, Long.parseLong(value));
				}
			}
		}
		return memory.values().stream()
				.reduce(0L, (a,b) -> a+b);
	}

	private Set<Long> getAddresses(String mask, Long baseAddress) {
		char[] binary = String.format("%" + mask.length() + "s", Long.toBinaryString(baseAddress)).replaceAll(" ", "0").toCharArray();
		char[] result = new char[mask.length()];
		for(int i = 0; i < mask.length(); i++) {
			char c = mask.charAt(i);
			if(c == '0') {
				result[i] = binary[i];
			} else {
				result[i] = c;
			}
		}
		return getAddresses(new String(result));
	}

	private Set<Long> getAddresses(String result) {
		Set<Long> results = new HashSet<>();
		if(!result.contains("X")) {
			results.add(Long.parseLong(result, 2));
		} else {
			results.addAll(getAddresses(result.replaceFirst("X", "0")));
			results.addAll(getAddresses(result.replaceFirst("X", "1")));
		}
		return results;
	}
}
