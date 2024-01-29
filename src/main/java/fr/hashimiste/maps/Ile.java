package fr.hashimiste.maps;

import java.awt.*;

public class Ile extends Component {
    private final int x;
    private final int y;
    private final int nbPont;

    /**
     * Créer une ile
     *
     * @param x      l'abscisse de l'ile
     * @param y      l'ordonnée de l'ile
     * @param nbPont le nombre de ponts que l'ile doit avoir
     */
    public Ile(int x, int y, int nbPont) {
        this.x = x;
        this.y = y;
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
        g.fillOval(x, y, 20, 20);
        g.setColor(Color.WHITE);
        g.drawString(String.valueOf(nbPont), x + 8, y + 14);
        super.paint(g);
    }
}
