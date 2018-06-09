package net.gunivers.commandlistgenerator;

import javafx.event.EventHandler;
import javafx.stage.WindowEvent;
import net.gunivers.commandlistgenerator.debug.Debug;

public class ShutdownThread implements EventHandler<WindowEvent>
{

    @Override
    public void handle(WindowEvent event)
    {
        CommandListGenerator.MAIN.exit();
        Debug.exit();
    }
}
