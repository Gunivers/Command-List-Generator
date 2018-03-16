package net.gunivers.cmdlg.gui.handler;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import net.gunivers.cmdlg.Main;

public class QuitHandler implements EventHandler<ActionEvent>
{
    @Override
    public void handle(ActionEvent event)
    {
        Main.MAIN_STAGE.close();
    }
}
