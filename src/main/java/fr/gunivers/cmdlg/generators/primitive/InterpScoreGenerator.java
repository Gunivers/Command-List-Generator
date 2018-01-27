package fr.gunivers.cmdlg.generators.primitive;

import fr.gunivers.cmdlg.api.PrimitiveGenerator;
import fr.gunivers.cmdlg.util.GeneratorType;
import java.util.ArrayList;

public class InterpScoreGenerator extends PrimitiveGenerator {

    public InterpScoreGenerator(String[] command, Object... args) {
        super(command, args);
    }

    @Override
    public Iterable<? extends String> generate() {
    	
    	ArrayList<String> commands = new ArrayList<>();

		int debut = (int) getArgs()[0];
		int fin = (int) getArgs()[1];
		double puissance = (double) getArgs()[2];  
		boolean invert = (boolean) getArgs()[3];
		String objective = (String) getArgs()[4];
		int nbreCommandes = (int) getArgs()[5];
		
		for(int i = 0; i < nbreCommandes; i++)
			commands.add( new String( 
					"score_" + objective + "_min="
				+	(String.valueOf(Math.round(interp(debut, fin, nbreCommandes+1, i, puissance, invert)) + ((i == 0) ? 0 : 1)))
				+	",score_" + objective + "="
				+	(String.valueOf(Math.round(interp(debut, fin, nbreCommandes+1, i+1, puissance, invert))))
					) );
        
        return commands;
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
	*@param nbreCommandes: le nombre de commandes a generer
	*@param nbreCommandes: le numero de la commande a generer
	*@param puissance: la courbure de la montee des valeurs
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

    @Override
    public GeneratorType getType() {
        return GeneratorType.DOUBLE;
    }
    
}
