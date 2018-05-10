package net.gunivers.listgenerator.gui.util;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDialog;
import javafx.fxml.FXML;

public abstract class FunctionalityController
{
	private JFXDialog dialog;

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
}
