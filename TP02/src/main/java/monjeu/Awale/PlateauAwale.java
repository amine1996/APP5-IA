package monjeu.awale;

import java.io.PrintStream;
import java.util.ArrayList;

import jeux.modele.*;
import jeux.modele.joueur.Joueur;

public class PlateauAwale implements PlateauJeu {

	/* *********** constantes *********** */

	/** Taille des rangées */
	public final static int TAILLE = 6;

	public final static int NB_LIGNES = 2;

	/** Nombre de billes */
	private static int NOMBRE_GRAINE = 48;


	private static int LIGNE_J1 = 0;

	private static int LIGNE_J2 = 1;

	/** Le joueur que joue "1" */
	private static Joueur joueur1;

	/** Le joueur que joue "2" */
	private static Joueur joueur2;

	/**Le nombre de graines du joueur 1*/
	public int score1;

	/**Le nombre de graines du joueur 2*/
	public int score2;

	/* *********** Attributs  *********** */

	/** le damier */
	private int plateau[][];


	/************* Constructeurs ****************/ 

	public PlateauAwale(){
		plateau = new int[2][TAILLE];
		for(int i=0; i < 2; i++){
			for(int j=0;j<TAILLE;j++){
			plateau[i][j] = 4;
			}
		}

		score1 = 0;
		score2 = 0;
	}

	public PlateauAwale(int plateau[][], int sc1, int sc2){
		this.plateau = new int[2][TAILLE];
		for(int i=0; i < 2; i++){
			for(int j=0;j<TAILLE;j++){
			this.plateau[i][j] = plateau[i][j];
			}
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
		return new PlateauAwale(this.plateau,this .score1, this.score2);
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
				CoupAwale coup = new CoupAwale(i, plateau[LIGNE_J1][i]);
				if(this.coupValide(joueur1,coup)) // on peut jouer
					lesCoupsPossibles.add(coup);
			}			
		} else { // Joueur 2
			for(int i=TAILLE-1 ; i >= 0 ; i--) { // tous les trous
				CoupAwale coup = new CoupAwale(i, plateau[LIGNE_J2][i]);
				if(this.coupValide(joueur2,coup)) // on peut jouer
					lesCoupsPossibles.add(coup);
			}	
		}
		return lesCoupsPossibles;
	}

	public boolean finDePartie() {
		int grainesRestantes = NOMBRE_GRAINE - score1 - score2;		
		return (score1 >= 26 || score2 >= 26 || grainesRestantes <=5);
	}

   
    public boolean testFamine(PlateauAwale plateauCp, int ligneAdversaire, int trouCourant)
	{
		while(trouCourant != -1)
		{
			if(plateauCp.plateau[ligneAdversaire][trouCourant] != 3 &&  plateauCp.plateau[ligneAdversaire][trouCourant] != 2)
				break;

			plateauCp.plateau[ligneAdversaire][trouCourant] = 0;
			trouCourant--;
		}

		int nbGraines = 0;
		for(int i=0;i<TAILLE;i++)
			nbGraines += plateauCp.plateau[ligneAdversaire][i];

		return nbGraines == 0;
	}

	public void joue(Joueur joueur, CoupJeu c) {
		CoupAwale cd = (CoupAwale) c;
		int trou = cd.getTrou();
		int ligneJoueur = 0;

		if(joueur.equals(joueur1))
			ligneJoueur = LIGNE_J1;
		else
			ligneJoueur = LIGNE_J2;

		int nbBilles = plateau[ligneJoueur][trou];

		int ligneCourante = ligneJoueur;
		int trouCourant = trou + 1;
		plateau[ligneCourante][trou] = 0;

		for(int i=0;i<nbBilles;i++)
		{
			trouCourant = trouCourant+i;
			if(trouCourant>=TAILLE)
			{
				ligneCourante = (ligneCourante + 1)%2;
			}	

			trouCourant = trouCourant%TAILLE;
			plateau[ligneCourante][trouCourant] += 1;
		}

		if(ligneJoueur == ligneCourante)
			return;

		PlateauAwale plateauCp = (PlateauAwale) this.copy();
		if(testFamine(plateauCp,ligneCourante,trouCourant))
			return;
			
		while(trouCourant != -1)
		{
			if(plateau[ligneCourante][trouCourant] != 3 &&  plateau[ligneCourante][trouCourant] != 2)
				break;

			if(joueur.equals(joueur1))
				score1 += plateau[ligneCourante][trouCourant];
			else
				score2 += plateau[ligneCourante][trouCourant];
			
			plateau[ligneCourante][trouCourant] = 0;
			trouCourant--;	
		}
	}

	/* ********************* Autres méthodes ***************** */	

	private boolean coupValide(Joueur joueur,int trou) {
		if (joueur.equals(joueur1))  
			return (plateau[LIGNE_J1][trou] >  0);
		else
			return (plateau[LIGNE_J2][trou] >  0);		
	}


	public String toString() {
		String retstr = new String("");
		retstr += "Score j1 : "+this.score1+"\n";
		retstr += "Score j2 : "+this.score2+"\n";

		for(int i=TAILLE-1; i >= 0; i--) {
			retstr += "(" + i + "," + plateau[LIGNE_J2][i] + ")\t";
		}

		retstr+="\n";

		for(int i=0; i < TAILLE; i++) {
			retstr += "(" + i + "," + plateau[LIGNE_J1][i] + ")\t";
		}

		return retstr;
	}

	public void printPlateau(PrintStream out) {
		out.println(this.toString());		
	}


	public int nbCoupsJoueur1(){
		int nbCoups = 0;		
		for(int i=0 ; i < TAILLE ; i++) { 
			if(plateau[LIGNE_J1][i] != 0)
				nbCoups++;
		}

		return nbCoups;
	}
	
	public int nbCoupsJoueur2(){
		int nbCoups = 0;		
		for(int i=0 ; i < TAILLE ; i++) { 
			if(plateau[LIGNE_J2][i] != 0)
				nbCoups++;
		}

		return nbCoups;
	}


}
