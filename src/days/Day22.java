package days;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import common.Pair;

public class Day22 extends Day {
	
	@Override
	protected int getChallengeNumber() {
		return 22;
	}
	
	@Override
	protected Object part1(List<String> input) {
		LinkedList<Integer> stack1 = new LinkedList<Integer>();
		LinkedList<Integer> stack2 = new LinkedList<Integer>();
		int i = 1;
		while(!input.get(i).isEmpty()) {
			stack1.addLast(Integer.parseInt(input.get(i)));
			i++;
		}
		i += 2;
		while(i < input.size()) {
			stack2.addLast(Integer.parseInt(input.get(i)));
			i++;
		}
		while(!stack1.isEmpty() && !stack2.isEmpty()) {
			int card1 = stack1.pop();
			int card2 = stack2.pop();
			if(card1 > card2) {
				stack1.addLast(card1);
				stack1.addLast(card2);
			} else {
				stack2.addLast(card2);
				stack2.addLast(card1);
			}
		}
		LinkedList<Integer> winner = stack1.isEmpty() ? stack2 : stack1;
		int sum = 0;
		i = 1;
		while(!winner.isEmpty()) {
			sum += i * winner.removeLast();
			i++;
		}
		return sum;
	}

	@Override
	protected Object part2(List<String> input) {
		LinkedList<Integer> stack1 = new LinkedList<Integer>();
		LinkedList<Integer> stack2 = new LinkedList<Integer>();
		int i = 1;
		while(!input.get(i).isEmpty()) {
			stack1.addLast(Integer.parseInt(input.get(i)));
			i++;
		}
		i += 2;
		while(i < input.size()) {
			stack2.addLast(Integer.parseInt(input.get(i)));
			i++;
		}
		p1winsRecursive(stack1, stack2, true);
		LinkedList<Integer> winner = stack1.isEmpty() ? stack2 : stack1;
		int sum = 0;
		i = 1;
		while(!winner.isEmpty()) {
			sum += i * winner.removeLast();
			i++;
		}
		return sum;
	}
	
	private boolean p1winsRecursive(LinkedList<Integer> stack1, LinkedList<Integer> stack2, boolean playFullGame) {
		if(!playFullGame) {
			// If p 1 has the higher card, he always wins
			// Not true for p2 since there can be an infinite loop -> p1 wins
			int max1 = stack1.stream().max(Integer::compare).orElse(0);
			int max2 = stack2.stream().max(Integer::compare).orElse(0);
			if(max1 > max2) {
				return true;
			}
		}
		Set<Pair<LinkedList<Integer>, LinkedList<Integer>>> history = new HashSet<>();
		while(!stack1.isEmpty() && !stack2.isEmpty()) {
			Pair<LinkedList<Integer>, LinkedList<Integer>> p = new Pair<>(stack1, stack2);
			if(history.contains(p)) {
				return true;
			}
			history.add(p);
			int card1 = stack1.pop();
			int card2 = stack2.pop();
			if(card1 > stack1.size() || card2 > stack2.size()) {
				if(card1 > card2) {
					stack1.addLast(card1);
					stack1.addLast(card2);
				} else {
					stack2.addLast(card2);
					stack2.addLast(card1);
				}
			} else {
				// Subgame
				LinkedList<Integer> sub1 = new LinkedList<>(stack1.subList(0, card1));
				LinkedList<Integer> sub2 = new LinkedList<>(stack2.subList(0, card2));
				if(p1winsRecursive(sub1, sub2, false)) {
					stack1.addLast(card1);
					stack1.addLast(card2);
				} else {
					stack2.addLast(card2);
					stack2.addLast(card1);
				}
			}
		}
		return !stack1.isEmpty();
	}
}
