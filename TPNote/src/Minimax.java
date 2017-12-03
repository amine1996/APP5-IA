package fousfous;


import java.util.ArrayList;

import fousfous.CoupFousfous; 
import fousfous.PlateauFousfous;
import fousfous.Joueur;
import fousfous.PartieFousfous;
import fousfous.MoveHistory;

public class Minimax{

    /** La profondeur de recherche par défaut
     */
    private final static int PROFMAXDEFAUT = 3;

   
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


   public String meilleurCoup(PlateauFousfous p) {
        /* A vous de compléter le corps de ce fichier */
        int meilleur = Integer.MIN_VALUE;
        String[] lesCoupsPossibles = p.mouvementPossibles(joueurMax.getName());

        String mCoup = null; 
        
        for (String coup : lesCoupsPossibles) {            
            p.play(coup, joueurMax.getName());
            int tmp = minMax(p,1 );
            if ( meilleur < tmp){
                meilleur = tmp ;
                mCoup = coup;
            }
            MoveHistory.pop();
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

    public int maxMin(PlateauFousfous p, int prof){
        int meilleur = Integer.MIN_VALUE;
        String[] lesCoupsPossibles = p.mouvementPossibles(joueurMax.getName());

        //System.out.println("Profondeur actuelle " + prof);
        if(prof > profMax || lesCoupsPossibles.length == 0){  
            return h.eval(joueurMax)/prof;
        }

        prof++;
        for (String coup : lesCoupsPossibles) {         
            p.play(coup, joueurMax.getName());

            meilleur = Integer.max(meilleur, minMax(p, prof));            
            MoveHistory.pop();
        }
        return meilleur;
    }  

    public int minMax(PlateauFousfous p, int prof){       
        int pire = Integer.MAX_VALUE;
        String[] lesCoupsPossibles = p.mouvementPossibles(joueurMin.getName());

        if(prof > profMax || lesCoupsPossibles.length == 0){       
            return h.eval(joueurMin)/prof;
        }

        prof++;
        for (String coup : lesCoupsPossibles) {                      
            p.play(coup, joueurMin.getName());
            pire = Integer.min(pire, maxMin(p, prof));            
            MoveHistory.pop();
        }
        return pire;
    }

}
