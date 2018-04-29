package net.gunivers.listgenerator.util.value;

import net.gunivers.listgenerator.util.Tag;

public abstract class IValue<E>
{
    private Tag key;

    public IValue(Tag key)
    {
        this.key = key;
    }

    public abstract void set(E type);

    public abstract E get();

    public Tag getKey()
    {
        return key;
    }
}
