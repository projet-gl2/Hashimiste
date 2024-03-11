package fr.hashimiste.core.jeu;

import fr.hashimiste.core.data.sql.Identifiable;

import java.util.function.Predicate;

/**
 * L'interface Ile représente une île dans le jeu.
 * Elle est identifiable et contient des méthodes pour obtenir des informations sur l'île et la grille de jeu.
 */
public interface Ile extends Identifiable {
    /**
     * Récupère le nombre N de l'île.
     *
     * @return le nombre N de l'île.
     */
    int getN();

    /**
     * Récupère la position x de l'île.
     *
     * @return la position x de l'île.
     */
    int getX();

    /**
     * Récupère la position y de l'île.
     *
     * @return la position y de l'île.
     */
    int getY();

    /**
     * Récupère le nombre de voisins de l'île.
     *
     * @return le nombre de voisins de l'île.
     */
    int getNbVoisin();

    /**
     * Récupère le nombre de voisins de l'île qui satisfont un certain filtre.
     *
     * @param filtre le filtre à appliquer.
     * @return le nombre de voisins de l'île qui satisfont le filtre.
     */
    int getNbVoisinFiltre(Predicate<Ile> filtre);

    /**
     * Récupère le nombre de ponts de l'île.
     *
     * @return le nombre de ponts de l'île.
     */
    int getNbPont();

    /**
     * Récupère le nombre de ponts possibles de l'île.
     *
     * @return le nombre de ponts possibles de l'île.
     */
    int getNbPontPossible();

    /**
     * Récupère le nombre de ponts de l'île dans une certaine direction.
     *
     * @param direction la direction à vérifier.
     * @return le nombre de ponts de l'île dans la direction spécifiée.
     */
    int getNbPontsDirections(Direction direction);

    /**
     * Récupère la valeur de l'île dans une certaine direction.
     *
     * @param direction la direction à vérifier.
     * @return la valeur de l'île dans la direction spécifiée.
     */
    int getValeurIleDirection(Direction direction);

    /**
     * Récupère la grille de jeu de l'île.
     *
     * @return la grille de jeu de l'île.
     */
    Grille getGrille();
}