//CONTRIBUTORS:

package fr.gunivers.cmdlg.generators;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import fr.gunivers.cmdlg.Material;


@SuppressWarnings("deprecation")
public class McFunctionGenerator {
	
	private GeneratorParser GP;
	
	private File file = null;
	private FileWriter fw;
	
	public ArrayList<String> commands;
	
	/**Constructor
	 * @param file location of the mcFunction
	 * @return a new McFunctionGenerator that will write functions on file
	 */
	public McFunctionGenerator(File file) {
		//Initialize file
		try {this.fw = new FileWriter(file);}
		catch (IOException exception) {exception.printStackTrace();}
		
		this.GP = new GeneratorParser(this);
	}
	
	/** Close the FileWriter -> avoid bug
	 * @throws IOException
	 */
	public void close() throws IOException{
		fw.close();
	}
	
	
	/**Real Generation
	 * @return count of written commands
	 * @throws IOException
	 */
	public int generate() throws IOException {
		for (String command : commands) {
			this.fw.write(command);
			this.fw.write("\n");
		}
		
		return commands.size();
	}
	/**Real Generation [Method 2]
	 * @param commands ArrayList containing the commands to be generated
	 * @return count of written commands
	 * @throws IOException
	 */
	public int generate(ArrayList<String> commands) throws IOException {
		for (String command : commands) {
			this.fw.write(command);
			this.fw.write("\n");
		}
		
		return commands.size();
	}
	
	/**AdvancedGeneration
	 * @param generators HashMap with the generators and their param
	 * @param command String[] parts of commands
	 * @return ArrayList<String> commands
	 */
	public ArrayList<String> generateAdvanced(HashMap<Generators, Object[]> generators, String [] command) {
		List<Generators> gens = new ArrayList<Generators>();
		List<Object[]> values = new ArrayList<Object[]>();
		
		ArrayList<String> commands = new ArrayList<String>();
		commands.add("");
		
		for (HashMap.Entry<Generators,?> entry : generators.entrySet()) {
			gens.add(entry.getKey());
			values.add((Object[]) entry.getValue());
		}
		
		for (int i = 0; i < gens.size(); i++) {
			String [] part = {command[i]};
			
		    ArrayList<String> parts = GP.parse(gens.get(i),values.get(i), part);
			
			int partsSize = parts.size();

			int commandsSize = commands.size();
			
			//Sync sizes
			if(commandsSize < partsSize) for(int e = 0; e <= partsSize - commandsSize; e++) commands.add(commands.get(0));
			else for (int e = 0; e < commandsSize - partsSize; e++) parts.add(parts.get(0));
			

			for (int e = 0; e < parts.size(); e++) {
				commands.set(e, commands.get(e) + parts.get(e));
			}
			
			if (i == gens.size() -1) for (int e = command.length - gens.size(); e > 0; e--) 
				for (int f = 0; f < commands.size(); f++) 
				commands.set(f, commands.get(f) + command[command.length - e]);
		}
		
		this.commands = commands;
		return commands;
	}
	
	
	/**Generate Int chain command variation
	 * @param start int start of chain
	 * @param end int end of chain
	 * @param step int step of walk in chain
	 * @param command String[] parts of command
	 * @return ArrayList<String> commands list
	 */
	public ArrayList<String> generateInt(int start, int end, int step, String[] command) {
		ArrayList<String> commands = new ArrayList<String>();
		
		for (int i = start; i <= end; i += step) {
			String temp = "";
			
			for (int e = 0; e < command.length -1; e++) {
				temp += command[e] + i;
				
				if (e == command.length -2) temp += command[e+1];
			}
			
			commands.add(temp);
		}
		
		this.commands = commands;
		return commands;
	}

