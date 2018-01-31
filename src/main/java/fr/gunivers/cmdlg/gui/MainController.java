package fr.gunivers.cmdlg.gui;

import com.jfoenix.controls.*;

import fr.gunivers.cmdlg.Main;
import fr.gunivers.cmdlg.api.BasicGenerator;
import fr.gunivers.cmdlg.generators.NullGenerator;
import fr.gunivers.cmdlg.util.GeneratorType;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Paint;
import javafx.scene.text.Text;

import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.net.URL;
import java.util.*;
import java.util.List;

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

    @FXML
    private JFXListView<Label> listFilter;

    private JFXSnackbar snackbar;

    /**
     * Auto init all GeneratorType
     * @param location
     * @param resources
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        for (GeneratorType type : Main.nameToGeneratorType.values()) {
            Label label = new Label();
            label.setText(type.getName());
            listView.getItems().add(label);
        }

        snackbar = new JFXSnackbar(mainPane);


    }

    /**
     * This is add filter.
     * @param event
     */
    @FXML
    public void addFilter(ActionEvent event) {
        JFXDialogLayout layout = new JFXDialogLayout();
        JFXDialog dialog = new JFXDialog(mainPane, layout, JFXDialog.DialogTransition.CENTER);

        AnchorPane pane = new AnchorPane();
        pane.setPrefHeight(400);
        pane.setPrefWidth(600);

        JFXTextField textField = new JFXTextField();
        textField.setPromptText("To");
        textField.setLabelFloat(true);
        textField.setLayoutX(65);
        textField.setLayoutY(46);
        pane.getChildren().add(textField);

        JFXTextArea area = new JFXTextArea();
        area.setPromptText("By");
        area.setLabelFloat(true);
        area.setLayoutX(65);
        area.setLayoutY(120);
        pane.getChildren().add(area);

        layout.setBody(pane);

        dialog.setOnDialogClosed(event1 -> {
            if (!textField.getText().isEmpty() && !area.getText().isEmpty())
                listFilter.getItems().add(new Label(textField.getText().trim() + "  ->  " + area.getText().trim()));
        });

        JFXButton done = new JFXButton("Done");
        done.setButtonType(JFXButton.ButtonType.RAISED);
        done.setStyle("-fx-background-color: #00a8d6;");
        done.setTextFill(Paint.valueOf("WHITE"));
        done.setOnAction(event12 -> {
            dialog.close();
        });

        layout.setActions(done);

        dialog.show(mainPane);
    }

    /**
     * This is remove filter.
     * @param event
     */
    @FXML
    public void removeFilter(ActionEvent event) {
        Label label = listFilter.getSelectionModel().getSelectedItem();

        if (label == null) {
            snackbar.show("Please select a filter.", 4 * 1000);
            return;
        }

        listFilter.getItems().remove(label);
        snackbar.show("Successfully removed filter.", 4 * 1000);
    }

    /**
     * For remove filter.
     * @param event
     */
    @FXML
    public void editFilter(ActionEvent event) {
        Label label = listFilter.getSelectionModel().getSelectedItem();

        if (label == null) {
            snackbar.show("Please select a filter.", 4 * 1000);
            return;
        }

        String x = label.getText().replaceAll("\\p{Z}","");

        String[] strings = x.trim().split("->");

        JFXDialogLayout layout = new JFXDialogLayout();
        JFXDialog dialog = new JFXDialog(mainPane, layout, JFXDialog.DialogTransition.CENTER);

        AnchorPane pane = new AnchorPane();
        pane.setPrefHeight(400);
        pane.setPrefWidth(600);

        JFXTextField textField = new JFXTextField();
        textField.setPromptText("To");

        textField.setText(strings[0]);

        textField.setLabelFloat(true);
        textField.setLayoutX(65);
        textField.setLayoutY(46);
        pane.getChildren().add(textField);

        JFXTextArea area = new JFXTextArea();
        area.setPromptText("By");
        area.setText(strings[1]);
        area.setLabelFloat(true);
        area.setLayoutX(65);
        area.setLayoutY(120);
        pane.getChildren().add(area);

        layout.setBody(pane);

        dialog.setOnDialogClosed(event1 -> {
            if (!textField.getText().isEmpty() && !area.getText().isEmpty()) {
                listFilter.getItems().remove(label);
                listFilter.getItems().add(new Label(textField.getText().trim() + "  ->  " + area.getText().trim()));
            }
        });

        JFXButton done = new JFXButton("Done");
        done.setButtonType(JFXButton.ButtonType.RAISED);
        done.setStyle("-fx-background-color: #00a8d6;");
        done.setTextFill(Paint.valueOf("WHITE"));
        done.setOnAction(event12 -> {
            dialog.close();
        });

        layout.setActions(done);

        dialog.show(mainPane);
    }

    /**
     * For set the new GeneratorType when user click on the list.
     * @param event
     */
    @FXML
    public void onMouseReleasedListView(MouseEvent event) {
        GeneratorType oldType = type;
        String string = listView.getSelectionModel().getSelectedItems().get(0).getText();
        type = Main.generatorTypeByDisplayName(string);
    }

    /**
     * Copy all command when click on the TextArea the outup text is not null
     * @param event
     */
    @FXML
    public void copyAllCommand(MouseEvent event) {
    	
        if (output.getText() != null && !output.getText().isEmpty()) {
        	
            Clipboard clpbrd = Toolkit.getDefaultToolkit().getSystemClipboard();
            clpbrd.setContents(new StringSelection(output.getText()), null);
            snackbar.show("All command has been copied.", 4 * 1000);
        }
    }

    /**
     * Generate command
     * @param event
     */
    @FXML
    public void generateAction(ActionEvent event) {

        if (command.getText() == null || command.getText().isEmpty()) {
            JFXDialogLayout content = new JFXDialogLayout();
            content.setHeading(new Text("Error: Incorrect command"));
            content.setBody(new Text("Please set the command"));

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

        String command = this.command.getText();

        BasicGenerator generator = getBasicGeneratorForType(type);

        Iterator<String> commands = generator.generate();



        StringBuilder builder = new StringBuilder();

        HashMap<String, List<String>> replacedString = getFilter();

        if (replacedString.size() > 0) {
            while (commands.hasNext()) {
                for (String key : replacedString.keySet()) {
                    List<String> by = replacedString.get(key);
                    for (String b : by) {
                        builder.append(commands.next().replaceAll(key, b)).append("\n");
                    }
                }
            }
        } else {
            builder.append(commands.next()).append("\n");
        }

        output.setText(builder.toString());

        snackbar.show("All command have been generated.", 4 * 1000);
    }

    /**
     * Return to the BasicGenerator of GeneratorType
     * @param type
     */
    private BasicGenerator getBasicGeneratorForType(GeneratorType type) {
        switch (type) {
            default:
                return new NullGenerator();
        }
    }

    /**
     * Return to the Filter list.
     */
    private HashMap<String, List<String>> getFilter() {
        HashMap<String, List<String>> filters = new HashMap<>();
        if (listFilter.getItems().size() > 0) {
            for (Label l : listFilter.getItems()) {
                String label = l.getText();
                String x = label.replaceAll("\\p{Z}","").replaceAll("\n", ",");
                String[] args = x.split("->");
                filters.put(args[0], Arrays.asList(args[2].split(",")));
            }
        }
        return filters;
    }
}
