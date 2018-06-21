package net.gunivers.commandlistgenerator;

import javafx.application.Platform;
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
        Platform.exit();
        //To make sure the program is closed.
        System.exit(0);
    }
}
