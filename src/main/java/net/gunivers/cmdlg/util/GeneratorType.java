package net.gunivers.cmdlg.util;

import fr.gunivers.cmdlg.api.BasicGenerator;

import fr.gunivers.cmdlg.generators.material.*;
import fr.gunivers.cmdlg.generators.materialID.*;

import fr.gunivers.cmdlg.generators.math.InterpGenerator;
import fr.gunivers.cmdlg.generators.math.InterpScoreGenerator;

import fr.gunivers.cmdlg.generators.primitive.DoubleGenerator;
import fr.gunivers.cmdlg.generators.primitive.FloatGenerator;
import fr.gunivers.cmdlg.generators.primitive.IntGenerator;
import fr.gunivers.cmdlg.generators.primitive.LongGenerator;


public enum GeneratorType {

	//PRIMITIVES GENERATORS
    INT("Integer", IntGenerator.class),
    LONG("Long", LongGenerator.class),
    FLOAT("Float", FloatGenerator.class),
    DOUBLE("Double", DoubleGenerator.class),
    
    //MATERIAL GENERATORS
    MATERIAL("Material", MaterialGenerator.class),
    BLOCK("Block", BlockGenerator.class),
    BURNABLE("Burnable", BurnableGenerator.class),
    EDIBLE("Edible", EdibleGenerator.class),
    FLAMMABLE("Flammable", FlammableGenerator.class),
    FUEL("Fuel", FuelGenerator.class),
    OCCLUDING("Occluding", OccludingGenerator.class),
    RECORD("Record", RecordGenerator.class),
    SOLID("Solid", SolidGenerator.class),
    TRANSPARENT("Transparent", TransparentGenerator.class),
    
    //MATERIAL ID GENERATORS
    MATERIAL_ID("Material ID", IDMaterialGenerator.class),
    BLOCK_ID("Block ID", IDBlockGenerator.class),
    BURNABLE_ID("Burnable ID", IDBurnableGenerator.class),
    EDIBLE_ID("Edible ID", IDEdibleGenerator.class),
    FLAMMABLE_ID("Flammable ID", IDFlammableGenerator.class),
    FUEL_ID("Fuel ID", IDFuelGenerator.class),
    OCCLUDING_ID("Occluding ID", IDOccludingGenerator.class),
    RECORD_ID("Record ID", IDRecordGenerator.class),
    SOLID_ID("Solid ID", IDSolidGenerator.class),
    TRANSPARENT_ID("Transparent ID", IDTransparentGenerator.class),
    
    //MATHEMATICS GENERATORS
    INTERP("Interp", InterpGenerator.class),
    INTERPSCORE("Interpscore", InterpScoreGenerator.class),
    DICHOTOMIE("Dichotomie", null);

    private String name;
    private Class<? extends BasicGenerator> clazz;

    /**
     *
     * @param name This is the name of Generator type.
     */
    GeneratorType(String name, Class<? extends BasicGenerator> clazz) {
        this.clazz = clazz;
        this.name = name;
    }

    /**
     * @return The name of Type.
     */
    public String getName() {
        return name;
    }

    /**
     *
     * @return To the class of Type.
     */
    public Class<? extends BasicGenerator> getClazz() {
        return clazz;
    }

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