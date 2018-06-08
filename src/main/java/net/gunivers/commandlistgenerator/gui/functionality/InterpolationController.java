package net.gunivers.commandlistgenerator.gui.functionality;

import java.net.URL;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXSnackbar;
import com.jfoenix.controls.JFXTextField;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import net.gunivers.commandlistgenerator.CommandListGenerator;
import net.gunivers.commandlistgenerator.functionality.Functionality;
import net.gunivers.commandlistgenerator.gui.CommandListGeneratorController;
import net.gunivers.commandlistgenerator.gui.handlers.ButtonEditHandler;
import net.gunivers.commandlistgenerator.gui.handlers.ButtonNextHandler;
import net.gunivers.commandlistgenerator.gui.handlers.list.SyncListHandler;
import net.gunivers.commandlistgenerator.gui.util.FunctionalityController;
import net.gunivers.commandlistgenerator.gui.util.OnlyDoublePosChangeListener;
import net.gunivers.commandlistgenerator.gui.util.OnlyIntPosChangeListener;
import net.gunivers.commandlistgenerator.util.Tag;
import net.gunivers.core.gui.ShakeEffect;
import net.gunivers.core.utils.tuple.Tuple;
import net.gunivers.core.utils.tuple.Tuple6;

public class InterpolationController extends FunctionalityController implements Initializable {
	
	private JFXSnackbar bar = new JFXSnackbar(CommandListGeneratorController.MAIN_PANE);
	
	@FXML
	private JFXCheckBox CHECK_BOX_1;

	@FXML
	private JFXCheckBox CHECK_BOX_2;

	@FXML
	private JFXTextField TEXT_FIELD_1;

	@FXML
	private JFXTextField TEXT_FIELD_2;

	@FXML
	private JFXTextField TEXT_FIELD_3;
	
	@FXML
	private JFXTextField TEXT_FIELD_4;

	private int INDEX = CommandListGeneratorController.SYNC_LIST_HANDLER.getListViewOne().getSelectionModel()
			.getSelectedIndex();

	@Override
	public void initialize(URL location, ResourceBundle resources) {		
		setDialog(ButtonNextHandler.newDialog);

		getDoneButton().setOnAction(event -> saveAll());
		getDoneButton().setDefaultButton(true);
		
		getDoneButton().setText(l.get("gui.button.done"));
		TEXT_FIELD_1.setPromptText(l.get("gui.interpolation.parameter.start"));
		TEXT_FIELD_2.setPromptText(l.get("gui.interpolation.parameter.end"));
		TEXT_FIELD_3.setPromptText(l.get("gui.interpolation.parameter.power"));
		TEXT_FIELD_4.setPromptText(l.get("gui.functionalities.decimale"));
		CHECK_BOX_1.setText(l.get("gui.interpolation.parameter.revert"));
		CHECK_BOX_2.setText(l.get("gui.interpolation.parameter.noextremes"));

		TEXT_FIELD_1.textProperty().addListener(new OnlyDoublePosChangeListener(TEXT_FIELD_1));
		TEXT_FIELD_2.textProperty().addListener(new OnlyDoublePosChangeListener(TEXT_FIELD_2));
		TEXT_FIELD_3.textProperty().addListener(new OnlyDoublePosChangeListener(TEXT_FIELD_3));
		TEXT_FIELD_4.textProperty().addListener(new OnlyIntPosChangeListener(TEXT_FIELD_4));

		if (CommandListGeneratorController.CONTROLLER.getTypeList().getSelectionModel().getSelectedItem() != null
				&& !CommandListGeneratorController.CONTROLLER.getTypeList().getSelectionModel().getSelectedItem()
						.getText().isEmpty()) {
			setDialog(ButtonEditHandler.dialog);
			Tuple t =Tag.tags.get(CommandListGeneratorController.CONTROLLER.getTagList()
					.getSelectionModel().getSelectedItem().getText()).getParameters();
			Tuple6<Double, Double, Double, Boolean, Integer, Boolean> tuple = Tuple.castTo(t,
					Tuple.newTuple(Double.class, Double.class, Double.class, Boolean.class, Integer.class, Boolean.class));
			TEXT_FIELD_1.setText(Double.toString(tuple._1));
			TEXT_FIELD_2.setText(Double.toString(tuple._2));
			TEXT_FIELD_3.setText(Double.toString(tuple._3));
			CHECK_BOX_1.setSelected(tuple._4);
			TEXT_FIELD_4.setText(Double.toString(tuple._5));
			CHECK_BOX_2.setSelected(tuple._6);
		}
	}

	@Override
	public void saveAll() {
		if(ShakeEffect.isFullOrElseShake(TEXT_FIELD_1, TEXT_FIELD_2, TEXT_FIELD_3, TEXT_FIELD_4)) {
			if(Double.valueOf(TEXT_FIELD_1.getText()) >= Double.valueOf(TEXT_FIELD_2.getText())) {
				ShakeEffect.shake(TEXT_FIELD_1, TEXT_FIELD_2);
				bar.show(CommandListGenerator.LANGUAGE.get("gui.functionalities.error.startsuperiorthanend"), 5 * 1000);
			} else {
				Tag tag = Tag.tags
						.get(((Label) CommandListGeneratorController.SYNC_LIST_HANDLER.getListViewOne().getItems().get(INDEX))
								.getText());
				tag.setType(Functionality.getFunctionalityByDefaultName("Interpolation"));
					tag.setParameters(Tuple.newTuple(Double.valueOf(TEXT_FIELD_1.getText()), Double.valueOf(TEXT_FIELD_2.getText()),
							Double.valueOf(TEXT_FIELD_3.getText()), CHECK_BOX_1.isSelected(), Integer.valueOf(TEXT_FIELD_4.getText()), CHECK_BOX_2.isSelected()));
		
				CommandListGeneratorController.SYNC_LIST_HANDLER.putInAndSelect(SyncListHandler.ListNumber.TWO,
						new Label(tag.getType().toString()), INDEX);
				getDialog().close();
			}
		}
	}
}
