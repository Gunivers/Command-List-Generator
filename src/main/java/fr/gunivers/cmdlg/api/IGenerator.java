package fr.gunivers.cmdlg.api;

import fr.gunivers.cmdlg.util.GeneratorType;

public interface IGenerator {

    void generate();

    Iterable<? extends String> getCommands();

    GeneratorType getType();
}
