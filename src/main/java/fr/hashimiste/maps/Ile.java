package fr.hashimiste.maps;

import fr.hashimiste.techniques.Direction;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

/**
 * La classe Ile représente une île dans une grille (Grille).
 * Elle contient des informations sur la grille à laquelle elle appartient, ses coordonnées et le nombre de ponts qu'elle doit avoir.
 * Elle contient également une liste de ponts (Pont) qui lui sont liés.
 * Elle fournit des méthodes pour créer un pont vers une autre île, récupérer des informations sur l'île et ses ponts, et vérifier si l'île est complète (c'est-à-dire si tous ses ponts ont été créés).
 */
public class Ile extends Component {
    private final Grille grille;
    private final int nbPont;
    private final List<Pont> pontsLiees = new ArrayList<>();

    /**
     * Créer une ile
     *
     * @param grille la grille de l'ile
     * @param x      l'abscisse de l'ile
     * @param y      l'ordonnée de l'ile
     * @param nbPont le nombre de ponts que l'ile doit avoir
     * @throws IllegalArgumentException si le nombre de ponts est négatif ou égal à 0
     */
    public Ile(Grille grille, int x, int y, int nbPont) {
        if (nbPont <= 0) {
            throw new IllegalArgumentException("Le nombre de ponts ne peut pas être négatif ou égal à 0");
        }
        this.grille = grille;
        setLocation(x, y);
        setSize(20, 20);
        this.nbPont = nbPont;
    }

    /**
     * Créer un pont entre l'ile et une autre ile
     *
     * @param ile l'ile à relier
     * @param n   le nombre de ponts à créer
     * @return le pont créé
     */
    public Pont creerPont(Ile ile, int n) {
        return new Pont(this, ile, n);
    }

    /**
     * Récupérer la grille de l'ile
     *
     * @return la grille de l'ile
     */
    public Grille getGrille() {
        return grille;
    }

    @Override
    public void paint(Graphics g) {
        g.setColor(Color.BLACK);
        g.drawOval(getX(), getY(), getWidth(), getHeight());
        g.drawString(String.valueOf(getNbPontPossible()), getX() + 8, getY() + 14);
        super.paint(g);
    }

    /**
     * Récupérer le nombre de ponts de l'ile
     *
     * @return le nombre de ponts de l'ile
     */
    public int getNbPontPossible() {
        return nbPont;
    }

    /**
     * Récupérer le nombre de ponts liés à l'ile
     *
     * @return le nombre de ponts liés à l'ile
     */
    public int getNbPont() {
        return pontsLiees.stream().mapToInt(Pont::getN).sum();
    }

    /**
     * Vérifier si l'ile est complète
     *
     * @return true si l'ile est complète, false sinon
     */
    public boolean isComplete() {
        return getNbPont() == getNbPontPossible();
    }

    /**
     * Retourne le nombre de ponts liés à l'ile qui vérifient le filtre
     *
     * @param filtre le filtre à appliquer
     * @return le nombre de ponts liés à l'ile qui vérifient le filtre
     */
    public int getNbPont(Predicate<Ile> filtre) {
        return pontsLiees.stream().filter(pont -> pont.getIle1() == this || pont.getIle2() == this).filter(pont -> filtre.test(pont.getIle1()) || filtre.test(pont.getIle2())).mapToInt(Pont::getN).sum();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Ile ile = (Ile) o;
        return getNbPontPossible() == ile.getNbPontPossible() && getX() == ile.getX() && getY() == ile.getY();
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 31 * hash + getX();
        hash = 31 * hash + getY();
        hash = 31 * hash + getNbPontPossible();
        return hash;
    }

    @Override
    public String toString() {
        return "Ile{" +
                "x=" + getX() +
                ", y=" + getY() +
                ", nbPont=" + nbPont +
                '}';
    }

    /**
     * Ajoute un pont à l'ile
     *
     * @param pont le pont à ajouter
     */
    void addPont(Pont pont) {
        pontsLiees.add(pont);
    }



    /**
     * Vérifie la valeur de l'île en face de l'île appelée dans la direction donnée.
     * @param d Direction à vérifier peut être NORD, EST, SUD ou OUEST
     * @return -1 s'il n'y aucune île accessible, la valeur de l'île en face sinon.
     */
    int valeurIleDirection(Direction d){
        //**A FAIRE**//
        return 0;
    }
}
