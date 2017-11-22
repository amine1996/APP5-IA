package espacesEtats.algorithmes;

import espacesEtats.modeles.Probleme;
import espacesEtats.modeles.Solution;

/**
 * Interface minimale d'un algorithme de r�solution de probl�me de recherche
 * dans un graphe d'�tats
 *
 * @author Philippe Chatalic
 */
public abstract class AlgorithmeRechercheEE {

    // Attributs 
    protected int nbNoeudsDeveloppes;   // Accessible dans les classes d�riv�es

    //----------------------------------------------------
    // Constructeurs
    //----------------------------------------------------
    AlgorithmeRechercheEE() {
        nbNoeudsDeveloppes = 0;
    }

    //----------------------------------------------------
    // Accesseurs modifieurs
    //----------------------------------------------------
     /**
     * Renvoie le nombre de noeuds d�velopp�s par l'algorithme
     *
     * @return le nombre de noeuds d�velopp�s
     */
    public int getNbNoeudsDeveloppes() {
        return nbNoeudsDeveloppes;
    }
    
     //----------------------------------------------------
    // M�thode(s) abstraite(s)
    //----------------------------------------------------
 
    /**
     * Recherche un chemin solution pour un probl�me formul� en terme de
     * recherche dans un espace d'�tats
     *
     * @param p le probl�me � r�soudre
     * @return une solution du probl�me
     */
    public abstract Solution chercheSolution(Probleme p);


}
