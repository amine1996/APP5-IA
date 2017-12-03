package fousfous;

import fousfous.PlateauFousfous;
import java.util.ArrayList; 

public final class MoveHistory 
{
    private static PartieFousfous controller;
    private static PlateauFousfous game;

	private static ArrayList<String> moves = new ArrayList<String>();
	private static ArrayList<Integer> oldStartStates = new ArrayList<Integer>();
	private static ArrayList<Integer> oldEndStates = new ArrayList<Integer>();
    private static ArrayList<Integer> oldScoresWhite = new ArrayList<Integer>();
    private static ArrayList<Integer> oldScoresBlack = new ArrayList<Integer>();

	private MoveHistory()
	{
		moves = new ArrayList<String>();
		oldStartStates = new ArrayList<Integer>();
		oldEndStates = new ArrayList<Integer>();
		oldScoresWhite = new ArrayList<Integer>();
		oldScoresBlack = new ArrayList<Integer>();
	}

    public static void setController(PartieFousfous pController)
    {
        controller = pController;
        game = controller.getGame();
    }

    //Add a move and states to history
	public static void push(String move, int oldStartState, int oldEndState, int scoreWhite, int scoreBlack)
	{
        if(game != null)
        {
            moves.add(move);
            oldStartStates.add(oldStartState);
            oldEndStates.add(oldEndState);
            oldScoresWhite.add(scoreWhite);
            oldScoresBlack.add(scoreBlack);
        }
    }

    //Pop a move and states from history
    public static void pop()
    {
        if(game != null && moves.size() > 0)
        {
            CoupFousfous lastMove = new CoupFousfous(moves.get(moves.size() - 1));
            int lastStartState = oldStartStates.get(oldStartStates.size() - 1);
            int lastEndState = oldEndStates.get(oldEndStates.size() - 1);
            int lastScoreWhite = oldScoresWhite.get(oldScoresWhite.size() - 1);
            int lastScoreBlack = oldScoresBlack.get(oldScoresBlack.size() - 1);

            game.setCase(lastMove.endRow,lastMove.endColumn,lastEndState);
            game.setCase(lastMove.startRow,lastMove.startColumn,lastStartState);

            controller.getWhitePlayer().setScore(lastScoreWhite);
            controller.getBlackPlayer().setScore(lastScoreBlack);

            moves.remove(moves.size() - 1);
            oldStartStates.remove(oldStartStates.size() - 1);
            oldEndStates.remove(oldEndStates.size() - 1);
            oldScoresWhite.remove(oldScoresWhite.size() - 1);
            oldScoresBlack.remove(oldScoresBlack.size() - 1);
        }
    }

    public static String getString()
    {
        if(game == null || moves.size() == 0)
            return "No history";

        String res = "History : \n";
        for(int i=0;i<moves.size();i++)
        {
            res += "\tMove done was : "+moves.get(i)+"\n";
            res += "\tWhiteScore was : "+oldScoresWhite.get(i)+" and BlackScore was : "+oldScoresBlack.get(i)+"\n";
            res += "\tStartState was : "+oldStartStates.get(i)+" and EndState was : "+oldEndStates.get(i)+"\n";
            res += "\t************************\n";
        }
        return res;
    }
}