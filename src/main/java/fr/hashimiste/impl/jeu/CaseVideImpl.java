package fr.hashimiste.impl.jeu;

import fr.hashimiste.core.jeu.CaseVide;
import fr.hashimiste.core.jeu.Direction;
import fr.hashimiste.core.jeu.Grille;

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
    public int opParcours(Direction d) {
        return 0;
    }
}
