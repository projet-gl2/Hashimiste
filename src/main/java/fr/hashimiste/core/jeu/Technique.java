package fr.hashimiste.core.jeu;

import java.util.function.Predicate;

/**
 * L'énumération Technique définit les différentes techniques utilisées dans le jeu.
 * Chaque technique a un nom, une description et un prédicat pour tester une condition spécifique sur une île.
 */
public enum Technique {
    /**
     * Technique vérifiant si une île est un 4 dans un coin, un 6 en bordure, ou un 8.
     */
    TECH_DEP_1("Technique 1 (bordure)",
            "Si une île a un chiffre pair n et que son nombre de voisins correspond à n/2 alors elle est reliée 2 fois à chacun de ses voisins",
            o -> {
                int n = o.getN();
                if (n == 8) return true;

                int x = o.getX();
                int y = o.getY();
                int l = (int) o.getGrille().getDimension().getWidth();
                int c = (int) o.getGrille().getDimension().getHeight();
                return (
                        n == 6 && (x == 0 || y == 0 || x == l - 1 || y == c - 1))
                        || (n == 4 && ((x == 0 && (y == 0 || y == c - 1)) || (x == l - 1 && (y == 0 || y == c - 1)))
                );
            }),
    /**
     * Technique vérifiant si une île est un 4 avec deux voisin, un 6 avec trois voisins, ou un 8.
     */
    TECH_DEP_2("Technique 1",
            "Si une île a un chiffre pair et son nombre de voisins correspond à n/2 alors elle est reliée 2 fois à chacun de ses voisins",
            o -> {
                int n = o.getN();
                int nbV = o.getNbVoisin(); //fonction qui calcule le nombre de voisin possible d'une île
                return (n == 8) || (n == 6 && nbV == 3) || (n == 4 && nbV == 2);
            }),
    /**
     * Technique vérifiant si une île a autant de ponts possible que sa valeur.
     */
    TECH_COMPL("Technique 2",
            "Si le nombre de ponts possibles de l'île est égale à sa valeur," +
                    " alors il faut ajouter tous ces ponts possibles.", o -> {
        int n = o.getN();
        int nbPoss = o.getNbPontPossible();
        return n == nbPoss;
    }),
    /**
     * Technique vérifiant si une île est un 3 avec deux voisins, un 5 avec trois voisins, ou un 7.
     * Vérifie aussi si elle a au moins un pont avec chacun de ses voisins.
     */
    TECH_BAS_1("Technique 3.1",
            "Si une île a un chiffre impair n, elle a au minimum n%2+1 voisins. Si elle a " +
                    "exactement ce nombre de voisins alors elle est obligatoirement relié au moins une fois à " +
                    "chacun de ses voisins",
            o -> {
                int n = o.getN();
                int nbV = o.getNbVoisin();
                int nbP = o.getNbPont();

                int i;
                boolean verif = false; //prend la valeur true s'il y a un pont possible dans une direction qui n'a pas été placé

                Direction[] lD = Direction.values();

                if (n == 7 && nbP < 4) {
                    for (i = 0; i < 4; i++) {
                        verif = (o.getNbPontsDirections(lD[i]) == 0) || verif;
                    }
                }
                if (n == 5 && nbV == 3 && nbP < 3)
                    for (i = 0; i < 4; i++) {
                        verif = (o.getNbPontsDirections(lD[i]) == 0) || verif;
                    }
                if (n == 3 && nbV == 2 && nbP < 2)
                    for (i = 0; i < 4; i++) {
                        verif = (o.getNbPontsDirections(lD[i]) == 0) || verif;
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
                    "tous ses autres voisins",
            o -> {
                int n = o.getN();
                int nbV = o.getNbVoisin();
                int nbV1 = o.getNbVoisinFiltre(i -> i.getN() == 1);
                return (n == 7 && nbV1 == 1) || (n == 5 && nbV == 3 && nbV1 == 1) || (n == 3 && nbV == 2 && nbV1 == 1);
            }),
    /**
     * Technique vérifiant si une île est un 1 ou un 2 avec un seul voisin.
     */
    TECH_BAS_3("Technique 4",
            "Si une île a le chiffre 1 ou 2 et qu’elle possède seulement 1 voisin alors elle sera " +
                    "forcément reliée à ce voisin, cela fonctionne seulement le chiffre 1 et 2 puisque dans le " +
                    "hashi il peut seulement exister maximum 2 ponts entre 2 îles.",
            o -> {
                int n = o.getN();
                int nbV = o.getNbVoisin();
                return (n == 1 || n == 2) && nbV == 1;
            }),
    /**
     * Technique vérifiant si une île est un 4 avec trois voisins dont deux d'entres eux sont des 1,
     * ou un 5 avec quatre voisins dont trois d'entre eux sont des 1.
     */
    TECH_BAS_4("Technique 5",
            "Si une île de valeur 4 possède trois voisins, " +
                    "dont deux d'entre eux sont de valeurs 1, alors on peut la compléter. " +
                    "Même chose si une île est de valeur 5, avec trois voisins de valeur 1.",
            o -> (o.getN() == 4 && o.getNbVoisin() == 3 && o.getNbVoisinFiltre(i -> i.getN() == 1) == 2)
                    || (o.getN() == 5 && o.getNbVoisin() == 4 && o.getNbVoisinFiltre(i -> i.getN() == 1) == 3)),
    /**
     * Technique vérifiant si une île est un 6 avec un 1 pour voisin, et si elle n'a pas de ponts avec ses voisins qui ne sont pas des 1.
     */
    TECH_BAS_5("Technique 6",
            "Si une île de valeur 6 possède un voisin de valeur 1, alors il possède au moins " +
                    "un pont avec chacun de ses autres voisins.",
            o -> {
                boolean verif = false;
                if (o.getN() == 6 && o.getNbVoisinFiltre(i -> i.getN() == 1) == 1) {
                    Direction[] lD = Direction.values();
                    for (int i = 0; i < 4; i++) {
                        verif |= (o.getValeurIleDirection(lD[i]) != 1 && o.getNbPontsDirections(lD[i]) == 0);
                    }
                }
                return verif;
            }),
    /**
     * Technique vérifiant si une île est un 1 possédant uniquement un voisin qui n'est pas non plus un 1.
     */
    TECH_ISO_1("Technique 7.1",
            "Si une île de valeur 1 est voisin avec un 1, il ne peut pas y avoir de pont " +
                    "entre eux, car cela brise la règle d'isolation.",
            o -> o.getN() == 1 && (o.getNbVoisin() - o.getNbVoisinFiltre(i -> i.getN() == 1) == 1)),
    /**
     * Technique vérifiant si une île est un 2 possédant deux voisin dont l'un est aussi un 2
     * et dont l'autre n'est relié par aucun pont.
     */
    TECH_ISO_2("Technique 7.2",
            "Si une île de valeur 2 est voisin avec un 2, il ne peut pas y avoir deux ponts " +
                    "entre eux, car cela brise la règle d'isolation.",
            o -> {
                boolean verifVoisin2 = false; //vérifie si l'un des voisins est un 2.
                boolean verifPont0 = false; //vérifie si le voisin qui n'est pas un 2 n'est pas relié par un pont.
                if (o.getN() == 2 && o.getNbVoisin() == 2) {
                    Direction[] lD = Direction.values();
                    for (int i = 0; i < 4; i++) {
                        verifVoisin2 = verifVoisin2 || (o.getValeurIleDirection(lD[i]) == 2);
                        verifPont0 = verifPont0 || (o.getValeurIleDirection(lD[i]) != 2 && o.getNbPontsDirections(lD[i]) == 0);
                    }
                }
                return (verifVoisin2 && verifPont0);
            });

    private final String nom;
    private final String description;
    private final Predicate<Ile> predicate;

    /**
     * Constructeur de Technique.
     *
     * @param nom         le nom de la technique.
     * @param description la description de la technique.
     * @param predicate   le prédicat pour tester une condition spécifique sur une île.
     */
    Technique(String nom, String description, Predicate<Ile> predicate) {
        this.nom = nom;
        this.description = description;
        this.predicate = predicate;
    }

    /**
     * Récupère le nom de la technique.
     *
     * @return le nom de la technique.
     */
    public String getNom() {
        return this.nom;
    }

    /**
     * Récupère la description de la technique.
     *
     * @return la description de la technique.
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * Teste si une île satisfait la condition spécifique de la technique.
     *
     * @param ile l'île à tester.
     * @return vrai si l'île satisfait la condition, faux sinon.
     */
    public boolean test(Ile ile) {
        return this.predicate.test(ile);
    }
}
