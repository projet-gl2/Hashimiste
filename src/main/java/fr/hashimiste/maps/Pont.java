package fr.hashimiste.maps;

import java.awt.*;

public class Pont extends Component {
    private final Ile ile1;
    private final Ile ile2;
    private int n;

    /**
     * Créer un pont
     *
     * @param ile1 l'ile 1
     * @param ile2 l'ile 2
     * @param n    le nombre de ponts
     */
    public Pont(Ile ile1, Ile ile2, int n) {
        if (n < 0) {
            throw new IllegalArgumentException("Le nombre de ponts ne peut pas être négatif");
        }
        if (ile1 == null || ile2 == null) {
            throw new IllegalArgumentException("Les iles ne peuvent pas être nulles");
        }
        if (ile1.equals(ile2)) {
            throw new IllegalArgumentException("Les iles ne peuvent pas être les mêmes");
        }
        this.ile1 = ile1;
        this.ile2 = ile2;
        this.n = n;
    }

    @Override
    public void paint(Graphics g) {
        g.setColor(Color.BLACK);
        boolean horizontal = ile1.getY() == ile2.getY();
        for (int i = 0; i < n; i++) {
            if (horizontal) {
                g.drawLine(ile1.getX() + ile1.getWidth() / 2 + i * 20 + ile1.getWidth() / 2,
                        ile1.getY() + ile1.getHeight() / 2,
                        ile2.getX() + ile2.getWidth() / 2 + i * 20 - ile2.getWidth() / 2,
                        ile2.getY() + ile2.getHeight() / 2);
            } else {
                g.drawLine(ile1.getX() + ile1.getHeight() / 2,
                        ile1.getY() + ile1.getHeight() / 2 + i * 20 + ile1.getHeight() / 2,
                        ile2.getX() + ile2.getHeight() / 2,
                        ile2.getY() + ile2.getHeight() / 2 + i * 20 - ile2.getHeight() / 2);
            }
        }
        super.paint(g);
    }

    /**
     * Modifie le nombre de ponts
     *
     * @param n le nouveau nombre de ponts
     */
    public void setN(int n) {
        this.n = n;
    }

    /**
     * Récupérer l'ile 1
     *
     * @return l'ile 1
     */
    public Ile getIle1() {
        return ile1;
    }

    /**
     * Récupérer l'ile 2
     *
     * @return l'ile 2
     */
    public Ile getIle2() {
        return ile2;
    }

    /**
     * Récupérer le nombre de ponts
     *
     * @return le nombre de ponts
     */
    public int getN() {
        return n;
    }
}
