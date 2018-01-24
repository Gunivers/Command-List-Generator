package old;

import fr.gunivers.cmdlg.util.Material;
import fr.gunivers.cmdlg.util.GeneratorType;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@SuppressWarnings("deprecation")
public class McFunctionGenerator {

    public ArrayList<String> commands;
    private GeneratorParser GP;
    private File file = null;
    private FileWriter fw;

    /**
     * Constructor
     *
     * @param file location of the mcFunction
     */
    public McFunctionGenerator(File file) {
        //Initialize file
        try {
            this.fw = new FileWriter(file);
        } catch (IOException exception) {
            exception.printStackTrace();
        }

        this.GP = new GeneratorParser(this);
    }

    /**
     * Close the FileWriter -> avoid bug
     */
    public void close() throws IOException {
        fw.close();
    }


    /**
     * Real Generation
     *
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

    /**
     * Real Generation [Method 2]
     *
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

    /**
     * AdvancedGeneration
     *
     * @param generators HashMap with the generators and their param
     * @param command    String[] parts of commands
     * @return ArrayList<String> commands
     */
    public ArrayList<String> generateAdvanced(HashMap<GeneratorType, Object[]> generators, String[] command) {
        List<GeneratorType> gens = new ArrayList<>();
        List<Object[]> values = new ArrayList<>();

        ArrayList<String> commands = new ArrayList<>();
        commands.add("");

        for (HashMap.Entry<GeneratorType, ?> entry : generators.entrySet()) {
            gens.add(entry.getKey());
            values.add((Object[]) entry.getValue());
        }

        for (int i = 0; i < gens.size(); i++) {
            String[] part = {command[i]};

            ArrayList<String> parts = GP.parse(gens.get(i), values.get(i), part);

            int partsSize = parts.size();

            int commandsSize = commands.size();

            //Sync sizes
            if (commandsSize < partsSize)
                for (int e = 0; e <= partsSize - commandsSize; e++) commands.add(commands.get(0));
            else for (int e = 0; e < commandsSize - partsSize; e++) parts.add(parts.get(0));


            for (int e = 0; e < parts.size(); e++) {
                commands.set(e, commands.get(e) + parts.get(e));
            }

            if (i == gens.size() - 1) for (int e = command.length - gens.size(); e > 0; e--)
                for (int f = 0; f < commands.size(); f++)
                    commands.set(f, commands.get(f) + command[command.length - e]);
        }

        this.commands = commands;
        return commands;
    }


    /**
     * Generate INT chain command variation
     *
     * @param start   int start of chain
     * @param end     int end of chain
     * @param step    int step of walk in chain
     * @param command String[] parts of command
     * @return ArrayList<String> commands list
     */
    public ArrayList<String> generateInt(int start, int end, int step, String[] command) {
        ArrayList<String> commands = new ArrayList<>();

        for (int i = start; i <= end; i += step) {
            StringBuilder temp = new StringBuilder();

            for (int e = 0; e < command.length - 1; e++) {
                temp.append(command[e]).append(i);

                if (e == command.length - 2) temp.append(command[e + 1]);
            }

            commands.add(temp.toString());
        }

        this.commands = commands;
        return commands;
    }

    /**
     * Generate FLOAT chain command variation
     *
     * @param start   float start of chain
     * @param end     float end of chain
     * @param step    float step of walk in chain
     * @param command String[] parts of command
     * @return ArrayList<String> commands list
     */
    public ArrayList<String> generateFloat(float start, float end, float step, String[] command) {
        ArrayList<String> commands = new ArrayList<>();

        for (float i = start; i <= end; i += step) {
            StringBuilder temp = new StringBuilder();

            for (int e = 0; e < command.length - 1; e++) {
                temp.append(command[e]).append(i).append("F");

                if (e == command.length - 2) temp.append(command[e + 1]);
            }

            commands.add(temp.toString());
        }

        this.commands = commands;
        return commands;
    }

