package net.gunivers;

import java.util.ArrayList;

import net.gunivers.commandparser.Command;
import net.gunivers.commandparser.commands.CommandGamemode;

public class ClassTest
{
	
	private static int TEST_NUMBER = 1;

	public static void main(String[] args)
	{

		
		
		if(TEST_NUMBER == 1) {
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
			
			for(String entry : entries) {
				System.out.println("Entrée :");
				System.out.println(entry + '\n');
				System.out.println("Sortie :");
				System.out.println(Command.validSyntax(entry));
				System.out.println("\n");
			}
		}

		
	}

}
