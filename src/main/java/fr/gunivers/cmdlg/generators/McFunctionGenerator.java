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
	
	//CONSTRUCTOR
	public McFunctionGenerator() {
		//Initialize file
		try {this.fw = new FileWriter(file);}
		catch (IOException exception) {exception.printStackTrace();}
		
		this.GP = new GeneratorParser(this);
	}
	
	public void close() throws IOException{
		fw.close();
	}
	
	
	//Real Generation
	public int generate() throws IOException {
		for (String command : commands) {
			this.fw.write(command);
			this.fw.write("\n");
		}
		
		return commands.size();
	}
	//Real Generation [Method 2]
	public int generate(ArrayList<String> commands) throws IOException {
		for (String command : commands) {
			this.fw.write(command);
			this.fw.write("\n");
		}
		
		return commands.size();
	}
	
	//GENERATE Advanced Generation
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
	
	
	//GENERATE RAW TYPES
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
	
	//GENERATE MATERIAL
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
	
	//GENERATE MATERIAL ID
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
	
	//GENERATE Material<Restricted>
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
	
	//GENERATE Material<Restricted>ID
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
	
	public File getFile() {
		return file;
	}
	
	public void setFile(File file) {
		this.file = file;
		GP = new GeneratorParser(this);
	}
}