    /**
     * Generate DOUBLE chain command variation
     *
     * @param start   double start of chain
     * @param end     double end of chain
     * @param step    double step of walk in chain
     * @param command String[] parts of command
     * @return ArrayList<String> commands list
     */
    public ArrayList<String> generateDouble(double start, double end, double step, String[] command) {
        ArrayList<String> commands = new ArrayList<>();

        for (double i = start; i <= end; i += step) {
            StringBuilder temp = new StringBuilder();

            for (int e = 0; e < command.length - 1; e++) {
                temp.append(command[e]).append(i).append("D");

                if (e == command.length - 2) temp.append(command[e + 1]);
            }

            commands.add(temp.toString());
        }

        this.commands = commands;
        return commands;
    }

    /**
     * Generate LONG chain command variation
     *
     * @param start   LONG start of chain
     * @param end     LONG end of chain
     * @param step    LONG step of walk in chain
     * @param command String[] parts of command
     * @return ArrayList<String> commands list
     */
    public ArrayList<String> generateLong(long start, long end, long step, String[] command) {
        ArrayList<String> commands = new ArrayList<>();

        for (long i = start; i <= end; i += step) {
            StringBuilder temp = new StringBuilder();

            for (int e = 0; e < command.length - 1; e++) {
                temp.append(command[e]).append(i).append("L");

                if (e == command.length - 2) temp.append(command[e + 1]);
            }

            commands.add(temp.toString());
        }

        this.commands = commands;
        return commands;
    }

    /**
     * Generate MATERIAL:MATERIAL command variation
     *
     * @param command String[] parts of command
     * @return ArrayList<String> commands list
     */
    public ArrayList<String> generateMaterial(String[] command) {
        ArrayList<String> commands = new ArrayList<>();

        for (Material material : Material.values()) {
            StringBuilder temp = new StringBuilder();

            for (int e = 0; e < command.length - 1; e++) {
                temp.append(command[e]).append(material.name());

                if (e == command.length - 2) temp.append(command[e + 1]);
            }

            commands.add(temp.toString());
        }

        this.commands = commands;
        return commands;
    }

    /**
     * Generate MATERIAL_ID:MATERIAL_ID command variation
     *
     * @param command String[] parts of command
     * @return ArrayList<String> commands list
     */
    public ArrayList<String> generateMaterialID(String[] command) {
        ArrayList<String> commands = new ArrayList<>();

        for (Material material : Material.values()) {
            StringBuilder temp = new StringBuilder();

            for (int e = 0; e < command.length - 1; e++) {
                temp.append(command[e]).append(material.getId());

                if (e == command.length - 2) temp.append(command[e + 1]);
            }

            commands.add(temp.toString());
        }

        this.commands = commands;
        return commands;
    }

    /**
     * Generate MATERIAL:BLOCK command variation
     *
     * @param command String[] parts of command
     * @return ArrayList<String> commands list
     */
    public ArrayList<String> generateBlock(String[] command) {
        ArrayList<String> commands = new ArrayList<>();

        for (Material material : Material.values()) {
            StringBuilder temp = new StringBuilder();

            if (material.isBlock()) {

                for (int e = 0; e < command.length - 1; e++) {
                    temp.append(command[e]).append(material.name());

                    if (e == command.length - 2) temp.append(command[e + 1]);
                }
            }

            if (!temp.toString().equals("")) commands.add(temp.toString());
        }

        this.commands = commands;
        return commands;
    }

    /**
     * Generate MATERIAL:BURNABLE command variation
     *
     * @param command String[] parts of command
     * @return ArrayList<String> commands list
     */
    public ArrayList<String> generateBurnable(String[] command) {
        ArrayList<String> commands = new ArrayList<>();

        for (Material material : Material.values()) {
            StringBuilder temp = new StringBuilder();

            if (material.isBurnable()) {

                for (int e = 0; e < command.length - 1; e++) {
                    temp.append(command[e]).append(material.name());

                    if (e == command.length - 2) temp.append(command[e + 1]);
                }
            }

            if (!temp.toString().equals("")) commands.add(temp.toString());
        }

        this.commands = commands;
        return commands;
    }

