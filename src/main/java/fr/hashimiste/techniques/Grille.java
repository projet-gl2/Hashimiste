package fr.hashimiste.techniques;
/**
 * Une grille correspond à un niveau de jeu. Elle sont définies par les Îles qu'elles contiennent, 
 * et permettent d'effectuer différentes opérations comme afficher une aide de jeu.
 */
public class Grille {

    private int c; //nb colonnes
    private int l; //nb lignes

    /**
     * Renvoie le nombre de colonnes de la grille.
     * @return nombre de colonnes de la grille
     */
    public int getC(){
        return c;
    }

    /**
     * Renvoie le nombre de lignes de la grille.
     * @return nombre de lignes de la grille
     */
    public int getL(){
        return l;
    }

    /**
     * Renvoie l'île située à l'emplacement (i,j). Renvoie null s'il n'y a pas d'île à cet endroit.
     * @param i coordonnées sur les lignes
     * @param j coordonnées sur les colonnes
     * @return l'île située à (i,j)
     */
    public Ile getIle(int i, int j){
        //**A FAIRE**//
        return null;
    }

    /**
     * Parcourt la grille dans son état actuel pour vérifier les techniques qui s'appliquent aux îles et donner un indice au joueur.
     * @return l'île qui peut bénéficier d'un indice.
     */
    public Ile aide(){
        Technique[] lTech = Technique.values();
        int fIndMin = lTech.length; //une liste des fonctions qui appliquent une technique
                                                            //elles prennent en paramètre une île, et renvoient vrai si la technique s'applique à l'île

        Ile aideIle = null; //l'île sur laquelle on peut avancer à l'aide des techniques

        for(int i=0;i<this.c;i++){ //parcours colonnes
            for(int j=0;j<this.l;j++){ //parcours lignes
                if(this.getIle(i,j) != null && !(this.getIle(i,j).complete())) { //si l'île existe et n'est pas complète
                    for (int fInd=0; fInd<fIndMin; fInd++){
                        if(lTech[fInd].execute(this.getIle(i, j))){ //si la technique s'applique à l'île
                            aideIle = this.getIle(i,j);
                            fIndMin = fInd; //on ne vérifie que les techniques de plus bas niveau que celles trouvées
                        }
                    }
                }
            }
        }

        return aideIle;
    }
}
