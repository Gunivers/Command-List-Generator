package old;

import fr.gunivers.cmdlg.util.GeneratorType;

import java.util.ArrayList;

public class GeneratorParser {
	
	private McFunctionGenerator mc;
	
	public GeneratorParser(McFunctionGenerator mc) {
		this.mc = mc;
	}
	
	/**Parse and redirect a generator to the right method
	 * @param gen GeneratorType
	 * @param args Object[] arguments for the method
	 * @param command String[] parts of the command
	 * @return ArrayLis<String> commands list
	 */
	public ArrayList<String> parse(GeneratorType gen, Object[] args, String[] command) {
		ArrayList<String> commands = null;
		
		if (gen == GeneratorType.INT) commands = mc.generateInt((int) args[0], (int) args[1], (int) args[2], command);
		if (gen == GeneratorType.LONG) commands = mc.generateLong((long) args[0], (long) args[1], (long) args[2], command);
		if (gen == GeneratorType.FLOAT) commands = mc.generateFloat((float) args[0], (float) args[1], (float) args[2], command);
		if (gen == GeneratorType.DOUBLE) commands = mc.generateDouble((double) args[0], (double) args[1], (double) args[2], command);
		
		if (gen == GeneratorType.MATERIAL) commands = mc.generateMaterial(command);
		if (gen == GeneratorType.BLOCK) commands = mc.generateMaterial(command);
		if (gen == GeneratorType.BURNABLE) commands = mc.generateMaterial(command);
		if (gen == GeneratorType.EDIBLE) commands = mc.generateMaterial(command);
		if (gen == GeneratorType.FLAMMABLE) commands = mc.generateMaterial(command);
		if (gen == GeneratorType.FUEL) commands = mc.generateMaterial(command);
		if (gen == GeneratorType.OCCLUDING) commands = mc.generateMaterial(command);
		if (gen == GeneratorType.RECORD) commands = mc.generateMaterial(command);
		if (gen == GeneratorType.SOLID) commands = mc.generateMaterial(command);
		if (gen == GeneratorType.TRANSPARENT) commands = mc.generateMaterial(command);

		if (gen == GeneratorType.MATERIAL_ID) commands = mc.generateMaterialID(command);
		if (gen == GeneratorType.BLOCK_ID) commands = mc.generateMaterialID(command);
		if (gen == GeneratorType.BURNABLE_ID) commands = mc.generateMaterialID(command);
		if (gen == GeneratorType.EDIBLE_ID) commands = mc.generateMaterialID(command);
		if (gen == GeneratorType.FLAMMABLE_ID) commands = mc.generateMaterialID(command);
		if (gen == GeneratorType.FUEL_ID) commands = mc.generateMaterialID(command);
		if (gen == GeneratorType.OCCLUDING_ID) commands = mc.generateMaterialID(command);
		if (gen == GeneratorType.RECORD_ID) commands = mc.generateMaterialID(command);
		if (gen == GeneratorType.SOLID_ID) commands = mc.generateMaterialID(command);
		if (gen == GeneratorType.TRANSPARENT) commands = mc.generateMaterial(command);
		
		return commands;
	}
}
