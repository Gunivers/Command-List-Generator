package fr.gunivers.cmdlg;

import java.util.ArrayList;

public class GeneratorParser {
	
	private McFunctionGenerator mc;
	
	public GeneratorParser(McFunctionGenerator mc) {
		this.mc = mc;
	}
	
	
	public ArrayList<String> parse(Generators gen, Object[] args, String[] command) {
		ArrayList<String> commands = null;
		
		if (gen == Generators.Int) commands = mc.generateInt((int) args[0], (int) args[1], (int) args[2], command);
		if (gen == Generators.Long) commands = mc.generateLong((long) args[0], (long) args[1], (long) args[2], command);
		if (gen == Generators.Float) commands = mc.generateFloat((float) args[0], (float) args[1], (float) args[2], command);
		if (gen == Generators.Double) commands = mc.generateDouble((double) args[0], (double) args[1], (double) args[2], command);
		
		if (gen == Generators.Material) commands = mc.generateMaterial(command);
		if (gen == Generators.Block) commands = mc.generateMaterial(command);
		if (gen == Generators.Burnable) commands = mc.generateMaterial(command);
		if (gen == Generators.Edible) commands = mc.generateMaterial(command);
		if (gen == Generators.Flammable) commands = mc.generateMaterial(command);
		if (gen == Generators.Fuel) commands = mc.generateMaterial(command);
		if (gen == Generators.Occluding) commands = mc.generateMaterial(command);
		if (gen == Generators.Record) commands = mc.generateMaterial(command);
		if (gen == Generators.Solid) commands = mc.generateMaterial(command);
		if (gen == Generators.Transparent) commands = mc.generateMaterial(command);

		if (gen == Generators.MaterialID) commands = mc.generateMaterialID(command);
		if (gen == Generators.BlockID) commands = mc.generateMaterialID(command);
		if (gen == Generators.BurnableID) commands = mc.generateMaterialID(command);
		if (gen == Generators.EdibleID) commands = mc.generateMaterialID(command);
		if (gen == Generators.FlammableID) commands = mc.generateMaterialID(command);
		if (gen == Generators.FuelID) commands = mc.generateMaterialID(command);
		if (gen == Generators.OccludingID) commands = mc.generateMaterialID(command);
		if (gen == Generators.RecordID) commands = mc.generateMaterialID(command);
		if (gen == Generators.SolidID) commands = mc.generateMaterialID(command);
		if (gen == Generators.Transparent) commands = mc.generateMaterial(command);
		
		return commands;
	}
}
