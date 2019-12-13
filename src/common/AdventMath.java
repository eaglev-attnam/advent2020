package common;

public class AdventMath {
	public static long gcd(long a, long b) {
		long biggest = a;
		long smallest = b;
		if(biggest < smallest) {
			long tmp = biggest;
			biggest = smallest;
			smallest = tmp;
		}
		if(smallest == 0) {
			return biggest;
		}
		while(biggest % smallest > 0) {
			long tmp = biggest % smallest;
			biggest = smallest;
			smallest = tmp;
		}
		return smallest;
	}
	
	public static long lcm(long a, long b) {
		return (a / gcd(a, b)) * b;
	}
}
