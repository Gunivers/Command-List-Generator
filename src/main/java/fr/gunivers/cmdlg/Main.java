package fr.gunivers.cmdlg;

import com.jfoenix.controls.JFXColorPicker;
import com.jfoenix.controls.JFXTabPane;
import fr.gunivers.cmdlg.util.FXMLUtil;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Tab;
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

            JFXTabPane root = FXMLLoader.load(FXMLUtil.getFXMLURL("Main"));
            
            Tab tab = new Tab();
            tab.setText("N 1");
            tab.setContent(FXMLLoader.load(FXMLUtil.getFXMLURL("tab1")));
            
            Tab tab1 = new Tab();
            tab1.setText("N 2");
            tab1.setContent(new JFXColorPicker());
            
            root.getTabs().addAll(tab, tab1);
           
            Scene scene = new Scene(root);
            
            primaryStage.setScene(scene);
            primaryStage.setTitle("Command List Generator");
            primaryStage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
