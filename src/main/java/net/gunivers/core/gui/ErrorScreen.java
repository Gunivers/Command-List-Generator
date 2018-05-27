package net.gunivers.core.gui;

import javafx.application.Application;
import javafx.stage.Stage;

public class ErrorScreen extends Application
{

    private Exception error;

    public ErrorScreen(Exception e)
    {

    }

    public Exception getError()
    {
        return error;
    }

    @Override
    public void start(Stage primaryStage) throws Exception
    {

    }
}
