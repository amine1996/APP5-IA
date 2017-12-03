package fousfous;

import java.io.PrintStream;
import java.util.ArrayList;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import fousfous.CoupFousfous;
import fousfous.MoveHistory;
import fousfous.Partie1;
import fousfous.PartieFousfous;
import fousfous.Joueur;

public class PlateauFousfous implements Partie1{

	/* *********** constantes *********** */

	/** Taille de la grille (Carré) */
	public final static int SIZE = 8;

	/* *********** Paramètres de classe *********** */

	/** Enumération pour représenter l'état d'une case */
	public final static int EMPTY = 0;
	public final static int WHITE = -1;
	public final static int BLACK = 1;
	public final static int USELESS = 3;

	public PartieFousfous controller;

	/* *********** Attributs  *********** */

	/** le plateau */
	private int plateau[][];

	/************* Constructeurs ****************/ 

	public PlateauFousfous(PartieFousfous controller)
	{
		this.controller = controller;
		this.plateau = new int[SIZE][SIZE];
		
		for(int i=0; i < SIZE; i++)
		{
			for (int j=0; j < SIZE; j++)
			{
				if(i%2 == j%2)
					this.plateau[i][j] = USELESS;
				else if(i%2 == 0 && j%2 == 1)
					this.plateau[i][j] = WHITE;
				else if(i%2 == 1 && j%2 == 0)
					this.plateau[i][j] = BLACK;
			}
		}
	}
	
	//Set value for a coordinates in the game
	public void setCase(int row, int col, int value)
	{
		plateau[row][col] = value;
	}

	//Set value for a coordinates in the game
	public int getCase(int row, int col)
	{
		return plateau[row][col];
	}

	/************* Méthodes de l'interface Partie1    ****************/

	public void setFromFile(String fileName)
	{
		File file = new File(fileName);
		FileReader fileReader = null;
		BufferedReader buffReader = null;

		if(file.exists())
		{
			try
			{
				fileReader = new FileReader(file);
				buffReader = new BufferedReader(fileReader);
			}
			catch(FileNotFoundException e)
			{
				e.printStackTrace(System.out);
			}

			String currentLine;

			try
			{
				while ((currentLine = buffReader.readLine()) != null) 
				{
					//Comment line
					if(currentLine.charAt(0) == '%')
						continue;

					String[] splitted = currentLine.split(" ");
					if(splitted.length == 3)
					{
						int lineNumber = -1;
						if(Character.isDigit(splitted[0].charAt(0)))
							lineNumber = Character.getNumericValue(splitted[0].charAt(0)) - 1;

						if(lineNumber != -1)
						{
							String lignePlateau = splitted[1];
							for(int i=0;i<splitted[1].length();i++)
							{
								if(lineNumber%2 == i%2)
									continue;

								Joueur whitePlayer = this.controller.getWhitePlayer();
								Joueur blackPlayer = this.controller.getBlackPlayer();

								if(lignePlateau.charAt(i) == '-')
									plateau[lineNumber][i] = EMPTY;
								else if(lignePlateau.charAt(i) == whitePlayer.getCharacter())
									plateau[lineNumber][i] = whitePlayer.getColor();
								else if(lignePlateau.charAt(i) == blackPlayer.getCharacter())
									plateau[lineNumber][i] = blackPlayer.getColor();
							}
						}
					}
					else
					{
						System.out.println("Malformed save file");
						System.exit(-1);
					}		
				}
				fileReader.close();
				buffReader.close();
			}
			catch(IOException e)
			{
				e.printStackTrace(System.out);				
			}

		}
	}

	public void saveToFile(String filename)
	{
		try
		{
			File outputFile = new File(filename); 
			FileWriter fileWriter = new FileWriter(outputFile);

			String save = "";
			save += "% ABCDEFGH  \n";
			for(int i=0;i<SIZE;i++)
			{
				save += String.valueOf(i+1) + " ";

				Joueur whitePlayer = this.controller.getWhitePlayer();
				Joueur blackPlayer = this.controller.getBlackPlayer();

				for(int j=0;j<SIZE;j++)
				{
					if(plateau[i][j] == whitePlayer.getColor())
						save += whitePlayer.getCharacter();
					else if(plateau[i][j] == blackPlayer.getColor())
						save += blackPlayer.getCharacter();
					else
						save += "-";
				}
				save += " " + String.valueOf(i+1) + "\n";
			}

			fileWriter.write(save);
			fileWriter.flush();
			fileWriter.close();
		}
		catch(IOException e)
		{
			e.printStackTrace(System.out);		
		}
	}

