package fr.hashimiste.maps;

import fr.hashimiste.Difficulte;

import java.util.ArrayList;
import java.util.List;

/**
 * La classe Grille représente une grille dans un jeu ou une tâche.
 * Elle contient des informations sur le niveau de difficulté de la grille, les îles (Ile) dans la grille et les ponts (Pont) dans la grille.
 * Elle fournit des méthodes pour récupérer des informations sur la grille, ses îles et ses ponts.
 */
public class Grille {
    private final Difficulte difficulte;
    private final List<Ile> iles;
    private final List<Pont> ponts;

    /**
     * Créer une grille
     *
     * @param difficulte la difficulté de la grille
     * @param iles       les iles de la grille
     * @param ponts      les ponts de la grille
     */
    public Grille(Difficulte difficulte, List<Ile> iles, List<Pont> ponts) {
        this.difficulte = difficulte;
        this.iles = iles;
        this.ponts = ponts;
    }

    /**
     * Créer une grille
     *
     * @param difficulte la difficulté de la grille
     * @param iles      les iles de la grille
     */
    public Grille(Difficulte difficulte, List<Ile> iles) {
        this(difficulte, iles, new ArrayList<>());
    }

    /**
     * Créer une grille
     *
     * @param difficulte la difficulté de la grille
     */
    public Grille(Difficulte difficulte) {
        this(difficulte, new ArrayList<>());
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
}
