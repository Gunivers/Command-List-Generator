package net.gunivers.cmdlg.gui;

import com.jfoenix.controls.JFXDecorator;
import javafx.scene.Node;
import javafx.stage.Stage;

public class CmdlgDecorator extends JFXDecorator {

    public CmdlgDecorator(Stage stage, Node node) {
        super(stage, node, false, false, true);
    }
}
