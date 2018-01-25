package fr.gunivers.cmdlg.generators.primitive;

import fr.gunivers.cmdlg.api.PrimitiveGenerator;
import fr.gunivers.cmdlg.util.GeneratorType;

import java.util.ArrayList;

public class LongGenerator extends PrimitiveGenerator {

    public LongGenerator(String[] command, long... args) {
        super(command, args);
    }

    @Override
    public Iterable<? extends String> generate() {
        ArrayList<String> commands = new ArrayList<>();

        long start = (long) getArgs()[0];
        long end = (long) getArgs()[1];
        long step = (long) getArgs()[2];

        for (long i = start; i <= end; i += step) {
            StringBuilder temp = new StringBuilder();

            for (int e = 0; e < getCommand().length - 1; e++) {
                temp.append(getCommand()[e]).append(i).append("L");

                if (e == getCommand().length - 2) temp.append(getCommand()[e + 1]);
            }

            commands.add(temp.toString());
        }
        return commands;
    }

    @Override
    public GeneratorType getType() {
        return GeneratorType.LONG;
    }
}
