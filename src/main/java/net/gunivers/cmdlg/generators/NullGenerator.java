package fr.gunivers.cmdlg.generators;

import fr.gunivers.cmdlg.api.BasicGenerator;
import fr.gunivers.cmdlg.util.GeneratorType;

import java.util.Collections;
import java.util.Iterator;

public class NullGenerator extends BasicGenerator {

    @Override
    public Iterator<String> generate() {
        return Collections.singletonList("This a null, please check your option.").iterator();
    }

    @Override
    public GeneratorType getType() {
        return null;
    }
}
