package net.gunivers.cmdlg.generators.primitive;

import net.gunivers.cmdlg.api.PrimitiveGenerator;
import net.gunivers.cmdlg.util.GeneratorType;

import java.util.ArrayList;

public class IntGenerator extends PrimitiveGenerator {

    public IntGenerator(String[] command, int... args) {
        super(command, args);
    }

    @Override
    public ArrayList<String> generate() {
        ArrayList<String> commands = new ArrayList<>();

        int start = (int) getArgs()[0];
        int end = (int) getArgs()[1];
        int step = (int) getArgs()[2];

        for (int i = start; i <= end; i += step) {
            StringBuilder temp = new StringBuilder();

            for (int e = 0; e < getCommand().length - 1; e++) {
                temp.append(getCommand()[e]).append(i);

                if (e == getArgs().length - 2) temp.append(getArgs()[e + 1]);
            }

            commands.add(temp.toString());
        }
        return commands;
    }

    @Override
    public GeneratorType getType() {
        return GeneratorType.INT;
    }
}