    /**
     * Generate MATERIAL:EDIBLE command variation
     *
     * @param command String[] parts of command
     * @return ArrayList<String> commands list
     */
    public ArrayList<String> generateEdible(String[] command) {
        ArrayList<String> commands = new ArrayList<>();

        for (Material material : Material.values()) {
            StringBuilder temp = new StringBuilder();

            if (material.isEdible()) {

                for (int e = 0; e < command.length - 1; e++) {
                    temp.append(command[e]).append(material.name());

                    if (e == command.length - 2) temp.append(command[e + 1]);
                }
            }

            if (!temp.toString().equals("")) commands.add(temp.toString());
        }

        this.commands = commands;
        return commands;
    }

    /**
     * Generate MATERIAL:FLAMMABLE command variation
     *
     * @param command String[] parts of command
     * @return ArrayList<String> commands list
     */
    public ArrayList<String> generateFlammable(String[] command) {
        ArrayList<String> commands = new ArrayList<>();

        for (Material material : Material.values()) {
            StringBuilder temp = new StringBuilder();

            if (material.isFlammable()) {

                for (int e = 0; e < command.length - 1; e++) {
                    temp.append(command[e]).append(material.name());

                    if (e == command.length - 2) temp.append(command[e + 1]);
                }
            }

            if (!temp.toString().equals("")) commands.add(temp.toString());
        }

        this.commands = commands;
        return commands;
    }

    /**
     * Generate MATERIAL:FUEL command variation
     *
     * @param command String[] parts of command
     * @return ArrayList<String> commands list
     */
    public ArrayList<String> generateFuel(String[] command) {
        ArrayList<String> commands = new ArrayList<>();

        for (Material material : Material.values()) {
            StringBuilder temp = new StringBuilder();

            if (material.isFuel()) {

                for (int e = 0; e < command.length - 1; e++) {
                    temp.append(command[e]).append(material.name());

                    if (e == command.length - 2) temp.append(command[e + 1]);
                }
            }

            if (!temp.toString().equals("")) commands.add(temp.toString());
        }

        this.commands = commands;
        return commands;
    }

    /**
     * Generate MATERIAL:OCCLUDING command variation
     *
     * @param command String[] parts of command
     * @return ArrayList<String> commands list
     */
    public ArrayList<String> generateOccluding(String[] command) {
        ArrayList<String> commands = new ArrayList<>();

        for (Material material : Material.values()) {
            StringBuilder temp = new StringBuilder();

            if (material.isOccluding()) {

                for (int e = 0; e < command.length - 1; e++) {
                    temp.append(command[e]).append(material.name());

                    if (e == command.length - 2) temp.append(command[e + 1]);
                }
            }

            if (!temp.toString().equals("")) commands.add(temp.toString());
        }

        this.commands = commands;
        return commands;
    }

    /**
     * Generate MATERIAL:RECORD command variation
     *
     * @param command String[] parts of command
     * @return ArrayList<String> commands list
     */
    public ArrayList<String> generateRecord(String[] command) {
        ArrayList<String> commands = new ArrayList<>();

        for (Material material : Material.values()) {
            StringBuilder temp = new StringBuilder();

            if (material.isRecord()) {

                for (int e = 0; e < command.length - 1; e++) {
                    temp.append(command[e]).append(material.name());

                    if (e == command.length - 2) temp.append(command[e + 1]);
                }
            }

            if (!temp.toString().equals("")) commands.add(temp.toString());
        }

        this.commands = commands;
        return commands;
    }

    /**
     * Generate MATERIAL:SOLID command variation
     *
     * @param command String[] parts of command
     * @return ArrayList<String> commands list
     */
    public ArrayList<String> generateSolid(String[] command) {
        ArrayList<String> commands = new ArrayList<>();

        for (Material material : Material.values()) {
            StringBuilder temp = new StringBuilder();

            if (material.isSolid()) {

                for (int e = 0; e < command.length - 1; e++) {
                    temp.append(command[e]).append(material.name());

                    if (e == command.length - 2) temp.append(command[e + 1]);
                }
            }

            if (!temp.toString().equals("")) commands.add(temp.toString());
        }

        this.commands = commands;
        return commands;
    }

