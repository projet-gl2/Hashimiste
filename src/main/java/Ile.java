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
     * Par exemple, s'il il a trois voisins, affichant les nombres 4, 2 et 1,
     * alors il est possible de faire 5 ponts au maximum. (deux pour 4 et 2, un pour 1)
     * @return nombre de ponts max possible.
     */
    int nbPontPossible(){
        return 0;
    }

    /**
     * Vérifie ce qu'il y a en face de l'île dans la direction donnée.
     * @param d Direction à vérifier peut être NORD, EST, SUD ou OUEST
     * @return -1 s'il n'y aucune île accessible,
     * 0 s'il y a une île sans pont,
     * 1 s'il y a une île reliée par un pont,
     * 2 s'il y a une île réliée par deux ponts
     */
    int nbPontsDirection(Direction d){
        return 0;
    }
}
