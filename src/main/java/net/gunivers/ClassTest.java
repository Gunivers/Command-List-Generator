package net.gunivers;

import java.util.InputMismatchException;
import java.util.Map.Entry;
import java.util.Scanner;

import net.gunivers.Test.CommandParserTest;
import net.gunivers.Test.SelectorTest;
import net.gunivers.Test.Test;

public class ClassTest
{


	public static void main(String[] args)
	{
		Scanner sc = new Scanner(System.in);
		
		new CommandParserTest();
		new SelectorTest();
		
		System.out.println("-------------------- List of test Programs --------------------");
		for (Entry<Integer, Test> entry : Test.tests.entrySet()) {
			System.out.println(" "+ entry.getKey() +": "+ entry.getValue());
		}
		System.out.println("---------------------------------------------------------------\n");
		
		System.out.print("Please select a program: ");
		
		int testId = -1;
		
		try
		{
			testId = sc.nextInt(); sc.nextLine();
		} catch (InputMismatchException e)
		{
			System.out.println(" - Invalid number; Using default program"); 
		} finally {
			sc.close();
		}
		
		if(testId < Test.tests.size() && testId >= 0) {
			System.out.println(" - Running program #" + testId + ": " + Test.tests.get(testId).toString());
			Test.tests.get(testId).test();
		} else if(testId >= Test.tests.size()) {
			System.out.println(" - No program found: "+ testId);
		}
	}
}
