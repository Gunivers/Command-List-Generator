package fr.gunivers.cmdlg.generators;

import fr.gunivers.cmdlg.api.IGenerator;
import fr.gunivers.cmdlg.util.GeneratorType;

public class DemoGenerator implements IGenerator {

    private GeneratorType type = GeneratorType.NULL;

    /**
     * Classic Generator.
     * @param type of the generator type.
     */
    public DemoGenerator(GeneratorType type) {
        //Mettre les variable ici.
        this.type = type;
    }

    @Override
    public void generate() {
        //Genéré tout les command d'un facon simple !
        /**
         * Executer le truc ? de McFunction en modifier pour que sa fonctione ici.
         */
    }

    @Override
    public Iterable<? extends String> getCommands() {
        //Recupère les commands.
        return null;
    }

    @Override
    public GeneratorType getType() {
        return null;
    }
}
