package net.gunivers.listgenerator.util.value;

public class DoubleValue extends IValue<Double>
{
    private Double val = 0.0D;

    public DoubleValue(Double val)
    {
        set(val);
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
