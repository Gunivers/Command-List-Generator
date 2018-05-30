package net.gunivers.test;

import java.util.Scanner;

import net.gunivers.commandparser.selector.SelectorNode;
import net.gunivers.core.utils.tuple.Tuple2;

public class SelectorTest extends Test {

	@Override
	public String toString() {
		return "Selectors";
	}

	@Override
	public void test() {
		Scanner sc = new Scanner(System.in);
		System.out.print("\nPlease type a selector: ");
		String selector = sc.nextLine();
		
		Tuple2<Integer, String> result = new SelectorNode("").matches(selector);
		
		int index = result._1;
		String message = (result._2 == null) ? "valid" : result._2;
		
		System.out.println(selector);
		
		for (int i = 0; i < index; i ++) {
			System.out.print(" ");
		}
		
		System.out.println("^ " + message);
		sc.close();
	}
}