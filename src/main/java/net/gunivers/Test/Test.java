package net.gunivers.Test;

import java.util.HashMap;

public abstract class Test {
	
	public static HashMap<Integer, Test> tests = new HashMap<Integer, Test>();
	private static Integer number = 0;
	
	{
		tests.put(number, this);
		number++;
	}

	public abstract String toString();
	
	public abstract void test();
	
}
