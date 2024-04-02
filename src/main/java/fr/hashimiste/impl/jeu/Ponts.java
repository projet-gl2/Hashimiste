package fr.hashimiste.impl.jeu;

import fr.hashimiste.core.jeu.Ile;

/**
 * Cette classe représente un pont avec 2 iles .
 *
 */
public class Ponts {
    private Ile ile1;
    private Ile ile2;


    public Ponts(Ile ile1, Ile ile2) {
        this.ile1 = ile1;
        this.ile2 = ile2;
    }

    public Ile getIle1() {
        return ile1;
    }

    public Ile getIle2() {
        return ile2;
    }
}
