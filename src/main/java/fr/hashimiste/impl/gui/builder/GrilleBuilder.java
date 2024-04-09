package fr.hashimiste.impl.gui.builder;

import fr.hashimiste.core.data.Stockage;
import fr.hashimiste.core.data.sql.Identifiable;
import fr.hashimiste.core.jeu.*;
import fr.hashimiste.core.utils.UnionIleTechnique;
import fr.hashimiste.core.jeu.Difficulte;
import fr.hashimiste.core.jeu.Grille;
import fr.hashimiste.core.jeu.Ile;
import fr.hashimiste.core.jeu.Sauvegarde;
import fr.hashimiste.core.jeu.Historique;
import fr.hashimiste.impl.jeu.Ponts;

import java.awt.*;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * Cette classe est utilisée pour construire une grille de jeu.
 * Elle implémente l'interface Grille et l'interface Identifiable.__UNSAFE__.
 */
public class GrilleBuilder implements Grille, Identifiable.UNSAFE {

    public static final UnsupportedOperationException UNSUPPORTED_OPERATION_EXCEPTION = new UnsupportedOperationException("Not implemented");

    private Dimension dimension = new Dimension(10, 10);
    private Case[][] iles = new Case[dimension.width][dimension.height];
    private Difficulte difficulte = Difficulte.FACILE;
    private int id = -1;
    private boolean aventure = false;

    /**
     * Constructeur de la classe GrilleBuilder.
     *
     * @param grille une instance de la classe Grille.
     */
    public GrilleBuilder(Grille grille) {
        this.dimension = grille.getDimension();
        this.iles = new Case[dimension.width][dimension.height];
        grille.getIles().forEach(ile -> iles[ile.getX()][ile.getY()] = ile);
        this.difficulte = grille.getDifficulte();
        this.aventure = grille.estAventure();
    }

    /**
     * Constructeur par défaut de la classe GrilleBuilder.
     */
    public GrilleBuilder() {
    }

    @Override
    public int getId() {
        if (id == -1) {
            throw new IllegalStateException("Id not set");
        }
        return id;
    }

    @Override
    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String getColonneId() {
        return "id_map";
    }

    @Override
    public Case getIle(int x, int y) {
        return iles[x][y];
    }

    @Override
    public List<Case> getIles() {
        return Arrays.asList(iles).stream().flatMap(Arrays::stream).filter(Objects::nonNull).collect(Collectors.toList());
    }

    @Override
    public Dimension getDimension() {
        return dimension;
    }

    @Override
    public boolean estAventure() {
        return aventure;
    }

    public void setAventure(boolean aventure) {
        this.aventure = aventure;
    }

    /**
     * Cette méthode est utilisée pour définir la dimension de la grille.
     *
     * @param dimension la nouvelle dimension de la grille.
     * @return l'instance de GrilleBuilder.
     */
    public GrilleBuilder setDimension(Dimension dimension) {
        this.dimension = dimension;
        this.iles = new Ile[dimension.width][dimension.height];
        return this;
    }

    @Override
    public List<Sauvegarde> getSauvegardes(Stockage stockage) {
        throw UNSUPPORTED_OPERATION_EXCEPTION;
    }

    @Override
    public void rafraichirSauvegardes(Stockage stockage) {
        throw UNSUPPORTED_OPERATION_EXCEPTION;
    }

    @Override
    public boolean verification(Historique historique) {
        throw UNSUPPORTED_OPERATION_EXCEPTION;
    }

    @Override
    public String aide() {
        throw UNSUPPORTED_OPERATION_EXCEPTION;
    }

    @Override
    public UnionIleTechnique chercherIle() {
        throw UNSUPPORTED_OPERATION_EXCEPTION;
    }

    @Override
    public Difficulte getDifficulte() {
        return difficulte;
    }

    @Override
    public List<Ponts> historiqueVersPonts(List<Ponts> res, List<Ponts> exclu, Historique histo) {
        throw UNSUPPORTED_OPERATION_EXCEPTION;
    }

    @Override
    public List<Ile> explorer(List<Ile> tmp, Ile ile,List<Ponts> ponts) {
        throw UNSUPPORTED_OPERATION_EXCEPTION;
    }

    /**
     * Cette méthode est utilisée pour définir la difficulté de la grille.
     *
     * @param difficulte la nouvelle difficulté de la grille.
     * @return l'instance de GrilleBuilder.
     */
    public GrilleBuilder setDifficulte(Difficulte difficulte) {
        this.difficulte = difficulte;
        return this;
    }

    /**
     * Cette méthode est utilisée pour ajouter une île à la grille.
     *
     * @param ile l'île à ajouter.
     * @return l'instance de GrilleBuilder.
     */
    public GrilleBuilder ajouterIle(Ile ile) {
        this.iles[ile.getX()][ile.getY()] = ile;
        return this;
    }

    /**
     * Cette méthode est utilisée pour réinitialiser la grille.
     * Elle efface toutes les îles de la grille.
     */
    public void clear() {
        this.iles = new Ile[dimension.width][dimension.height];
    }
}