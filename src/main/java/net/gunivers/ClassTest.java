package net.gunivers;

import net.gunivers.commandparser.commands.CommandWeather;

public class ClassTest
{

    public static void main(String[] args)
    {
    	
        CommandWeather cw = new CommandWeather();
        String test = "weather thunder 10";
        System.out.println(cw.hasCorrectSyntax(test));

    }

}
