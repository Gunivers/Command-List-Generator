package fr.gunivers.cmdlg;

import fr.gunivers.cmdlg.api.BasicGenerator;
import fr.gunivers.cmdlg.generators.IntGenerator;
import fr.gunivers.cmdlg.util.FXMLUtil;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {

        try {

            /**
             * INIT the default page.
             */
            AnchorPane root = FXMLLoader.load(FXMLUtil.getFXMLURL("Main"));

            Scene scene = new Scene(root);
            primaryStage.setScene(scene);
            primaryStage.setTitle("Command List Generator");
            primaryStage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }

        BasicGenerator generator = new IntGenerator(new String[]{"LES COMMAND ICI"}, "LES ARGS ICI");
        generator.generate().forEach(System.out::println);
    }
}
