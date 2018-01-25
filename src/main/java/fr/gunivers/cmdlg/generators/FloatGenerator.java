package fr.gunivers.cmdlg.generators;

import fr.gunivers.cmdlg.api.MathGenerator;
import fr.gunivers.cmdlg.util.GeneratorType;

import java.util.ArrayList;

public class FloatGenerator extends MathGenerator {

    public FloatGenerator(String[] command, float... args) {
        super(command, args);
    }

    @Override
    public Iterable<? extends String> generate() {
        ArrayList<String> commands = new ArrayList<>();

        float start = (float) getArgs()[0];
        float end = (float) getArgs()[1];
        float step = (float) getArgs()[2];

        for (float i = start; i <= end; i += step) {
            StringBuilder temp = new StringBuilder();

            for (int e = 0; e < getCommand().length - 1; e++) {
                temp.append(getCommand()[e]).append(i).append("F");

                if (e == getCommand().length - 2) temp.append(getCommand()[e + 1]);
            }

            commands.add(temp.toString());
        }
        return commands;
    }

    @Override
    public GeneratorType getType() {
        return GeneratorType.FLOAT;
    }

}
