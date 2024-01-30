public class Grille {

    int c,l; //nb colonnes et nb lignes

    /**
     * Renvoie l'île située à l'emplacement (i,j). Renvoie null s'il n'y a pas d'île à cet endroit.
     * @param i coordonnées sur les lignes
     * @param j coordonnées sur les colonnes
     * @return l'île située à (i,j)
     */
    public Ile getIle(int i, int j){
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

        for(int i;i<this.c;i++){
            for(int j;j<this.l;j++){
                if(this.getIle(i,j) != null) {
                    for (int fInd; fInd<fIndMin; fInd++){
                        if(lTech[fInd].getFonction().apply(this.getIle(i, j))){ //si la technique s'applique à l'île
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
