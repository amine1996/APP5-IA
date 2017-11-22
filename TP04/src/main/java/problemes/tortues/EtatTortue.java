package problemes.tortues;

import java.util.Collection;
import java.util.LinkedList;

import espacesEtats.modeles.Etat;

/**
 * Formalisation du probl�mes des tortues en tant que probl�me de recherche dans
 * un graphe d'�tats.
 *
 * @author Philippe Chatalic
 * @author Alexandre Allauzen
 */
public class EtatTortue implements Etat {

    // Constantes ----------------------------------------------------------
    /**
     * Nombre de tortues pour un �tat du probl�me.
     */
    public final static int NOMBRE_DE_TORTUES = 6;
    
    // Attributs -----------------------------------------------------------
    /**
     * Les tortues 
     * Elles sont repr�sent�es par un tableau de bool�ens.
     * L'ordre des indices sur le tableau correspond aux tortues consid�r�es
     * de gauche a droite
     */
    private boolean[] tortues;

    // Constructeurs -----------------------------------------------------------
    /**
     * Par d�faut toutes les tortues sont sur le dos sauf la troisi�me et la
     * quatri�me en partant de la gauche.
     */
    public EtatTortue() {
        tortues = new boolean[NOMBRE_DE_TORTUES];
        for (int i = 0; i < NOMBRE_DE_TORTUES; i++) {
            tortues[i] = false;
        }
        tortues[1] = true;
        tortues[2] = true;
    }

    /**
     * Constructeur a partir d'un tableau de bool�ens (avec recopie du tableau)
     * @param _tab : un tableau de bool�ens
     */
    public EtatTortue(boolean[] _tab) {
        tortues = new boolean[NOMBRE_DE_TORTUES];
        System.arraycopy(_tab, 0, tortues, 0, NOMBRE_DE_TORTUES);
    }

    /**
     * Constructeur dupliquant � l'identique un EtatTortue
     * @param et : un EtatTortue
     */
    public EtatTortue(EtatTortue et) {
        tortues = new boolean[NOMBRE_DE_TORTUES];
        System.arraycopy(et.tortues, 0, tortues, 0, NOMBRE_DE_TORTUES);
    }

    // Accesseurs -----------------------------------------------------------
    public boolean[] getTortues() {
        return tortues;
    }

    public boolean getTortue(int i)  {
        if ((i > tortues.length) || (i < 0)) {
            throw new RuntimeException("Erreur :getTortue(" + i +")... il nas autant de tortues !");
        }
        return tortues[i];
    }

    // Modifieurs -----------------------------------------------------------
    /**
     * Modifie le statut de i-�me tortue (num�rot�es de O � NOMBRE_DE_TORTUES -
     * 1)
     *
     * @param i l'index de la tortues
     * @param p de la tortues modifi�e
     */
    public void setPositionTortue(int i, boolean p) throws Exception {
        if (0 <= i && i < NOMBRE_DE_TORTUES) {
            throw new Exception("Erreur : Pas autant de tortues");
        }
        tortues[i] = p;
    }

    /* ********** Methodes requises par l'interface Etat ************* */
    @Override
    public Collection<Etat> successeurs() {
        LinkedList<Etat> successeurs = new LinkedList<Etat>();
        for (int i = 0; i < EtatTortue.NOMBRE_DE_TORTUES - 1; i++) {
            EtatTortue successeur = new EtatTortue(this);
            try {
                successeur.inverseTortue(i);
                successeurs.add(successeur);
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        }
        return successeurs;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof EtatTortue) {
            EtatTortue et = (EtatTortue) obj;
            for (int i = 0; i < NOMBRE_DE_TORTUES; i++) {
                if (this.tortues[i] != et.tortues[i]) {
                    return false;
                }
            }
            return true;
        }
        return false;
    }

    @Override
    public String toString() {
        String s = "tortues = [ ";
        for (int i = 0; i < NOMBRE_DE_TORTUES; i++) {
            if (tortues[i]) {
                s = s.concat("1 ");
            } else {
                s = s.concat("0 ");
            }
        }
        s = s.concat("]");
        return s;
    }

    /**
     * Inverse la tortue d�sign�e ainsi que celle situ�e � sa droite.
     *
     * @param i l'indice de la tortue � inverser (implique l'inversion de sa
     * voisine de droite). i est donc < (NOMBRE_DE_TORTUES-1)
     * @throws Exception
     */
    public void inverseTortue(int i) {
        if ((i > EtatTortue.NOMBRE_DE_TORTUES - 1) || (i < 0)) {
            throw new RuntimeException("Mauvais appel de inverseTortue (i = " + i + ")\n");
        }
        this.tortues[i] = !this.tortues[i];
        this.tortues[i + 1] = !this.tortues[i + 1];
    }
}
