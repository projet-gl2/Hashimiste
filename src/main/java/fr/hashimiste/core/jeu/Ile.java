package fr.hashimiste.core.jeu;

import fr.hashimiste.core.data.sql.Identifiable;

import java.util.function.Predicate;

/**
 * L'interface Ile représente une île dans le jeu.
 * Elle est identifiable et contient des méthodes pour obtenir des informations sur l'île et la grille de jeu.
 */
public interface Ile extends Identifiable, Case {
    /**
     * Récupère le nombre N de l'île.
     *
     * @return le nombre N de l'île.
     */
    int getN();

    /**
     * Vérifie si l'île est complète (nb de ponts égal au nombre N de l'île).
     *
     * @return true si l'île est complète, false sinon.
     */
    boolean isComplete();

    /**
     * Vérifie si l'île a un voisin accessible dans la direction donnée.
     *
     * @param direction la direction à vérifier.
     * @return true si l'île a un voisin dans la direction donnée, false sinon.
     */
    boolean isVoisinDirection(Direction direction);

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
     * Récupère la région de la grille dans laquelle se trouve l'île.
     *
     * @return Un string contenant la région (Nord, Sud...)
     */
    String getRegion();
}