package fr.gunivers.cmdlg;


import java.util.ArrayList;

public enum Generators {
    Int, Long, Float, Double,
    Material, Block, Burnable, Edible, Flammable, Fuel, Occluding, Record, Solid, Transparent,
    MaterialID, BlockID, BurnableID, EdibleID, FlammableID, FuelID, OccludingID, RecordID, SolidID, TransparentID;

    public McFunctionGenerator McFuncGen = new McFunctionGenerator();

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

    public ArrayList<String> GeneratorParser(Object[] args, String command) {
        ArrayList<String> commands = null;

        System.out.println("Int recognized? " + this.isPrimitiveGenerator());
        if (this.isPrimitiveGenerator()) commands = PrimitiveGenParser(this, args, command);
        if (this.isMaterialGenerator()) commands = MaterialGenParser(this, command);
        if (this.isMaterialIDGenerator()) commands = MaterialIDGenParser(this, command);

        return commands;
    }

    private ArrayList<String> PrimitiveGenParser(Generators generators, Object[] args, String command) {
        ArrayList<String> commands = null;

        String[] part = {command, ""};

        System.out.println("Int[] recognized? " + (this == Int && args instanceof Integer[]));
        if (args.length >= 3) {
            if (this == Int && args instanceof Integer[]) {
                commands = McFuncGen.generateInt((Integer) args[1], (Integer) args[1], (Integer) args[1], part);
            } else if (this == Long && args instanceof Long[]) {
                commands = McFuncGen.generateLong((Long) args[1], (Long) args[1], (Long) args[1], part);
            } else if (this == Float && args instanceof Float[]) {
                commands = McFuncGen.generateFloat((Float) args[1], (Float) args[1], (Float) args[1], part);
            } else if (this == Double && args instanceof Double[]) {
                commands = McFuncGen.generateDouble((Double) args[1], (Double) args[1], (Double) args[1], part);
            }
        }

        return commands;
    }

    private ArrayList<String> MaterialGenParser(Generators generators, String command) {
        String[] part = {command, ""};
        ArrayList<String> commands = null;

        if (this == Material) commands = McFuncGen.generateMaterial(part);
        if (this == Block) commands = McFuncGen.generateBlock(part);
        if (this == Burnable) commands = McFuncGen.generateBurnable(part);
        if (this == Edible) commands = McFuncGen.generateEdible(part);
        if (this == Flammable) commands = McFuncGen.generateFlammable(part);
        if (this == Fuel) commands = McFuncGen.generateFuel(part);
        if (this == Occluding) commands = McFuncGen.generateOccluding(part);
        if (this == Record) commands = McFuncGen.generateRecord(part);
        if (this == Solid) commands = McFuncGen.generateSolid(part);
        if (this == Transparent) commands = McFuncGen.generateTransparent(part);

        return commands;
    }

    private ArrayList<String> MaterialIDGenParser(Generators generators, String command) {
        String[] part = {command, ""};
        ArrayList<String> commands = null;

        if (this == MaterialID) commands = McFuncGen.generateMaterialID(part);
        if (this == BlockID) commands = McFuncGen.generateBlockID(part);
        if (this == BurnableID) commands = McFuncGen.generateBurnableID(part);
        if (this == EdibleID) commands = McFuncGen.generateEdibleID(part);
        if (this == FlammableID) commands = McFuncGen.generateFlammableID(part);
        if (this == FuelID) commands = McFuncGen.generateFuelID(part);
        if (this == OccludingID) commands = McFuncGen.generateOccludingID(part);
        if (this == RecordID) commands = McFuncGen.generateRecordID(part);
        if (this == SolidID) commands = McFuncGen.generateSolidID(part);
        if (this == TransparentID) commands = McFuncGen.generateTransparentID(part);

        return commands;
    }
}