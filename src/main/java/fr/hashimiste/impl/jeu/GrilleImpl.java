package fr.hashimiste.impl.jeu;

import fr.hashimiste.core.data.Stockage;
import fr.hashimiste.core.data.sql.Identifiable;
import fr.hashimiste.core.jeu.*;
import fr.hashimiste.core.utils.DevUtils;
import fr.hashimiste.core.utils.Union;

import java.awt.*;
import java.util.List;
import java.util.*;
import java.util.function.Consumer;
import java.util.stream.Collectors;

/**
 * Cette classe représente une grille de jeu.
 * Elle implémente les interfaces Grille et Identifiable.__UNSAFE__.
 */
public class GrilleImpl implements Grille, Identifiable.UNSAFE {
    private final Dimension dimension;
    private final Case[][] iles;
    private final Difficulte difficulte;
    private final boolean estAventure;
    private final boolean estJouable;
    private Grille solution;
    private List<Sauvegarde> sauvegardes;
    /**
     * Indique le nombre de fois que l'utilisateur à cliqué sur le bouton d'aide d'affilée.
     */
    private int nbClicSurAide = 0;

    private int id;

    /**
     * Constructeur de la classe GrilleImpl.
     *
     * @param dimension  la dimension de la grille.
     * @param difficulte la difficulté de la grille.
     * @param stockage
     */
    public GrilleImpl(Dimension dimension, Difficulte difficulte, boolean estAventure, boolean estJouable, Stockage stockage) {
        this(-1, dimension, difficulte, estAventure, estJouable, stockage, new ArrayList<>());
    }

    /**
     * Constructeur de la classe GrilleImpl.
     *
     * @param id         l'identifiant de la grille.
     * @param dimension  la dimension de la grille.
     * @param difficulte la difficulté de la grille.
     * @param estJouable
     * @param stockage
     */
    public GrilleImpl(int id, Dimension dimension, Difficulte difficulte, boolean estAventure, boolean estJouable, Stockage stockage) {
        this(id, dimension, difficulte, estAventure, estJouable, stockage, null);
    }

    /**
     * Constructeur de la classe GrilleImpl.
     *
     * @param id          l'identifiant de la grille.
     * @param dimension   la dimension de la grille.
     * @param difficulte  la difficulté de la grille.
     * @param estJouable
     * @param stockage
     * @param sauvegardes la liste des sauvegardes de la grille.
     */
    public GrilleImpl(int id, Dimension dimension, Difficulte difficulte, boolean estAventure, boolean estJouable, Stockage stockage, List<Sauvegarde> sauvegardes) {
        this.id = id;
        this.dimension = dimension;
        this.iles = new Case[dimension.width][dimension.height];
        for (int i = 0; i < dimension.width; i++) {
            for (int j = 0; j < dimension.height; j++) {
                this.iles[i][j] = new CaseVideImpl(i, j, this);
            }
        }
        this.difficulte = difficulte;
        this.estAventure = estAventure;
        this.estJouable = estJouable;
        this.sauvegardes = sauvegardes;
    }

    /**
     * Cette méthode est utilisée pour poser une île sur la grille.
     *
     * @param ile l'île à poser.
     */
    public void poserIle(Ile ile) {
        iles[ile.getX()][ile.getY()] = ile;
    }

    /**
     * Cette méthode est utilisée pour enlever une île de la grille aux coordonnées indiquées. Utilisée pour les tests unitaires.
     *
     * @param x coordonnées en x de l'espace à vider.
     * @param y coordonnées en x de l'espace à vider.
     */
    protected void oterIle(int x, int y) {
        iles[x][y] = new CaseVideImpl(x, y, this);
    }

    /**
     * Cette méthode est utilisée pour vider une grille de toutes ses îles. Utilisée pour les tests unitaires.
     */
    protected void viderGrille() {
        nbClicSurAide = 0;
        for (int i = 0; i < dimension.width; i++) {
            for (int j = 0; j < dimension.height; j++) {
                oterIle(i, j);
            }
        }
    }

