package net.gunivers.listgenerator.gui.handlers;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import net.gunivers.listgenerator.gui.CommandListGeneratorController;
import net.gunivers.listgenerator.gui.handlers.list.SyncListHandler;

public class ButtonEditHandler implements EventHandler<ActionEvent>
{
    private SyncListHandler doubleListView;

    private int maxSize;

    public ButtonEditHandler(SyncListHandler syncListHandler, int maxSize)
    {
        this.doubleListView = syncListHandler;
        this.maxSize = maxSize;
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
