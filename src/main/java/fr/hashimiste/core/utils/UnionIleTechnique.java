package fr.hashimiste.core.utils;

import fr.hashimiste.core.jeu.Ile;
import fr.hashimiste.core.jeu.Technique;

/**
 * Union d'une île et d'une technique
 */
public class UnionIleTechnique {
    private final Ile ileU;
    private final Technique techU;

    public UnionIleTechnique(Ile i, Technique t){
        this.ileU = i;
        this.techU = t;
    }

    /**
     * Renvoie l'île de l'union
     * @return l'île de l'union
     */
    public Ile getIleU(){
        return this.ileU;
    }

    /**
     * Renvoie la technique de l'union
     * @return le technique de l'union
     */
    public Technique getTechU(){
        return this.techU;
    }


}
