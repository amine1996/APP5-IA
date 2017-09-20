package monjeu.awale;

import java.util.ArrayList;

import jeux.alg.AlgoJeu;
import jeux.alg.Minimax;
import jeux.modele.CoupJeu;
import jeux.modele.PlateauJeu;
import jeux.modele.joueur.Joueur;

public class PartieAwale {

    public static void main(String[] args) {

        Joueur joueur1 = new Joueur("1");
        Joueur joueur2 = new Joueur("2");

        Joueur[] lesJoueurs = new Joueur[2];

        lesJoueurs[0] = joueur1;
        lesJoueurs[1] = joueur2;


        AlgoJeu AlgoJoueur[] = new AlgoJeu[2];
        AlgoJoueur[0] = new Minimax(HeuristiquesAwale.hblanc, joueur1, joueur2); // Il faut remplir la méthode !!!
        AlgoJoueur[1] = new Minimax(HeuristiquesAwale.hnoir, joueur2, joueur1);  // Il faut remplir la méthode !!!

        System.out.println("Jeux n°1 : Algorithmes pour les Jeux");
        System.out.println("Etat Initial du plateau de jeu:");

        boolean jeufini = false;
        CoupJeu meilleurCoup = null;
        int jnum;

        PlateauJeu plateauCourant = new PlateauAwale();
        PlateauAwale.setJoueurs(joueur1, joueur2);
        // Pour savoir qui joue "noir" et qui joue "blanc"


        // A chaque itération de la boucle, on fait jouer un des deux joueurs
        // tour a tour
        jnum = 0; // On commence par le joueur Blanc (arbitraire)

        while (!jeufini) {
            System.out.println("" + plateauCourant);
            System.out.println("C'est au joueur " + lesJoueurs[jnum] + " de jouer.");
            // Vérifie qu'il y a bien des coups possibles
            // Ce n'est pas tres efficace, mais c'est plus rapide... a écrire...
            ArrayList<CoupJeu> lesCoupsPossibles = plateauCourant.coupsPossibles(lesJoueurs[jnum]);
            System.out.println("Coups possibles pour" + lesJoueurs[jnum] + " : " + lesCoupsPossibles);
            if (lesCoupsPossibles.size() > 0 && !plateauCourant.finDePartie()) {
                // On écrit le plateau

                // Lancement de l'algo de recherche du meilleur coup
                System.out.println("Recherche du meilleur coup avec l'algo " + AlgoJoueur[jnum]);
                meilleurCoup = AlgoJoueur[jnum].meilleurCoup(plateauCourant);
                System.out.println("Coup joué : " + meilleurCoup + " par le joueur " + lesJoueurs[jnum]);

                plateauCourant.joue(lesJoueurs[jnum], meilleurCoup);
                // Le coup est effectivement joué
                jnum = 1 - jnum;

            } else {
                System.out.println("Le joueur " + lesJoueurs[jnum] + " ne peut plus jouer et abandone !");
                System.out.println("Le joueur " + lesJoueurs[1 - jnum] + " a gagné cette partie !");
                jeufini = true;

            }
        }
    }
}
