package jeux.alg;

import jeux.modele.PlateauJeu;
import jeux.modele.joueur.Joueur;

public interface Heuristique {

	public int eval(PlateauJeu p, Joueur j);

}
 