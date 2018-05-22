package net.gunivers.commandlistgenerator.gui.functionality;

import java.net.URL;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXTextField;

import javafx.event.ActionEvent;
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
import net.gunivers.commandlistgenerator.util.Tag;
import net.gunivers.core.gui.ShakeEffect;
import net.gunivers.core.utils.tuple.Tuple;
import net.gunivers.core.utils.tuple.Tuple3;

public class DuplicationController extends FunctionalityController implements Initializable {
	@FXML
	private JFXTextField TOP_TEXT;

	@FXML
	private JFXTextField MIDDLE_TEXT;

	@FXML
	private JFXTextField BOTTOM_TEXT;

	private int INDEX = CommandListGeneratorController.SYNC_LIST_HANDLER.getListViewOne().getSelectionModel()
			.getSelectedIndex();

	
	@FXML
	public void onEnter(ActionEvent ae){
	   System.out.println("test") ;
	}
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		setDialog(ButtonNextHandler.newDialog);

		getDoneButton().setOnAction(event -> saveAll());
		getDoneButton().setDefaultButton(true);
		
		MIDDLE_TEXT.textProperty().addListener(new OnlyDoublePosChangeListener(MIDDLE_TEXT));
		BOTTOM_TEXT.textProperty().addListener(new OnlyDoublePosChangeListener(BOTTOM_TEXT));

		if (CommandListGeneratorController.CONTROLLER.getTypeList().getSelectionModel().getSelectedItem() != null
				&& !CommandListGeneratorController.CONTROLLER.getTypeList().getSelectionModel().getSelectedItem()
						.getText().isEmpty()) {
			setDialog(ButtonEditHandler.dialog);
			Tuple t = Tag.tags.get(CommandListGeneratorController.CONTROLLER.getTagList().getSelectionModel()
					.getSelectedItem().getText()).getParameters();
			Tuple3<String, Double, Double> tuple = Tuple.castTo(t,
					Tuple.newTuple(String.class, Double.class, Double.class));
			TOP_TEXT.setText(tuple._1);
			MIDDLE_TEXT.setText(Double.toString(tuple._2));
			BOTTOM_TEXT.setText(Double.toString(tuple._3));
		}
	}

	@Override
	public void saveAll() {
		if(ShakeEffect.isFullOrElseShake(TOP_TEXT, MIDDLE_TEXT, BOTTOM_TEXT)) {
			Tag tag = Tag.tags
					.get(((Label) CommandListGeneratorController.SYNC_LIST_HANDLER.getListViewOne().getItems().get(INDEX))
							.getText());
			tag.setType(Functionality.getFunctionalities("Duplication"));
			tag.setParameters(Tuple.newTuple(TOP_TEXT.getText(), Double.valueOf(MIDDLE_TEXT.getText()),
					Double.valueOf(BOTTOM_TEXT.getText())));
	
			CommandListGeneratorController.SYNC_LIST_HANDLER.putInAndSelect(SyncListHandler.ListNumber.TWO,
					new Label(tag.getType().toString()), INDEX);
			getDialog().close();
		}
	}
}
