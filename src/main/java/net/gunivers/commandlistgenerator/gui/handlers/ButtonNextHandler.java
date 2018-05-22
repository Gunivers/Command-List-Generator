package net.gunivers.commandlistgenerator.gui.handlers;

import java.io.IOException;

import com.jfoenix.controls.JFXDialog;
import com.jfoenix.controls.JFXDialogLayout;
import com.jfoenix.controls.JFXListView;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import net.gunivers.commandlistgenerator.functionality.Functionality;
import net.gunivers.commandlistgenerator.gui.CommandListGeneratorController;

public class ButtonNextHandler implements EventHandler<ActionEvent>
{
	private JFXListView<Label> listView;

	public static JFXDialog dialog;

	public static JFXDialog newDialog;

	public ButtonNextHandler(JFXListView<Label> listView, JFXDialog dialog)
	{
		ButtonNextHandler.dialog = dialog;
		this.listView = listView;
	}

	@Override
	public void handle(ActionEvent event)
	{
		if (listView.getSelectionModel().getSelectedItem() != null)
		{
			Functionality functionality = Functionality.getFunctionalities(listView.getSelectionModel().getSelectedItem().getText());
			newDialog = new JFXDialog();
			JFXDialogLayout layout = null;

			try
			{
				layout = FXMLLoader.load(getClass().getResource("/fxml/functionality/" + functionality.getDefaultName() + ".fxml"));
			} catch (IOException e)
			{
				e.printStackTrace();
			}

			newDialog.setContent(layout);

			dialog.close();
			newDialog.show(CommandListGeneratorController.MAIN_PANE);
		}
	}
}