	/**Generate Float chain command variation
	 * @param start float start of chain
	 * @param end float end of chain
	 * @param step float step of walk in chain
	 * @param command String[] parts of command
	 * @return ArrayList<String> commands list
	 */
	public ArrayList<String> generateFloat(float start, float end, float step, String[] command) {
		ArrayList<String> commands = new ArrayList<String>();
		
		for (float i = start; i <= end; i += step) {
			String temp = "";
			
			for (int e = 0; e < command.length -1; e++) {
				temp += command[e] + i + "F";
				
				if (e == command.length -2) temp += command[e+1];
			}
			
			commands.add(temp);
		}
		
		this.commands = commands;
		return commands;
	}

	/**Generate Double chain command variation
	 * @param start double start of chain
	 * @param end double end of chain
	 * @param step double step of walk in chain
	 * @param command String[] parts of command
	 * @return ArrayList<String> commands list
	 */
	public ArrayList<String> generateDouble(double start, double end, double step, String[] command) {
		ArrayList<String> commands = new ArrayList<String>();
		
		for (double i = start; i <= end; i += step) {
			String temp = "";
			
			for (int e = 0; e < command.length -1; e++) {
				temp += command[e] + i + "D";
				
				if (e == command.length -2) temp += command[e+1];
			}
			
			commands.add(temp);
		}
		
		this.commands = commands;
		return commands;
	}

	/**Generate Long chain command variation
	 * @param start Long start of chain
	 * @param end Long end of chain
	 * @param step Long step of walk in chain
	 * @param command String[] parts of command
	 * @return ArrayList<String> commands list
	 */
	public ArrayList<String> generateLong(long start, long end, long step, String[] command) {
		ArrayList<String> commands = new ArrayList<String>();
		
		for (long i = start; i <= end; i += step) {
			String temp = "";
			
			for (int e = 0; e < command.length -1; e++) {
				temp += command[e] + i + "L";
				
				if (e == command.length -2) temp += command[e+1];
			}
			
			commands.add(temp);
		}
		
		this.commands = commands;
		return commands;
	}
	
	/**Generate Material:Material command variation
	 * @param command String[] parts of command
	 * @return ArrayList<String> commands list
	 */
	public ArrayList<String> generateMaterial(String[] command) {
		ArrayList<String> commands = new ArrayList<String>();
		
		for (Material matos : Material.values()) {
			String temp = "";
			
			for (int e = 0; e < command.length -1; e++) {
				temp += command[e] + matos.name();
				
				if (e == command.length -2) temp += command[e+1];
			}
			
			commands.add(temp);
		}
		
		this.commands = commands;
		return commands;
	}
	
	/**Generate MaterialID:MaterialID command variation
	 * @param command String[] parts of command
	 * @return ArrayList<String> commands list
	 */
	public ArrayList<String> generateMaterialID(String[] command) {
		ArrayList<String> commands = new ArrayList<String>();
		
		for (Material matos : Material.values()) {
			String temp = "";
			
			for (int e = 0; e < command.length -1; e++) {
				temp += command[e] + matos.getId();
				
				if (e == command.length -2) temp += command[e+1];
			}
			
			commands.add(temp);
		}
		
		this.commands = commands;
		return commands;
	}
	
	/**Generate Material:Block command variation
	 * @param command String[] parts of command
	 * @return ArrayList<String> commands list
	 */
	public ArrayList<String> generateBlock(String[] command) {
		ArrayList<String> commands = new ArrayList<String>();
		
		for (Material matos : Material.values()) {
			String temp = "";
			
			if (matos.isBlock()) {
				
				for (int e = 0; e < command.length -1; e++) {
					temp += command[e] + matos.name();
					
					if (e == command.length -2) temp += command[e+1];
				}
			}
			
			if (!temp.equals("")) commands.add(temp);
		}
		
		this.commands = commands;
		return commands;
	}
	
