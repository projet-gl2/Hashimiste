package fr.hashimiste.core.jeu;


/**
 * L'interface Case représente une case dans la matrice d'une grille.
 * Elle contient des méthodes pour obtenir des informations sur la position de la case et la grille de jeu.
 */
public interface Case {

    /**
     * Récupère la position x de la case sur laquelle se trouve l'objet.
     *
     * @return la position x de la case sur laquelle se trouve l'objet.
     */
    int getX();

    /**
     * Récupère la position y de la case sur laquelle se trouve l'objet.
     *
     * @return la position y de la case sur laquelle se trouve l'objet.
     */
    int getY();

    /**
     * Récupère la grille de jeu de la case.
     *
     * @return la grille de jeu de la case.
     */
    Grille getGrille();

    /**
     * Indique si on peut traverser la case dans la direction donnée.
     *
     * @return -1 si on ne peut pas la traverser (pont perpendiculaire, ile complète, bordure), 0 si on peut continuer (pont parrallèle, case vide, ne devrait pas arriver), et un nombre entre 1 et 8 (île non complétée)
     */
    int opParcours(Direction d);
}
