package fousfous;

import fousfous.PlateauFousfous;
import fousfous.PartieFousfous;

public abstract class Heuristique
{
    PartieFousfous controller;
    PlateauFousfous game;

    public Heuristique(PartieFousfous controller, PlateauFousfous game)
    {
        this.controller = controller;
        this.game = game;
    }

    public abstract int eval(Joueur j);
}