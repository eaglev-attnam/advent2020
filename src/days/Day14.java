package days;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class Day14 extends Day {

	@Override
	protected int getChallengeNumber() {
		return 14;
	}
	
	@Override
	protected Object part1(List<String> input) {
		Map<String, Formula> formulae = getFormulae(input);
		return getOreForNFuel(new HashMap<>(), formulae, 1);
	}

	private Map<String, Formula> getFormulae(List<String> input) {
		Map<String, Formula> formulae = new HashMap<>();
		for(String formula : input) {
			String[] inout = formula.split(" => ");
			String[] inputs = inout[0].split(", ");
			String[] out = inout[1].split(" ");
			String[] in = new String[inputs.length];
			if(formulae.containsKey(out[1])) {
				System.err.print("Two formulae with output " + out[1]);
			}
			int[] inAmount = new int[inputs.length];
			for(int i = 0; i < inputs.length; i++) {
				String[] split = inputs[i].split(" ");
				in[i] = split[1];
				inAmount[i] = Integer.parseInt(split[0]);
			}
			formulae.put(out[1], new Formula(in, inAmount, Integer.parseInt(out[0])));
		}
		return formulae;
	}
	
	private long getOreForNFuel(Map<String, Long> stock, Map<String, Formula> formulae, long stepSize) {
		LinkedList<String> todo = new LinkedList<>();
		LinkedList<Long> amountTodo = new LinkedList<>();
		long ore = 0;
		todo.add("FUEL");
		amountTodo.add(stepSize);
		while(!todo.isEmpty()) {
			String name = todo.pop();
			long amount = amountTodo.pop();
			if(name.equals("ORE")) {
				ore += amount;
			} else {
				if(stock.containsKey(name)) {
					amount -= stock.get(name);
					if(amount < 0) {
						stock.put(name, -amount);
						amount = 0;
					} else {
						stock.put(name, 0L);
					}
				}
				Formula f = formulae.get(name);
				long runs = amount == 0 ? 0 :((amount-1) / f.amountOut) + 1;
				long left = (runs * f.amountOut) - amount;
				if(!stock.containsKey(name)) {
					stock.put(name, left);
				} else {
					stock.put(name, stock.get(name) + left);
				}
				
				for(int i = 0; i < f.in.length; i++) {
					todo.add(f.in[i]);
					amountTodo.add(f.amountIn[i] * runs);
				}
			}
		}
		return ore;
	}
	
	@Override
	protected Object part2(List<String> input) {
		Map<String, Formula> formulae = getFormulae(input);
		
		int stepSize = 1_000_000_000;
		int stepChange = 10;
		long lastMax = 0;
		while(stepSize >= 1) {
			long totalOre = 1_000_000_000_000L;
			Map<String, Long> stock = new HashMap<>();
			// Get to last save point
			if(lastMax > 0) {
				totalOre -= getOreForNFuel(stock, formulae, lastMax);
			}
			
			while(totalOre > 0) {
				totalOre -= getOreForNFuel(stock, formulae, stepSize);
				lastMax += stepSize;
			}
			lastMax -= stepSize;
			stepSize /= stepChange;
		}
		return lastMax;
	}

	public class Formula {
		private String[] in;
		private int[] amountIn;
		private int amountOut;
		
		public Formula(String[] in, int[] amountIn, int amountOut) {
			super();
			this.in = in;
			this.amountIn = amountIn;
			this.amountOut = amountOut;
		}
	}
}
