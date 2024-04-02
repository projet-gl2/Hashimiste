package fr.hashimiste.core.utils;

import fr.hashimiste.core.jeu.Ile;
import fr.hashimiste.core.jeu.Technique;

public class UnionIleTechnique {
    private final Ile ileU;
    private final Technique techU;

    public UnionIleTechnique(Ile i, Technique t){
        this.ileU = i;
        this.techU = t;
    }

    public Ile getIleU(){
        return this.ileU;
    }

    public Technique getTechU(){
        return this.techU;
    }


}
