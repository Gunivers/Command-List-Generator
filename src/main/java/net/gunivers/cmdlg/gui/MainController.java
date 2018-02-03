package net.gunivers.cmdlg.gui;

import com.jfoenix.controls.*;

import net.gunivers.cmdlg.Main;
import net.gunivers.cmdlg.api.BasicGenerator;
import net.gunivers.cmdlg.generators.NullGenerator;
import net.gunivers.cmdlg.gui.console.Console;
import net.gunivers.cmdlg.util.GeneratorType;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
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
    private JFXListView<Label> listFilter;

    private JFXSnackbar snackbar;

    /**
     * Auto init all GeneratorType
     * @param location
     * @param resources
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //Add label to the list view for select the GeneratorType
        Console.logDebug("Adding all Generator type to the listview");
        for (GeneratorType type : Main.nameToGeneratorType.values()) {
            Label label = new Label();
            label.setText(type.getName());
            listView.getItems().add(label);
        }

        Console.logDebug("Init the snack bar");
        //init the snackBar
        snackbar = new JFXSnackbar(mainPane);
    }

    /**
     * This is add filter.
     * @param event
     */
    @FXML
    public void addFilter(ActionEvent event) {
        //Init layout and dialog
        Console.logDebug("Init new filter dialog");
        Console.logInfo("Init new filter dialog");
        displayFilter(new Label(), true);
    }

    /**
     * This is remove filter.
     * @param event
     */
    @FXML
    public void removeFilter(ActionEvent event) {
        //Get the selected label
        Label label = listFilter.getSelectionModel().getSelectedItem();

        //if the panel is not null
        if (label == null) {
            //display error because is cool
            displayError("Please select a filter", "Error: selected filter is null.");
            return;
        }

        Console.logDebug("Removing filter: " + label.toString());
        Console.logInfo("Removing filter [" + label.getText() + "]");
        //remove the label of the list
        listFilter.getItems().remove(label);
        //show a snack bar for 4sec
        snackbar.show("Successfully removed filter.", 4 * 1000);
    }

    /**
     * For edit filter.
     * @param event
     */
    @FXML
    public void editFilter(ActionEvent event) {
        Label label = listFilter.getSelectionModel().getSelectedItem();
        Console.logDebug("Editing filter: " + label.toString());
        Console.logInfo("Editing filter [" + label.getText() + "]");
        displayFilter(label, false);
    }

    /**
     * For set the new GeneratorType when user click on the list.
     * @param event
     */
    @FXML
    public void onMouseReleasedListView(MouseEvent event) {
        //Get the generatorType by label
        String string = listView.getSelectionModel().getSelectedItems().get(0).getText();
        //set the Generator type
        GeneratorType t = Main.generatorTypeByDisplayName(string);

        Console.logDebug("Generator type clicked: " + t);

        if (t != null && t != type) {
            type = t;
            Console.logInfo("New generator type selected = " + type.getName());
        }
    }

    /**
     * Copy all command when click on the TextArea the outup text is not null
     * @param event
     */
    @FXML
    public void copyAllCommand(MouseEvent event) {
        if (output.getText() != null ) {
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
        try {
            if (command.getText() == null || command.getText().isEmpty()) {
                displayError("Please insert a command", "Error: command is null.");
                return;
            }

            if (type == null) {
                displayError("Please select a type", "Error: type is null.");
                return;
            }

            if (type.getClazz() == null) {
                displayError("This type is not supported yet.", "Error: incorrect value.");
                return;
            }

            //get the command
            String command = this.command.getText();
            Console.logDebug("Command = " + this.command.toString());
            //Get the generator by the Type of generator
            BasicGenerator generator = getBasicGeneratorForType(type);
            Console.logDebug("BasicGenerator = " + generator);
            //TODO: Complete this !
            //Generator command
            ArrayList<String> arrayList = generator.generate();
            Iterator<String> commands = arrayList.iterator();
            Console.logDebug("commands = " + arrayList.toString());
            //Create a string builder for the filter value.
            StringBuilder builder = new StringBuilder();
            //Get all filter
            HashMap<String, List<String>> replacedString = getFilter();

            //process of replacement
            if (replacedString.size() > 0) {
                Console.logInfo("Starting replacement for command by " + replacedString.size() + " filters");
                while (commands.hasNext()) {
                    String cmd = commands.next();
                    for (String key : replacedString.keySet()) {
                        List<String> by = replacedString.get(key);
                        for (String b : by) {
                            Console.logDebug("Replace filter for command: " + cmd + " if has key: " + key + " and replaced by " + b);
                            builder.append(cmd.replaceAll(key, b)).append("\n");
                        }
                    }
                }
            } else {
                while (commands.hasNext()) {
                    builder.append(commands.next()).append("\n");
                }
            }

            //Set the output Text
            output.clear();
            output.setText(builder.toString());
            Console.logDebug("new output = " + output.toString());
            Console.logInfo("Generated " + arrayList.size() + " commands.");
            //Show the snackbar for 4sec
            snackbar.show("All command have been generated.", 4 * 1000);
        } catch (Exception e) {
            e.printStackTrace();
        }
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
                filters.put(args[0], Arrays.asList(args[1].split(",")));
            }
        }
        return filters;
    }

    public void displayError(String message, String error) {
        JFXDialogLayout content = new JFXDialogLayout();
        content.setHeading(new Text(error));
        content.setBody(new Text(message));

        JFXDialog dialog = new JFXDialog(mainPane, content, JFXDialog.DialogTransition.CENTER);
        JFXButton button = new JFXButton("Done");
        button.setButtonType(JFXButton.ButtonType.RAISED);
        button.setStyle("-fx-background-color: #00a8d6");
        button.setTextFill(Paint.valueOf("WHITE"));
        button.setOnAction(event1 -> dialog.close());
        content.setActions(button);
        dialog.show();
    }

    private void displayFilter(Label label, boolean isAdd) {

        //if the panel is not null
        if (label == null) {
            //display error because is cool
            displayError("Please select a filter", "Error: selected filter is null.");
            return;
        }

        //replace all " " and "     " by a ""
        String x = label.getText().replaceAll("\\p{Z}","").trim();

        //split the x string
        String[] strings = x.split("->");

        //Init layout and dialog
        JFXDialogLayout layout = new JFXDialogLayout();
        JFXDialog dialog = new JFXDialog(mainPane, layout, JFXDialog.DialogTransition.CENTER);

        //Set the title of dialog
        layout.setHeading(new StackPane(new Label("Edit Filter")));

        //Create stack pane to add the text field ans text area
        AnchorPane pane = new AnchorPane();

        JFXTextField textField = new JFXTextField();
        textField.setPromptText("To");

        if(!isAdd)
            textField.setText(strings[0]);

        textField.setLabelFloat(true);
        textField.setLayoutX(65);
        textField.setLayoutY(46);
        StackPane.setMargin(textField, new Insets(0, 0, 150, 0));
        //add the text field to the pane
        Console.logDebug("Adding JFXTextField " + textField.toString() + " To the pane");
        pane.getChildren().add(textField);

        JFXTextArea area = new JFXTextArea();
        area.setPromptText("By");

        if(!isAdd)
            area.setText(strings[1]);

        area.setLabelFloat(true);
        area.setLayoutX(65);
        area.setLayoutY(120);
        StackPane.setMargin(area, new Insets(100, 0, 0, 0));
        //add the text area to the pane
        Console.logDebug("Adding JFXTextArea " + area.toString() + " To the pane");
        pane.getChildren().add(area);

        //Set the body layout
        layout.setBody(pane);

        //add the on close event
        dialog.setOnDialogClosed(event1 -> {
            //replace the Label if the text field and text area is not null
            if (!textField.getText().isEmpty() && !area.getText().isEmpty()) {
                Console.logDebug("Removing label: " + label + " for editing");
                listFilter.getItems().remove(label);
                Label l = new Label(textField.getText().trim() + "  ->  " + area.getText().trim());
                Console.logDebug("Adding new label: " + l + " for editing");
                listFilter.getItems().add(l);
            }

            Console.logDebug("Quiting current dialog");
        });

        //add the done button and set the event to return of the dialog close event
        JFXButton done = new JFXButton("Done");
        done.setButtonType(JFXButton.ButtonType.RAISED);
        done.setStyle("-fx-background-color: #00a8d6;");
        done.setTextFill(Paint.valueOf("WHITE"));
        done.setOnAction(event12 -> {

            Console.logDebug("Closing dialog by button");
            dialog.close();
        });

        //add the button
        layout.setActions(done);

        //show the dialog
        dialog.show(mainPane);
    }
}
