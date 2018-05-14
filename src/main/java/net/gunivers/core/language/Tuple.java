package net.gunivers.core.language;

public class Tuple<A, B> {
	
	private A value1;
	private B value2;
	
	public Tuple(A value1, B value2) {
		this.value1 = value1;
		this.value2 = value2;
	}
	
	public A _1() {
		return value1;
	}
	
	public B _2() {
		return value2;
	}

}
