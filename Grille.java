public class Grille {

    int c,l //nb colonnes et nb lignes
    public Ile aide(){
        int fIndMin = Technique.TECHNIQUE_LIST.getLength(); //une liste des fonctions qui appliquent une technique
                                                            //elles prennent en paramètre une île, et renvoient vrai si la technique s'applique à l'île

        Ile aideIle = null; //l'île sur laquelle on peut avancer à l'aide des techniques

        for(int i,i<this.c,i++){
            for(int j,j<this.l,j++){
                if(this.getIle(i,j) != null) {
                    for (int fInd, fInd<fInfMin, fInd++){
                        if(Technique.TECHNIQUE_LIST.get(fInd).apply(this.getIle(i, j))){
                            aideIle = this.getIle(i,j);
                            fInfMin = fInd; //on ne vérifie que les techniques de plus bas niveau que celles trouvées
                        }
                    }
                }
            }
        }

        return aideIle;
    }
}
