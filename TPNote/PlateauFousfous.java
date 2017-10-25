package monjeu.dominos;

import java.io.PrintStream;
import java.util.ArrayList;

import jeux.modele.*;
import jeux.modele.joueur.Joueur;

import java.io.BufferedReader;
import java.io.FileReader;

public class PlateauFousfous implements PlateauJeu {

	/* *********** constantes *********** */

	/** Taille de la grille (Carré) */
	public final static int TAILLE = 8;

	/* *********** Paramètres de classe *********** */

	private final static int EMPTY = 0;
	private final static int WHITE = 1;
	private final static int BLACK = 2;
	private final static int USELESS = 3;

	/** Le joueur que joue "Blanc" */
	final static String blanc = "blanc";
	final static char pionBlanc = 'b';
	public int scoreBlanc = 0;

	/** Le joueur que joue "noir" */
	final static String noir = "noir";
	final static char pionNoir = 'n';
	public int scoreNoir = 0;


	/* *********** Attributs  *********** */

	/** le plateau */
	private int plateau[][];

	/************* Constructeurs ****************/ 

	public PlateauFousfous()
	{
		plateau = new int[TAILLE][TAILLE];
		
		for(int i=0; i < TAILLE; i++)di
		{
			for (int j=0; j < TAILLE; j++)
			{
				if(i%2 == j%2)
					plateau[i][j] = USELESS;
				else if(i%2 == 0 && j%2 == 1)
					plateau[i][j] = BLANC;
				else if(i%2 == 1 && j%2 == 0)
					plateau[i][j] = NOIR;
			}
		}
	}

	/************* Gestion des paramètres de classe** ****************/ 


	public static void setJoueurs(Joueur jb, Joueur jn) {
		//TODO (PAS OBLIGATOIRE)
	}

	public boolean isJoueurBlanc(Joueur jb) {
		//TODO (PAS OBLIGATOIRE)
	}

	public boolean isJoueurNoir(Joueur jn) {
		//TODO (PAS OBLIGATOIRE)
	}


	/************* Méthodes de l'interface Partie1    ****************/

	public void setFromFile(String fileName)
	{
		File file = File(filename);

		if(file.exists())
		{
			fileReader = new FileReader(fileName);
			buffReader = new BufferedReader(fileReader);

			String currentLine;

			while ((currentLine = br.readLine()) != null) 
			{
				if(currentLine.charAt(0) == '%')
					continue;

				String[] splitted = currentLine.split(" ");
				//Comment line
				if(splitted.length == 3)
				{
					int lineNumber = -1;
					if(Character.isDigit(splitted[0].charAt(0)))
						int lineNumber = Character.getNumericValue(splitted[0].charAt(0)) - 1;

					if(lineNumber != -1)
					{
						String lignePlateau = splitted[1];
						for(int i=0;i<splitted[1].length;i++)
						{
							plateau[lineNumber][i] = lignePlateau.charAt(i);
						}
					}
				}
				else
				{
					System.out.println("Erreur a besoin de 3 elements");
					System.exit(-1);
				}
			
				

				
			}

			fileReader.close();
			buffReader.close();
		}

		file.close();
	}

	public void saveToFile(String filename)
	{
		//TODO
	}

	public void estValide(String move, String player)
	{
		//TODO
	}

	public String[] mouvementPossibles(String player)
	{
		//TODO
	}

	public void play(String move, String player)
	{
		//TODO
		//
	}

	public boolean finDePartie()
	{
		//TODO
		//UN DES SCORES EGAL A 16
	}

	public PlateauJeu copy() 
	{
		return new PlateauFousfous(this.plateau);
	}

	/* ********************* Autres méthodes ***************** */	

	public String toString() 
	{
		//TODO
	}

	public void printPlateau(PrintStream out) 
	{
		out.println(this.toString());		
	}

}
