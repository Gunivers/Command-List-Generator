package fr.gunivers.cmdlg.generators.primitive;

import fr.gunivers.cmdlg.api.MathGenerator;
import fr.gunivers.cmdlg.util.GeneratorType;

import java.util.ArrayList;

public class DoubleGenerator extends MathGenerator {

    public DoubleGenerator(String[] command, double... args) {
        super(command, args);
    }

    @Override
    public Iterable<? extends String> generate() {
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
