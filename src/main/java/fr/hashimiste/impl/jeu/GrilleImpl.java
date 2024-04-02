package fr.hashimiste.impl.jeu;

import fr.hashimiste.core.data.Stockage;
import fr.hashimiste.core.data.sql.Identifiable;
import fr.hashimiste.core.jeu.Difficulte;
import fr.hashimiste.core.jeu.Grille;
import fr.hashimiste.core.jeu.Ile;
import fr.hashimiste.core.jeu.Sauvegarde;
import fr.hashimiste.core.jeu.Historique;
import fr.hashimiste.core.jeu.Historique.Action;

import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * Cette classe représente une grille de jeu.
 * Elle implémente les interfaces Grille et Identifiable.__UNSAFE__.
 */
public class GrilleImpl implements Grille, Identifiable.UNSAFE {
    private final Dimension dimension;
    private final Ile[][] iles;
    private final Difficulte difficulte;
    private final boolean estAventure;
    private List<Sauvegarde> sauvegardes;

    private int id;

    /**
     * Constructeur de la classe GrilleImpl.
     *
     * @param dimension  la dimension de la grille.
     * @param difficulte la difficulté de la grille.
     */
    public GrilleImpl(Dimension dimension, Difficulte difficulte, boolean estAventure) {
        this(-1, dimension, difficulte, estAventure, new ArrayList<>());
    }

    /**
     * Constructeur de la classe GrilleImpl.
     *
     * @param id         l'identifiant de la grille.
     * @param dimension  la dimension de la grille.
     * @param difficulte la difficulté de la grille.
     */
    public GrilleImpl(int id, Dimension dimension, Difficulte difficulte, boolean estAventure) {
        this(id, dimension, difficulte, estAventure, null);
    }

    /**
     * Constructeur de la classe GrilleImpl.
     *
     * @param id          l'identifiant de la grille.
     * @param dimension   la dimension de la grille.
     * @param difficulte  la difficulté de la grille.
     * @param sauvegardes la liste des sauvegardes de la grille.
     */
    public GrilleImpl(int id, Dimension dimension, Difficulte difficulte, boolean estAventure, List<Sauvegarde> sauvegardes) {
        this.id = id;
        this.dimension = dimension;
        this.iles = new Ile[dimension.width][dimension.height];
        this.difficulte = difficulte;
        this.estAventure = estAventure;
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
     * Cette méthode est utilisée pour poser un pont entre deux îles.
     *
     * @param ile1 la première île.
     * @param ile2 la deuxième île.
     * @param n    le nombre de ponts à poser.
     */
    public void poserPont(Ile ile1, Ile ile2, int n) {
//        iles[x1][y1].poserPont(iles[x2][y2], n); TODO
    }

    @Override
    public Ile getIle(int x, int y) {
        return iles[x][y];
    }

    @Override
    public List<Ile> getIles() {
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
    public boolean verification(Historique histo) {
        List<Ile> res=parcoursGrille(histo);
        List<Ile> tmp = getIles();
        tmp.removeIf(ile -> res.contains(ile));
        return tmp.isEmpty();
    }
    
  
    private List<Ile> parcoursGrille(Historique histo){
        return explorer(new ArrayList<>(),histo.getIle1(),histo);
    }

    private List<Ile> explorer(List<Ile> tmp, Ile ile, Historique histo){
        if(histo.getAction()!=Action.NOUVELLE_GRILLE){
            if(histo.getAction()==Action.UN_PONT || histo.getAction()==Action.DEUX_PONTS){
                if(histo.getIle1().equals(ile)){
                    if((tmp.indexOf(histo.getIle2())==-1)){
                        tmp.add(ile);
                        tmp = explorer(tmp, histo.getIle2(),histo.getAvant());
                    }
                }else{
                    if(histo.getIle2().equals(ile)){
                        if((tmp.indexOf(histo.getIle1())==-1)){
                            tmp.add(ile);
                            tmp = explorer(tmp, histo.getIle1(),histo.getAvant());
                        }
                    }
                }

            }  
        }
        return tmp;
    }
    
    @Override
    public Ile aide() {
        return null;
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
}