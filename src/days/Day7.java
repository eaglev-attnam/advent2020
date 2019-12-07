package days;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import common.IntComputer;

public class Day7 extends Day {

	@Override
	protected int getChallengeNumber() {
		return 7;
	}
	
	@Override
	protected Object part1(List<String> input) {
		List<Integer> program = Arrays.asList(input.get(0).split(",")).stream().map(Integer::parseInt).collect(Collectors.toList());
		List<Integer> phases = Arrays.asList(0,1,2,3,4);
		List<List<Integer>> permutations = getPermutations(phases);
		return permutations.stream().map(p -> getOutputForPerm(p, program)).max(Integer::compare).get();
	}

	@Override
	protected Object part2(List<String> input) {
		List<Integer> program = Arrays.asList(input.get(0).split(",")).stream().map(Integer::parseInt).collect(Collectors.toList());
		List<Integer> phases = Arrays.asList(5,6,7,8,9);
		List<List<Integer>> permutations = getPermutations(phases);
		return permutations.stream().map(p -> getOutputForPermLooped(p, program)).max(Integer::compare).get();
	}
	
	private int getOutputForPerm(List<Integer> perm, List<Integer> program) {
		int input = 0;
		IntComputer comp = new IntComputer();
		for(int i = 0; i < 5; i++) {
			comp.addInput(perm.get(i));
			comp.addInput(input);
			comp.runProgram(program);
			input = comp.getOutput().get(0);
		}
		return input;
	}
	
	private int getOutputForPermLooped(List<Integer> perm, List<Integer> program) {
		IntComputer[] comps = new IntComputer[5];
		for(int i = 0; i < 5; i++) {
			comps[i] = new IntComputer();
			comps[i].addInput(perm.get(i));
			comps[i].runProgram(program);
		}
		boolean finished = false;
		int input = 0;
		while(!finished) {
			for(int i = 0; i < 5; i++) {
				comps[i].addInput(input);
				input = comps[i].getOutput().get(comps[i].getOutput().size() - 1);
			}
			finished = !comps[0].isPaused();
		}
		return input;
	}

	List<List<Integer>> getPermutations(List<Integer> input) {
		if (input.size() == 1) {
			return Arrays.asList(input);
		}
		List<List<Integer>> permutations = new ArrayList<>();
		for(int i = 0; i < input.size(); i++) {
			int last = input.get(i);
			List<Integer> smaller = new ArrayList<>();
			smaller.addAll(input);
			smaller.remove((Integer) last);
			List<List<Integer>> subPerms = getPermutations(smaller);
			subPerms.forEach(perm -> perm.add(last));
			permutations.addAll(subPerms);
		}
		return permutations;
	}
}
