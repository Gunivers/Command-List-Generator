package net.gunivers.commandlistgenerator.gui;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.Set;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXListView;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import net.gunivers.commandlistgenerator.CommandListGenerator;
import net.gunivers.commandlistgenerator.gui.handlers.ButtonEditHandler;
import net.gunivers.commandlistgenerator.gui.handlers.ButtonGenerateHandler;
import net.gunivers.commandlistgenerator.gui.handlers.TextFieldCommandChangeHandler;
import net.gunivers.commandlistgenerator.gui.handlers.list.SyncListHandler;
import net.gunivers.commandlistgenerator.gui.util.OnlyIntPosChangeListener;
import net.gunivers.commandlistgenerator.util.Tag;
import net.gunivers.core.language.Language;

public class CommandListGeneratorController implements Initializable {
	public static CommandListGeneratorController CONTROLLER;

	public static StackPane MAIN_PANE;

	@FXML
	private StackPane PANE;

	@FXML
	private JFXButton BUTTON_GENERATE;

	@FXML
	private JFXButton BUTTON_EDIT;

	@FXML
	private JFXTextArea COMMAND_OUTPUT;

	@FXML
	private JFXTextField COMMAND_INPUT;

	@FXML
	private JFXTextField MAX_COMMAND;

	@FXML
	private JFXListView<Label> TAG_LIST;

	@FXML
	private JFXListView<Label> TYPE_LIST;
	
	@FXML
	private Label LABEL_TAG;
	
	@FXML
	private Label LABEL_TYPE;

	public static SyncListHandler<Label> SYNC_LIST_HANDLER = null;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		Language l = CommandListGenerator.LANGUAGE;
		CONTROLLER = this;
		MAIN_PANE = PANE;

		SYNC_LIST_HANDLER = new SyncListHandler<Label>(TAG_LIST, TYPE_LIST);

		MAX_COMMAND.setPromptText(l.get("gui.textfield.maxcommand"));
		COMMAND_INPUT.setPromptText(l.get("gui.commandlistgenerator.command"));
		COMMAND_OUTPUT.setPromptText(l.get("gui.textarea.output"));
		BUTTON_GENERATE.setText(l.get("gui.button.generate"));
		BUTTON_EDIT.setText(l.get("gui.button.edit"));
		LABEL_TAG.setText(l.get("gui.label.tag"));
		LABEL_TYPE.setText(l.get("gui.label.type"));
		
		MAX_COMMAND.textProperty().addListener(new OnlyIntPosChangeListener(MAX_COMMAND));

		COMMAND_INPUT.setOnKeyReleased(new TextFieldCommandChangeHandler(COMMAND_INPUT));
		

		BUTTON_GENERATE.setOnAction(new ButtonGenerateHandler(BUTTON_GENERATE, COMMAND_INPUT, COMMAND_OUTPUT, MAX_COMMAND, TYPE_LIST));
		BUTTON_GENERATE.setDefaultButton(true);
		
		BUTTON_EDIT.setOnAction(new ButtonEditHandler());
		
		
		EventHandler<MouseEvent> event = new EventHandler<MouseEvent>() {
			 @Override
			    public void handle(MouseEvent mouseEvent) {
			        if(mouseEvent.getButton().equals(MouseButton.PRIMARY))
			            if(mouseEvent.getClickCount() == 2)
			            	BUTTON_EDIT.fire();	            
			 }
		};
		
		
		TAG_LIST.setOnMouseClicked(event);
		TYPE_LIST.setOnMouseClicked(event);
	}

	public SyncListHandler<Label> getSyncListHandler() {
		return SYNC_LIST_HANDLER;
	}

	public void checksTag(Set<String> tags) {
		ArrayList<String> displayed = new ArrayList<>();
		for (Label l : TAG_LIST.getItems())
			displayed.add(l.getText());

		ArrayList<Label> needRemoveTag = new ArrayList<>();

		ArrayList<Label> needAddedTag = new ArrayList<>();

		for (String tag : tags) {
			if (!displayed.contains(tag)) {
				needAddedTag.add(new Label(tag));
			}
		}

		for (Label label : TAG_LIST.getItems()) {
			if (!tags.contains(label.getText()))
				needRemoveTag.add(label);

		}

		for (Label label : needRemoveTag) {
			int index = TAG_LIST.getItems().indexOf(label);

			if (!TYPE_LIST.getItems().get(index).getText().isEmpty())
				Tag.tags.remove(TAG_LIST.getItems().get(index).getText());

			TAG_LIST.getItems().remove(index);
			TYPE_LIST.getItems().remove(index);
		}

		for (Label label : needAddedTag) {
			TAG_LIST.getItems().add(label);
			TYPE_LIST.getItems().add(new Label());
		}

		TAG_LIST.refresh();
		TYPE_LIST.refresh();
	}

	public int getMaxCommand() {
		try {
			return Integer.valueOf(MAX_COMMAND.getText());
		} catch (Exception e) {
			return 0;
		}
	}

	public JFXListView<Label> getTagList() {
		return TAG_LIST;
	}

	public JFXListView<Label> getTypeList() {
		return TYPE_LIST;
	}
}
