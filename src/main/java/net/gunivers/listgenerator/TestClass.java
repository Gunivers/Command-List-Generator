package net.gunivers.listgenerator;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;

import net.gunivers.listgenerator.functionality.*;

public class TestClass {

	public static void main(String... args) {
		new Incrementation();
		test();
		
		System.out.print("\n\n\n");
	
		new Interpolation();
		test2();
	}

	@SuppressWarnings("unchecked")
	public static void test() {
		Tag<Integer> test = new Tag<Integer>(Functionality.getFunctionnalities().get("Incrémentation"), 10);

		ArrayList<String> output = new ArrayList<String>();

		try {
			output = (ArrayList<String>) test.getType().getMethod().invoke(test.getType(), 0, 10, 10);
		} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
			e.printStackTrace();
		}

		for (String s : output)
			System.out.println(s);
	}
	
	@SuppressWarnings("unchecked")
	public static void test2() {
		Tag<Integer> test = new Tag<Integer>(Functionality.getFunctionnalities().get("Interpolation"), 10);

		ArrayList<String> output = new ArrayList<String>();

		try {
			output = (ArrayList<String>) test.getType().getMethod().invoke(test.getType(), 0, 100, 1.9, false, 0, false, 10);
		} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
			e.printStackTrace();
		}

		for (String s : output)
			System.out.println(s);
	}

}
