package net.gunivers;

public class ClassTest
{

    public static void main(String[] args)
    {
    	
    	String doubleT = "([0-9]+(\\.[0-9]+)?)";
    	String noMinOrLim = "(" + doubleT + "?" + "(\\.\\." + doubleT + "){1})"; 
    	String noMax = "(" + doubleT + "\\.\\.)";
    	
        System.out.println("..5".matches(noMax + "|" + noMinOrLim + "|" + doubleT));

    }

}
