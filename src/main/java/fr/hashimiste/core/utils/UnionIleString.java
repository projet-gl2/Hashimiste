package fr.hashimiste.core.utils;

import fr.hashimiste.core.jeu.Ile;
import fr.hashimiste.core.jeu.Technique;

/**
 * union d'une île et d'un string
 */
public class UnionIleString {
    private final Ile ileU;
    private final String strU;

    public UnionIleString(Ile i, String s){
        this.ileU = i;
        this.strU = s;
    }

    /**
     * Renvoie l'île de l'union
     * @return l'île de l'union
     */
    public Ile getIleU(){
        return this.ileU;
    }

    /**
     * Renvoie le string de l'union
     * @return le string de l'union
     */
    public String getStrU(){
        return this.strU;
    }


}
