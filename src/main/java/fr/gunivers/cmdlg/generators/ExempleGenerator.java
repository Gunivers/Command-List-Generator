package fr.gunivers.cmdlg.generators;

import fr.gunivers.cmdlg.api.IGenerator;
import fr.gunivers.cmdlg.util.GeneratorType;

public class ExempleGenerator implements IGenerator {

    private GeneratorType type = GeneratorType.NULL;

    /**
     * Classic Generator.
     * @param type of the generator type.
     */
    public ExempleGenerator(GeneratorType type) {
        //Mettre les variable ici.
        this.type = type;
    }

    @Override
    public void generate() {

    }

    @Override
    public Iterable<? extends String> getCommands() {
        return null;
    }

    @Override
    public GeneratorType getType() {
        return null;
    }
}
