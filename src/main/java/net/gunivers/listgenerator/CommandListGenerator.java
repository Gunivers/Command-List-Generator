package net.gunivers.listgenerator;

import java.net.URL;

import com.jfoenix.controls.JFXDecorator;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import net.gunivers.listgenerator.functionality.Dichotomy;
import net.gunivers.listgenerator.functionality.Duplication;
import net.gunivers.listgenerator.functionality.GeneratorByList;
import net.gunivers.listgenerator.functionality.Sequence;
import net.gunivers.listgenerator.functionality.Interpolation;
import net.gunivers.listgenerator.functionality.ScoreInterpolation;
import net.gunivers.listgenerator.gui.CommandListGeneratorController;

public class CommandListGenerator extends Application
{

    public static Stage MAIN_STAGE;

    public static void main(String[] args)
    {
        launch(args);
    }

    /**
     * Starting of interface
     *
     * @param primaryStage Default stage giving by JavaFX
     */
    @Override
    public void start(Stage primaryStage) throws Exception
    {
    	this.MAIN_STAGE = primaryStage;

    	new Dichotomy();
    	new Duplication();
    	new GeneratorByList();
    	new Sequence();
    	new Interpolation();
    	new ScoreInterpolation();
    	
        //Start of fxml load
        FXMLLoader loader = new FXMLLoader(new URL(getClass().getResource("/fxml/CommandListGenerator.fxml").toExternalForm()));

        //Set the controller of loader
        loader.setController(new CommandListGeneratorController());

        //Load the loader
        loader.load();

        //Decorator of window
        JFXDecorator decorator = new JFXDecorator(primaryStage, loader.getRoot(), false, true, true);

        //Set the tittle of window
        decorator.setText("Command List Generator");
        primaryStage.setTitle("Command List Generator");

        //Create new scene
        Scene scene = new Scene(decorator);

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
    }
}
