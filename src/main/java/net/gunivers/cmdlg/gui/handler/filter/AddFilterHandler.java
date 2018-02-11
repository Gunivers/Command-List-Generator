package net.gunivers.cmdlg.gui.handler.filter;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;

import net.gunivers.cmdlg.gui.Dialog;


public class AddFilterHandler implements EventHandler<ActionEvent>
{
	public static ListView<Label> filter_list;

	public AddFilterHandler(ListView<Label> listView) {
		filter_list = listView;
	}

	@Override
	public void handle(ActionEvent event)
	{
		Dialog dialog = new Dialog();
		dialog.getDoneButton().setOnAction(new DialogHandler(dialog));
		dialog.getExitButton().setOnAction(event1 -> dialog.showMenuStage());
		dialog.show();
	}

	public class DialogHandler implements EventHandler<ActionEvent>
	{
		private Dialog dialog;

		public DialogHandler(Dialog dialog)
		{
			this.dialog = dialog;
		}

		public Dialog getDialog()
		{
			return dialog;
		}

		@Override
		public void handle(ActionEvent event)
		{
			if (getDialog().getTextFieldTo().getText().isEmpty() || dialog.getTextAreaBy().getText().isEmpty())
			{
				getDialog().showMenuStage();
				return;
			}

			String by = getDialog().getTextAreaBy().getText();
			String to = getDialog().getTextFieldTo().getText();

			if (by.contains("\n"))
				by = by.replaceAll("\n", "\u2759");

			Label label = new Label(to + " \u2192 " + by);
			label.setStyle("-fx-font-size: 12pt");
			AddFilterHandler.filter_list.getItems().add(label);
			getDialog().showMenuStage();
		}
	}
}
