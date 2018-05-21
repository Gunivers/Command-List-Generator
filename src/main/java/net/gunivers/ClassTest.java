package net.gunivers;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

import net.gunivers.commandparser.Command;
import net.gunivers.commandparser.commands.CommandGamemode;
import net.gunivers.commandparser.selector.SelectorMatcher;
import net.gunivers.commandparser.selector.SelectorNode;

public class ClassTest {

	private static int TEST_NUMBER = 0;
	private static String[] tests = { "Selectors" };

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		System.out.println("-------------------- List of tests programs --------------------");
		for (int i = 0; i < tests.length; i++) {
			System.out.println(i + ": " + tests[i]);
		}
		System.out.println("----------------------------------------------------------------");

		System.out.print("\nChoose a program: ");

		try
		{
			TEST_NUMBER = sc.nextInt();
			sc.nextLine();
		} catch (InputMismatchException e)
		{
			System.out.println(" - Invalid number, using default program\n");
		} finally
		{
			if(TEST_NUMBER >= tests.length)
			{
				System.out.println(" - Unknown test program, using default program\n");
				TEST_NUMBER = 0;
			}
			System.out.println("Running program #"+ TEST_NUMBER +": "+ tests[TEST_NUMBER]);
			System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n");
		}

		switch (TEST_NUMBER)
		{
			case 0:
				new CommandGamemode();
				ArrayList<String> entries = new ArrayList<String>();
				entries.add("gamemode survival @a");
				entries.add("gamemode survival test");
				entries.add("gamemode crea");
				entries.add("gamemode creative @a[tag=test,dx=5,dz=5..7]");
				entries.add("gamemode creative @a[dz=5,da=5]");
				entries.add("gamemode creative @a[tag=test,dx=5,dx=10]");
				entries.add("gamemode creative @a[distance=3,tagtest]");
				entries.add("gamemode creative @a[distance=3,tag=test,test={test=a},tag=potato]");
				entries.add("gamemode creative @a[distance=pomme]");

				System.out.print("Please type a selector: ");
				String selector = sc.next();
				
				for (String entry : entries)
				{
					System.out.println("Entrée: " + entry + "\n");
					System.out.println("Sortie: " + Command.validSyntax(entry) + "\n");
					System.out.println("\n");
				}
				
				System.out.println("Selector: " + selector);
			//	System.out.println("Output: " + new SelectorNode(selector).matches()[1]);
				
				break;
		}
		
		sc.close();
	}
}