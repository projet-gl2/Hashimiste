package fr.hashimiste.impl.jeu;

import fr.hashimiste.core.jeu.*;

public class PontImpl implements Pont {

    //todo java doc :)

    private final int x;
    private final int y;
    private final int n;
    private final Grille grille;
    private final Direction direction;
    private final Ile ile1;
    private final Ile ile2;

    public PontImpl(int x, int y, int n, Grille g, Direction d, Ile ile1, Ile ile2) {
        this.x = x;
        this.y = y;
        this.n = n;
        this.grille = g;
        this.direction = d;
        this.ile1 = ile1;
        this.ile2 = ile2;
    }

    @Override
    public int getX() {
        return this.x;
    }

    @Override
    public int getY() {
        return this.y;
    }

    @Override
    public Grille getGrille() {
        return this.grille;
    }

    @Override
    public Case getVoisinCase(Direction d) {
        Case c = null;
        switch (d) {
            case NORD:
                c = (grille.getIle(x - 1, y));
                break;
            case EST:
                c = (grille.getIle(x, y + 1));
                break;
            case SUD:
                c = (grille.getIle(x + 1, y));
                break;
            case OUEST:
                c = (grille.getIle(x, y - 1));
        }
        return c;
    }

    @Override
    public Ile getVoisinIle(Direction d) {
        return getVoisinCase(d).getVoisinIle(d);
    }

    @Override
    public int opParcours(Direction d) {
        if (direction == Direction.NORD || direction == Direction.SUD)
            if (d == Direction.NORD || d == Direction.SUD)
                return (getVoisinCase(d).opParcours(d));
        if (direction == Direction.EST || direction == Direction.OUEST)
            if (d == Direction.EST || d == Direction.OUEST)
                return (getVoisinCase(d).opParcours(d));
        return -1;
    }

    @Override
    public int getN() {
        return this.n;
    }

    @Override
    public Direction getDirection() {
        return this.direction;
    }

    public Ile getIle1() {
        return ile1;
    }

    public Ile getIle2() {
        return ile2;
    }

    public boolean estConnecte(Ile ile) {
        return ile.equals(ile1) || ile.equals(ile2);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Pont) {
            PontImpl p = (PontImpl) obj;
            return p.getX() == x && p.getY() == y && p.getN() == n && p.getGrille().getId() == grille.getId()
                    && ile1.equals(p.getIle1()) && ile2.equals(p.getIle2());
        }
        return false;
    }

    @Override
    public String toString() {
        return "PontImpl{" +
                "x=" + x +
                ", y=" + y +
                ", n=" + n +
                ", id(grille)=" + grille.getId() +
                ", direction=" + direction +
                '}';
    }
}
