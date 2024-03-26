package fr.hashimiste.impl.jeu;

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
    public int getN() {
        return this.n;
    }

    @Override
    public Direction getDirection(){
        return this.direction;
    }
}
