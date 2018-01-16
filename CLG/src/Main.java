package src;

import java.util.ArrayList;

public class Main {

	
	//Cette m�thode sera ensuite inutile, puisque le bouton "Generate" appellera directement la m�thode generate()
	public static void main(String[] args) {
	
		String entr�e = "vjfnvnf #jdnvjn# jdnsvjbidn #njdv # kd\\#djnj";

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
