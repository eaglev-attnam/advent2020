package common;

import java.util.Objects;

public class Pair<A, B> {
	private A a;
	private B b;
	
	public Pair(A a, B b) {
		this.a = a;
		this.b = b;
	}
	
	public A getA() {
		return a;
	}
	
	public B getB() {
		return b;
	}
	
	@Override
	public boolean equals(Object obj) {
		if(this == obj) {
			return true;
		}
		if(obj == null || !(obj instanceof Pair)) {
			return false;
		}
		Pair<?,?> other = (Pair<?,?>) obj;
		return Objects.equals(this.a, other.a) && Objects.equals(this.b, other.b);
	}
}
