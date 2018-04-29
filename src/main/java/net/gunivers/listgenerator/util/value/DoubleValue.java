package net.gunivers.listgenerator.util.value;

import net.gunivers.listgenerator.util.Tag;

public class DoubleValue extends IValue<Double>
{
    private Double val = 0.0D;

    public DoubleValue(Tag key)
    {
        super(key);
    }

    @Override
    public void set(Double type)
    {
        this.val = type;
    }

    @Override
    public Double get()
    {
        return val;
    }
}
