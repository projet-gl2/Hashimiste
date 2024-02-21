package fr.hashimiste.maps;
import fr.hashimiste.techniques.Technique;

import java.awt.geom.Dimension2D;
import java.util.List;

/**
 * La classe Grille représente une grille dans un jeu ou une tâche.
 * Elle contient des informations sur le niveau de difficulté de la grille, les îles (Ile) dans la grille et les ponts (Pont) dans la grille.
 * Elle fournit des méthodes pour récupérer des informations sur la grille, ses îles et ses ponts.
 */
public class Grille {
    private final Dimension2D dimension;
    private final Difficulte difficulte;
    private final Case[][] matrice;
    private final List<Ile> iles;
    private final List<Pont> ponts;
    private final Map map;

    /**
     * Créer une grille
     *
     * @param dimension  la dimension de la grille
     * @param idMap      l'id de la map
     * @param difficulte la difficulté de la grille
     */
    public Grille(Dimension2D dimension, int idMap, Difficulte difficulte) {
        this.dimension = dimension;
        this.difficulte = difficulte;
        this.map = Map.chargerMap(idMap);
        this.matrice = new Case[(int) dimension.getWidth()][(int) dimension.getHeight()];
        this.importerIles();
        this.importerPonts();
        this.iles = map.iles;
        this.ponts = map.ponts;
    }

    private void importerIles() {
        for (Ile ile : map.iles) {
            this.matrice[ile.getX()][ile.getY()] = new CaseIle(ile.getX(), ile.getY(), ile.getNbPont());
        }
    }

    private void importerPonts() {
        for (Pont pont : map.ponts) {
            Ile ile1 = pont.getIle1();
            Ile ile2 = pont.getIle2();
            if (pont.getIle1().getX() == pont.getIle2().getX()) {
                if (pont.getIle1().getY() - pont.getIle2().getY() < 0) {
                    ile2 = pont.getIle1();
                    ile1 = pont.getIle2();
                }
                for (int i = ile1.getY() + 1; i < ile2.getY(); i++) {
                    this.matrice[ile1.getX()][i] = new CasePont(ile1.getX(), i, pont.getN());
                }
            }
            else {
                if (pont.getIle1().getX() - pont.getIle2().getX() < 0) {
                    ile2 = pont.getIle1();
                    ile1 = pont.getIle2();
                }
                for (int i = ile1.getX() + 1; i < ile2.getX(); i++) {
                    this.matrice[i][ile1.getY()] = new CasePont(i, ile1.getY(), pont.getN());
                }
            }
        }
    }

    private void caseVide() {
        for (int i = 0; i < dimension.getHeight(); i++) {
            for (int j = 0; j < dimension.getWidth(); j++) {
                if (this.matrice[i][j] == null) {
                    this.matrice[i][j] = new CaseVide(i, j);
                }
            }
        }
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
     * Récupérer la longeur de la grille
     *
     * @return la longeur de la grille
     */
    public int getL(){
        // TODO
        return 0;
    }

    /**
     * Récupérer la hauteur de la grille
     *
     * @return la hauteur de la grille
     */
    public int getC(){
        // TODO
        return 0;
    }

    /**
     * Vérifie s'il n'y a aucune erreur dans la grille, c'est-à-dire, s'il n'y a aucun pont en trop par rapport à la solution.
     * @return true si la grille ne contient pas d'erreur, false sinon
     */
    public Boolean verification(){
        // TODO
        return true;
    }

    /**
     * Parcourt la grille dans son état actuel pour vérifier les techniques qui s'appliquent aux îles et donner un indice au joueur.
     * Si la grille contient une erreur, l'aide l'indiquera en renvoyant une île vide.
     * @return l'île qui peut bénéficier d'un indice, ou alors null si la grille contient déjà une erreur
     */
    public Ile aide(){
        if (!this.verification()) return null;
        else{
            Technique[] lTech = Technique.values();
            int fIndMin = lTech.length; //une liste des fonctions qui appliquent une technique
            //elles prennent en paramètre une île, et renvoient vrai si la technique s'applique à l'île

            Ile aideIle = null; //l'île sur laquelle on peut avancer à l'aide des techniques

            for (int i = 0; i < this.dimension.getWidth(); i++) { //parcours colonnes
                for (int j = 0; j < this.dimension.getHeight(); j++) { //parcours lignes
                    if (this.getIle(i, j) != null && !(this.getIle(i, j).isComplete())) { //si l'île existe et n'est pas complète
                        for (int fInd = 0; fInd < fIndMin; fInd++) { //parcours techniques
                            if (lTech[fInd].execute(this.getIle(i, j))) { //si la technique s'applique à l'île
                                aideIle = this.getIle(i, j);
                                fIndMin = fInd; //on ne vérifie que les techniques de plus bas niveau que celles trouvées précédemments
                            }
                        }
                    }
                }
            }

            return aideIle;
        }
    }
}
