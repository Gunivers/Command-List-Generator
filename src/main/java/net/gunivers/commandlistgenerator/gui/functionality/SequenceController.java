package net.gunivers.commandlistgenerator.gui.functionality;

import java.net.URL;
import java.util.Arrays;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import net.gunivers.commandlistgenerator.functionality.Functionality;
import net.gunivers.commandlistgenerator.gui.CommandListGeneratorController;
import net.gunivers.commandlistgenerator.gui.handlers.ButtonEditHandler;
import net.gunivers.commandlistgenerator.gui.handlers.ButtonNextHandler;
import net.gunivers.commandlistgenerator.gui.handlers.list.SyncListHandler;
import net.gunivers.commandlistgenerator.gui.util.FunctionalityController;
import net.gunivers.commandlistgenerator.gui.util.OnlyDoublePosChangeListener;
import net.gunivers.commandlistgenerator.gui.util.OnlyIntPosChangeListener;
import net.gunivers.commandlistgenerator.util.Tag;
import net.gunivers.commandlistgenerator.util.Type;
import net.gunivers.core.language.tuple.Tuple;
import net.gunivers.core.language.tuple.Tuple4;

public class SequenceController extends FunctionalityController implements Initializable {

	//Sequence
	@FXML
	private JFXTextField TOP_TEXT;

	//InitValue
	@FXML
	private JFXTextField MIDDLE_TEXT;

	//Round
	@FXML
	private JFXTextField BOTTOM_TEXT;
	
	@FXML 
	private JFXComboBox<String> COMBO_BOX;

	private int INDEX = CommandListGeneratorController.SYNC_LIST_HANDLER.getListViewOne().getSelectionModel()
			.getSelectedIndex();

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		setDialog(ButtonNextHandler.newDialog);

		getDoneButton().setOnAction(event -> saveAll());
		COMBO_BOX.getItems().setAll(Arrays.stream(Type.values()).map(name -> name.toString()).toArray(String[]::new));
		MIDDLE_TEXT.textProperty().addListener(new OnlyDoublePosChangeListener(MIDDLE_TEXT));
		BOTTOM_TEXT.textProperty().addListener(new OnlyIntPosChangeListener(BOTTOM_TEXT));

		if (CommandListGeneratorController.CONTROLLER.getTypeList().getSelectionModel().getSelectedItem() != null
				&& !CommandListGeneratorController.CONTROLLER.getTypeList().getSelectionModel().getSelectedItem()
						.getText().isEmpty()) {
			setDialog(ButtonEditHandler.dialog);
			Tuple t = Tag.tags.get(CommandListGeneratorController.CONTROLLER.getTagList().getSelectionModel()
					.getSelectedItem().getText()).getParameters();
			Tuple4<Double, String, Integer, Type> tuple = Tuple.castTo(t, Tuple.newTuple(Double.class, String.class, Integer.class, Type.class));
			TOP_TEXT.setText(tuple._2);
			MIDDLE_TEXT.setText(Double.toString(tuple._1));
			BOTTOM_TEXT.setText(Integer.toString(tuple._3));
			COMBO_BOX.setValue(tuple._4.toString());
		}
	}

	@Override
	public void saveAll() {
		Tag tag = Tag.tags
				.get(((Label) CommandListGeneratorController.SYNC_LIST_HANDLER.getListViewOne().getItems().get(INDEX))
						.getText());
		tag.setType(Functionality.getFunctionalities("Sequence"));

		tag.setParameters(Tuple.newTuple(Double.valueOf(MIDDLE_TEXT.getText()), TOP_TEXT.getText(), Integer.valueOf(BOTTOM_TEXT.getText()), Type.valueOf(COMBO_BOX.getValue().toUpperCase())));

		CommandListGeneratorController.SYNC_LIST_HANDLER.putInAndSelect(SyncListHandler.ListNumber.TWO,
				new Label(tag.getType().toString()), INDEX);
		getDialog().close();
	}
}
