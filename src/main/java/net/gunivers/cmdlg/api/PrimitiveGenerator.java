package net.gunivers.cmdlg.api;

import java.lang.reflect.MalformedParametersException;

public abstract class PrimitiveGenerator extends BasicGenerator {

    private Object[] args;

    protected PrimitiveGenerator(String[] command, Object... args) {
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
