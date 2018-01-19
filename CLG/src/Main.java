import java.util.ArrayList;

public class Main {

	
	//Cette méthode sera ensuite inutile, puisque le bouton "Generate" appellera directement la méthode generate()
	public static void main(String[] args) {
	
		String entree = "#InterpScore:0,100,1.8,0,GNE#";
		//disp(generateInterp(entree, 1));
		//Modif test
		
		System.out.print("\n\n");
		
		disp(readTag(entree));
			
	}
	
	public static void disp(double[] d) {
		
		for(double i : d)
			System.out.println(i);
		
	}
	
	public static void disp(String[] d) {
		
		for(String i : d)
			System.out.println(i);
		
	}
	
	public static String readName(String balise) {
		    
		String name = "";
		int i = 1;    
		
		do {
			name += balise.charAt(i);
			i++;  
		} while(balise.charAt(i) != ':');
		    
		return name;
	}
		  
	public static String[] readTag(String balise) {
		    
		int beginIndex = 0;
		int nbreArgs = 0;
		    
	    switch(readName(balise)) {  
	    case "Interp":
	        beginIndex = 8;
	        nbreArgs = 5;
	        break;
	    case "InterpScore":
	        beginIndex = 13;
	        nbreArgs = 5;
	        break;
	    default:;
	    }
	    
	    String[] args = new String[nbreArgs];
	    
	    int i = beginIndex;
	    int numeroArg = 0;
	    String argument = "";
	    
	    do {
			
			if(balise.charAt(i) == ',') {
				args[numeroArg] = argument;
				argument = "";
				numeroArg++;
			}
			else
				argument += balise.charAt(i);
			
			i++;
			
		} while(balise.charAt(i) != '#');
		//On lit le dernier (qui n'est pas détecté comme les autres par une virgule)
		args[nbreArgs-1] = argument;
	    
	    return args;
	}
	
	public static double linearInterp(double debut, double fin, double alpha) {
		return fin*alpha + debut;
	}
	
	public static double interp(double debut, double fin, int nbreCommandes, int numeroCommande, double puissance, boolean invert) {
		
		double alpha;
		if(nbreCommandes > 1)
			alpha = (double) numeroCommande/ (double) (nbreCommandes-1);	
		else 
			alpha = 0.5;
		
		if(invert)
			alpha = 1-alpha;
		
		alpha = Math.pow(alpha, puissance);
		
		if(invert)
			alpha = 1-alpha;
		
		return linearInterp(debut, fin, alpha);
	}
	
	public static String[] generateInterp(String balise, int nbreCommandes) {
	
		String[] infosS = readTag(balise);
		double[] infos = new double[5];
		//index 0: nombre de départ
		//index 1: nombre de fin
		//index 2: puissance (courbure de la montée)
		//index 3: invert (0 ou 1)
		//index 4: nombre de décimales
		
		for(int i = 0; i <= 4; i++) {
			infos[i] = Double.valueOf(infosS[i]);
		}
		
		String[] ret = new String[nbreCommandes];
		
		for(int i = 0; i < nbreCommandes; i++)
			if(infos[4] == 0)
				ret[i] = String.valueOf((int) Math.round(interp(infos[0], infos[1], nbreCommandes, i, infos[2], infos[3] == 1)));
			else
				ret[i] = String.valueOf(round(interp(infos[0], infos[1], nbreCommandes, i, infos[2], infos[3] == 1), (int) infos[4]));
				
		return ret;
	}
	
	public static double round(double number, int decimalsNumber) {
		
		int power = (int) Math.pow(10, decimalsNumber);
		number *= power;
		return ((double) Math.round(number)) / power;
	}
	
	public static ArrayList<String> generate(String entree) {
		
		//Temporaire
		//Apres ce sera une commande complete par String
		return decompose(entree);
	}
	
	public static ArrayList<String> decompose(String entree) {
		
		ArrayList<String> composants = new ArrayList<String>();
		String composant = new String();
		//isInSpecial est sur true quand le caractere lu est entre 2 #
		boolean isInSpecial = false;
		boolean ignoreNext = false;
		
		for(int i = 0; i < entree.length(); i++) {		
			
			if(!ignoreNext) {
			
				switch (entree.charAt(i)) {
				
				case '\\': 
					i++;
					ignoreNext = true;
					break;
					
				case '#':
					if(isInSpecial) {
						composant += "#";
						i++;
						isInSpecial = false;
						composants.add(composant);
						composant = "";					
					}
					else {
						isInSpecial = true;
						composants.add(composant);
						composant = "";
					}
					break;
				default:;
				}		
			}
			else
				ignoreNext = false;
			
			composant += entree.charAt(i);
			
		}
		composants.add(composant);
		
		return composants;
	}

}
