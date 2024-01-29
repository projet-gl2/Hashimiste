package fr.hashimiste.maps;

import java.awt.*;

public class Pont extends Component {
    private final Ile ile1;
    private final Ile ile2;
    private int n;

    public Pont(Ile ile1, Ile ile2, int n) {
        this.ile1 = ile1;
        this.ile2 = ile2;
        this.n = n;
    }

    @Override
    public void paint(Graphics g) {
        g.setColor(Color.BLACK);
        boolean horizontal = ile1.getX() == ile2.getX() && ile1.getY() != ile2.getY();
        for (int i = 0; i < n; i++) {
            if (horizontal) {
                g.drawLine(ile1.getX() + 20 + i * 20, ile1.getY(), ile2.getX() + 20 + i * 20, ile2.getY());
            } else {
                g.drawLine(ile1.getX(), ile1.getY() + 20 + i * 20, ile2.getX(), ile2.getY() + 20 + i * 20);
            }
        }
        super.paint(g);
    }

    public void setN(int n) {
        this.n = n;
    }

    public Ile getIle1() {
        return ile1;
    }

    public Ile getIle2() {
        return ile2;
    }

    public int getN() {
        return n;
    }
}