	/**Generate Material:Burnable command variation
	 * @param command String[] parts of command
	 * @return ArrayList<String> commands list
	 */
	public ArrayList<String> generateBurnable(String[] command) {
		ArrayList<String> commands = new ArrayList<String>();
		
		for (Material matos : Material.values()) {
			String temp = "";
			
			if (matos.isBurnable()) {
				
				for (int e = 0; e < command.length -1; e++) {
					temp += command[e] + matos.name();
					
					if (e == command.length -2) temp += command[e+1];
				}
			}
			
			if (!temp.equals("")) commands.add(temp);
		}
		
		this.commands = commands;
		return commands;
	}

	/**Generate Material:Edible command variation
	 * @param command String[] parts of command
	 * @return ArrayList<String> commands list
	 */
	public ArrayList<String> generateEdible(String[] command) {
		ArrayList<String> commands = new ArrayList<String>();
		
		for (Material matos : Material.values()) {
			String temp = "";
			
			if (matos.isEdible()) {
				
				for (int e = 0; e < command.length -1; e++) {
					temp += command[e] + matos.name();
					
					if (e == command.length -2) temp += command[e+1];
				}
			}
			
			if (!temp.equals("")) commands.add(temp);
		}
		
		this.commands = commands;
		return commands;
	}

	/**Generate Material:Flammable command variation
	 * @param command String[] parts of command
	 * @return ArrayList<String> commands list
	 */
	public ArrayList<String> generateFlammable(String[] command) {
		ArrayList<String> commands = new ArrayList<String>();
		
		for (Material matos : Material.values()) {
			String temp = "";
			
			if (matos.isFlammable()) {
				
				for (int e = 0; e < command.length -1; e++) {
					temp += command[e] + matos.name();
					
					if (e == command.length -2) temp += command[e+1];
				}
			}
			
			if (!temp.equals("")) commands.add(temp);
		}
		
		this.commands = commands;
		return commands;
	}

	/**Generate Material:Fuel command variation
	 * @param command String[] parts of command
	 * @return ArrayList<String> commands list
	 */
	public ArrayList<String> generateFuel(String[] command) {
		ArrayList<String> commands = new ArrayList<String>();
		
		for (Material matos : Material.values()) {
			String temp = "";
			
			if (matos.isFuel()) {
				
				for (int e = 0; e < command.length -1; e++) {
					temp += command[e] + matos.name();
					
					if (e == command.length -2) temp += command[e+1];
				}
			}
			
			if (!temp.equals("")) commands.add(temp);
		}
		
		this.commands = commands;
		return commands;
	}

	/**Generate Material:Occluding command variation
	 * @param command String[] parts of command
	 * @return ArrayList<String> commands list
	 */
	public ArrayList<String> generateOccluding(String[] command) {
		ArrayList<String> commands = new ArrayList<String>();
		
		for (Material matos : Material.values()) {
			String temp = "";
			
			if (matos.isOccluding()) {
				
				for (int e = 0; e < command.length -1; e++) {
					temp += command[e] + matos.name();
					
					if (e == command.length -2) temp += command[e+1];
				}
			}
			
			if (!temp.equals("")) commands.add(temp);
		}
		
		this.commands = commands;
		return commands;
	}

	/**Generate Material:Record command variation
	 * @param command String[] parts of command
	 * @return ArrayList<String> commands list
	 */
	public ArrayList<String> generateRecord(String[] command) {
		ArrayList<String> commands = new ArrayList<String>();
		
		for (Material matos : Material.values()) {
			String temp = "";
			
			if (matos.isRecord()) {
				
				for (int e = 0; e < command.length -1; e++) {
					temp += command[e] + matos.name();
					
					if (e == command.length -2) temp += command[e+1];
				}
			}
			
			if (!temp.equals("")) commands.add(temp);
		}
		
		this.commands = commands;
		return commands;
	}

