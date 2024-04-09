package fr.hashimiste.core.utils;

import fr.hashimiste.core.jeu.Ile;
import fr.hashimiste.core.jeu.Technique;

public class UnionIleString {
    private final Ile ileU;
    private final String strU;

    public UnionIleString(Ile i, String s){
        this.ileU = i;
        this.strU = s;
    }

    public Ile getIleU(){
        return this.ileU;
    }

    public String getStrU(){
        return this.strU;
    }


}
