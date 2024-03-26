package fr.hashimiste.impl.jeu;

import fr.hashimiste.core.jeu.*;

public class CaseVideImpl implements CaseVide {

    private final int x;
    private final int y;
    private final Grille grille;

    public CaseVideImpl(int x, int y, Grille grille) {
        this.x = x;
        this.y = y;
        this.grille = grille;
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
    public Case getVoisinCase(Direction d){
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
    public Ile getVoisinIle(Direction d){
        return getVoisinCase(d).getVoisinIle(d);
    }

    @Override
    public int opParcours(Direction d) {
        switch (d){
            case NORD:
                if(x < 1)
                    return -1;
                break;
            case EST:
                if(y > grille.getDimension().getWidth()-1)
                    return -1;
                break;
            case SUD:
                if(x > grille.getDimension().getHeight()-1)
                    return -1;
                break;
            case OUEST:
                if(y < 1)
                    return -1;
                break;
        }
        return (getVoisinCase(d).opParcours(d));
    }
}
