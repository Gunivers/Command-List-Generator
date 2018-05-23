package net.gunivers.commandlistgenerator.gui.handlers;

import java.io.IOException;

import com.jfoenix.controls.JFXDialog;
import com.jfoenix.controls.JFXDialogLayout;
import com.jfoenix.controls.JFXSnackbar;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import net.gunivers.commandlistgenerator.CommandListGenerator;
import net.gunivers.commandlistgenerator.functionality.Functionality;
import net.gunivers.commandlistgenerator.gui.CommandListGeneratorController;
import net.gunivers.commandlistgenerator.util.Tag;

public class ButtonEditHandler implements EventHandler<ActionEvent> {

	private JFXSnackbar bar = new JFXSnackbar(CommandListGeneratorController.MAIN_PANE);

	public static JFXDialog dialog;

	@Override
	public void handle(ActionEvent event) {
		if (CommandListGeneratorController.CONTROLLER.getTagList().getSelectionModel().getSelectedItem() != null) {
			String tagName = CommandListGeneratorController.CONTROLLER.getTagList().getSelectionModel()
					.getSelectedItem().getText();

			if (Tag.tags.get(tagName).getParameters() == null) {
				dialog = new JFXDialog();

				JFXDialogLayout layout = null;
				try {
					layout = FXMLLoader.load(getClass().getResource("/fxml/ButtonEdit.fxml"));
				} catch (IOException e) {
					e.printStackTrace();
				}

				dialog.setContent(layout);
				dialog.show(CommandListGeneratorController.MAIN_PANE);
			} else {
				dialog = new JFXDialog();

				Functionality functionality = Functionality.getFunctionalityByTag(CommandListGeneratorController.CONTROLLER
						.getTypeList().getSelectionModel().getSelectedItem().getText());
				JFXDialogLayout layout = null;

				try {
					layout = FXMLLoader
							.load(getClass().getResource("/fxml/functionality/" + functionality.getDefaultName() + ".fxml"));
				} catch (IOException e) {
					e.printStackTrace();
				}

				dialog.setContent(layout);
				dialog.show(CommandListGeneratorController.MAIN_PANE);
			}
		} else {
			bar.show(CommandListGenerator.language.get("gui.commandlistgenerator.notagselected"), 3 * 1000);
		}
	}
}
