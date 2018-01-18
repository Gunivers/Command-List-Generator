import java.util.ArrayList;

public class Main {

	
	//Cette méthode sera ensuite inutile, puisque le bouton "Generate" appellera directement la méthode generate()
	public static void main(String[] args) {
	
		String entree = "#Interp:0,100,1.8,0,0#";
		disp(generateInterp(entree, 10));
		//Modif test test
	}
	
	public static void disp(double[] d) {
		
		for(double i : d)
			System.out.println(i);
		
	}
	
	public static void disp(String[] d) {
		
		for(String i : d)
			System.out.println(i);
		
	}
	
	
	
	
	
	
	
	public static double linearInterp(double debut, double fin, double alpha) {
		return fin*alpha + debut;
	}
	
	public static double interp(double debut, double fin, int nbreCommandes, int numeroCommande, double puissance, boolean invert) {
		
		double alpha = (double) numeroCommande/ (double) (nbreCommandes-1);	
		if(invert)
			alpha = 1-alpha;
		
		alpha = Math.pow(alpha, puissance);
		
		if(invert)
			alpha = 1-alpha;
		
		return linearInterp(debut, fin, alpha);
	}
	
	public static String[] generateInterp(String balise, int nbreCommandes) {
	
		double[] infos = readInterp(balise);
		
		double debut = infos[0];
		double fin = infos[1];
		double puissance = infos[2];
		boolean invert = infos[3] == 1;
		int decimalsNumber = (int) infos[4];
		
		String[] ret = new String[nbreCommandes];
		
		for(int i = 0; i < nbreCommandes; i++)
			if(decimalsNumber == 0)
				ret[i] = String.valueOf((int) Math.round(interp(debut, fin, nbreCommandes, i, puissance, invert)));
			else
				ret[i] = String.valueOf(round(interp(debut, fin, nbreCommandes, i, puissance, invert), decimalsNumber));
				
		return ret;
	}
	
	public static double[] readInterp(String balise) {
		
		double[] ret = new double[5];
		int index = 0;
		String valeur = "";
		
		//On commence apres "#Interp:"
		for(int i = 8; i < balise.length()-1; i++) {
			
			if(balise.charAt(i) == ',') {
				ret[index] = Double.valueOf(valeur);
				valeur = "";
				index++;
			}
			else
				valeur += balise.charAt(i);
			
		}
		//On lit le dernier (qui n'est pas détecté comme les autres par une virgule)
		ret[4] = Double.valueOf(valeur);
		
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