    /**
     * Cette méthode est utilisée pour poser un pont entre deux îles.
     *
     * @param ile1 la première île.
     * @param ile2 la deuxième île.
     * @param n    le nombre de ponts à poser.
     */
    public void poserPont(Ile ile1, Ile ile2, int n) {
        Consumer<Void> consumer = v -> {
        };
        if (ile1.getX() == ile2.getX()) {
            for (int i = Math.min(ile1.getY(), ile2.getY()) + 1; i < Math.max(ile1.getY(), ile2.getY()); i++) {
                if (iles[ile1.getX()][i] instanceof Pont) {
                    return;
                }
                int fI = i;
                consumer = consumer.andThen(v -> iles[ile1.getX()][fI] = new PontImpl(ile1.getX(), fI, n, this, Direction.NORD, ile1, ile2));
            }

        } else if (ile1.getY() == ile2.getY()) {
            for (int i = Math.min(ile1.getX(), ile2.getX()) + 1; i < Math.max(ile1.getX(), ile2.getX()); i++) {
                if (iles[i][ile1.getY()] instanceof Pont) {
                    return;
                }
                int fI = i;
                consumer = consumer.andThen(v -> iles[fI][ile1.getY()] = new PontImpl(fI, ile1.getY(), n, this, Direction.EST, ile1, ile2));
            }
        }
        consumer.accept(null);
    }

    public void supprimerPont(Ile ile1, Ile ile2) {
        Consumer<Void> consumer = v -> {
        };
        if (ile1.getX() == ile2.getX()) {
            for (int i = Math.min(ile1.getY(), ile2.getY()) + 1; i < Math.max(ile1.getY(), ile2.getY()); i++) {
                if (iles[ile1.getX()][i] instanceof Pont) {
                    int fI = i;
                    consumer = consumer.andThen(v -> iles[ile1.getX()][fI] = new CaseVideImpl(ile1.getX(), fI, this));
                }
            }
        } else if (ile1.getY() == ile2.getY()) {
            for (int i = Math.min(ile1.getX(), ile2.getX()) + 1; i < Math.max(ile1.getX(), ile2.getX()); i++) {
                if (iles[i][ile1.getY()] instanceof Pont) {
                    int fI = i;
                    consumer = consumer.andThen(v -> iles[fI][ile1.getY()] = new CaseVideImpl(fI, ile1.getY(), this));
                }
            }
        }
        consumer.accept(null);
    }

    @Override
    public Case getIle(int x, int y) {
        return x < 0 || x >= dimension.width || y < 0 || y >= dimension.height ? null : iles[x][y];
    }

    @Override
    public List<Case> getIles() {
        return Arrays.asList(iles).stream().flatMap(Arrays::stream).filter(Objects::nonNull).collect(Collectors.toList());
    }

    @Override
    public Dimension getDimension() {
        return dimension;
    }

    @Override
    public Difficulte getDifficulte() {
        return difficulte;
    }

    @Override
    public boolean estAventure() {
        return estAventure;
    }

    @Override
    public List<Sauvegarde> getSauvegardes(Stockage stockage) {
        if (sauvegardes == null) {
            rafraichirSauvegardes(stockage);
        }
        return sauvegardes;
    }

    @Override
    public void rafraichirSauvegardes(Stockage stockage) {
        sauvegardes = stockage.charger(Sauvegarde.class, "WHERE reference IN (SELECT date FROM historique WHERE id_map = " + id + ")");
    }

