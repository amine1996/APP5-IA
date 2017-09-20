package monjeu.Awale;
import jeux.modele.CoupJeu;

public class CoupAwale implements CoupJeu{

	/****** Attributs *******/

	private int trou;

	/****** Clonstructeur *******/ 

	public CoupAwale(int trou) {
		trou = trou;
	}

	/****** Accesseurs *******/ 

	public int getTrou() {
		return trou;
	}

	/****** Accesseurs *******/ 

	public String toString() {
		return "("+trou+")";
	}
}

