package net.gunivers.listgenerator;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;

import net.gunivers.listgenerator.functionality.Functionality;
import net.gunivers.listgenerator.functionality.Incrementation;

public class TestClass {

	public static void main(String... args) {
		new Incrementation();
		test();
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

}
