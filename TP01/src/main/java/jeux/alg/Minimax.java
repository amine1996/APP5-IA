/**
 * 
 */

package jeux.alg;

import java.util.ArrayList;

import jeux.modele.CoupJeu; 
import jeux.modele.PlateauJeu;
import jeux.modele.joueur.Joueur;

public class Minimax implements AlgoJeu {

    /** La profondeur de recherche par défaut
     */
    private final static int PROFMAXDEFAUT = 4;

   
    // -------------------------------------------
    // Attributs
    // -------------------------------------------
 
    /**  La profondeur de recherche utilisée pour l'algorithme
     */
    private int profMax = PROFMAXDEFAUT;

     /**  L'heuristique utilisée par l'algorithme
      */
   private Heuristique h;

    /** Le joueur Min
     *  (l'adversaire) */
    private Joueur joueurMin;

    /** Le joueur Max
     * (celui dont l'algorithme de recherche adopte le point de vue) */
    private Joueur joueurMax;

    /**  Le nombre de noeuds développé par l'algorithme
     * (intéressant pour se faire une idée du nombre de noeuds développés) */
       private int nbnoeuds;

    /** Le nombre de feuilles évaluées par l'algorithme
     */
    private int nbfeuilles;


  // -------------------------------------------
  // Constructeurs
  // -------------------------------------------
    public Minimax(Heuristique h, Joueur joueurMax, Joueur joueurMin) {
        this(h,joueurMax,joueurMin,PROFMAXDEFAUT);
    }

    public Minimax(Heuristique h, Joueur joueurMax, Joueur joueurMin, int profMaxi) {
        this.h = h;
        this.joueurMin = joueurMin;
        this.joueurMax = joueurMax;
        profMax = profMaxi;
//		System.out.println("Initialisation d'un MiniMax de profondeur " + profMax);
    }

   // -------------------------------------------
  // Méthodes de l'interface AlgoJeu
  // -------------------------------------------
   public CoupJeu meilleurCoup(PlateauJeu p) {
        /* A vous de compléter le corps de ce fichier */
        int meilleur = Integer.MIN_VALUE;
        ArrayList<CoupJeu> lesCoupsPossibles = p.coupsPossibles(joueurMax);

        CoupJeu mCoup = null; 
        
        for (CoupJeu coup : lesCoupsPossibles) {            
            PlateauJeu cp = p.copy();       
            cp.joue(joueurMax, coup);
            int tmp = maxMin(cp, 1);
            if ( meilleur < tmp){
                meilleur = tmp ;
                mCoup = coup;
            }
        }
        return mCoup;
    }
  // -------------------------------------------
  // Méthodes publiques
  // -------------------------------------------
    public String toString() {
        return "MiniMax(ProfMax="+profMax+")";
    }



  // -------------------------------------------
  // Méthodes internes
  // -------------------------------------------

    //A vous de jouer pour implanter Minimax

    public int maxMin(PlateauJeu p, int prof){
        
        int meilleur = Integer.MIN_VALUE;
        ArrayList<CoupJeu> lesCoupsPossibles = p.coupsPossibles(joueurMax);

        if(prof > profMax || lesCoupsPossibles.size() == 0){            
            return h.eval(p, joueurMax);
        }

        for (CoupJeu coup : lesCoupsPossibles) {            
            PlateauJeu cp = p.copy();       
            cp.joue(joueurMax, coup);
            meilleur = Integer.max(meilleur, minMax(cp, prof++));
        }
        return meilleur;
    }  

    public int minMax(PlateauJeu p, int prof){
        
        int pire = Integer.MAX_VALUE;
        ArrayList<CoupJeu> lesCoupsPossibles = p.coupsPossibles(joueurMin);

        if(prof > profMax || lesCoupsPossibles.size() == 0){            
            return h.eval(p, joueurMin);
        }

        for (CoupJeu coup : lesCoupsPossibles) {            
            PlateauJeu cp = p.copy();       
            cp.joue(joueurMin, coup);
            pire = Integer.min(pire, maxMin(cp, prof++));
        }
        return pire;
    }

}
