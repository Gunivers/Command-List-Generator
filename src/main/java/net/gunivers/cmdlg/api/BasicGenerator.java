package net.gunivers.cmdlg.api;

import net.gunivers.cmdlg.util.GeneratorType;

import java.util.ArrayList;
import java.util.Arrays;

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
    public abstract ArrayList<String> generate();

    
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

    @Override
    public String toString() {
        return Arrays.toString(command) + " | Type=" + getType();
    }
}
