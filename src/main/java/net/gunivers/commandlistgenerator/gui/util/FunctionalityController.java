package net.gunivers.commandlistgenerator.gui.util;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDialog;

import javafx.fxml.FXML;
import net.gunivers.commandlistgenerator.CommandListGenerator;
import net.gunivers.core.language.Language;

public abstract class FunctionalityController
{
	private JFXDialog dialog;
	protected static Language l = CommandListGenerator.LANGUAGE;

	@FXML
	private JFXButton DONE;

	public JFXButton getDoneButton()
	{
		return DONE;
	}

	public abstract void saveAll();

	public JFXDialog getDialog()
	{
		return dialog;
	}

	public void setDialog(JFXDialog dialog)
	{
		this.dialog = dialog;
	}
	
	public static void refreshLanguage() {
		l = CommandListGenerator.LANGUAGE;
	}
}
