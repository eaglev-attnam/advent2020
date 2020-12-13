package days;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

public class Day13 extends Day {

	@Override
	protected int getChallengeNumber() {
		return 13;
	}
	
	@Override
	protected Object part1(List<String> input) {
		int starttime = Integer.parseInt(input.get(0));
		int bestWait = Integer.MAX_VALUE;
		int bestLine = 0;
		for(String line : input.get(1).split(",")) {
			if(!line.equals("x")) {
				int period = Integer.parseInt(line);
				int last = starttime % period;
				if(last == 0) {
					last = period;
				}
				int wait = period - last;
				if(wait < bestWait) {
					bestWait = wait;
					bestLine = period;
				}
			}
		}
		return bestWait * bestLine;
	}

	@Override
	protected Object part2(List<String> input) {
		List<Integer> lines = new ArrayList<>();
		List<Integer> mods = new ArrayList<>();
		String[] numbers = input.get(1).split(",");
		for(int i = 0; i < numbers.length; i++) {
			if(!numbers[i].equals("x")) {
				int line = Integer.parseInt(numbers[i]);
				lines.add(line);
				int mod = line - (i % line);
				if(mod == line) {
					mod = 0;
				}
				mods.add(mod);
			}
		}
		
		BigInteger currentN = BigInteger.valueOf(lines.get(0));
		BigInteger currentM = BigInteger.valueOf(mods.get(0));
		for(int i = 1; i < lines.size(); i++) {
			BigInteger nextN = BigInteger.valueOf(lines.get(i));
			BigInteger nextM = BigInteger.valueOf(mods.get(i));
			currentM = computeBezoutMod(currentN, nextN, currentM, nextM);
			currentN = currentN.multiply(nextN);
		}
		return currentM;
	}
	
	    //  438,040,289,829,461 too low
	private BigInteger computeBezoutMod(BigInteger a, BigInteger b, BigInteger aMod, BigInteger bMod) {
		BigInteger r = a, r1 = b;
		BigInteger s = BigInteger.ONE, s1 = BigInteger.ZERO; // sa * tb = r
		BigInteger t = BigInteger.ZERO, t1 = BigInteger.ONE; // s1a * t1b = r1
		while(!r1.equals(BigInteger.ZERO)) {
			BigInteger q = r.divide(r1);
			BigInteger rNext = r.subtract(q.multiply(r1));
			BigInteger sNext = s.subtract(q.multiply(s1));
			BigInteger tNext = t.subtract(q.multiply(t1));
			r = r1;
			s = s1;
			t = t1;
			r1 = rNext;
			s1 = sNext;
			t1 = tNext;
		}
//		System.out.println(s + "*" + a + " + " + t + "*" + b + " = " + r);
		BigInteger mod = s.multiply(a).multiply(bMod).add(t.multiply(b).multiply(aMod));
		mod = mod.remainder(a.multiply(b));
		while(mod.compareTo(BigInteger.ZERO) < 0) {
			mod = mod.add(a.multiply(b));
		}
		mod = mod.remainder(a.multiply(b));
		return mod;
	}
}
