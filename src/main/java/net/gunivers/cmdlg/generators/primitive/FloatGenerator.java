package fr.gunivers.cmdlg.generators.primitive;

import fr.gunivers.cmdlg.api.PrimitiveGenerator;
import fr.gunivers.cmdlg.util.GeneratorType;

import java.util.ArrayList;
import java.util.Iterator;

public class FloatGenerator extends PrimitiveGenerator {

    public FloatGenerator(String[] command, float... args) {
        super(command, args);
    }

    @Override
    public Iterator<String> generate() {
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
        return commands.iterator();
    }

    @Override
    public GeneratorType getType() {
        return GeneratorType.FLOAT;
    }

}
