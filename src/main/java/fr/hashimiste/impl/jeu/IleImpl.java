package fr.hashimiste.impl.jeu;

import fr.hashimiste.core.data.sql.Identifiable;
import fr.hashimiste.core.jeu.Direction;
import fr.hashimiste.core.jeu.Grille;
import fr.hashimiste.core.jeu.Ile;

import java.util.Arrays;
import java.util.function.Predicate;

/**
 * Cette classe représente une île dans le jeu.
 * Elle implémente les interfaces Ile et Identifiable.__UNSAFE__.
 */
public class IleImpl implements Ile, Identifiable.UNSAFE {

    private final int x;
    private final int y;
    private final int n;
    private final Grille grille;
    private int id;

    /**
     * Constructeur de la classe IleImpl.
     *
     * @param x      la position x de l'île.
     * @param y      la position y de l'île.
     * @param n      le nombre de ponts de l'île.
     * @param grille la grille contenant l'île.
     */
    public IleImpl(int x, int y, int n, Grille grille) {
        this.x = x;
        this.y = y;
        this.n = n;
        this.grille = grille;
    }

    @Override
    public int getN() {
        return n;
    }

    @Override
    public int getX() {
        if (Arrays.stream(Thread.currentThread().getStackTrace()).anyMatch(e -> e.toString().contains("Cell"))) {
            return 0;
        }
        return x;
    }

    @Override
    public int getY() {
        if (Arrays.stream(Thread.currentThread().getStackTrace()).anyMatch(e -> e.toString().contains("Cell"))) {
            return 0;
        }
        return y;
    }

    @Override
    public int getNbVoisin() {
        return 0;
    }

    @Override
    public int getNbVoisinFiltre(Predicate<Ile> filtre) {
        return 0;
    }

    @Override
    public int getNbPont() {
        return 0;
    }

    @Override
    public int getNbPontPossible() {
        return 0;
    }

    @Override
    public int getNbPontsDirections(Direction direction) {
        return 0;
    }

    @Override
    public int getValeurIleDirection(Direction direction) {
        return 0;
    }

    @Override
    public Grille getGrille() {
        return grille;
    }

    @Override
    public String toString() {
        return "Ile{" +
                "id=" + id +
                ", x=" + x +
                ", y=" + y +
                ", n=" + n +
                ", grille(id)=" + (grille == null ? "null" : grille.getId()) +
                '}';
    }

    @Override
    public int getId() {
        return id;
    }

    @Override
    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String getColonneId() {
        return "id_ile";
    }
}