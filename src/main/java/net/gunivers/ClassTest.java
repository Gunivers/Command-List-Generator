package net.gunivers;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

import net.gunivers.commandlistgenerator.util.Type;
import net.gunivers.commandparser.Command;
import net.gunivers.commandparser.commands.CommandGamemode;
import net.gunivers.commandparser.selector.SelectorNode;
import net.gunivers.core.language.tuple.Tuple2;

public class ClassTest
{

	private static int TEST_NUMBER = 0;
	private static String[] tests = {"Commands", "Selectors", "Type#Double"};

	public static void main(String[] args)
	{
		Scanner sc = new Scanner(System.in);
		
		System.out.println("-------------------- List of test Programs --------------------");
		for (int i = 0; i < tests.length; i++) {
			System.out.println(" "+ i +": "+ tests[i]);
		}
		System.out.println("---------------------------------------------------------------\n");
		
		System.out.print("Please select a program: ");
		
		try
		{
			TEST_NUMBER = sc.nextInt(); sc.nextLine();
		} catch (InputMismatchException e)
		{
			System.out.println(" - Invalid number; Using default program"); 
		}
		
		switch (TEST_NUMBER)
		{
			case 0:
				System.out.println(" - Running program #0: Commands");
				
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

				for (String entry : entries)
				{
					System.out.println("Entrée :");
					System.out.println(entry + '\n');
					System.out.println("Sortie :");
					System.out.println(Command.validSyntax(entry));
					System.out.println("\n");
				}
				break;
			
			case 1:
				System.out.println(" - Running program #1: Selectors");
				
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
				
				break;
				
			case 2:
				System.out.println(" - Running program #2: Type#Double");
				
				System.out.println(Type.DOUBLE.toString());
				break;
				
			default:
				System.out.println(" - No program found: "+ TEST_NUMBER);
		}
		
		sc.close();
	}
}
