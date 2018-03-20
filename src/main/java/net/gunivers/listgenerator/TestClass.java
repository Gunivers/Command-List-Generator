package net.gunivers.listgenerator;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;

public class TestClass {

	@SuppressWarnings("unchecked")
	public static void main(String... args) {
		Tag<Integer> tag1 = new Tag<Integer>(Type.INCREMENTATION, 10);
		ArrayList<String> output = new ArrayList<String>();
		
		
		try {
			output = (ArrayList<String>) tag1.getType().getMethod().invoke(null, 0, 10, 10);
		} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
	
		for(String s : output)
			System.out.println(s);
	}
	
}