    @Override
    public boolean verification() { // TODO: a réadapter
        if (solution == null) {
            DevUtils.debug("No solution found for this map. Verifying with n of islands.");
            boolean isComplete = true;
            for (Case[] ile : iles) {
                for (Case aCase : ile) {
                    if (aCase instanceof Ile) {
                        Ile ile1 = (Ile) aCase;
                        if (!ile1.isComplete()) {
                            DevUtils.debug("L'île en x = " + ile1.getX() + " et y = " + ile1.getY() + " n'est pas complète. {" + ile1 + "}");
                            DevUtils.debug("ile1.getNbPont() = " + ile1.getNbPont());
                            isComplete = false;
                        }
                    }
                }
            }
            return isComplete;
        } else {
            DevUtils.debug("Solution found for this map. Verifying with solution.");
            return equals(solution);
        }
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Grille) {
            Grille grille = (Grille) obj;
            if (!dimension.equals(grille.getDimension())) {
                return false;
            }
            for (int i = 0; i < dimension.width; i++) {
                for (int j = 0; j < dimension.height; j++) {
                    if (!getIle(i, j).equals(grille.getIle(i, j))) {
                        return false;
                    }
                }
            }
            return true;
        }
        return false;
    }

    @Override
    public Union<Ile, String> aide() {
        Union<Ile, Technique> uIT = this.chercherIle();
        String mess = "";
        if (nbClicSurAide == 0) mess = "La " + uIT.getDroite().getNom() + " peut être utilisée !";
        if (nbClicSurAide == 1)
            mess = "La " + uIT.getDroite().getNom() + " peut être utilisée : " + uIT.getDroite().getDescription();
        if (nbClicSurAide == 2)
            mess = "La " + uIT.getDroite().getNom() + " peut être utilisée dans la région " + uIT.getGauche().getRegion();
        if (nbClicSurAide > 2)
            mess = "La " + uIT.getDroite().getNom() + " peut être utilisée en x = " + uIT.getGauche().getX() + " et en y = " + uIT.getGauche().getY();

        System.out.println(mess);

        nbClicSurAide++;

        return new Union<>(uIT.getGauche(), mess);
    }

    @Override
    public Union<Ile, Technique> chercherIle() {
        if (this.verification()) return null; //TODO quand verification sera fait correctement, remettre le not au début
        else {
            Technique[] lTech = Technique.values();
            int fIndMin = lTech.length; //une liste des fonctions qui appliquent une technique
            //elles prennent en paramètre une île, et renvoient vrai si la technique s'applique à l'île

            Ile aideIle = null; //l'île sur laquelle on peut avancer à l'aide des techniques
            Case tempCase;
            Ile tempIle;
            for (int i = 0; i < this.dimension.getWidth(); i++) { //parcours colonnes
                for (int j = 0; j < this.dimension.getHeight(); j++) { //parcours
                    tempCase = this.getIle(i, j);
                    if (tempCase instanceof IleImpl) {   //si l'île existe
                        tempIle = (IleImpl) tempCase;
                        if (!(tempIle.isComplete())) { //si l'île n'est pas complète
                            for (int fInd = 0; fInd < fIndMin; fInd++) { //parcours techniques
                                if (lTech[fInd].test(tempIle)) { //si la technique s'applique à l'île
                                    aideIle = tempIle;
                                    fIndMin = fInd; //on ne vérifie que les techniques de plus bas niveau que celles trouvées précédemments
                                }
                            }
                        }
                    }
                }
            }

            return new Union<>(aideIle, lTech[fIndMin]);

        }
    }

    @Override
    public boolean estJouable() {
        return estJouable;
    }

    @Override
    public int getId() {
        if (id == -1) {
            throw new IllegalStateException("L'id n'a pas été défini, impossible de le récupérer.");
        }
        return id;
    }

    @Override
    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String getColonneId() {
        return "id_map";
    }

    @Override
    public String toString() {
        return "Grille{" +
                "id=" + id +
                ", dimension=" + dimension +
                ", difficulte=" + difficulte +
                ", iles=" + Arrays.toString(iles) +
                '}';
    }

    @Override
    public void reset() {
        for (int i = 0; i < dimension.width; i++) {
            for (int j = 0; j < dimension.height; j++) {
                if (!(iles[i][j] instanceof Ile) && !(iles[i][j] instanceof CaseVide)) {
                    iles[i][j] = new CaseVideImpl(i, j, this);
                }
            }
        }
    }

    @Override
    public void chargerSauvegarde(Sauvegarde sauvegarde) {
        if (sauvegarde.getGrille().getId() != id) {
            throw new IllegalArgumentException("La sauvegarde ne correspond pas à cette grille.");
        }
        reset();
        Historique historique = sauvegarde.getReference();
        List<Historique> historiques = new ArrayList<>();
        while (historique.getAction() != Historique.Action.NOUVELLE_GRILLE) {
            historiques.add(historique);
            historique = historique.getAvant();
        }
        historiques.sort(Comparator.comparing(Historique::getTimestamp));
        for (Historique histo : historiques) {
            if (histo.getAction() == Historique.Action.UN_PONT) {
                poserPont(histo.getIle1(), histo.getIle2(), 1);
            } else if (histo.getAction() == Historique.Action.DEUX_PONTS) {
                poserPont(histo.getIle1(), histo.getIle2(), 2);
            } else {
                supprimerPont(histo.getIle1(), histo.getIle2());
            }
        }
    }

    /**
     * Recherche la solution de la grille.
     * @param stockage le stockage à utiliser pour charger les données.
     */
    public void fetchSolution(Stockage stockage) {
        if (id >= 1000) {
            return;
        }
        Sauvegarde sauvegarde = getSauvegardes(stockage)
                .stream()
                .filter(s -> s.getProfil().getId() == 1 && s.getNom().equals("Solution " + id))
                .findFirst()
                .orElseThrow(() -> new IllegalStateException("La solution n'a pas été trouvée."));
        this.solution = new GrilleImpl(id, dimension, difficulte, estAventure, estJouable, stockage);
        for (Case ile : getIles()) {
            if (ile instanceof Ile) {
                Ile i = (Ile) ile;
                ((GrilleImpl) this.solution).poserIle(new IleImpl(i.getX(), i.getY(), i.getN(), this.solution));
            }
        }
        this.solution.chargerSauvegarde(sauvegarde);
    }
}