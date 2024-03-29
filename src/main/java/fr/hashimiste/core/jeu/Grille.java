package fr.hashimiste.core.jeu;

import fr.hashimiste.core.data.Stockage;
import fr.hashimiste.core.data.sql.Identifiable;

import java.awt.*;
import java.util.List;

/**
 * L'interface Grille représente une grille de jeu.
 * Elle est identifiable et contient des méthodes pour gérer les îles et les sauvegardes.
 */
public interface Grille extends Identifiable {
    /**
     * Récupère une île à une position spécifique.
     * @param x la position x de l'île.
     * @param y la position y de l'île.
     * @return l'île à la position spécifiée.
     */
    Ile getIle(int x, int y);

    /**
     * Récupère la liste de toutes les îles.
     * @return la liste de toutes les îles.
     */
    List<Ile> getIles();

    /**
     * Récupère les dimensions de la grille.
     * @return les dimensions de la grille.
     */
    Dimension getDimension();

    /**
     * Récupère la liste de toutes les sauvegardes.
     * @param stockage le système de stockage des données.
     * @return la liste de toutes les sauvegardes.
     */
    List<Sauvegarde> getSauvegardes(Stockage stockage);

    /**
     * Rafraîchit la liste des sauvegardes.
     * @param stockage le système de stockage des données.
     */
    void rafraichirSauvegardes(Stockage stockage);

    /**
     * Vérifie si la grille est correcte.
     * @return vrai si la grille est correcte, faux sinon.
     */
    boolean verification();

    /**
     * Fournit une aide pour résoudre la grille.
     * @return une île qui peut aider à résoudre la grille.
     */
    Ile aide();

    /**
     * Récupère la difficulté de la grille.
     * @return la difficulté de la grille.
     */
    Difficulte getDifficulte();
}