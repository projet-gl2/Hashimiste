public class Ile {

    private int n; //valeur de l'île
    private int x; //coordonée en ligne
    private int y; //coordonée en colonnes
    private Grille grille; //grille dans laquelle se trouve l'île

    public int getN() {
        return n;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public Grille getGrille() {
        return grille;
    }

    /**
     * Vérifie si l'île est déjà complétée (elle a autant de ponts que son nombre).
     * @return vrai si l'île est complétée, faux sinon.
     */
    boolean complete(){
        return false;
    }

    /**
     * Compte le nombre de voisins possibles pour l'île, c'est-à-dire le nombre
     * d'îles différentes avec lesquelles il est possible de construire un pont.
     * @return nombre d'îles voisines.
     */
    int nbVoisin(){
        return 0;
    }

    /**
     * Compte le nombre de voisins de valeur "1" possibles pour l'île.
     * @return nombre d'îles voisines de valeur "1".
     */
    int nbVoisinAvec1(){
        return 0;
    }

    /**
     * Compte le nombre de ponts actuel de l'île.
     * @return nombre de ponts actuel.
     */
    int nbPont(){
        return 0;
    }

    /**
     * Compte le nombre de ponts max possibles de l'île par rapport à ses voisins.
     * @return nombre de ponts max possible.
     */
    int nbPontPossible(){
        return 0;
    }
}
