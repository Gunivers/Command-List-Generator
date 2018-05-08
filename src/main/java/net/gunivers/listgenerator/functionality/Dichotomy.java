package net.gunivers.listgenerator.functionality;

import net.gunivers.listgenerator.util.Call;
import net.gunivers.listgenerator.util.Functionality;

import java.util.ArrayList;

public class Dichotomy extends Functionality
{

    @Override
    public String toString()
    {
        return "Dichotomy";
    }

    @Call
    public ArrayList<String> dichotomy(int min, int max)
    {

        ArrayList<String> commands = new ArrayList<>();

        long l = 1;

        while (l < max)
        {
            commands.add(String.valueOf(l + min));
            l = 2 * l;
        }

        return commands;
    }

    @Override
    public ArrayList<Object> callParameterOverlay()
    {
        // TODO
        return null;
    }
}