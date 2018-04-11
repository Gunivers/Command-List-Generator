package net.gunivers.listgenerator.gui.handlers;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import net.gunivers.listgenerator.gui.handlers.list.SyncListHandler;

public class ButtonEditHandler implements EventHandler<ActionEvent>
{
    private SyncListHandler doubleListView;

    public ButtonEditHandler(SyncListHandler syncListHandler)
    {
        this.doubleListView = syncListHandler;
    }

    @Override
    public void handle(ActionEvent event)
    {

    }

    public SyncListHandler getDoubleListView()
    {
        return doubleListView;
    }
}