	//Return true if you are threatening an enemy after your move
	public boolean isThreatening(int startRow, int startColumn, String player)
	{
		if(plateau[startRow][startColumn] != EMPTY)
			return false;

		boolean threatening = false;

		Joueur currentPlayer = this.controller.getPlayerByName(player);
		Joueur currentEnemy = this.controller.getEnemyByName(player);

		for(int row=-1;row<=1;row+=2)
		{
			for(int col=-1;col<=1;col+=2)
			{
				int testRow = startRow;
				int testColumn = startColumn;
				while((testRow+row < SIZE && testRow+row >= 0) && (testColumn+col < SIZE && testColumn+col  >= 0))
				{
					testRow += row;
					testColumn += col;

					if(plateau[testRow][testColumn] == currentPlayer.getColor())
						break;

					if(plateau[testRow][testColumn] == currentEnemy.getColor())
						return true;
				}
			}
		}

		return false;
	}

	public boolean isThreatening2(int startRow, int startColumn, String player)
	{

		boolean threatening = false;

		Joueur currentPlayer = this.controller.getPlayerByName(player);
		Joueur currentEnemy = this.controller.getEnemyByName(player);

		for(int row=-1;row<=1;row+=2)
		{
			for(int col=-1;col<=1;col+=2)
			{
				int testRow = startRow;
				int testColumn = startColumn;
				while((testRow+row < SIZE && testRow+row >= 0) && (testColumn+col < SIZE && testColumn+col  >= 0))
				{
					testRow += row;
					testColumn += col;

					if(plateau[testRow][testColumn] == currentPlayer.getColor())
						break;

					if(plateau[testRow][testColumn] == currentEnemy.getColor())
						return true;
				}
			}
		}

		return false;
	}

	//Return true if a move is valid
	public boolean estValide(String move, String player)
	{
		CoupFousfous coup = new CoupFousfous(move);

		Joueur currentPlayer = this.controller.getPlayerByName(player);
		Joueur currentEnemy = this.controller.getEnemyByName(player);

		//If piece to move doesn't belong to the player
		if(plateau[coup.startRow][coup.startColumn] != currentPlayer.getColor())
			return false;

		//If non-playable case
		if(plateau[coup.endRow][coup.endColumn] == USELESS)
			return false;

		//If not diagonal
		if(Math.abs(coup.startRow - coup.endRow) != Math.abs(coup.startColumn - coup.endColumn))
			return false;

		//If taking enemy piece
		if(plateau[coup.endRow][coup.endColumn] == currentEnemy.getColor())
			return true;

		//If is threating after move return true
		return isThreatening(coup.endRow,coup.endColumn,player);
	}

	public String[] mouvementPossibles(String player)
	{
		boolean hasTakenEnemy = false;

		ArrayList<String> coupsPossibles = new ArrayList<String>();
		ArrayList<CoupFousfous> coupsPossiblesTemp = new ArrayList<CoupFousfous>();

		Joueur currentPlayer = this.controller.getPlayerByName(player);
		Joueur currentEnemy = this.controller.getEnemyByName(player);

		for(int i=0;i<SIZE;i++)
		{
			for(int j=0;j<SIZE;j++)
			{
				if(plateau[i][j] == currentPlayer.getColor())
				{
					hasTakenEnemy = false;
					coupsPossiblesTemp = new ArrayList<CoupFousfous>();

					for(int row=-1;row<=1;row+=2)
					{
						for(int col=-1;col<=1;col+=2)
						{
							int testRow = i;
							int testColumn = j;
							while((testRow+row < SIZE && testRow+row >= 0) && (testColumn+col < SIZE && testColumn+col  >= 0))
							{
								testRow += row;
								testColumn += col;
								CoupFousfous coup = new CoupFousfous(j,i,testColumn,testRow);

								//New
								if(plateau[testRow][testColumn] == currentPlayer.getColor())
									break;
								//New

								//If has taken enemy
								if(plateau[testRow][testColumn] == currentEnemy.getColor())
								{
									coup.setTakeEnemy(true);
									hasTakenEnemy = true;
								}

								coupsPossiblesTemp.add(coup);

								//Useless to keep looking
								if(hasTakenEnemy)
									break;
							}
						}
					}

					//Remove bad moves
					for(int k=0;k<coupsPossiblesTemp.size();k++)
					{
						if(hasTakenEnemy && !coupsPossiblesTemp.get(k).takeEnemy)
						{
							coupsPossiblesTemp.remove(k);
							k--;
						}
					}

					//Add good moves
					for(int k=0;k<coupsPossiblesTemp.size();k++)
					{
						if(estValide(coupsPossiblesTemp.get(k).move, player))
							coupsPossibles.add(coupsPossiblesTemp.get(k).move);
					}
				}
			}
		}

		String[] res = new String[coupsPossibles.size()];
		res = coupsPossibles.toArray(res);

		return res;
	}

