package monjeu.Awale;

import jeux.alg.Heuristique;
import jeux.modele.PlateauJeu;
import jeux.modele.joueur.Joueur;


public class HeuristiquesAwale{

	public static  Heuristique hblanc = new Heuristique(){
				
		public int eval(PlateauJeu p, Joueur j){
			PlateauDominos pDomino = (PlateauDominos) p;
			return 1000/(1 + pDomino.nbCoupsBlanc());
		}
	};

	public static  Heuristique hnoir = new Heuristique(){
	
		public int eval(PlateauJeu p, Joueur j){
			PlateauDominos pDomino = (PlateauDominos) p;

			if(pDomino.nbCoupsBlanc() == 0)
				return Integer.MIN_VALUE;

			return -(1000 * pDomino.nbCoupsNoir());
		}
	};

}
