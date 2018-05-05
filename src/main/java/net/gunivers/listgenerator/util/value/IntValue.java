package net.gunivers.listgenerator.util.value;

import net.gunivers.listgenerator.util.Tag;

public class IntValue extends IValue<Integer>
{
    private int val = 0;

    public IntValue(Integer val)
    {
        set(val);
    }

    @Override
    public void set(Integer type)
    {
        this.val = type;
    }

    @Override
    public Integer get()
    {
        return val;
    }
}
