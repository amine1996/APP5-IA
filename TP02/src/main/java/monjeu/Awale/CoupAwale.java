package monjeu.awale;
import jeux.modele.CoupJeu;

public class CoupAwale implements CoupJeu{

	/****** Attributs *******/

	private int trou;

	private int nbBilles;

	/****** Clonstructeur *******/ 

	public CoupAwale(int trou, int nbBilles) {
		this.trou = trou;
		this.nbBilles = nbBilles;
	}

	/****** Accesseurs *******/ 

	public int getTrou() {
		return trou;
	}

	/****** Accesseurs *******/ 

	public String toString() {
		return "("+(trou+1)+"," + nbBilles + ")";
	}
}

