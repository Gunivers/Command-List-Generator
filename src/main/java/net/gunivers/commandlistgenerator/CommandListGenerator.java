package net.gunivers.commandlistgenerator;

import java.io.Console;
import java.io.IOException;
import java.net.URL;
import java.net.URLDecoder;

import com.jfoenix.controls.JFXDecorator;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import net.gunivers.commandlistgenerator.functionality.*;
import net.gunivers.commandlistgenerator.gui.CommandListGeneratorController;
import net.gunivers.core.language.Language;
import net.gunivers.core.language.Locale;
import net.gunivers.updater.Updater;

public class CommandListGenerator extends Application
{

    public static Boolean DEBUG = false;

    public static Stage MAIN_STAGE;

    public static Language language = Language.getLanguage(Locale.FRENCH);

    public static void main(String[] args)
    {
        if (args.length > 0)
        {
            for (int index = 0; args.length > index; index++)
            {
                String arg = args[index];

                if (arg.startsWith("-update"))
                {
                    launch(Updater.class, "-DUpdate-File=" + args[index].split("=")[1]);
                    index += 1;
                } else if (arg.startsWith("-debug"))
                {
                    DEBUG = true;

                    if (arg.split("=").length > 1)
                    {
                        DEBUG = Boolean.valueOf(arg.split("=")[1]);
                    }

                    try
                    {
                        String path = CommandListGenerator.class.getProtectionDomain().getCodeSource().getLocation().getPath().substring(1);
                        String decodedPath = URLDecoder.decode(path, "UTF-8");
                        System.out.println(decodedPath);
                        Runtime.getRuntime().exec("cmd /c start java -jar \"" + decodedPath + "\" actual_run");
                        Thread.currentThread().setDaemon(true);
                        Thread.currentThread().stop();
                    } catch (IOException e)
                    {
                        e.printStackTrace();
                    }

                }
            }
        }

        if (!DEBUG) launch(args);
    }

    /**
     * Starting of interface
     *
     * @param primaryStage Default stage giving by JavaFX
     */
    @Override
    public void start(Stage primaryStage) throws Exception
    {
        CommandListGenerator.MAIN_STAGE = primaryStage;

        //Start of fxml load
        FXMLLoader loader = new FXMLLoader(new URL(getClass().getResource("/fxml/CommandListGenerator.fxml").toExternalForm()));

        //Set the controller of loader
        loader.setController(new CommandListGeneratorController());

        //Load the loader
        loader.load();

        //Decorator of window
        JFXDecorator decorator = new JFXDecorator(primaryStage, loader.getRoot(), false, true, true);

        //Set the tittle of window
        decorator.setText(language.get("gui.commandlistgenerator.title"));
        primaryStage.setTitle(language.get("gui.commandlistgenerator.title"));

        //Create new scene
        Scene scene = new Scene(decorator);

        //Make sure font is loaded
        Font.loadFont(CommandListGenerator.class.getResource("/css/font/Roboto-Regular.ttf").toExternalForm(), 10D);
        Font.loadFont(CommandListGenerator.class.getResource("/css/font/Roboto-Bold.ttf").toExternalForm(), 10D);

        //Clear all CSS option
        scene.getStylesheets().clear();

        //Add custom CSS value
        scene.getStylesheets().add(getClass().getResource("/css/Gunivers.css").toExternalForm());

        //Set the dimension of window
        primaryStage.setMinWidth(640);
        primaryStage.setMinHeight(400);

        //Set the scene
        primaryStage.setScene(scene);

        //Show the scene
        primaryStage.show();

        //Init of functionalities
        boolean b = Functionality.register();
        if (!b) System.out.println("Functionality has been not correctly registered.");
    }
}
