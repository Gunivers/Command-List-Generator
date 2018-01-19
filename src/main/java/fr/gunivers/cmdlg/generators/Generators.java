package fr.gunivers.cmdlg.generators;

public enum Generators {
    Int, Long, Float, Double,
    Material, Block, Burnable, Edible, Flammable, Fuel, Occluding, Record, Solid, Transparent,
    MaterialID, BlockID, BurnableID, EdibleID, FlammableID, FuelID, OccludingID, RecordID, SolidID, TransparentID,
    Interp, Dichotomie;

	/**Test the primitive type of a generator
	 * @return boolean is this a primitive generator
	 */
    public boolean isPrimitiveGenerator() {
        if (this == Int || this == Long || this == Float || this == Double) return true;
        
        return false;
    }

	/**Test the material type of a generator
	 * @return boolean is this a material generator
	 */
    public boolean isMaterialGenerator() {
        if (this == Material || this == Block || this == Burnable || this == Edible || this == Flammable || this == Fuel ||
                this == Occluding || this == Record || this == Solid || this == Transparent) return true;
        
        return false;
    }

	/**Test the materialID type of a generator
	 * @return boolean is this a materialID generator
	 */
    public boolean isMaterialIDGenerator() {
        if (this == MaterialID || this == BlockID || this == BurnableID || this == EdibleID || this == FlammableID
                || this == FuelID || this == OccludingID || this == RecordID ||
                this == SolidID || this == TransparentID) return true;
        
        return false;
    }

	/**Test the mathematics type of a generator
	 * @return boolean is this a mathematics generator
	 */
    public boolean isMathematicGenerator() {
    	if (this == Interp || this == Dichotomie) return true;
    	
    	return false;
    }
}