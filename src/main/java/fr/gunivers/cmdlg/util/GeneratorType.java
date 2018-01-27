package fr.gunivers.cmdlg.util;

public enum GeneratorType {

	//PRIMITIVES GENERATORS
    INT,
    LONG,
    FLOAT,
    DOUBLE,
    
    //MATERIAL GENERATORS
    MATERIAL,
    BLOCK,
    BURNABLE,
    EDIBLE,
    FLAMMABLE,
    FUEL,
    OCCLUDING,
    RECORD,
    SOLID,
    TRANSPARENT,
    
    //MATERIAL ID GENERATORS
    MATERIAL_ID,
    BLOCK_ID,
    BURNABLE_ID,
    EDIBLE_ID,
    FLAMMABLE_ID,
    FUEL_ID,
    OCCLUDING_ID,
    RECORD_ID,
    SOLID_ID,
    TRANSPARENT_ID,
    
    //MATHEMATICS GENERATORS
    INTERP,
    INTERPSCORE,
    DICHOTOMIE;

    /**
     * Test the primitive type of a generator
     *
     * @return boolean if this is a primitive generator
     */
    public boolean isPrimitiveGenerator() {
        if (this == INT || this == LONG || this == FLOAT || this == DOUBLE) return true;

        return false;
    }

    /**
     * Test the material type of a generator
     *
     * @return boolean if this is a material generator
     */
    public boolean isMaterialGenerator() {
        if (this == MATERIAL || this == BLOCK || this == BURNABLE || this == EDIBLE || this == FLAMMABLE || this == FUEL ||
                this == OCCLUDING || this == RECORD || this == SOLID || this == TRANSPARENT) return true;

        return false;
    }

    /**
     * Test the materialID type of a generator
     *
     * @return boolean if this is a materialID generator
     */
    public boolean isMaterialIDGenerator() {
        if (this == MATERIAL_ID || this == BLOCK_ID || this == BURNABLE_ID || this == EDIBLE_ID || this == FLAMMABLE_ID
                || this == FUEL_ID || this == OCCLUDING_ID || this == RECORD_ID ||
                this == SOLID_ID || this == TRANSPARENT_ID) return true;

        return false;
    }

    /**
     * Test the mathematics type of a generator
     *
     * @return boolean if this is a mathematics generator
     */
    public boolean isMathematicGenerator() {
        if (this == INTERP || this == INTERPSCORE || this == DICHOTOMIE) return true;

        return false;
    }
}