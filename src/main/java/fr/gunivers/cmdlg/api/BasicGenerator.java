package fr.gunivers.cmdlg.api;

import fr.gunivers.cmdlg.util.GeneratorType;

import java.util.Iterator;

public abstract class BasicGenerator {

    private String[] command;

    /**Constructor
     * 
     * @param command to generate
     */
    protected BasicGenerator(String... command) {
        this.command = command;
    }

    /**Generator
     * 
     * @return the generated command
     */
    public abstract Iterator<String> generate();

    
    /**Get the registered command
     * 
     * @return command
     */
    public String[] getCommand() {
        return command;
    }

    /**Get the registered type
     * 
     * @return type
     */
    public abstract GeneratorType getType();
}
