package net.gunivers.commandlistgenerator.gui;

import java.net.URL;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDialog;
import com.jfoenix.controls.JFXDialogLayout;
import com.jfoenix.controls.JFXListView;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import net.gunivers.commandlistgenerator.CommandListGenerator;
import net.gunivers.commandlistgenerator.functionality.Functionality;
import net.gunivers.commandlistgenerator.gui.handlers.ButtonEditHandler;
import net.gunivers.commandlistgenerator.gui.handlers.ButtonNextHandler;

public class EditController implements Initializable
{
	public static StackPane MAIN_PANE = CommandListGeneratorController.MAIN_PANE;

	@FXML
	private JFXDialog dialog;

	@FXML
	private JFXDialogLayout layout;

	@FXML
	private JFXButton NEXT_BUTTON;

	@FXML
	private JFXListView<Label> FUNCTIONALITY_LIST;

	@Override
	public void initialize(URL location, ResourceBundle resources)
	{
		layout.setPrefSize(CommandListGenerator.MAIN_STAGE.getWidth() / 1.25, CommandListGenerator.MAIN_STAGE.getHeight() / 1.25);

		for (Functionality functionality : Functionality.getFunctionalities().values())
			FUNCTIONALITY_LIST.getItems().add(new Label(functionality.toString()));

		NEXT_BUTTON.setOnAction(new ButtonNextHandler(FUNCTIONALITY_LIST, ButtonEditHandler.dialog));
	}
}
