package monjeu.awale;

import jeux.alg.Heuristique;
import jeux.modele.PlateauJeu;
import jeux.modele.joueur.Joueur;


public class HeuristiquesAwale{

	public static  Heuristique hblanc = new Heuristique(){
				
		public int eval(PlateauJeu p, Joueur j){
			PlateauAwale pAwale = (PlateauAwale) p;
			return pAwale.score1-pAwale.score2;
		}
	};

	public static  Heuristique hnoir = new Heuristique(){
	
		public int eval(PlateauJeu p, Joueur j){
			PlateauAwale pAwale = (PlateauAwale) p;
			return pAwale.score2-pAwale.score1;
		}
	};

}
