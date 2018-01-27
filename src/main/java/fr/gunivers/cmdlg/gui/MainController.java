package fr.gunivers.cmdlg.gui;

import com.jfoenix.controls.*;
import fr.gunivers.cmdlg.Main;
import fr.gunivers.cmdlg.api.BasicGenerator;
import fr.gunivers.cmdlg.util.GeneratorType;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Paint;
import javafx.scene.text.Text;

import java.net.URL;
import java.util.ResourceBundle;

public class MainController implements Initializable {

    private static GeneratorType type = null;

    @FXML
    private JFXListView<Label> listView;

    @FXML
    private JFXTextField command;

    @FXML
    private TextArea output;

    @FXML
    private StackPane mainPane;

    @FXML
    private StackPane basic;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        for (GeneratorType type : Main.nameToGeneratorType.values()) {
            Label label = new Label();
            label.setText(type.getName());
            listView.getItems().add(label);
        }
    }

    @FXML
    public void onMouseReleasedListView(MouseEvent event) {
        GeneratorType oldType = type;
        String string = listView.getSelectionModel().getSelectedItems().get(0).getText();
        type = Main.generatorTypeByDisplayName(string);
    }

    @FXML
    public void generateAction(ActionEvent event) {
        if (type == null) {
            JFXDialogLayout content = new JFXDialogLayout();
            content.setHeading(new Text("Error: Incorrect value"));
            content.setBody(new Text("The generator Type is not set"));

            JFXDialog dialog = new JFXDialog(mainPane, content, JFXDialog.DialogTransition.CENTER);
            JFXButton button = new JFXButton("Done");
            button.setButtonType(JFXButton.ButtonType.RAISED);
            button.setStyle("-fx-background-color: #00a8d6");
            button.setTextFill(Paint.valueOf("WHITE"));
            button.setOnAction(event1 -> dialog.close());
            content.setActions(button);
            dialog.show();
            return;
        }

        if (type.getClazz() == null) {
            JFXDialogLayout content = new JFXDialogLayout();
            content.setHeading(new Text("Error: Incorrect value"));
            content.setBody(new Text("Not supported yet"));

            JFXDialog dialog = new JFXDialog(mainPane, content, JFXDialog.DialogTransition.CENTER);
            JFXButton button = new JFXButton("Done");
            button.setButtonType(JFXButton.ButtonType.RAISED);
            button.setStyle("-fx-background-color: #00a8d6");
            button.setTextFill(Paint.valueOf("WHITE"));
            button.setOnAction(event1 -> dialog.close());
            content.setActions(button);
            dialog.show();
            return;
        }

        BasicGenerator generator = getBasicGeneratorForType(type);
    }

    private BasicGenerator getBasicGeneratorForType(GeneratorType type) {
        return null;
    }
}
