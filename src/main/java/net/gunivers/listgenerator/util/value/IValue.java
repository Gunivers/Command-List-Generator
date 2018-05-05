package net.gunivers.listgenerator.util.value;

import net.gunivers.listgenerator.util.Tag;

public abstract class IValue<E>
{
    public abstract void set(E type);

    public abstract E get();
}
