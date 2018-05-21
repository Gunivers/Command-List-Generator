package net.gunivers.commandlistgenerator.gui.functionality;

import java.net.URL;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXTextArea;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import net.gunivers.commandlistgenerator.functionality.Functionality;
import net.gunivers.commandlistgenerator.gui.CommandListGeneratorController;
import net.gunivers.commandlistgenerator.gui.handlers.ButtonEditHandler;
import net.gunivers.commandlistgenerator.gui.handlers.ButtonNextHandler;
import net.gunivers.commandlistgenerator.gui.handlers.list.SyncListHandler;
import net.gunivers.commandlistgenerator.gui.util.FunctionalityController;
import net.gunivers.commandlistgenerator.util.Tag;
import net.gunivers.llistgenerator.util.value.IValue;
import net.gunivers.llistgenerator.util.value.StringValue;
import net.gunivers.llistgenerator.util.value.ValueManager;

public class ListController extends FunctionalityController implements Initializable
{
	@FXML
	JFXTextArea TEXT_AREA;

	private int INDEX = CommandListGeneratorController.SYNC_LIST_HANDLER.getListViewOne().getSelectionModel().getSelectedIndex();

	@Override
	public void initialize(URL location, ResourceBundle resources)
	{
		setDialog(ButtonNextHandler.newDialog);

		getDoneButton().setOnAction(event -> saveAll());

		if (CommandListGeneratorController.CONTROLLER.getTypeList().getSelectionModel().getSelectedItem() != null &&
				!CommandListGeneratorController.CONTROLLER.getTypeList().getSelectionModel().getSelectedItem().getText().isEmpty())
		{
			setDialog(ButtonEditHandler.dialog);
			IValue[] values = ValueManager.getValues(CommandListGeneratorController.CONTROLLER.getTagList().getSelectionModel().getSelectedItem().getText());
			TEXT_AREA.setText(((StringValue) values[0]).get() + "");
		}
	}

	@Override
	public void saveAll()
	{
		Tag tag = Tag.tags.get(((Label) CommandListGeneratorController.SYNC_LIST_HANDLER.getListViewOne().getItems().get(INDEX)).getText());
		tag.setType(Functionality.getFunctionalities("List"));

		try
		{
			ValueManager.register(tag, TEXT_AREA.getText(), CommandListGeneratorController.CONTROLLER.getMaxCommand());
		} catch (Exception e)
		{
			return;
		}

		CommandListGeneratorController.SYNC_LIST_HANDLER.putInAndSelect(SyncListHandler.ListNumber.TWO, new Label(tag.getType().toString()), INDEX);
		getDialog().close();
	}
}
