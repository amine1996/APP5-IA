package monjeu.Awale;

import java.io.PrintStream;
import java.util.ArrayList;

import jeux.modele.*;
import jeux.modele.joueur.Joueur;

public class PlateauAwale implements PlateauJeu {

	/* *********** constantes *********** */

	/** Taille des rangées */
	public final static int TAILLE = 6;

	/** Nombre de billes */
	private static int NOMBRE_GRAINE = 48;

	/** Le joueur que joue "1" */
	private static Joueur joueur1;

	/**Le nombre de graines du joueur 1*/
	private int score1;

	/**Le nombre de graines du joueur 2*/
	private int score2;

	/** Le joueur que joue "2" */
	private static Joueur joueur2;

	/* *********** Attributs  *********** */

	/** le damier */
	private int rangee1[];
	private int rangee2[];

	/************* Constructeurs ****************/ 

	public PlateauAwale(){
		rangee1 = new int[TAILLE];
		rangee2 = new int[TAILLE];
		for(int i=0; i < TAILLE; i++){
			rangee1[i] = 4;
			rangee2[i] = 4;
		}

		score1 = 0;
		score2 = 0;
	}

	public PlateauAwale(int rangee1[], int rangee2[], int sc1, int sc2){
		this.rangee1 = new int[TAILLE];
		this.rangee2 = new int[TAILLE];
		for(int i=0; i < TAILLE; i++){
			this.rangee1[i] = rangee1[i];
			this.rangee2[i] = rangee2[i];
		}
		
				score1 = sc1;
				score2 = sc2;
	}

	/************* Gestion des paramètres de classe** ****************/ 

	public static void setJoueurs(Joueur j1, Joueur j2) {
		joueur1 = j1;
		joueur2 = j2;
	}

	public boolean isJoueur1(Joueur j1) {
		return joueur1.equals(j1);
	}

	public boolean isJoueur2(Joueur j2) {
		return joueur2.equals(j2);
	}


	/************* Méthodes de l'interface PlateauJeu ****************/ 

	public PlateauJeu copy() {
		return new PlateauAwale(this.rangee1, this.rangee2,this .score1, this.score2);
	}

	public boolean coupValide(Joueur joueur,CoupJeu cj) {
		CoupAwale ca = (CoupAwale) cj;
		int trou = ca.getTrou();
		return coupValide(joueur, trou);		
	}

	public ArrayList<CoupJeu> coupsPossibles(Joueur joueur) {
		ArrayList<CoupJeu> lesCoupsPossibles = new ArrayList<CoupJeu>();
		if (joueur.equals(joueur1)) {
			for(int i=0 ; i < TAILLE ; i++) { // tous les trous
				if(rangee1[i] >  0) // on peut jouer
					lesCoupsPossibles.add(new CoupAwale(i));
			}			
		} else { // Joueur 2
			for(int i=0 ; i < TAILLE ; i++) { // tous les trous
				if(rangee2[i] >  0) // on peut jouer
					lesCoupsPossibles.add(new CoupAwale(i));
			}	
		}
		return lesCoupsPossibles;
	}

	public boolean finDePartie() {
		int grainesRestantes = NOMBRE_GRAINE - score1 - score2;		
		return (score1 >= 26 || score2 >= 26 || grainesRestantes <=5);
	}

   
	public void joue(Joueur joueur, CoupJeu c) {
		CoupAwale cd = (CoupAwale) c;
		int trou = cd.getTrou();


		if (joueur.equals(joueur1)) { 
			damier[ligne][colonne] = BLANC;
			damier[ligne][colonne+1] = BLANC;
		} else {
			damier[ligne][colonne] = NOIR;
			damier[ligne+1][colonne] = NOIR;
		}
	}

	/* ********************* Autres méthodes ***************** */	

	private boolean coupValide(Joueur joueur,int trou) {
		if (joueur.equals(joueur1))  
			return (rangee1[trou] >  0);
		else
			return (rangee2[trou] >  0);		
	}


	public String toString() {
		String retstr = new String("");
		for(int i=0; i < TAILLE; i++) {
			for (int j=0; j < TAILLE; j++)
				if (damier[i][j]==VIDE)
					retstr += "-";
				else if (damier[i][j]==BLANC)
					retstr += "O";
				else 	// damier[i][j] == NOIR
					retstr += "#";
			retstr += "\n";
		}
		return retstr;
	}

	public void printPlateau(PrintStream out) {
		out.println(this.toString());		
	}


	public int nbCoupsBlanc(){
		int nbCoups = 0;		
		for(int i=0 ; i < TAILLE ; i++) { 
			for (int j=0 ; j < TAILLE - 1 ; j++) {
				if (damier[i][j]==VIDE && damier[i][j+1]==VIDE)
					nbCoups++;				
			}
		}
		return nbCoups;
	}
	
	public int nbCoupsNoir(){
		int nbCoups = 0;		
		for(int i=0 ; i < TAILLE ; i++) { 
			for (int j=0 ; j < TAILLE - 1 ; j++) {
				if (damier[j][i]==VIDE && damier[j+1][i]==VIDE)  
					nbCoups++;			
			}
		}
		return nbCoups;
	}


}
