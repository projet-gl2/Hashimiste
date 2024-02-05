import java.util.function.Function;

/**
 * Une technique correspond à une description et à un algoritmhe qui vérifie si
 * une île peut bénéficier de la technique dans son état actuel (Ile -> Boolean).
 * Pas besoin de vérifier dans les fonctions si l'île est complète, cette étape est déjà faite dans la méthode aide de la classe Grille.
 */
public enum Technique {
    TECH_DEP_1("Technique 1 (bordure)",
            "Si une île a un chiffre pair n et que son nombre de voisins correspond à n/2 alors elle est reliée 2 fois à chacun de ses voisins"
            , o -> {
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
    TECH_DEP_2("Technique 1",
            "Si une île a un chiffre pair et son nombre de voisins correspond à n/2 alors elle est reliée 2 fois à chacun de ses voisins"
                       , o -> {
        int n = o.getN();
        int nbV = o.nbVoisin(); //fonction qui calcule le nombre de voisin possible d'une île
        if(n == 8) return true;
        if(n == 6 && nbV == 3) return true;
        if(n == 4 && nbV == 2) return true;
        return false;
    }),
    TECH_COMPL("Technique 2",
            "Si le nombre de ponts possibles de l'île est égale à sa valeur," +
            " alors il faut ajouter tous ces ponts possibles."
            , o -> {
        int n = o.getN();
        int nbPoss = o.nbPontPossible();
        return n == nbPoss;
    }),
    TECH_BAS_1("Technique 3",
            " Si une île a un chiffre impair n elle a au minimum n%2+1 voisins. Si elle a " +
            "exactement ce nombre de voisins alors elle est obligatoirement relié au moins une fois à " +
            "chacun de ses voisins"
            , o -> {
        int n = o.getN();
        int nbV = o.nbVoisin();
        int nbP = o.nbPont();
        if(n == 7 && nbP < 4) return true;
        if(n == 5 && nbV == 3 && nbP < 3) return true;
        if(n == 3 && nbV == 2 && nbP < 2) return true;
        return false;
    }),
    TECH_BAS_2("Technique 3.5",
            ": Si une île a un chiffre impair > 1 elle à au minimum n%2+1 voisins. Si elle a " +
            "exactement ce nombre de voisins alors elle est obligatoirement relié au moins une fois à " +
            "chacun de ses voisins. Si parmi ses voisins il y a une île avec le chiffre 1, alors l'île sera relié 2 fois à " +
            "tous ses autres voisins"
            , o -> {
        int n = o.getN();
        int nbV = o.nbVoisin();
        int nbV1 = o.nbVoisinAvec1();
        if(n == 7 && nbV1 == 1) return true;
        if(n == 5 && nbV == 3 && nbV1 == 1) return true;
        if(n == 3 && nbV == 2 && nbV1 == 1) return true;
        return false;
    }),
    TECH_BAS_3("Technique 4",
            "Si une île a le chiffre 1 ou 2 et qu’elle possède seulement 1 voisin alors elle sera " +
            "forcément reliée à ce voisin, cela fonctionne seulement le chiffre 1 et 2 puisque dans le " +
            "hashi il peut seulement exister maximum 2 ponts entre 2 îles."
            , o -> {
        int n = o.getN();
        int nbV = o.nbVoisin();
        if(n == 1 || n == 2){
            if(nbV == 1) return true;
        }
        return false;
    }),
    TECH_BAS_4("Technique 5",
            "Si une île de valeur 4 possède trois voisins, " +
            "dont deux d'entre eux sont de valeurs 1, alors on peut la compléter. " +
                    "Même chose si une île est de valeur 5, avec trois voisins de valeur 1." ,
            o -> {
        return ((o.getN() == 4 && o.nbVoisin() == 3 && o.nbVoisinAvec1() == 2) || (o.getN() == 5 && o.nbVoisin() == 4 && o.nbVoisinAvec1() == 3));
    }),
    TECH_BAS_5("Technique 6",
            "Si une île de valeur 6 possède un voisin de valeur 1, alors il possède au moins " +
                    "un pont avec chacun de ses autres voisins."
            , o -> {
        return (o.getN() == 6 && o.nbVoisinAvec1() == 1);
    }),
    TECH_ISO_1("Technique 7",
            "Si une île de valeur 1 est voisin avec un 1, il ne peut pas y avoir de pont " +
                    "entre eux, car cela brise la règle d'isolation."
            , o -> {
        return(o.getN() == 1 && (o.nbVoisin()-o.nbVoisinAvec1() == 1));
    });

    private final String nom;
    private final String description;
    private final Function<Ile, Boolean> fonction;

    Technique(String nom, String description, Function<Ile, Boolean> fonction) {
        this.nom = nom;
        this.description = description;
        this.fonction = fonction;
    }

    public String getDescription() {
        return description;
    }

    public Function<Ile, Boolean> getFonction() {
        return fonction;
    }

    public String getNom() {
        return nom;
    }
}
