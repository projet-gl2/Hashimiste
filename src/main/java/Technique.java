import java.util.function.Function;

/**
 * Une technique correspond à une description et à un algoritmhe qui vérifie si
 * une île peut bénéficier de la technique dans son état actuel (Ile -> Boolean).
 */
public enum Technique {
    TECH_DEP_1("Si une île a un chiffre pair et son nombre de voisins correspond à n/2 alors elle est reliée 2 fois à chacun de ses voisins"
            , o -> {

        if(o.complete) return false; //si l'île est déjà complète, cette technique est inutile.

        int n = o.getN();
        if(n == 8) return true;

        int x = o.getX();
        int y = o.getY();
        int l = o.getGrille().getL();
        int c = o.getGrille().getC();
        if(n == 6){
            if(x == 0 || y == 0 || x == l-1 || y == c-1) return true;
        }
        if(n == 4){
            if(x == 0){
                if(y == 0) return true;
                if(y == c-1) return true;
            }
            if(x == l-1){
                if(y == 0) return true;
                if(y == c-1) return true;
            }
        }
        return false;
    });
    TECH_DEP_2("Si une île a un chiffre pair et son nombre de voisins correspond à n/2 alors elle est reliée 2 fois à chacun de ses voisins"
                            , o -> {

        if(o.complete) return false; //si l'île est déjà complète, cette technique est inutile.

        int n = o.getN();
        int nbV = o.nbVoisin(); //fonction qui calcule le nombre de voisin possible d'une île
        if(n == 8) return true;
        if(n == 6 && nbV == 3) return true;
        if(n == 4 && nbV == 2) return true;
        return false;
    });

    private final String description;
    private final Function<Object, Boolean> fonction;

    Technique(String description, Function<Object, Boolean> fonction) {
        this.description = description;
        this.fonction = fonction;
    }

    public String getDescription() {
        return description;
    }

    public Function<Object, Boolean> getFonction() {
        return fonction;
    }
}
