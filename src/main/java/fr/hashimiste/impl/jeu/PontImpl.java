package fr.hashimiste.impl.jeu;

import fr.hashimiste.core.jeu.Case;
import fr.hashimiste.core.jeu.Direction;
import fr.hashimiste.core.jeu.Grille;
import fr.hashimiste.core.jeu.Pont;

public class PontImpl implements Pont {

    private final int x;
    private final int y;
    private final int n;
    private final Grille grille;
    private final Direction direction;

    public PontImpl(int x, int y, int n, Grille g, Direction d){
        this.x = x;
        this.y = y;
        this.n = n;
        this.grille = g;
        this.direction = d;
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
    public Case getVoisin(Direction d){
        Case c = null;
        switch (d){
            case NORD:
                c = (grille.getIle(x-1,y));
            case EST:
                c = (grille.getIle(x,y+1));
            case SUD:
                c = (grille.getIle(x+1,y));
            case OUEST:
                c = (grille.getIle(x,y-1));
        }
        return c;
    }

    @Override
    public int opParcours(Direction d){
        if(direction == Direction.NORD || direction == Direction.SUD)
            if(d == Direction.NORD || d == Direction.SUD)
                return (getVoisin(d).opParcours(d));
        if(direction == Direction.EST || direction == Direction.OUEST)
            if(d == Direction.EST || d == Direction.OUEST)
                return (getVoisin(d).opParcours(d));
        return -1;
    }

    @Override
    public int getN() {
        return this.n;
    }

    @Override
    public Direction getDirection(){
        return this.direction;
    }
}
