package fr.gunivers.cmdlg.generators;

import fr.gunivers.cmdlg.api.BasicGenerator;

import java.lang.reflect.MalformedParametersException;

public abstract class MathGenerator extends BasicGenerator {

    private Object[] args;

    public MathGenerator(String[] command, Object... args) {
        super(command);

        if (args.length <= 0)
            throw new MalformedParametersException("args is empty, please fix that.");

        if (args.length > 3)
            throw new MalformedParametersException("Je sais pas quoi dire mais c'est pas bon.");

        this.args = args;
    }

    public Object[] getArgs() {
        return args;
    }
}
