package fr.gunivers.cmdlg;

public enum Generators {
    Int, Long, Float, Double,
    Material, Block, Burnable, Edible, Flammable, Fuel, Occluding, Record, Solid, Transparent,
    MaterialID, BlockID, BurnableID, EdibleID, FlammableID, FuelID, OccludingID, RecordID, SolidID, TransparentID,
    Interp, Dichotomie;

    public boolean isPrimitiveGenerator() {
        if (this == Int || this == Long || this == Float || this == Double) return true;
        
        return false;
    }

    
    public boolean isMaterialGenerator() {
        if (this == Material || this == Block || this == Burnable || this == Edible || this == Flammable || this == Fuel ||
                this == Occluding || this == Record || this == Solid || this == Transparent) return true;
        
        return false;
    }

    
    public boolean isMaterialIDGenerator() {
        if (this == MaterialID || this == BlockID || this == BurnableID || this == EdibleID || this == FlammableID
                || this == FuelID || this == OccludingID || this == RecordID ||
                this == SolidID || this == TransparentID) return true;
        
        return false;
    }

    
    public boolean isMathematicGenerator() {
    	if (this == Interp || this == Dichotomie) return true;
    	
    	return false;
    }
}