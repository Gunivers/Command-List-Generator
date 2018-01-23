package old;

import java.util.ArrayList;

public class Main {
	
	//Cette m�thode sera ensuite inutile, puisque le bouton "Generate" appellera directement la m�thode generate()
	/*public static void fakeMain(String[] args) {
	
		String entree = "#INTERP:0,100,1.8,0,1#";
		disp(generate(entree, 10));
		
		System.out.print("\n\n");
		
		entree = "#InterpScore:0,100,1.8,0,Score#";
		disp(generate(entree, 10));

	}/*
		
	/**
	* Renvoie un tableau contenant les Strings a placer dans les commandes g�n�r�es, a la place de la balise donn�e en entree
	*@param entree: la balise (seule: "#example#")
	*@param nbreCommandes: le nombre de commandes a generer
	*@return le String a mettre a la place de la balise sur chaque commande
	*/
	public static String[] generate(String entree, int nbreCommandes) {
		
		String baliseName = readName(entree);
		
		switch(baliseName) {
		case "INTERP": return generateInterp(entree, nbreCommandes);
		case "InterpScore": return generateScoreInterp(entree, nbreCommandes);
		default:;
		}
		
		return null;
	}
	
	/**
	* Renvoie un tableau contenant les Strings a placer dans les commandes g�n�r�es, a la place de la balise donn�e en entree si la balise est un INTERP
	* Format de la balise: #INTERP:Debut,Fin,Puissance,Invert(0 ou 1),NombreDeDecimales#
	*@param entree: la balise (seule: "#example#")
	*@param nbreCommandes: le nombre de commandes a generer
	*@return le String a mettre a la place de la balise sur chaque commande
	*/
	public static String[] generateInterp(String balise, int nbreCommandes) {
	
		String[] infosS = readTag(balise, "INTERP");
		double[] infos = new double[5];
		//index 0: nombre de d�part
		//index 1: nombre de fin
		//index 2: puissance (courbure de la mont�e)
		//index 3: invert (0 ou 1)
		//index 4: nombre de d�cimales
		
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
	
	/**
	* Renvoie un tableau contenant les Strings a placer dans les commandes g�n�r�es, a la place de la balise donn�e en entree si la balise est un InterpScore
	* Format de la balise: #InterpScore:Debut,Fin,Puissance,Invert(0 ou 1),Objectif#
	*@param entree: la balise (seule: "#example#")
	*@param nbreCommandes: le nombre de commandes a generer
	*@return le String a mettre a la place de la balise sur chaque commande
	*/
	public static String[] generateScoreInterp(String balise, int nbreCommandes) {
		
		String[] infos = readTag(balise, "InterpScore");
		//index 0: nombre de d�part
		//index 1: nombre de fin
		//index 2: puissance (courbure de la mont�e)
		//index 3: invert (0 ou 1)
		//index 4: nom de l'objectif
		
		int debut = Integer.valueOf(infos[0]);
		int fin = Integer.valueOf(infos[1]);
		double puissance = Double.valueOf(infos[2]);
		boolean invert = (infos[3] == "1");
		String objective = infos[4];		
		
		String[] ret = new String[nbreCommandes];
		String line = "";
		
		for(int i = 0; i < nbreCommandes; i++) {
			line = "score_" + objective + "_min=";
			line += String.valueOf(Math.round(interp(debut, fin, nbreCommandes+1, i, puissance, invert)) + ((i == 0) ? 0 : 1));
			line += ",score_" + objective + "=";
			line += String.valueOf(Math.round(interp(debut, fin, nbreCommandes+1, i+1, puissance, invert)));
			ret[i] = line;
		}
				
		return ret;		
	}
	
	public static void disp(double[] d) {
		
		for(double i : d)
			System.out.println(i);
		
	}
	
	public static void disp(String[] d) {
		
		for(String i : d)
			System.out.println(i);
		
	}
	
	/**
	* Renvoie le nom de la balise ("#INTERP:25,4324,4,32,4#" renvoie "INTERP")
	*@param entree: la balise (seule: "#example#")
	*@return le nom de la balise
	*/
	public static String readName(String balise) {
		    
		StringBuilder name = new StringBuilder();
		int i = 1;    
		
		do {
			name.append(balise.charAt(i));
			i++;  
		} while(balise.charAt(i) != ':');
		    
		return name.toString();
	}
		  
	/**
	* Renvoie les arguments de la balise dans des Strings s�par�s
	*@param entree: la balise (seule: "#example#")
	*@param baliseName: le nom de la balise (voir readName())
	*@return les arguments de la balise
	*/
	public static String[] readTag(String balise, String baliseName) {
		 
		int beginIndex = baliseName.length()+2;
		int nbreArgs = 1;
		
		for(int i = beginIndex; i < balise.length(); i++)
			if(balise.charAt(i) == ',')
				nbreArgs++;
	    
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
		//On lit le dernier (qui n'est pas d�tect� comme les autres par une virgule)
		args[nbreArgs-1] = argument;
	    
	    return args;
	}
	
	/**
	* Renvoie la valeur a alpha% de l'intervalle [debut; fin]
	*@param debut: le debut de l'intervale
	*@param fin: la fin de l'intervale
	*@param alpha: le pourcentage (0 a 1)
	*@return la valeur a alpha% de l'intervalle [debut; fin]
	*/
	public static double linearInterp(double debut, double fin, double alpha) {
		return fin*alpha + debut;
	}
	
	/**
	* Calcule alpha et renvoie la valeur a (alpha^puissance)% ou 1-(1-alpha)^puissance% de l'intervalle [debut; fin]
	*@param debut: le debut de l'intervale
	*@param fin: la fin de l'intervale
	*@param nbreCommandes: le nombre de commandes a g�n�rer
	*@param nbreCommandes: le num�ro de la commande a g�n�rer
	*@param puissance: la courbure de la mont�e des valeurs
	*@param invert: inversion de la progression de la pente (rapide puis lente ou lente puis rapide)
	*@return la valeur
	*/
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
	
	/**
	* Renvoie l'arrondi a 10^-decimalsNumber
	*@param number: le nombre
	*@param decimalsNumber: le nombre de d�cimales
	*@return le nombre arrondi
	*/
	public static double round(double number, int decimalsNumber) {
		
		int power = (int) Math.pow(10, decimalsNumber);
		number *= power;
		return ((double) Math.round(number)) / power;
	}
	
	/**
	* Decompose l'entree en une ArrayList de chaque composants (les balises sont s�par�es)
	*@param entree: l'entr�e (commande brute donn�e par l'utilisateur)
	*@return l'ArrayList contenant chaque composant dans l'ordre
	*/
	public static ArrayList<String> decompose(String entree) {
		
		ArrayList<String> composants = new ArrayList<String>();
		StringBuilder composant = new StringBuilder(new String());
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
						composant.append("#");
						i++;
						isInSpecial = false;
						composants.add(composant.toString());
						composant = new StringBuilder();
					}
					else {
						isInSpecial = true;
						composants.add(composant.toString());
						composant = new StringBuilder();
					}
					break;
				default:;
				}		
			}
			else
				ignoreNext = false;
			
			composant.append(entree.charAt(i));
			
		}
		composants.add(composant.toString());
		
		return composants;
	}

}