	public void play(String move, String player)
	{
		//System.out.println(player+" essaie de jouer le coup "+move);

		Joueur whitePlayer = this.controller.getWhitePlayer();
		Joueur blackPlayer = this.controller.getBlackPlayer();


		//System.out.println("Coup "+move+" joué par "+player);

		CoupFousfous coup = new CoupFousfous(move);
		MoveHistory.push(move,plateau[coup.startRow][coup.startColumn],plateau[coup.endRow][coup.endColumn],whitePlayer.getScore(),blackPlayer.getScore());

		if(player == whitePlayer.getName() && plateau[coup.endRow][coup.endColumn] == blackPlayer.getColor())
			whitePlayer.incrementScore();
		else if(player == blackPlayer.getName() && plateau[coup.endRow][coup.endColumn] == whitePlayer.getColor())
			blackPlayer.incrementScore();

		plateau[coup.endRow][coup.endColumn] = plateau[coup.startRow][coup.startColumn];
		plateau[coup.startRow][coup.startColumn] = EMPTY;
	
	}

	public boolean finDePartie()
	{
		Joueur whitePlayer = this.controller.getWhitePlayer();
		Joueur blackPlayer = this.controller.getBlackPlayer();

		if(whitePlayer.getScore() >= 16 || blackPlayer.getScore() >= 16)
			return true;
		return false;
	}

	public PlateauFousfous copy() 
	{
		//TODO ?
		return null;
	}

	/* ********************* Autres méthodes ***************** */	

	public String toString() 
	{
		Joueur whitePlayer = this.controller.getWhitePlayer();
		Joueur blackPlayer = this.controller.getBlackPlayer();

		String res = "";
		res += "% ABCDEFGH  \n";
		for(int i=0;i<SIZE;i++)
		{
			res += String.valueOf(i+1) + " ";
			for(int j=0;j<SIZE;j++)
			{
				if(plateau[i][j] == whitePlayer.getColor())
					res += whitePlayer.getCharacter();
				else if(plateau[i][j] == blackPlayer.getColor())
					res += blackPlayer.getCharacter();
				else
					res += "-";
			}
			res += " " + String.valueOf(i+1) + "\n";
		}
		res += "Score white player : "+whitePlayer.getScore()+"\n";
		res += "Score black player : "+blackPlayer.getScore()+"\n";

		return res;
	}

	public void printPlateau(PrintStream out) 
	{
		out.println(this.toString());		
	}

   /*public static void main (String[] args)
   {
        //New game
        PlateauFousfous game = new PlateauFousfous();
        MoveHistory.setGame(game);

		//Loading a saved game
		game.setFromFile("input.txt");

        //Printing game
        System.out.println(game);

        //Getting possible move list for player white
        String [] moveList = game.mouvementPossibles("white");

		//Printing of list of valid moves
		ArrayList<String> validMoves = new ArrayList<String>();
		for(int i=0;i<moveList.length - 1;i++)
		{
			if(game.estValide(moveList[i],"white"))
			{
				validMoves.add(moveList[i]);
				System.out.println(moveList[i]);
			}
		}
        //Playing moveList[1]
		System.out.println("validMoves.get(0) : "+validMoves.get(0));
        game.play(validMoves.get(0),"white");
        System.out.println(game);

		//Saving game after a move
		game.saveToFile("save.txt");

        //Printing play history
        System.out.println(MoveHistory.getString());  

        //Popping last move 
        MoveHistory.pop();
        System.out.println(game);            
   }*/
}

