package net.gunivers.commandlistgenerator;

import javafx.event.EventHandler;
import javafx.stage.WindowEvent;
import net.gunivers.commandlistgenerator.debug.Debug;

public class ShutdownThread implements EventHandler<WindowEvent>
{

    @Override
    public void handle(WindowEvent event)
    {
        if (CommandListGenerator.MAIN_STAGE.isShowing()) CommandListGenerator.MAIN_STAGE.close();

        if (Debug.DEBUG) {
            Debug.DEBUG_THREAD.stop();
            if (Debug.STAGE.isShowing()) Debug.STAGE.close();
        }

        System.exit(0);
    }
}
