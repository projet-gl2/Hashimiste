package fr.hashimiste.impl.jeu;

import fr.hashimiste.core.data.Stockage;
import fr.hashimiste.core.data.sql.Identifiable;
import fr.hashimiste.core.jeu.*;
import fr.hashimiste.core.utils.UnionIleTechnique;

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
    private final Case[][] iles;
    private final Difficulte difficulte;
    private final boolean estAventure;
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
        this.iles = new Case[dimension.width][dimension.height];
        for(int i=0; i<dimension.width; i++){
            for(int j=0; j<dimension.height; j++){
                this.iles[i][j] = new CaseVideImpl(i,j,this);
            }
        }
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
     * Cette méthode est utilisée pour enlever une île de la grille aux coordonnées indiquées. Utilisée pour les tests unitaires.
     *
     * @param x coordonnées en x de l'espace à vider.
     * @param y coordonnées en x de l'espace à vider.
     */
    protected void oterIle(int x, int y){
        iles[x][y] = new CaseVideImpl(x,y,this);
    }

    /**
     * Cette méthode est utilisée pour vider une grille de toutes ses îles. Utilisée pour les tests unitaires.
     */
    protected void viderGrille(){
        for(int i=0;i<dimension.width;i++){
            for(int j=0;j<dimension.height;j++){
                oterIle(i,j);
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
        Direction d = null;
        Case temp = ile1;

        for(Direction value: Direction.values()){

            if(ile1.isVoisinDirection(value)) {
                if (ile1.getVoisinCase(value).getVoisinIle(value) == ile2) {
                    d = value;
                    break;
                }
            }
        }

        if(d != null){
            temp = temp.getVoisinCase(d);
            while(temp != ile2 && !(temp instanceof PontImpl)){
                temp = temp.getVoisinCase(d);
            }

            if(!(temp instanceof PontImpl)){
                temp = ile1.getVoisinCase(d);
                while(temp != ile2){
                    iles[temp.getX()][temp.getY()] = new PontImpl(temp.getX(), temp.getY(), n, this, d);
                    temp = temp.getVoisinCase(d);
                }
            }
            nbClicSurAide = 0; //si un pont a été posé, on réinitialise le compteur de clic sur aide
        }
    }

    @Override
    public Case getIle(int x, int y) {
        return iles[x][y];
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
    public boolean verification() {
        return false;
    }

    @Override
    public String aide(){ //TODO faire un affichage dans l'appli, pas juste dans le terminal
        UnionIleTechnique uIT = this.chercherIle();
        String mess = "";
        if(nbClicSurAide == 0) mess = "La "+uIT.getTechU().getNom()+" peut être utilisée !";
        if(nbClicSurAide == 1) mess = "La "+uIT.getTechU().getNom()+" peut être utilisée : "+uIT.getTechU().getDescription();
        if(nbClicSurAide == 2) mess = "La "+uIT.getTechU().getNom()+" peut être utilisée dans la région "+uIT.getIleU().getRegion();
        if(nbClicSurAide > 2) mess = "La "+uIT.getTechU().getNom()+" peut être utilisée en x = "+uIT.getIleU().getX()+" et en y = "+uIT.getIleU().getY();

        nbClicSurAide ++;

        return mess;
    }

    @Override
    public UnionIleTechnique chercherIle() {

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
                    tempCase = this.getIle(i,j);
                    if (tempCase instanceof IleImpl){   //si l'île existe
                        tempIle = (IleImpl)tempCase;
                        if(!(tempIle.isComplete())) { //si l'île n'est pas complète
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

            return new UnionIleTechnique(aideIle,lTech[fIndMin]);

        }
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