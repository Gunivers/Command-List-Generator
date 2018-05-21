package net.gunivers.commandlistgenerator.gui.util;

import com.jfoenix.controls.JFXTextField;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;

public class OnlyDoublePosChangeListener implements ChangeListener<String>
{
	private JFXTextField textField;

	public OnlyDoublePosChangeListener(JFXTextField textField)
	{
		this.textField = textField;
	}

	@Override
	public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue)
	{
		if (!newValue.matches("\\dDd.*"))
		{
			String msg = newValue.replaceAll("[^\\dDd.]", "");
			textField.setText(msg);
		}
	}
}
