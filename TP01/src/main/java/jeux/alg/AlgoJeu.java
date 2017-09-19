package  jeux.alg;

import jeux.modele.CoupJeu;
import jeux.modele.PlateauJeu;

public interface AlgoJeu {

    /** Renvoie le meilleur
     * @param p
     * @return
     */
	public CoupJeu meilleurCoup(PlateauJeu p);

}
 