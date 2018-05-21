package net.gunivers.core.language;

public class Tuple<A, B> {
	
	private A key;
	private B entry;
	
	public Tuple(A key, B entry) {
		this.key = key;
		this.entry = entry;
	}
	
	public A _1() {
		return key;
	}
	
	public B _2() {
		return entry;
	}

}