	/**Generate Material:Solid command variation
	 * @param command String[] parts of command
	 * @return ArrayList<String> commands list
	 */
	public ArrayList<String> generateSolid(String[] command) {
		ArrayList<String> commands = new ArrayList<String>();
		
		for (Material matos : Material.values()) {
			String temp = "";
			
			if (matos.isSolid()) {
				
				for (int e = 0; e < command.length -1; e++) {
					temp += command[e] + matos.name();
					
					if (e == command.length -2) temp += command[e+1];
				}
			}
			
			if (!temp.equals("")) commands.add(temp);
		}
		
		this.commands = commands;
		return commands;
	}

	/**Generate Material:Transparent command variation
	 * @param command String[] parts of command
	 * @return ArrayList<String> commands list
	 */
	public ArrayList<String> generateTransparent(String[] command) {
		ArrayList<String> commands = new ArrayList<String>();
		
		for (Material matos : Material.values()) {
			String temp = "";
			
			if (matos.isTransparent()) {
				
				for (int e = 0; e < command.length -1; e++) {
					temp += command[e] + matos.name();
					
					if (e == command.length -2) temp += command[e+1];
				}
			}
			
			if (!temp.equals("")) commands.add(temp);
		}
		
		this.commands = commands;
		return commands;
	}

	/**Generate MaterialID:BlockID command variation
	 * @param command String[] parts of command
	 * @return ArrayList<String> commands list
	 */
	public ArrayList<String> generateBlockID(String[] command) {
		ArrayList<String> commands = new ArrayList<String>();
		
		for (Material matos : Material.values()) {
			String temp = "";
			
			if (matos.isBlock()) {
				for (int e = 0; e < command.length -1; e++) {
					temp += command[e] + matos.getId();
					
					if (e == command.length -2) temp += command[e+1];
				}
			}
			
			if (!temp.equals("")) commands.add(temp);
		}
		
		this.commands = commands;
		return commands;
	}

	/**Generate MaterialID:BurnableID command variation
	 * @param command String[] parts of command
	 * @return ArrayList<String> commands list
	 */
	public ArrayList<String> generateBurnableID(String[] command) {
		ArrayList<String> commands = new ArrayList<String>();
		
		for (Material matos : Material.values()) {
			String temp = "";
			
			if (matos.isBurnable()) {
				for (int e = 0; e < command.length -1; e++) {
					temp += command[e] + matos.getId();
					
					if (e == command.length -2) temp += command[e+1];
				}
			}
			
			if (!temp.equals("")) commands.add(temp);
		}
		
		this.commands = commands;
		return commands;
	}

	/**Generate MaterialID:EdibleID command variation
	 * @param command String[] parts of command
	 * @return ArrayList<String> commands list
	 */
	public ArrayList<String> generateEdibleID(String[] command) {
		ArrayList<String> commands = new ArrayList<String>();
		
		for (Material matos : Material.values()) {
			String temp = "";
			
			if (matos.isEdible()) {
				for (int e = 0; e < command.length -1; e++) {
					temp += command[e] + matos.getId();
					
					if (e == command.length -2) temp += command[e+1];
				}
			}
			
			if (!temp.equals("")) commands.add(temp);
		}
		
		this.commands = commands;
		return commands;
	}

	/**Generate MaterialID:FlammableID command variation
	 * @param command String[] parts of command
	 * @return ArrayList<String> commands list
	 */
	public ArrayList<String> generateFlammableID(String[] command) {
		ArrayList<String> commands = new ArrayList<String>();
		
		for (Material matos : Material.values()) {
			String temp = "";
			
			if (matos.isFlammable()) {
				for (int e = 0; e < command.length -1; e++) {
					temp += command[e] + matos.getId();
					
					if (e == command.length -2) temp += command[e+1];
				}
			}
			
			if (!temp.equals("")) commands.add(temp);
		}
		
		this.commands = commands;
		return commands;
	}

