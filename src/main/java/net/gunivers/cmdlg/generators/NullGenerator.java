package net.gunivers.cmdlg.generators;

import net.gunivers.cmdlg.api.BasicGenerator;
import net.gunivers.cmdlg.util.GeneratorType;

import java.util.ArrayList;
import java.util.Collections;

public class NullGenerator extends BasicGenerator
{

	@Override
	public ArrayList<String> generate()
	{
		return new ArrayList<>(Collections.singletonList("This a null, please check your option."));
	}

	@Override
	public GeneratorType getType()
	{
		return null;
	}
}
