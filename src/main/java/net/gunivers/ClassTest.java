package net.gunivers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import net.gunivers.commandparser.commands.CommandDatapack;
import net.gunivers.commandparser.commands.CommandTag;
import net.gunivers.commandparser.selector.SelectorFields;

public class ClassTest
{
	

	public static void main(String[] args)
	{

		int[] test = new int[]{1, 2};	
		int i = 0;
		try {
			i++;
			System.out.println(test[3]);
		} catch(ArrayIndexOutOfBoundsException e) {
			System.out.println(i);
		}
		
		
		
		new CommandTag();
		new CommandDatapack();
		
		ArrayList<String> entries = new ArrayList<String>();

		String a = "[^\\[\\],{}]+=(?:[^\\[\\],{}]+|(?=\\{)(?:(?=.*?\\{(?!.*?\\1)(.*\\}(?!.*\\2).*))(?=.*?\\}(?!.*?\\2)(.*)).)+?.*?(?=\\1)[^{]*(?=\\2$))";
		String b = "[potato=carrot,test=12b,apple=peer,tree={oak=1,birch={value=3,test={test=b}}},foo=bar]";
		
		Pattern p = Pattern.compile(a);
		Matcher m = p.matcher(b);
		while(m.find())
			System.out.println(m.group());
				
		
		/*for(String entry : entries) {
			System.out.println("Entrée :");
			System.out.println(entry + '\n');
			System.out.println("Sortie :");
			System.out.println(Command.validSyntax(entry));
			System.out.println("\n");
		}*/

		
	}

}
