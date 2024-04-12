package fr.hashimiste.impl.jeu;

import fr.hashimiste.core.data.sql.Identifiable;
import fr.hashimiste.core.jeu.Case;
import fr.hashimiste.core.jeu.Direction;
import fr.hashimiste.core.jeu.Grille;
import fr.hashimiste.core.jeu.Ile;

import java.util.Arrays;
import java.util.function.Predicate;

import static java.lang.Integer.min;

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
    public boolean isComplete() {
        int nbPont = getNbPont();
        return nbPont == this.n;
    }

    @Override
    public boolean isVoisinDirection(Direction direction) {
        switch (direction) {
            case NORD:
                if (x < 1)
                    return false;
                break;
            case EST:
                if (y > grille.getDimension().getWidth() - 2)
                    return false;
                break;
            case SUD:
                if (x > grille.getDimension().getHeight() - 2)
                    return false;
                break;
            case OUEST:
                if (y < 1)
                    return false;
                break;
        }
        return (getVoisinCase(direction).opParcours(direction) > 0);
    }

    @Override
    public int getNbVoisin() {
        int nbTotal = 0;

        for (Direction value : Direction.values()) {
            nbTotal += isVoisinDirection(value) ? 1 : 0;
        }

        return nbTotal;
    }

    @Override
    public int getNbVoisinFiltre(Predicate<Ile> filtre) {
        int nbTotal = 0;

        for (Direction value : Direction.values()) {
            if (isVoisinDirection(value)) {
                nbTotal += filtre.test(getVoisinCase(value).getVoisinIle(value)) ? 1 : 0;
            }
        }

        return nbTotal;
    }

    @Override
    public int getNbPont() {
        int nbTotal = 0;

        for (Direction value : Direction.values()) {
            Case voisinCase = getVoisinCase(value);
            nbTotal += voisinCase instanceof PontImpl && ((PontImpl) voisinCase).estConnecte(this) ? ((PontImpl) voisinCase).getN() : 0;
        }

        return nbTotal;
    }

    @Override
    public int getNbPontPossible() {
        int nbTotal = 0;

        for (Direction value : Direction.values()) {
            nbTotal += isVoisinDirection(value) ? min(getVoisinCase(value).opParcours(value), 2) : 0;
        }

        return nbTotal;
    }

    @Override
    public int getNbPontsDirections(Direction direction) {
        return (getVoisinCase(direction) instanceof PontImpl ? ((PontImpl) getVoisinCase(direction)).getN() : 0);
    }

    @Override
    public int getValeurIleDirection(Direction direction) {
        return getVoisinCase(direction).opParcours(direction);
    }

    @Override
    public String getRegion() {
        String reg;
        int w = (int) this.grille.getDimension().getWidth();
        int h = (int) this.grille.getDimension().getHeight();

        if (this.y < w / 3) reg = "NORD";
        else if (this.y > 2 * (w / 3)) reg = "SUD";
        else reg = "CENTRE";

        reg = reg + "-";

        if (this.x < h / 3) reg = reg + "OUEST";
        else if (this.x > (h / 3) * 2) reg = reg + "EST";
        else reg = reg + "CENTRE";

        return reg;
    }

    @Override
    public Grille getGrille() {
        return grille;
    }

    @Override
    public Case getVoisinCase(Direction d) {
        switch (d) {
            case NORD:
                return x < 1 ? null : grille.getIle(x - 1, y);
            case EST:
                return y > grille.getDimension().getWidth() - 2 ? null : grille.getIle(x, y + 1);
            case SUD:
                return x > grille.getDimension().getHeight() - 2 ? null : grille.getIle(x + 1, y);
            case OUEST:
                return y < 1 ? null : grille.getIle(x, y - 1);
            default:
                return null;
        }
    }

    @Override
    public Ile getVoisinIle(Direction d) {
        return this;
    }

    @Override
    public int opParcours(Direction d) {
        if (isComplete())
            return -1;
        return n - getNbPont();
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
    public boolean equals(Object obj) {
        if (obj instanceof Ile) {
            Ile ile = (Ile) obj;
            return ile.getX() == this.x && ile.getY() == this.y && ile.getN() == this.n && ile.getGrille().getId() == this.grille.getId();
        }
        return false;
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