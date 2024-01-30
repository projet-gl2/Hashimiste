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
    }),
    TECH_DEP_2("Si une île a un chiffre pair et son nombre de voisins correspond à n/2 alors elle est reliée 2 fois à chacun de ses voisins"
                       , o -> {

        if(o.complete) return false; //si l'île est déjà complète, cette technique est inutile.

        int n = o.getN();
        int nbV = o.nbVoisin(); //fonction qui calcule le nombre de voisin possible d'une île
        if(n == 8) return true;
        if(n == 6 && nbV == 3) return true;
        if(n == 4 && nbV == 2) return true;
        return false;
    }),
    TECH_BAS_1(" Si une île a un chiffre impair > 1 elle à au minimum n%2+1 voisins. Si elle a\n" +
            "exactement ce nombre de voisins alors elle est obligatoirement relié au moins une fois à\n" +
            "chacun de ses voisins"
            , o -> {
        if(o.complete) return false;

        int n = o.getN();
        int nbV = o.nbVoisin();
        int nbL = o.nbLien();
        if(n == 7 && nbL < 4) return true;
        if(n == 5 && nbV < 4 && nbL < 3) return true;
        if(n == 3 && nbV < 3 && nbL < 2) return true;
        if(n == 1 && nbV < 2 && nbL < 1) return true;
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
