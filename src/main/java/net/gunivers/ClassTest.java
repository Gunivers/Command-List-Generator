package net.gunivers;

import net.gunivers.listgenerator.functionality.Sequence;

public class ClassTest
{

    public static void main(String[] args)
    {
        Sequence i = new Sequence();
        System.out.println(i.generate((double) 10, "(U + 1 * 2) / 4", 10, 3, null));

    }

}
