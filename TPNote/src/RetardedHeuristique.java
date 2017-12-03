package fousfous    ;

import fousfous.Heuristique;
import fousfous.PartieFousfous;
import fousfous.PlateauFousfous;

public class RetardedHeuristique extends Heuristique
{
    public RetardedHeuristique(PartieFousfous controller, PlateauFousfous game)
    {
        super(controller,game);
    }

    public int eval(Joueur j)
    {
        return -1;
    }
}