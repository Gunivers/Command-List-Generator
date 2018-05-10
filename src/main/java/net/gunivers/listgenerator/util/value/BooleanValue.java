package net.gunivers.listgenerator.util.value;

public class BooleanValue extends IValue<Boolean>
{

	private Boolean val = false;

	public BooleanValue(Boolean object)
	{
		set(object);
	}

	@Override
	public void set(Boolean type)
	{
		this.val = type;
	}

	@Override
	public Boolean get()
	{
		return val;
	}
}
