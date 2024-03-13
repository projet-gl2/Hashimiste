package fr.hashimiste.impl.gui.builder;

import fr.hashimiste.core.jeu.Grille;
import fr.hashimiste.core.jeu.Ile;
import fr.hashimiste.impl.jeu.IleImpl;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * Cette classe représente une cellule dans l'interface graphique du jeu.
 * Chaque cellule correspond à une île dans la grille du jeu.
 */
public class Cell extends JComponent {

    private final transient Grille grille;
    private final int ileX;
    private final int ileY;
    private int n = 0;

    /**
     * Constructeur de la classe Cell.
     *
     * @param ileX   la position X de l'île dans la grille.
     * @param ileY   la position Y de l'île dans la grille.
     * @param grille la grille du jeu.
     */
    public Cell(int ileX, int ileY, Grille grille) {
        super();
        this.ileX = ileX;
        this.ileY = ileY;
        this.grille = grille;
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                if (SwingUtilities.isLeftMouseButton(e)) {
                    n++;
                    if (n > 9) {
                        n = 0;
                    }
                } else if (SwingUtilities.isRightMouseButton(e)) {
                    n--;
                    if (n < 0) {
                        n = 9;
                    }
                }
                repaint();
            }
        });
        setPreferredSize(new Dimension(100, 100));
    }

    /**
     * Constructeur de la classe Cell.
     *
     * @param ile    une instance de la classe Ile.
     * @param grille la grille du jeu.
     */
    public Cell(Ile ile, Grille grille) {
        this(ile.getX(), ile.getY(), grille);
        this.n = ile.getN();
    }

    /**
     * Cette méthode est utilisée pour dessiner la cellule.
     *
     * @param g l'instance de Graphics utilisée pour dessiner la cellule.
     */
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.WHITE);
        g.fillRect(0, 0, getWidth(), getHeight());
        g.setColor(Color.BLACK);
        g.drawRect(0, 0, getWidth(), getHeight());
        if (n > 0) {
            g.drawOval(getWidth() / 4, getHeight() / 4, getWidth() / 2, getHeight() / 2);
            g.drawString(String.valueOf(n), getWidth() / 2, getHeight() / 2);
        }
    }

    /**
     * Cette méthode est utilisée pour convertir la cellule en une instance de la classe Ile.
     *
     * @return une instance de la classe Ile correspondant à la cellule.
     */
    public Ile conversionIle() {
        return new IleImpl(ileX, ileY, n, grille);
    }

    /**
     * Cette méthode est utilisée pour obtenir la valeur de n.
     *
     * @return la valeur de n.
     */
    public int getN() {
        return n;
    }
}