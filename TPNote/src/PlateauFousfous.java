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

public class PlateauFousfous implements Partie1 {

	/* *********** constantes *********** */

	/** Taille de la grille (Carré) */
	public final static int SIZE = 8;

	/* *********** Paramètres de classe *********** */

	/** Enumération pour représenter l'état d'une case */
	private final static int EMPTY = 0;
	private final static int WHITE = 1;
	private final static int BLACK = 2;
	private final static int USELESS = 3;

	/** Le joueur que joue "white" */
	final static String WHITE_PLAYER = "white";
	final static char WHITE_CHAR = 'b';
	public int scoreWhite = 0;

	/** Le joueur que joue "black" */
	final static String BLACK_PLAYER = "black";
	final static char BLACK_CHAR = 'n';
	public int scoreBlack = 0;

	/* *********** Attributs  *********** */

	/** le plateau */
	private int plateau[][];

	/************* Constructeurs ****************/ 

	public PlateauFousfous()
	{
		plateau = new int[SIZE][SIZE];
		
		for(int i=0; i < SIZE; i++)
		{
			for (int j=0; j < SIZE; j++)
			{
				if(i%2 == j%2)
					plateau[i][j] = USELESS;
				else if(i%2 == 0 && j%2 == 1)
					plateau[i][j] = WHITE;
				else if(i%2 == 1 && j%2 == 0)
					plateau[i][j] = BLACK;
			}
		}
	}

	/************* Autres méthodes ****************/

	//Get ally depending on player
	public int getAlly(String player)
	{
		if(player == WHITE_PLAYER)
			return WHITE;

		return BLACK;
	}

	//Get enemy depending on player
	public int getEnemy(String player)
	{
		if(player == BLACK_PLAYER)
			return WHITE;

		return BLACK;
	}

	//Increment score of player given on parameter
	public void incrementScore(String player)
	{
		if(player == BLACK_PLAYER)
			scoreBlack++;
		else if (player == WHITE_PLAYER)
			scoreWhite++;
	}

	//Decrement score of player given on parameter
	public void decrementScore(String player)
	{
		if(player == BLACK_PLAYER)
			scoreBlack--;
		else if (player == WHITE_PLAYER)
			scoreWhite--;
	}

	//Set score of white player
	public void setScoreWhite(int score)
	{
		scoreWhite = score;
	}

	//Set score of black player
	public void setScoreBlack(int score)
	{
		scoreBlack = score;
	}

	//Set value for a coordinates in the game
	public void setCase(int row, int col, int value)
	{
		plateau[row][col] = value;
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

								if(lignePlateau.charAt(i) == '-')
									plateau[lineNumber][i] = EMPTY;
								else if(lignePlateau.charAt(i) == WHITE_CHAR)
									plateau[lineNumber][i] = WHITE;
								else if(lignePlateau.charAt(i) == BLACK_CHAR)
									plateau[lineNumber][i] = BLACK;
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
				for(int j=0;j<SIZE;j++)
				{
					if(plateau[i][j] == WHITE)
						save += WHITE_CHAR;
					else if(plateau[i][j] == BLACK)
						save += BLACK_CHAR;
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

	//Return true is you are threatening an enemy after your move
	public boolean isThreatening(int startRow, int startColumn, String player)
	{
		boolean threatening = false;

		int enemy = getEnemy(player);

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

					if(plateau[testRow][testColumn] == enemy)
					{
						return true;
					}
				}
			}
		}

		return false;
	}

	//Return true if a move is valid
	public boolean estValide(String move, String player)
	{
		int ally = getAlly(player);
		int enemy = getEnemy(player);

		CoupFousfous coup = new CoupFousfous(move);

		//If piece to move doesn't belong to the player
		if(plateau[coup.startRow][coup.startColumn] != ally)
			return false;

		//If non-playable case
		if(plateau[coup.endRow][coup.endColumn] == USELESS)
			return false;

		//If not diagonal
		if(Math.abs(coup.startRow - coup.endRow) != Math.abs(coup.startColumn - coup.endColumn))
			return false;

		//If taking enemy piece
		if(plateau[coup.endRow][coup.endColumn] == enemy)
			return true;

		//If is threating after move return true
		return isThreatening(coup.endRow,coup.endColumn,player);
	}

	public String[] mouvementPossibles(String player)
	{
		int ally = getAlly(player);
		int enemy = getEnemy(player);

		boolean hasTakenEnemy = false;

		ArrayList<String> coupsPossibles = new ArrayList<String>();
		ArrayList<CoupFousfous> coupsPossiblesTemp = new ArrayList<CoupFousfous>();

		for(int i=0;i<SIZE;i++)
		{
			for(int j=0;j<SIZE;j++)
			{
				if(plateau[i][j] == ally)
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

								//If has taken moves
								if(plateau[testRow][testColumn] == enemy)
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
						System.out.println(coupsPossiblesTemp.get(k));	
						if(hasTakenEnemy && !coupsPossiblesTemp.get(k).takeEnemy)
						{
							coupsPossiblesTemp.remove(k);
							k--;
						}
					}

					//Add good moves
					for(int k=0;k<coupsPossiblesTemp.size();k++)
					{
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
		if(estValide(move,player))
		{
			int ally = getAlly(player);
			int enemy = getEnemy(player);

			CoupFousfous coup = new CoupFousfous(move);
			MoveHistory.push(move,plateau[coup.startRow][coup.startColumn],plateau[coup.endRow][coup.endColumn],scoreWhite,scoreBlack);

			if(plateau[coup.endRow][coup.endColumn] == enemy)
				incrementScore(player);

			plateau[coup.endRow][coup.endColumn] = plateau[coup.startRow][coup.startColumn];
			plateau[coup.startRow][coup.startColumn] = EMPTY;


		}
	}

	public boolean finDePartie()
	{
		if(scoreWhite >= 16 || scoreBlack >= 16)
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
		String res = "";
		res += "% ABCDEFGH  \n";
		for(int i=0;i<SIZE;i++)
		{
			res += String.valueOf(i+1) + " ";
			for(int j=0;j<SIZE;j++)
			{
				if(plateau[i][j] == WHITE)
					res += WHITE_CHAR;
				else if(plateau[i][j] == BLACK)
					res += BLACK_CHAR;
				else
					res += "-";
			}
			res += " " + String.valueOf(i+1) + "\n";
		}
		res += "Score white player : "+scoreWhite+"\n";
		res += "Score black player : "+scoreBlack+"\n";
		return res;
	}

	public void printPlateau(PrintStream out) 
	{
		out.println(this.toString());		
	}

   public static void main (String[] args)
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
   }
}

