package fousfous;

import fousfous.Heuristique;
import fousfous.PartieFousfous;
import fousfous.PlateauFousfous;

public class BestHeuristique extends Heuristique
{
    public BestHeuristique(PartieFousfous controller, PlateauFousfous game)
    {
        super(controller,game);
    }

    public int eval()
    {
        return 1000;
    }
}