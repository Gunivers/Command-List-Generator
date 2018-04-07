package net.gunivers.listgenerator;

import com.jfoenix.controls.JFXDecorator;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class CommandListGenerator extends Application {

    public static void main(String[] args)
    {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/CommandListGenerator.fxml"));

        loader.load();

        JFXDecorator decorator = new JFXDecorator(primaryStage, loader.getRoot(), false, true, true);

        decorator.setText("Command List Generator");

        Scene scene = new Scene(decorator);

        scene.getStylesheets().clear();
        scene.getStylesheets().add(getClass().getResource("/css/Gunivers.css").toExternalForm());

        primaryStage.setMinWidth(640);
        primaryStage.setMinHeight(400);

        primaryStage.setTitle("Command List Generator");

        primaryStage.setScene(scene);

        primaryStage.show();
    }
}
