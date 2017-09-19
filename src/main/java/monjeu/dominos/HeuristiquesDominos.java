package monjeu.dominos;

import jeux.alg.Heuristique;
import jeux.modele.PlateauJeu;
import jeux.modele.joueur.Joueur;


public class HeuristiquesDominos{

	public static  Heuristique hblanc = new Heuristique(){
				
		public int eval(PlateauJeu p, Joueur j){
			PlateauDominos pDomino = (PlateauDominos) p;
			return pDomino.nbCoupsBlanc();
		}
	};

	public static  Heuristique hnoir = new Heuristique(){
	
		public int eval(PlateauJeu p, Joueur j){
			PlateauDominos pDomino = (PlateauDominos) p;
			return pDomino.nbCoupsNoir();
		}
	};

}
