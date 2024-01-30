import java.util.function.Function;

/**
 * Une technique correspond à une description et à un algoritmhe qui vérifie si
 * une île peut bénéficier de la technique dans son état actuel (Ile -> Boolean).
 */
public enum Technique {
    TECH_DEP_1("Si une île a un chiffre pair et son nombre de voisins correspond à n/2 alors elle est reliée 2 fois à chacun de ses voisins"
            , o -> {

        if(o.complete()) return false; //si l'île est déjà complète, cette technique est inutile.

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

        if(o.complete()) return false; //si l'île est déjà complète, cette technique est inutile.

        int n = o.getN();
        int nbV = o.nbVoisin(); //fonction qui calcule le nombre de voisin possible d'une île
        if(n == 8) return true;
        if(n == 6 && nbV == 3) return true;
        if(n == 4 && nbV == 2) return true;
        return false;
    }),
    TECH_COMPL("Si le nombre de ponts possibles de l'île est égale à sa valeur," +
            " alors il faut ajouter tous ces ponts possibles."
            , o -> {
        if(o.complete()) return false;

        int n = o.getN();
        int nbPoss = o.nbPontPossible();
        return n == nbPoss;
    }),
    TECH_BAS_1(" Si une île a un chiffre impair > 1 elle à au minimum n%2+1 voisins. Si elle a\n" +
            "exactement ce nombre de voisins alors elle est obligatoirement relié au moins une fois à\n" +
            "chacun de ses voisins"
            , o -> {
        if(o.complete()) return false;

        int n = o.getN();
        int nbV = o.nbVoisin();
        int nbP = o.nbPont();
        if(n == 7 && nbP < 4) return true;
        if(n == 5 && nbV == 3 && nbP < 3) return true;
        if(n == 3 && nbV == 2 && nbP < 2) return true;
        return false;
    }),
    TECH_BAS_2(": Si une île a un chiffre impair > 1 elle à au minimum n%2+1 voisins. Si elle a\n" +
            "exactement ce nombre de voisins alors elle est obligatoirement relié au moins une fois à\n" +
            "chacun de ses voisins. Si parmi ses voisins il y a une île avec le chiffre 1, alors l'île sera relié 2 fois à\n" +
            "tous ses autres voisins"
            , o -> {
        if(o.complete()) return false;

        int n = o.getN();
        int nbV = o.nbVoisin();
        int nbV1 = o.nbVoisinAvec1();
        if(n == 7 && nbV1 == 1) return true;
        if(n == 5 && nbV == 3 && nbV1 == 1) return true;
        if(n == 3 && nbV == 2 && nbV1 == 1) return true;
        return false;
    }),
    TECH_BAS_3("Si une île a le chiffre 1 ou 2 et qu’elle possède seulement 1 voisin alors elle sera\n" +
            "forcément reliée à ce voisin, cela fonctionne seulement le chiffre 1 et 2 puisque dans le\n" +
            "hashi il peut seulement exister maximum 2 ponts entre 2 îles."
            , o -> {
        if(o.complete()) return false;

        int n = o.getN();
        int nbV = o.nbVoisin();
        if(n == 1 || n == 2){
            if(nbV == 1) return true;
        }
        return false;
    });

    private final String description;
    private final Function<Ile, Boolean> fonction;

    Technique(String description, Function<Ile, Boolean> fonction) {
        this.description = description;
        this.fonction = fonction;
    }

    public String getDescription() {
        return description;
    }

    public Function<Ile, Boolean> getFonction() {
        return fonction;
    }
}
