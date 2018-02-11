package net.gunivers.cmdlg.gui.handler.filter;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import net.gunivers.cmdlg.gui.Dialog;
import net.gunivers.cmdlg.gui.console.Console;

public class EditFilterHandler implements EventHandler<ActionEvent>
{
	private static ListView<Label> filter_list;

	public EditFilterHandler(ListView<Label> listView)
	{
		filter_list = listView;
	}

	@Override
	public void handle(ActionEvent event)
	{
		Label selected_label = filter_list.getSelectionModel().getSelectedItems().get(0);
		if (selected_label != null)
		{
			Dialog dialog = new Dialog();
			Console.logDebug("Set action of done button to: " + DialogHandler.class.toString());
			dialog.getDoneButton().setOnAction(new DialogHandler(dialog, selected_label));
			Console.logDebug("Set action of done button to: event1 -> dialog.showMenuStage()");
			dialog.getExitButton().setOnAction(event1 -> dialog.showMenuStage());
			Console.logDebug("Showing new dialog");
			String[] args = selected_label.getText().split(" \u2192 ");
			String to = args[0];
			String by = args[1].replaceAll("\u2759", "\n");
			dialog.getTextFieldTo().setText(to);
			dialog.getTextAreaBy().setText(by);
			dialog.show();
		}
	}

	private class DialogHandler implements EventHandler<ActionEvent>
	{
		private Dialog dialog;
		private Label oldLabel;

		public DialogHandler(Dialog dialog, Label oldLabel)
		{
			this.oldLabel = oldLabel;
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
			filter_list.getItems().remove(oldLabel);
			filter_list.getItems().add(label);
			getDialog().showMenuStage();
		}
	}
}
