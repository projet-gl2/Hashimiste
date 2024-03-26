package fr.hashimiste.core.jeu;


/**
 * L'interface Pont représente une pont dans la matrice d'une grille.
 * Elle contient des méthodes pour obtenir des informations sur le pont et la grille de jeu.
 */
public interface Pont extends Case {
    /**
     * Récupère le nombre N de ponts sur la case.
     *
     * @return le nombre N de ponts sur la case.
     */
    int getN();

    /**
     * Récupère la direction du pont. (NORD ou SUD, et EST ou OUEST peuvent être utilisés interchangablement)
     *
     * @return la direction du pont
     */
    Direction getDirection();
}
