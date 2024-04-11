package fr.hashimiste.core.utils;

/**
 * Représente une union de deux objets.
 * @param <G> objet gauche
 * @param <D> objet droit
 */
public class Union<G, D> {
    private final G gauche;
    private final D droite;

    /**
     * Crée une union.
     * @param gauche l'objet gauche
     * @param droite l'objet droit
     */
    public Union(G gauche, D droite) {
        this.gauche = gauche;
        this.droite = droite;
    }

    /**
     * Renvoie l'objet gauche.
     * @return l'objet gauche
     */
    public G getGauche() {
        return gauche;
    }

    /**
     * Renvoie l'objet droit.
     * @return l'objet droit
     */
    public D getDroite() {
        return droite;
    }
}
