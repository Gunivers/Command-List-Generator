package net.gunivers.core.gui;

import javafx.fxml.FXMLLoader;

import java.net.URL;

public class ErrorScreen
{

    private Exception error;

    public ErrorScreen(Exception e)
    {
        this.error = e;

    }

    public Exception getError()
    {
        return error;
    }

    public void show() throws Exception
    {
        FXMLLoader loader = new FXMLLoader(new URL(getClass().getResource("/fxml/error/Error.fxml").toExternalForm()));
    }
}
