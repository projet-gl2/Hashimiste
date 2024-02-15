package fr.hashimiste.techniques;

import fr.hashimiste.maps.Ile;

import java.util.function.Function;

/**
 * Une technique correspond à une description et à un algoritmhe qui vérifie si
 * une île peut bénéficier de la technique dans son état actuel (Ile -> Boolean).
 * Pas besoin de vérifier dans les fonctions si l'île est complète, cette étape est déjà faite dans la méthode aide de la classe Grille.
 */
public enum Technique {
    /**
     * Technique vérifiant si une île est un 4 dans un coin, un 6 en bordure, ou un 8.
     */
    TECH_DEP_1("Technique 1 (bordure)",
            "Si une île a un chiffre pair n et que son nombre de voisins correspond à n/2 " +
                    "alors elle est reliée 2 fois à chacun de ses voisins"
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
    /**
     * Technique vérifiant si une île est un 4 avec deux voisin, un 6 avec trois voisins, ou un 8.
     */
    TECH_DEP_2("Technique 1",
            "Si une île a un chiffre pair et son nombre de voisins correspond à n/2 alors " +
                    "elle est reliée 2 fois à chacun de ses voisins"
                       , o -> {
        int n = o.getN();
        int nbV = o.nbVoisin(); //fonction qui calcule le nombre de voisin possible d'une île
        if(n == 8) return true;
        if(n == 6 && nbV == 3) return true;
        if(n == 4 && nbV == 2) return true;
        return false;
    }),
    /**
     * Technique vérifiant si une île a autant de ponts possible que sa valeur.
     */
    TECH_COMPL("Technique 2",
            "Si le nombre de ponts possibles de l'île est égale à sa valeur," +
            " alors il faut ajouter tous ces ponts possibles."
            , o -> {
        int n = o.getN();
        int nbPoss = o.nbPontPossible();
        return n == nbPoss;
    }),
    /**
     * Technique vérifiant si une île est un 3 avec deux voisins, un 5 avec trois voisins, ou un 7. 
     * Vérifie aussi si elle a au moins un pont avec chacun de ses voisins.
     */
    TECH_BAS_1("Technique 3.1",
            " Si une île a un chiffre impair n, elle a au minimum n%2+1 voisins. Si elle a " +
            "exactement ce nombre de voisins alors elle est obligatoirement relié au moins une fois à " +
            "chacun de ses voisins"
            , o -> {
        int n = o.getN();
        int nbV = o.nbVoisin();
        int nbP = o.nbPont();

        int i;
        boolean verif = false; //prend la valeur true s'il y a un pont possible dans une direction qui n'a pas été placé

        Direction[] lD = Direction.values();

        if(n == 7 && nbP < 4) {
            for(i=0;i<4;i++){
                verif = (o.nbPontsDirection(lD[i]) == 0) || verif;
            }
        }
        if(n == 5 && nbV == 3 && nbP < 3)
            for(i=0;i<4;i++){
                verif = (o.nbPontsDirection(lD[i]) == 0) || verif;
            }
        if(n == 3 && nbV == 2 && nbP < 2)
            for(i=0;i<4;i++){
                verif = (o.nbPontsDirection(lD[i]) == 0) || verif;
            }
        return false;
    }),
    /**
     * Technique vérifiant si une île est un 3 avec deux voisins, un 5 avec trois voisins, ou un 7. 
     * Vérifie en plus si l'un de ses voisins est un 1.
     */
    TECH_BAS_2("Technique 3.2",
            "Si une île a un chiffre impair > 1 elle à au minimum n%2+1 voisins. Si elle a " +
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
    /**
     * Technique vérifiant si une île est un 1 ou un 2 avec un seul voisin.
     */
    TECH_BAS_3("Technique 4",
            "Si une île a le chiffre 1 ou 2 et qu’elle possède seulement 1 voisin alors elle sera " +
            "forcément reliée à ce voisin, cela fonctionne seulement le chiffre 1 et 2 puisque dans le " +
            "hashi il peut seulement exister maximum 2 ponts entre 2 îles."
            , o -> {
        int n = o.getN();
        int nbV = o.nbVoisin();
        if(n == 1 || n == 2){
            return nbV == 1;
        }
        return false;
    }),
    /**
     * Technique vérifiant si une île est un 4 avec trois voisins dont deux d'entres eux sont des 1,
     * ou un 5 avec quatre voisins dont trois d'entre eux sont des 1.
     */
    TECH_BAS_4("Technique 5",
            "Si une île de valeur 4 possède trois voisins, " +
            "dont deux d'entre eux sont de valeurs 1, alors on peut la compléter. " +
                    "Même chose si une île est de valeur 5, avec trois voisins de valeur 1." ,
            o -> {
        return ((o.getN() == 4 && o.nbVoisin() == 3 && o.nbVoisinAvec1() == 2) || (o.getN() == 5 && o.nbVoisin() == 4 && o.nbVoisinAvec1() == 3));
    }),
    /**
     * Technique vérifiant si une île est un 2 avec deux voisins, dont un avec un 1, et si elle n'a pas de ponts avec son autre voisin.
     */
    TECH_BAS_5("Technique 6.1",
            "Si une île de valeur 2 possède deux voisins, dont un de valeur 1, alors il possède au moins " +
                    "un pont avec son autre voisin."
            , o -> {
        boolean verif = false;
        if (o.getN() == 2 && o.getNbVoisin() == 2 && o.nbVoisinAvec1() == 1){
            Direction[] lD = Direction.values();
            for(int i=0; i<4; i++){
                verif = verif || (o.valeurIleDirection(lD[i]) > 1 && o.nbPontsDirection(lD[i]) == 0);
            }
        }
        return verif;
    }),
    /**
     * Technique vérifiant si une île est un 4 avec trois voisins, dont un avec un 1, et si elle n'a pas de ponts avec ses voisins qui ne sont pas des 1.
     */
    TECH_BAS_6("Technique 6.2",
            "Si une île de valeur 4 possède trois voisins dont un de valeur 1, alors elle possède au moins " +
                    "un pont avec chacun de ses autres voisins."
            , o -> {
        boolean verif = false;
        if (o.getN() == 4 && o.getNbVoisin() == 3 && o.nbVoisinAvec1() == 1){
            Direction[] lD = Direction.values();
            for(int i=0; i<4; i++){
                verif = verif || (o.valeurIleDirection(lD[i]) > 1 && o.nbPontsDirection(lD[i]) == 0);
            }
        }
        return verif;
    }),
    /**
     * Technique vérifiant si une île est un 6 avec un 1 pour voisin, et si elle n'a pas de ponts avec ses voisins qui ne sont pas des 1.
     */
    TECH_BAS_7("Technique 6.3",
            "Si une île de valeur 6 possède un voisin de valeur 1, alors elle possède au moins " +
                    "un pont avec chacun de ses autres voisins."
            , o -> {
        boolean verif = false;
        if (o.getN() == 6 && o.nbVoisinAvec1() == 1){
            Direction[] lD = Direction.values();
            for(int i=0; i<4; i++){
                verif = verif || (o.valeurIleDirection(lD[i]) != 1 && o.nbPontsDirection(lD[i]) == 0);
            }
        }
        return verif;
    }),
    /**
     * Technique vérifiant si une île est un 1 possédant uniquement un voisin qui n'est pas non plus un 1.
     */
    TECH_ISO_1("Technique 7.1",
            "Si une île de valeur 1 est voisin avec un 1, il ne peut pas y avoir de pont " +
                    "entre eux, car cela brise la règle d'isolation."
            , o -> {
        return(o.getN() == 1 && (o.nbVoisin()-o.nbVoisinAvec1() == 1));
    }),
    /**
     * Technique vérifiant si une île est un 2 possédant deux voisin dont l'un est aussi un 2
     * et dont l'autre n'est relié par aucun pont.
     */
    TECH_ISO_2("Technique 7.2",
            "Si une île de valeur 2 est voisin avec un 2, il ne peut pas y avoir deux ponts " +
                    "entre eux, car cela brise la règle d'isolation."
                       , o -> {
        boolean verifVoisin2 = false; //vérifie si l'un des voisins est un 2.
        boolean verifPont0 = false; //vérifie si le voisin qui n'est pas un 2 n'est pas relié par un pont.
        if (o.getN() == 2 && o.nbVoisin() == 2){
            Direction[] lD = Direction.values();
            for(int i=0; i<4; i++){
                verifVoisin2 = verifVoisin2 || (o.valeurIleDirection(lD[i]) == 2);
                verifPont0 = verifPont0 || (o.valeurIleDirection(lD[i]) != 2 && o.nbPontsDirection(lD[i]) == 0);
            }
        }
        return (verifVoisin2 && verifPont0);
    }),
    /**
     * Technique vérifiant si une île est un 2 possédant trois voisin dont deux sont des 1
     * et dont l'autre n'est relié par aucun pont.
     */
    TECH_ISO_3("Technique 8.1",
            "Si une île de valeur 2 est voisin avec deux 1, il ne peut pas être rélié " +
                    "aux deux, car cela brise la règle d'isolation."
            , o -> {
        boolean verifPont0 = false; //vérifie si le voisin qui n'est pas un 1 n'est pas relié par un pont.
        if (o.getN() == 2 && (o.nbVoisin() - o.nbVoisinAvec1()) == 1){
            Direction[] lD = Direction.values();
            for(int i=0; i<4; i++){
                verifPont0 = verifPont0 || (o.valeurIleDirection(lD[i]) != 1 && o.nbPontsDirection(lD[i]) == 0);
            }
        }
        return (verifPont0);
    });

    private final String nom;
    private final String description;
    private final Function<Ile, Boolean> fonction;

    Technique(String nom, String description, Function<Ile, Boolean> fonction) {
        this.nom = nom;
        this.description = description;
        this.fonction = fonction;
    }

    /**
     * Renvoie la description de la technique choisie.
     * @return description de la technique choisie
     */
    public String getDescription() {
        return description;
    }

    /**
     * Applique la fonction associée à la technique sur l'île entrée en paramètre.
     * @param ile l'île dont on veut vérifier si la technique concernée peut aider à la compléter
     * @return renvoie vrai si la technique peut aider à compléter l'île, faux sinon.
     */
    public boolean execute(Ile ile) {
        return this.fonction.apply(ile);
    }

    /**
     * Renvoie le nom de la technique choisie.
     * @return nom de la technique choisie
     */
    public String getNom() {
        return nom;
    }
}
