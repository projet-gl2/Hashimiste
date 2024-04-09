package fr.hashimiste.core.jeu;

import fr.hashimiste.core.data.Stockage;
import fr.hashimiste.core.data.sql.Identifiable;
import fr.hashimiste.core.utils.UnionIleTechnique;

import fr.hashimiste.impl.jeu.Ponts;

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
    Case getIle(int x, int y);

    /**
     * Récupère la liste de toutes les îles.
     * @return la liste de toutes les îles.
     */
    List<Case> getIles();

    /**
     * Récupère les dimensions de la grille.
     * @return les dimensions de la grille.
     */
    Dimension getDimension();

    /**
     * Si une île doit figurer dans l'écran aventure
     * @return vrai si l'île doit figurer dans l'écran aventure, faux sinon.
     */
    boolean estAventure();

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
    boolean verification(Historique historique);

    /**
     * Fournit une aide pour résoudre la grille.
     * @return Un message d'aide pour résoudre la grille.
     */
    String aide();

    /**
     * Parcourt la grille à la recherche de l'île sur laquelle on peut appliquer une technique.
     * @return une Ile avec la technique qui peut s'y appliquer.
     */
    UnionIleTechnique chercherIle();

    /**
     * Récupère la difficulté de la grille.
     * @return la difficulté de la grille.
     */
    Difficulte getDifficulte();

    /**
     * Parcourt un historique pour créer une liste de ponts
     * @return une Liste de ponts.
     */
    List<Ponts> historiqueVersPonts(List<Ponts> res,List<Ponts> exclu,Historique histo);

    /**
     * Parcourt une grille en partant d'une ile 
     * @return une Liste d'Ile parcourue.
     */
    List<Ile> explorer(List<Ile> tmp, Ile ile,List<Ponts> ponts);
}