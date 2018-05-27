package net.gunivers.updater;

import javafx.application.Application;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileNotFoundException;

public class Updater extends Application
{

    @Override
    public void start(Stage primaryStage) throws Exception
    {
        //Option = -DUpdate-File
        String fileUpdate = System.getProperty("Update-File");

        File file = new File(fileUpdate);

        if (!file.exists())
            new Error(new FileNotFoundException("Update file not found ! \n" + file.toString() + "\n"));
    }
}
