package net.gunivers.listgenerator.gui.util;

import com.jfoenix.controls.JFXTextField;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;

public class OnlyDoubleChangeListener implements ChangeListener<String>
{
	private JFXTextField textField;

	public OnlyDoubleChangeListener(JFXTextField textField)
	{
		this.textField = textField;
	}

	@Override
	public void changed(ObservableValue observable, String oldValue, String newValue)
	{
		if (!newValue.matches("\\dDd.*"))
		{
			String msg = newValue.replaceAll("[^\\dDd.]", "");
			textField.setText(msg);
		}
	}
}
