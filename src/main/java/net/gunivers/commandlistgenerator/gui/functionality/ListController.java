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
import net.gunivers.core.utils.tuple.Tuple;
import net.gunivers.core.utils.tuple.Tuple1;

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
		getDoneButton().setDefaultButton(true);

		if (CommandListGeneratorController.CONTROLLER.getTypeList().getSelectionModel().getSelectedItem() != null &&
				!CommandListGeneratorController.CONTROLLER.getTypeList().getSelectionModel().getSelectedItem().getText().isEmpty())
		{
			setDialog(ButtonEditHandler.dialog);
			Tuple t = Tag.tags.get(CommandListGeneratorController.CONTROLLER.getTagList().getSelectionModel().getSelectedItem().getText()).getParameters();
			Tuple1<String[]> tuple = Tuple.castTo(t, Tuple.newTuple(String[].class));
			String text = "";
			for(int i = 0; i < tuple._1.length; i++)
				text += tuple._1[i] + ((i == text.length() - 1) ? "" : "\n");
				
			TEXT_AREA.setText(text);
		}
	}

	@Override
	public void saveAll()
	{
		Tag tag = Tag.tags.get(((Label) CommandListGeneratorController.SYNC_LIST_HANDLER.getListViewOne().getItems().get(INDEX)).getText());
		tag.setType(Functionality.getFunctionalities("List"));
		
		tag.setParameters(Tuple.newTuple(TEXT_AREA.getText().split("\n")));

		CommandListGeneratorController.SYNC_LIST_HANDLER.putInAndSelect(SyncListHandler.ListNumber.TWO, new Label(tag.getType().toString()), INDEX);
		getDialog().close();
	}
}
