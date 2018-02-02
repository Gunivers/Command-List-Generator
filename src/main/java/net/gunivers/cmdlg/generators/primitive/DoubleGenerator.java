package net.gunivers.cmdlg.generators.primitive;

import net.gunivers.cmdlg.api.PrimitiveGenerator;
import net.gunivers.cmdlg.util.GeneratorType;

import java.util.ArrayList;
import java.util.Iterator;

public class DoubleGenerator extends PrimitiveGenerator {

    public DoubleGenerator(String[] command, double... args) {
        super(command, args);
    }

    @Override
    public ArrayList<String> generate() {
        ArrayList<String> commands = new ArrayList<>();

        double start = (double) getArgs()[0];
        double end = (double) getArgs()[1];
        double step = (double) getArgs()[2];

        for (double i = start; i <= end; i += step) {
            StringBuilder temp = new StringBuilder();

            for (int e = 0; e < getCommand().length - 1; e++) {
                temp.append(getCommand()[e]).append(i).append("D");

                if (e == getArgs().length - 2) temp.append(getArgs()[e + 1]);
            }

            commands.add(temp.toString());
        }
        return commands;
    }

    @Override
    public GeneratorType getType() {
        return GeneratorType.DOUBLE;
    }

}
