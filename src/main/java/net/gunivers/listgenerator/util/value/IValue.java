package net.gunivers.listgenerator.util.value;

public abstract class IValue<E>
{
	public abstract void set(E type);

	public abstract E get();
}
