package problemes.tortues;

import espacesEtats.modeles.Etat;
import espacesEtats.modeles.Probleme;

/**
 * Mod�lisation du probl�me des tortues
 * 
 * @author Philippe Chatalic
 */
public class ProblemeTortue implements Probleme {
    
    // Attributs  -------------------------------------------------------
    private EtatTortue etatInitial;
    
    private String descriptionBut = "But : Remettre toutes les tortues sur les pattes\n";

    // Constructeurs -------------------------------------------------------
    public ProblemeTortue(Etat eInit) {
        try{
                    this.etatInitial = (EtatTortue) eInit;
        }
        catch (Exception e) {
            throw new RuntimeException("Un ProblemeTortue doit se créer à partir d'un EtatTortue ! ");
        }
    }

    public ProblemeTortue(Etat eInit,String description) {
         this(eInit);
         descriptionBut = description;
         
    }

    // M�thodes requises par l'interface Probleme
    
    @Override
    public Etat getEtatInitial() {
        return etatInitial;
    }
    
    
    @Override
    public boolean isTerminal(Etat e) {
        if (!(e instanceof EtatTortue)) {
            return false;
        }
        EtatTortue et = (EtatTortue) e;
        boolean toutesSurPattes = et.getTortue(0);
        int i = 1;
        while(toutesSurPattes && i < EtatTortue.NOMBRE_DE_TORTUES){
            toutesSurPattes = toutesSurPattes && et.getTortue(i);
            i++ ;
         }
        return toutesSurPattes;
    }

    
   /** Méthodes abstraites de la classe Problème  à instancier **/
    @Override
    public String toString() {
        return "Etat initial : " + etatInitial + "\n"
              + descriptionBut;
    }
   
}