	/**Generate MaterialID:FuelID command variation
	 * @param command String[] parts of command
	 * @return ArrayList<String> commands list
	 */
	public ArrayList<String> generateFuelID(String[] command) {
		ArrayList<String> commands = new ArrayList<String>();
		
		for (Material matos : Material.values()) {
			String temp = "";
			
			if (matos.isFuel()) {
				for (int e = 0; e < command.length -1; e++) {
					temp += command[e] + matos.getId();
					
					if (e == command.length -2) temp += command[e+1];
				}
			}
			
			if (!temp.equals("")) commands.add(temp);
		}
		
		this.commands = commands;
		return commands;
	}

	/**Generate MaterialID:OccludingID command variation
	 * @param command String[] parts of command
	 * @return ArrayList<String> commands list
	 */
	public ArrayList<String> generateOccludingID(String[] command) {
		ArrayList<String> commands = new ArrayList<String>();
		
		for (Material matos : Material.values()) {
			String temp = "";
			
			if (matos.isOccluding()) {
				for (int e = 0; e < command.length -1; e++) {
					temp += command[e] + matos.getId();
					
					if (e == command.length -2) temp += command[e+1];
				}
			}
			
			if (!temp.equals("")) commands.add(temp);
		}
		
		this.commands = commands;
		return commands;
	}

	/**Generate MaterialID:RecordID command variation
	 * @param command String[] parts of command
	 * @return ArrayList<String> commands list
	 */
	public ArrayList<String> generateRecordID(String[] command) {
		ArrayList<String> commands = new ArrayList<String>();
		
		for (Material matos : Material.values()) {
			String temp = "";
			
			if (matos.isRecord()) {
				for (int e = 0; e < command.length -1; e++) {
					temp += command[e] + matos.getId();
					
					if (e == command.length -2) temp += command[e+1];
				}
			}
			
			if (!temp.equals("")) commands.add(temp);
		}
		
		this.commands = commands;
		return commands;
	}

	/**Generate MaterialID:SolidID command variation
	 * @param command String[] parts of command
	 * @return ArrayList<String> commands list
	 */
	public ArrayList<String> generateSolidID(String[] command) {
		ArrayList<String> commands = new ArrayList<String>();
		
		for (Material matos : Material.values()) {
			String temp = "";
			
			if (matos.isSolid()) {
				for (int e = 0; e < command.length -1; e++) {
					temp += command[e] + matos.getId();
					
					if (e == command.length -2) temp += command[e+1];
				}
			}
			
			if (!temp.equals("")) commands.add(temp);
		}
		
		this.commands = commands;
		return commands;
	}

	/**Generate MaterialID:TransparentID command variation
	 * @param command String[] parts of command
	 * @return ArrayList<String> commands list
	 */
	public ArrayList<String> generateTransparentID(String[] command) {
		ArrayList<String> commands = new ArrayList<String>();
		
		for (Material matos : Material.values()) {
			String temp = "";
			
			if (matos.isTransparent()) {
				for (int e = 0; e < command.length -1; e++) {
					temp += command[e] + matos.getId();
					
					if (e == command.length -2) temp += command[e+1];
				}
			}
			
			if (!temp.equals("")) commands.add(temp);
		}
		
		this.commands = commands;
		return commands;
	}
	
	/**Generate Mathematics:Interpolation
	 * @param command String[] parts of command
	 * @return ArrayList<String> commands List
	 */
	public ArrayList<String> generateInterp( String[] command) {
		//TODO finish generateInterp -> KubbyDev
		ArrayList<String> commands = null;
		
		return commands;
	}
	
	/**Generate Mathematics:Dichotomie
	 * @param command String[] parts of command
	 * @return ArrayList<String> commands List
	 */
	public ArrayList<String> generateDichotomie( String[] command) {
		//TODO finish generateDichotomie -> Oromis
		ArrayList<String> commands = null;
		
		return commands;
	}
	
	/**Get the file of this McFunctionGenerator
	 * @param File file
	 */
	public File getFile() {
		return file;
	}

	/**Set the file of this McFunctionGenerator
	 * @return File file
	 */
	public void setFile(File file) {
		this.file = file;
		GP = new GeneratorParser(this);
	}
}
