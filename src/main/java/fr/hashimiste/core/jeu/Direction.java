package fr.hashimiste.core.jeu;

/**
 * L'énumération Direction définit les différentes directions possibles dans le jeu.
 */
public enum Direction {
    /**
     * Représente le haut.
     */
    NORD,

    /**
     * Représente la droite.
     */
    EST,

    /**
     * Représente le bas.
     */
    SUD,

    /**
     * Représente la gauche.
     */
    OUEST;

    /**
     * Renvoie la direction opposée.
     * @return la direction opposée.
     */
    public Direction oppose(){
        if(this == NORD)
                return SUD;
        if(this == SUD)
            return NORD;
        if(this == EST)
            return OUEST;
        return EST;
    }
}