package fousfous;

import org.omg.CosNaming.IstringHelper;

import fousfous.Heuristique;
import fousfous.PartieFousfous;
import fousfous.PlateauFousfous;

public class BestHeuristique extends Heuristique
{
    public BestHeuristique(PartieFousfous controller, PlateauFousfous game)
    {
        super(controller,game);
    }

    public int eval(Joueur player)
    {
        int val = 0;
        int threateningpiece = 0;
        for(int i=0; i < PlateauFousfous.SIZE; i++)
		{
			for (int j=0; j < PlateauFousfous.SIZE; j++)
			{
				if(i%2 == j%2)
					continue;
                if(PlateauFousfous.EMPTY == game.getCase(i, j))
                    continue;
                if(game.getCase(i, j) == player.getColor()){
                    if(game.isThreatening2(i, j, player.getName()))
                        threateningpiece++;
                }
			}
		}
        
        if(threateningpiece == 0)
            val+= 3000000;

        if(player.getScore() == 16)
            val+= 20000000;

        val+= (player.getScore() * 10000);

        val-= threateningpiece ;

        val+= ((16 - controller.getMyEnemy().getScore()) * 100);

        return val;
    }
}