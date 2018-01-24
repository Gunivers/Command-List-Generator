package fr.gunivers.cmdlg.api;

import fr.gunivers.cmdlg.util.GeneratorType;

public abstract class BasicGenerator {

    private String[] command;

    public BasicGenerator(String... command) {
        this.command = command;
    }

    public abstract Iterable<? extends String> generate();

    public String[] getCommand() {
        return command;
    }

    public abstract GeneratorType getType();
}