    /**
     * Generate MATERIAL:TRANSPARENT command variation
     *
     * @param command String[] parts of command
     * @return ArrayList<String> commands list
     */
    public ArrayList<String> generateTransparent(String[] command) {
        ArrayList<String> commands = new ArrayList<>();

        for (Material material : Material.values()) {
            StringBuilder temp = new StringBuilder();

            if (material.isTransparent()) {

                for (int e = 0; e < command.length - 1; e++) {
                    temp.append(command[e]).append(material.name());

                    if (e == command.length - 2) temp.append(command[e + 1]);
                }
            }

            if (!temp.toString().equals("")) commands.add(temp.toString());
        }

        this.commands = commands;
        return commands;
    }

    /**
     * Generate MATERIAL_ID:BLOCK_ID command variation
     *
     * @param command String[] parts of command
     * @return ArrayList<String> commands list
     */
    public ArrayList<String> generateBlockID(String[] command) {
        ArrayList<String> commands = new ArrayList<>();

        for (Material material : Material.values()) {
            StringBuilder temp = new StringBuilder();

            if (material.isBlock()) {
                for (int e = 0; e < command.length - 1; e++) {
                    temp.append(command[e]).append(material.getId());

                    if (e == command.length - 2) temp.append(command[e + 1]);
                }
            }

            if (!temp.toString().equals("")) commands.add(temp.toString());
        }

        this.commands = commands;
        return commands;
    }

    /**
     * Generate MATERIAL_ID:BURNABLE_ID command variation
     *
     * @param command String[] parts of command
     * @return ArrayList<String> commands list
     */
    public ArrayList<String> generateBurnableID(String[] command) {
        ArrayList<String> commands = new ArrayList<>();

        for (Material material : Material.values()) {
            StringBuilder temp = new StringBuilder();

            if (material.isBurnable()) {
                for (int e = 0; e < command.length - 1; e++) {
                    temp.append(command[e]).append(material.getId());

                    if (e == command.length - 2) temp.append(command[e + 1]);
                }
            }

            if (!temp.toString().equals("")) commands.add(temp.toString());
        }

        this.commands = commands;
        return commands;
    }

    /**
     * Generate MATERIAL_ID:EDIBLE_ID command variation
     *
     * @param command String[] parts of command
     * @return ArrayList<String> commands list
     */
    public ArrayList<String> generateEdibleID(String[] command) {
        ArrayList<String> commands = new ArrayList<>();

        for (Material material : Material.values()) {
            StringBuilder temp = new StringBuilder();

            if (material.isEdible()) {
                for (int e = 0; e < command.length - 1; e++) {
                    temp.append(command[e]).append(material.getId());

                    if (e == command.length - 2) temp.append(command[e + 1]);
                }
            }

            if (!temp.toString().equals("")) commands.add(temp.toString());
        }

        this.commands = commands;
        return commands;
    }

    /**
     * Generate MATERIAL_ID:FLAMMABLE_ID command variation
     *
     * @param command String[] parts of command
     * @return ArrayList<String> commands list
     */
    public ArrayList<String> generateFlammableID(String[] command) {
        ArrayList<String> commands = new ArrayList<>();

        for (Material material : Material.values()) {
            StringBuilder temp = new StringBuilder();

            if (material.isFlammable()) {
                for (int e = 0; e < command.length - 1; e++) {
                    temp.append(command[e]).append(material.getId());

                    if (e == command.length - 2) temp.append(command[e + 1]);
                }
            }

            if (!temp.toString().equals("")) commands.add(temp.toString());
        }

        this.commands = commands;
        return commands;
    }

    /**
     * Generate MATERIAL_ID:FUEL_ID command variation
     *
     * @param command String[] parts of command
     * @return ArrayList<String> commands list
     */
    public ArrayList<String> generateFuelID(String[] command) {
        ArrayList<String> commands = new ArrayList<>();

        for (Material material : Material.values()) {
            StringBuilder temp = new StringBuilder();

            if (material.isFuel()) {
                for (int e = 0; e < command.length - 1; e++) {
                    temp.append(command[e]).append(material.getId());

                    if (e == command.length - 2) temp.append(command[e + 1]);
                }
            }

            if (!temp.toString().equals("")) commands.add(temp.toString());
        }

        this.commands = commands;
        return commands;
    }

