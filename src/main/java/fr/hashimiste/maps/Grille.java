package fr.hashimiste.maps;

import fr.hashimiste.Difficulte;
import fr.hashimiste.techniques.Technique;

import java.awt.geom.Dimension2D;
import java.util.ArrayList;
import java.util.List;

/**
 * La classe Grille représente une grille dans un jeu ou une tâche.
 * Elle contient des informations sur le niveau de difficulté de la grille, les îles (Ile) dans la grille et les ponts (Pont) dans la grille.
 * Elle fournit des méthodes pour récupérer des informations sur la grille, ses îles et ses ponts.
 */
public class Grille {
    private final Dimension2D dimension;
    private final Difficulte difficulte;
    private final List<Ile> iles;
    private final List<Pont> ponts;

    /**
     * Créer une grille
     *
     * @param dimension  la dimension de la grille
     * @param difficulte la difficulté de la grille
     * @param iles       les iles de la grille
     * @param ponts      les ponts de la grille
     */
    public Grille(Dimension2D dimension, Difficulte difficulte, List<Ile> iles, List<Pont> ponts) {
        this.dimension = dimension;
        this.difficulte = difficulte;
        this.iles = iles;
        this.ponts = ponts;
    }

    /**
     * Créer une grille
     *
     * @param dimension  la dimension de la grille
     * @param difficulte la difficulté de la grille
     * @param iles      les iles de la grille
     */
    public Grille(Dimension2D dimension, Difficulte difficulte, List<Ile> iles) {
        this(dimension, difficulte, iles, new ArrayList<>());
    }

    /**
     * Créer une grille
     *
     * @param dimension  la dimension de la grille
     * @param difficulte la difficulté de la grille
     */
    public Grille(Dimension2D dimension, Difficulte difficulte) {
        this(dimension, difficulte, new ArrayList<>());
    }

    /**
     * Récupérer la difficulté de la grille
     *
     * @return la difficulté de la grille
     */
    public Difficulte getDifficulte() {
        return difficulte;
    }

    /**
     * Récupérer les iles de la grille
     *
     * @return les iles de la grille
     */
    public List<Ile> getIles() {
        return iles;
    }

    /**
     * Récupérer les ponts de la grille
     *
     * @return les ponts de la grille
     */
    public List<Pont> getPonts() {
        return ponts;
    }

    /**
     * Récupérer une île de la grille
     * @param x l'abscisse de l'île
     * @param y l'ordonnée de l'île
     * @return l'île de la grille
     */
    public Ile getIle(int x, int y) {
        for (Ile ile : iles) {
            if (ile.getX() == x && ile.getY() == y) {
                return ile;
            }
        }
        return null;
    }

    /**
     * Récupérer la dimension de la grille
     *
     * @return la dimension de la grille
     */
    public Dimension2D getDimension() {
        return dimension;
    }

    /**
     * Parcourt la grille dans son état actuel pour vérifier les techniques qui s'appliquent aux îles et donner un indice au joueur.
     * @return l'île qui peut bénéficier d'un indice.
     */
    public Ile aide(){
        Technique[] lTech = Technique.values();
        int fIndMin = lTech.length; //une liste des fonctions qui appliquent une technique
        //elles prennent en paramètre une île, et renvoient vrai si la technique s'applique à l'île

        Ile aideIle = null; //l'île sur laquelle on peut avancer à l'aide des techniques

        for(int i=0;i<this.dimension.getWidth();i++){ //parcours colonnes
            for(int j=0;j<this.dimension.getHeight();j++){ //parcours lignes
                if(this.getIle(i,j) != null && !(this.getIle(i,j).isComplete())) { //si l'île existe et n'est pas complète
                    for (int fInd=0; fInd<fIndMin; fInd++){
                        if(lTech[fInd].execute(this.getIle(i, j))){ //si la technique s'applique à l'île
                            aideIle = this.getIle(i,j);
                            fIndMin = fInd; //on ne vérifie que les techniques de plus bas niveau que celles trouvées
                        }
                    }
                }
            }
        }

        return aideIle;
    }
}
