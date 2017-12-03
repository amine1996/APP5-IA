package fousfous;

import java.util.ArrayList;

import fousfous.IJoueur;
import fousfous.PlateauFousfous;
import fousfous.MoveHistory;
import fousfous.Joueur;
import fousfous.Minimax;

public class PartieFousfous implements IJoueur
{
    public final static String binoName = "Ismail Girard";
    public int myColor;

    private Joueur whitePlayer;
    private Joueur blackPlayer;
    private PlateauFousfous game;
    private Minimax minMax;

    public PartieFousfous()
    {
        this.game = new PlateauFousfous(this);

        MoveHistory.setController(this);

        this.whitePlayer = new Joueur(PlateauFousfous.WHITE,"white",'b');
        this.blackPlayer = new Joueur(PlateauFousfous.BLACK,"black",'n');


    }

    /*********** Interface IJoueur ****************/

    public void initJoueur(int mycolour)
    {
        this.myColor = mycolour;

        this.getMyPlayer().setBrain(new BestHeuristique(this,this.game));
        this.getMyEnemy().setBrain(new RetardedHeuristique(this,this.game));
        minMax = new Minimax(new BestHeuristique(this, game), this.getMyPlayer(), this.getMyEnemy());
    }

    public int getNumJoueur()
    {
        return this.myColor;
    }

	public String choixMouvement()
	{
        String playerName = this.getMyPlayer().getName();

		String[] coupsPossibles = this.game.mouvementPossibles(playerName);

        if(coupsPossibles.length > 0){
            String coup = minMax.meilleurCoup(game);

            this.game.play(coup, playerName);
            System.out.println("Mon coup ("+playerName+")  : "+coup);
            System.out.println("Mon score : " + this.getMyPlayer().getScore() + " Score ennemi : "+ this.getMyEnemy().getScore());
            return coup;
        }

		return "Aucun coup possible";
	}

    public void declareLeVainqueur(int colour)
    {
        if(colour == this.myColor)
            System.out.println(this.binoName + " est le boss du game");
        else
            System.out.println(this.binoName + " doit encore s'entraîner...");
    }

    public void mouvementEnnemi(String coup)
    {
        String enemyName = this.getMyEnemy().getName();

        this.game.play(coup,enemyName);
    }

    public String binoName()
    {
        return this.binoName;
    }

    /************ Getters ******************/

    public Joueur getWhitePlayer()
    {
        return this.whitePlayer;
    }

    public Joueur getBlackPlayer()
    {
        return this.blackPlayer;
    }

    public Joueur getPlayerByColor(int color)
    {
        if(color == this.whitePlayer.getColor())
            return this.whitePlayer;
        else if(color== this.blackPlayer.getColor())
            return this.blackPlayer;

        return null;
    }

    public Joueur getPlayerByName(String name)
    {
        if(name == this.whitePlayer.getName())
            return this.whitePlayer;
        else if(name == this.blackPlayer.getName())
            return this.blackPlayer;

        return null;
    }

    public Joueur getEnemyByName(String name)
    {
        if(name == this.whitePlayer.getName())
            return this.blackPlayer;
        else
            return this.whitePlayer;
    }

    public Joueur getMyPlayer()
    {
        return this.getPlayerByColor(this.myColor);
    }

    public Joueur getMyEnemy()
    {
        return this.myColor == this.whitePlayer.getColor() ? this.blackPlayer : this.whitePlayer;
    }

    public PlateauFousfous getGame()
    {
        return this.game;
    }


}