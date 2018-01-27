package fr.gunivers.cmdlg;

import com.jfoenix.controls.JFXDecorator;
import com.jfoenix.svg.SVGGlyphLoader;
import fr.gunivers.cmdlg.gui.MainController;
import fr.gunivers.cmdlg.util.GeneratorType;
import fr.gunivers.cmdlg.util.Util;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.LinkedHashMap;

public class Main extends Application {

    public static LinkedHashMap<String, GeneratorType> nameToGeneratorType = new LinkedHashMap<>();

    static {
        for (GeneratorType type : GeneratorType.values()) {
            nameToGeneratorType.put(type.name(), type);
        }
    }

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) {
        new Thread(() -> {
            try {
                SVGGlyphLoader.loadGlyphsFont(Main.class.getResourceAsStream("/fonts/icomoon.svg"),
                        "icomoon.svg");
            } catch (IOException ioExc) {
                ioExc.printStackTrace();
            }
        }).start();

        try {
            FXMLLoader loader = new FXMLLoader(Util.getFXMLURL("Main"));
            loader.setController(new MainController());
            StackPane pane = loader.load();
            JFXDecorator decorator = new JFXDecorator(stage, pane, false, true, true);
            decorator.setCustomMaximize(true);
            decorator.setText("Command list generator");
            Scene scene = new Scene(decorator, 800, 850);
            scene.getStylesheets().addAll(Main.class.getResource("/css/Main.css").toExternalForm());
            stage.setScene(scene);
            stage.setMinHeight(850);
            stage.setMinWidth(800);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static GeneratorType generatorTypeByDisplayName(String s) {
        for (GeneratorType type : nameToGeneratorType.values()) {
            if (s.equalsIgnoreCase(type.getName()))
                return type;
        }
        return null;
    }
}
