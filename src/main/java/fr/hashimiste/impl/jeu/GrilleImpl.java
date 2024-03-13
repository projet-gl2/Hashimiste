package fr.hashimiste.impl.jeu;

import fr.hashimiste.core.data.Stockage;
import fr.hashimiste.core.data.sql.Identifiable;
import fr.hashimiste.core.jeu.Difficulte;
import fr.hashimiste.core.jeu.Grille;
import fr.hashimiste.core.jeu.Ile;
import fr.hashimiste.core.jeu.Sauvegarde;
import fr.hashimiste.core.jeu.Technique;

import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * Cette classe représente une grille de jeu.
 * Elle implémente les interfaces Grille et Identifiable.__UNSAFE__.
 */
public class GrilleImpl implements Grille, Identifiable.UNSAFE {
    private final Dimension dimension;
    private final Ile[][] iles;
    private final Difficulte difficulte;
    private List<Sauvegarde> sauvegardes;

    private int id;

    /**
     * Constructeur de la classe GrilleImpl.
     *
     * @param dimension  la dimension de la grille.
     * @param difficulte la difficulté de la grille.
     */
    public GrilleImpl(Dimension dimension, Difficulte difficulte) {
        this(-1, dimension, difficulte, new ArrayList<>());
    }

    /**
     * Constructeur de la classe GrilleImpl.
     *
     * @param id         l'identifiant de la grille.
     * @param dimension  la dimension de la grille.
     * @param difficulte la difficulté de la grille.
     */
    public GrilleImpl(int id, Dimension dimension, Difficulte difficulte) {
        this(id, dimension, difficulte, null);
    }

    /**
     * Constructeur de la classe GrilleImpl.
     *
     * @param id          l'identifiant de la grille.
     * @param dimension   la dimension de la grille.
     * @param difficulte  la difficulté de la grille.
     * @param sauvegardes la liste des sauvegardes de la grille.
     */
    public GrilleImpl(int id, Dimension dimension, Difficulte difficulte, List<Sauvegarde> sauvegardes) {
        this.id = id;
        this.dimension = dimension;
        this.iles = new Ile[dimension.width][dimension.height];
        this.difficulte = difficulte;
        this.sauvegardes = sauvegardes;
    }

    /**
     * Cette méthode est utilisée pour poser une île sur la grille.
     *
     * @param ile l'île à poser.
     */
    public void poserIle(Ile ile) {
        iles[ile.getX()][ile.getY()] = ile;
    }

    /**
     * Cette méthode est utilisée pour poser un pont entre deux îles.
     *
     * @param ile1 la première île.
     * @param ile2 la deuxième île.
     * @param n    le nombre de ponts à poser.
     */
    public void poserPont(Ile ile1, Ile ile2, int n) {
//        iles[x1][y1].poserPont(iles[x2][y2], n); TODO
    }

    @Override
    public Ile getIle(int x, int y) {
        return iles[x][y];
    }

    @Override
    public List<Ile> getIles() {
        return Arrays.asList(iles).stream().flatMap(Arrays::stream).filter(Objects::nonNull).collect(Collectors.toList());
    }

    @Override
    public Dimension getDimension() {
        return dimension;
    }

    @Override
    public Difficulte getDifficulte() {
        return difficulte;
    }

    @Override
    public List<Sauvegarde> getSauvegardes(Stockage stockage) {
        if (sauvegardes == null) {
            rafraichirSauvegardes(stockage);
        }
        return sauvegardes;
    }

    @Override
    public void rafraichirSauvegardes(Stockage stockage) {
        sauvegardes = stockage.charger(Sauvegarde.class, "WHERE reference IN (SELECT date FROM historique WHERE id_map = " + id + ")");
    }

    @Override
    public boolean verification() {
        return false;
    }

    @Override
    public Ile aide() {

        if (!this.verification()) return null;
        else {
            Technique[] lTech = Technique.values();
            int fIndMin = lTech.length; //une liste des fonctions qui appliquent une technique
            //elles prennent en paramètre une île, et renvoient vrai si la technique s'applique à l'île

            Ile aideIle = null; //l'île sur laquelle on peut avancer à l'aide des techniques

            for (int i = 0; i < this.dimension.getWidth(); i++) { //parcours colonnes
                for (int j = 0; j < this.dimension.getHeight(); j++) { //parcours lignes
                    if (this.getIle(i, j) != null && !(this.getIle(i, j).isComplete())) { //si l'île existe et n'est pas complète
                        for (int fInd = 0; fInd < fIndMin; fInd++) { //parcours techniques
                            if (lTech[fInd].test(this.getIle(i, j))) { //si la technique s'applique à l'île
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

    @Override
    public int getId() {
        if (id == -1) {
            throw new IllegalStateException("L'id n'a pas été défini, impossible de le récupérer.");
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
    public String toString() {
        return "Grille{" +
                "id=" + id +
                ", dimension=" + dimension +
                ", difficulte=" + difficulte +
                ", iles=" + Arrays.toString(iles) +
                '}';
    }
}