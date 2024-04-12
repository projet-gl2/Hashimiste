package fr.hashimiste.impl.gui.component;

import fr.hashimiste.core.image.AppImage;
import fr.hashimiste.core.jeu.Case;
import fr.hashimiste.core.jeu.Grille;
import fr.hashimiste.core.jeu.Ile;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;

/**
 * Cette classe représente un composant de prévisualisation de la grille du jeu.
 * Elle hérite de JComponent et affiche une représentation graphique de la grille.
 */
public class PreviewComponent extends JComponent {
    private transient Grille grille;

    /**
     * Constructeur de la classe PreviewComponent.
     *
     * @param grille la grille à prévisualiser.
     */
    public PreviewComponent(Grille grille) {
        this.grille = grille;
    }

    public void setGrille(Grille grille) {
        this.grille = grille;
        repaint();
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
            BufferedImage logo = AppImage.INSTANCE.getLogo(true);
            double factor = Math.min((getSize().getWidth() - 5) / logo.getWidth(), (getSize().getHeight() - 5) / logo.getHeight());
            int zeroX = (int) ((getSize().width / 2d) - ((logo.getWidth() * factor) / 2));
            int zeroY = (int) ((getSize().height / 2d) - ((logo.getHeight() * factor) / 2));
            g.drawImage(logo, zeroX, zeroY, (int) (logo.getWidth() * factor), (int) (logo.getHeight() * factor), null);
            return;
        } else if (grille.getDimension().width == 0 || grille.getDimension().height == 0) {
            return;
        }
        double factor = Math.min((getSize().getWidth() - 5) / grille.getDimension().width, (getSize().getHeight() - 5) / grille.getDimension().height);
        int zeroX = (int) ((getSize().width / 2d) - ((grille.getDimension().width * factor) / 2));
        int zeroY = (int) ((getSize().height / 2d) - ((grille.getDimension().height * factor) / 2));
        Font font = new Font("Arial", Font.PLAIN, (int) (factor));
        g.drawRect(zeroX, zeroY, (int) (grille.getDimension().width * factor), (int) (grille.getDimension().height * factor));
        for (Case c : grille.getIles()) {
            if (c instanceof Ile) {
                Ile ile = (Ile) c;
                g.setColor(Color.BLACK);
                g.drawOval((int) (zeroX + ile.getX() * factor), (int) (zeroY + ile.getY() * factor), (int) (factor), (int) (factor));
                if (ile.getN() >= -26) {
                    String n = String.valueOf(ile.getN());
                    if (ile.getN() < 0) {
                        n = String.valueOf(Character.toChars('A' + -ile.getN() - 1)[0]);
                    }
                    g.setFont(font);
                    Rectangle2D d = g.getFontMetrics().getStringBounds(n, g);
                    g.drawString(n, (int) (zeroX + ile.getX() * factor + d.getCenterX()), (int) (zeroY + ile.getY() * factor + (d.getMaxY() * 3.5)));
                }
            }
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