package fr.hashimiste.maps;

import java.awt.*;

public class Ile extends Component {
    private final int nbPont;

    /**
     * Créer une ile
     *
     * @param x      l'abscisse de l'ile
     * @param y      l'ordonnée de l'ile
     * @param nbPont le nombre de ponts que l'ile doit avoir
     */
    public Ile(int x, int y, int nbPont) {
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

    @Override
    public void paint(Graphics g) {
        g.setColor(Color.BLACK);
        g.drawOval(getX(), getY(), getWidth(), getHeight());
        g.drawString(String.valueOf(nbPont), getX() + 8, getY() + 14);
        super.paint(g);
    }

    /**
     * Récupérer le nombre de ponts de l'ile
     *
     * @return le nombre de ponts de l'ile
     */
    public int getNbPont() {
        return nbPont;
    }

    @Override
    public String toString() {
        return "Ile{" +
                "x=" + getX() +
                ", y=" + getY() +
                ", nbPont=" + nbPont +
                '}';
    }
}
