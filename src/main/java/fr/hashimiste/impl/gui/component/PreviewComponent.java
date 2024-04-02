package fr.hashimiste.impl.gui.component;

import fr.hashimiste.core.jeu.Grille;
import fr.hashimiste.core.jeu.Ile;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Rectangle2D;

/**
 * Cette classe représente un composant de prévisualisation de la grille du jeu.
 * Elle hérite de JComponent et affiche une représentation graphique de la grille.
 */
public class PreviewComponent extends JComponent {
    private final transient Grille grille;

    /**
     * Constructeur de la classe PreviewComponent.
     *
     * @param grille la grille à prévisualiser.
     */
    public PreviewComponent(Grille grille) {
        this.grille = grille;
    }

    /**
     * Cette méthode est utilisée pour dessiner le composant de prévisualisation.
     * Elle dessine une représentation graphique de la grille.
     *
     * @param g l'instance de Graphics utilisée pour dessiner le composant.
     */
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (grille == null) {
            return;
        }
        double factor = Math.min((getSize().getWidth() - 5) / grille.getDimension().width, (getSize().getHeight() - 5) / grille.getDimension().height);
        int zeroX = (int) ((getSize().width / 2d) - ((grille.getDimension().width * factor) / 2));
        int zeroY = (int) ((getSize().height / 2d) - ((grille.getDimension().height * factor) / 2));
        Font font = new Font("Arial", Font.PLAIN, (int) (factor));
        g.drawRect(zeroX, zeroY, (int) (grille.getDimension().width * factor), (int) (grille.getDimension().height * factor));
        for (Ile ile : grille.getIles()) {
            g.setColor(Color.BLACK);
            //System.out.println(ile.getX() + " : " + ile.getY());
            g.drawOval((int) (zeroX + ile.getX() * factor), (int) (zeroY + ile.getY() * factor), (int) (factor), (int) (factor));
            g.setFont(font);
            Rectangle2D d = g.getFontMetrics().getStringBounds(String.valueOf(ile.getN()), g);
            g.drawString(String.valueOf(ile.getN()), (int) (zeroX + ile.getX() * factor + d.getCenterX()), (int) (zeroY + ile.getY() * factor + (d.getMaxY() * 3.5)));
        }
    }

    /**
     * Cette méthode est utilisée pour obtenir la grille actuellement prévisualisée.
     *
     * @return la grille actuellement prévisualisée.
     */
    public Grille getGrille() {
        return grille;
    }
}