    /**
     * Generate MATERIAL_ID:OCCLUDING_ID command variation
     *
     * @param command String[] parts of command
     * @return ArrayList<String> commands list
     */
    public ArrayList<String> generateOccludingID(String[] command) {
        ArrayList<String> commands = new ArrayList<>();

        for (Material material : Material.values()) {
            StringBuilder temp = new StringBuilder();

            if (material.isOccluding()) {
                for (int e = 0; e < command.length - 1; e++) {
                    temp.append(command[e]).append(material.getId());

                    if (e == command.length - 2) temp.append(command[e + 1]);
                }
            }

            if (!temp.toString().equals("")) commands.add(temp.toString());
        }

        this.commands = commands;
        return commands;
    }

    /**
     * Generate MATERIAL_ID:RECORD_ID command variation
     *
     * @param command String[] parts of command
     * @return ArrayList<String> commands list
     */
    public ArrayList<String> generateRecordID(String[] command) {
        ArrayList<String> commands = new ArrayList<>();

        for (Material material : Material.values()) {
            StringBuilder temp = new StringBuilder();

            if (material.isRecord()) {
                for (int e = 0; e < command.length - 1; e++) {
                    temp.append(command[e]).append(material.getId());

                    if (e == command.length - 2) temp.append(command[e + 1]);
                }
            }

            if (!temp.toString().equals("")) commands.add(temp.toString());
        }

        this.commands = commands;
        return commands;
    }

    /**
     * Generate MATERIAL_ID:SOLID_ID command variation
     *
     * @param command String[] parts of command
     * @return ArrayList<String> commands list
     */
    public ArrayList<String> generateSolidID(String[] command) {
        ArrayList<String> commands = new ArrayList<>();

        for (Material material : Material.values()) {
            StringBuilder temp = new StringBuilder();

            if (material.isSolid()) {
                for (int e = 0; e < command.length - 1; e++) {
                    temp.append(command[e]).append(material.getId());

                    if (e == command.length - 2) temp.append(command[e + 1]);
                }
            }

            if (!temp.toString().equals("")) commands.add(temp.toString());
        }

        this.commands = commands;
        return commands;
    }

    /**
     * Generate MATERIAL_ID:TRANSPARENT_ID command variation
     *
     * @param command String[] parts of command
     * @return ArrayList<String> commands list
     */
    public ArrayList<String> generateTransparentID(String[] command) {
        ArrayList<String> commands = new ArrayList<>();

        for (Material material : Material.values()) {
            StringBuilder temp = new StringBuilder();

            if (material.isTransparent()) {
                for (int e = 0; e < command.length - 1; e++) {
                    temp.append(command[e]).append(material.getId());

                    if (e == command.length - 2) temp.append(command[e + 1]);
                }
            }

            if (!temp.toString().equals("")) commands.add(temp.toString());
        }

        this.commands = commands;
        return commands;
    }

    /**
     * Generate Mathematics:Interpolation
     *
     * @param command String[] parts of command
     * @return ArrayList<String> commands List
     */
    public ArrayList<String> generateInterp(String[] command) {
        //TODO finish generateInterp -> KubbyDev
        ArrayList<String> commands = null;

        return commands;
    }

    /**
     * Generate Mathematics:DICHOTOMIE
     *
     * @param command String[] parts of command
     * @return ArrayList<String> commands List
     */
    public ArrayList<String> generateDichotomie(String[] command) {
        //TODO finish generateDichotomie -> Oromis
        ArrayList<String> commands = null;

        return commands;
    }

    /**
     * @return Of the file of this McFunctionGenerator
     */
    public File getFile() {
        return file;
    }

    /**
     * Set the file of this McFunctionGenerator
     *
     * @return File file
     */
    public void setFile(File file) {
        this.file = file;
        GP = new GeneratorParser(this);
    }
}
