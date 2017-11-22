package problemes.tortues;

import espacesEtats.algorithmes.*;
import espacesEtats.modeles.*;

/**
 * Exemple de résolution de probléme
 *
 * @author Philippe Chatalic
 * @author Alexandre Allauzen
 */
public class ResolutionTortue {

    public static void main(String[] args) {
        /* Définition d'un problème à partir d'un état initial */
        Probleme pb = new ProblemeTortue(new EtatTortue());
        
        /* Choix de l'algorithme de résolution*/     
        AlgorithmeRechercheEE algo = null;      // Mettre le bon algorithme ici 
        
        /* La solution, si elle est trouvé est retournée sous forme d'une
         * liste de noeuds allant de l'état initial à l'état final
         */
        Solution sol = algo.chercheSolution(pb);
        if (sol != null) {
            System.out.println("Solution trouv�e : ");
            sol.affiche();
            System.out.println("Nombre total de noeuds d�velopp�s : " + algo.getNbNoeudsDeveloppes());
       } else {
            System.out.println("Echec !");
        }
    }
}
s