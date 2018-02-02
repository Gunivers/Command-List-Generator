package fr.gunivers.cmdlg;

import com.jfoenix.controls.JFXDecorator;
import com.jfoenix.svg.SVGGlyphLoader;
import fr.gunivers.cmdlg.gui.CmdlgDecorator;
import fr.gunivers.cmdlg.gui.DimensionGui;
import fr.gunivers.cmdlg.gui.MainController;
import fr.gunivers.cmdlg.util.GeneratorType;
import fr.gunivers.cmdlg.util.Util;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import javax.swing.*;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.io.IOException;
import java.util.*;

public class Main extends Application {

    public static LinkedHashMap<String, GeneratorType> nameToGeneratorType = new LinkedHashMap<>();
    public static List<DimensionGui> dimensionGuis = new ArrayList<>();

    public static DimensionGui SELECTED_DIMENTION_GUI = null;

    static {
        for (GeneratorType type : GeneratorType.values()) {
            nameToGeneratorType.put(type.name(), type);
        }

        dimensionGuis.add(new DimensionGui(3840, 2160, 1200, 1700, "Main"));
        dimensionGuis.add(new DimensionGui(1920, 1080, 800, 850, "Main"));
        dimensionGuis.add(new DimensionGui(1280, 720, 500, 550, "Main"));
    }

    public static void main(String[] args) {
        try {
            launch(args);
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error cant be start: " + e.fillInStackTrace(), "Error !", JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * Init all class #bordel
     *
     * @param stage
     */
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
            DimensionGui dimensionGui = getDimensionGui();

            Main.SELECTED_DIMENTION_GUI = dimensionGui;

            System.out.println("Dimension Gui Selected: " + dimensionGui.toString());

            FXMLLoader loader = new FXMLLoader(Util.getFXMLURL(dimensionGui.toString()));
            loader.setController(new MainController());
            StackPane pane = loader.load();
            JFXDecorator decorator = new CmdlgDecorator(stage, pane);
            decorator.setCustomMaximize(true);
            decorator.setText("Command list generator");


            Scene scene = new Scene(decorator, dimensionGui.getPrefWidth(), dimensionGui.getPrefHeigt());
            scene.getStylesheets().addAll(Main.class.getResource("/css/Main.css").toExternalForm());
            stage.setScene(scene);
            stage.setResizable(false);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static DimensionGui getDimensionGui() {
        HashMap<Integer, DimensionGui> dimensionGuiHashMap = new HashMap<>();

        Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();

        for (DimensionGui gui : Main.dimensionGuis) {
            dimensionGuiHashMap.put(gui.getHeight() + gui.getWidth(), gui);
        }

        List<Integer> keys = new ArrayList<>(dimensionGuiHashMap.keySet());

        int index = closest((int) (screen.getHeight() + screen.getWidth()), keys);

        System.out.println("GUI INDEX: " + index);

        return dimensionGuiHashMap.get(index);
    }

    public static int closest(int value, Collection<Integer> in) {
        Integer[] a = in.toArray(new Integer[in.size()]);
        int low = 0;
        int high = a.length - 1;

        if (high < 0)
            throw new IllegalArgumentException("The array cannot be empty");

        while (low < high) {
            int mid = (low + high) / 2;
            assert (mid < high);
            int d1 = Math.abs(a[mid] - value);
            int d2 = Math.abs(a[mid + 1] - value);
            if (d2 <= d1) {
                low = mid + 1;
            } else {
                high = mid;
            }
        }
        return a[high];
    }

    public static GeneratorType generatorTypeByDisplayName(String s) {
        for (GeneratorType type : nameToGeneratorType.values()) {
            if (s.equalsIgnoreCase(type.getName()))
                return type;
        }
        return null;
    }
}
