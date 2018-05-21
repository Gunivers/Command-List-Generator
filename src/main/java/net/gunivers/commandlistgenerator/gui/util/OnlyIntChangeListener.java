package net.gunivers.commandlistgenerator.gui.util;

import com.jfoenix.controls.JFXTextField;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;

public class OnlyIntChangeListener implements ChangeListener<String>
{
	private JFXTextField textField;

	public OnlyIntChangeListener(JFXTextField textField)
	{
		this.textField = textField;
	}

	@Override
	public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue)
	{
		if (!newValue.matches("[-]?\\d*"))
		{
			String msg = newValue.replaceAll("[^\\d]", "");
			textField.setText(msg);
		}
	}
}
