package fr.hashimiste.gui.modelibre.components;

import fr.hashimiste.maps.Grille;
import fr.hashimiste.maps.Ile;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Random;

/**
 * Classe gérant les composants de prévisualisation de grille
 * @author  elie
 */

public class PreviewComponent extends JComponent {

    private Color color;
    private Grille grille;

    /**
     *
     *  Créer un composant de prévisualisation
     *
     * @param color     la couleur du composant
     */
    public PreviewComponent(Color color, Grille grille)
    {
        this.grille = grille;
        this.color = color;
        //this.setMinimumSize(new Dimension(50,50)); inutile car grid layout
    }

    /**
     *
     *  Affiche le composant de prévisualisation
     *
     * @param g the <code>Graphics</code> object to protect
     */
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        int grid_size = grille == null ? 0 : (int)grille.getDimension().getWidth();
        Dimension size = this.getSize();
        int d = Math.min(size.width, size.height) - 10;
        int x = (size.width - d) / 2;
        int y = (size.height - d) / 2;
        g.setColor(this.color);
        g.fillRect(x,y,d,d);
        if(this.grille != null)
        {
            for(Ile ile : grille.getIles()) {
                g.setColor(Color.black);
                g.drawOval(x + (d / grid_size) * ile.getX(), y + (d / grid_size) *ile.getY(), d / grid_size, d / grid_size);
                g.setFont(new Font("Futura", Font.PLAIN, d / grid_size));
                g.drawString(String.valueOf(ile.getN()), x + (d / grid_size)*ile.getX()+(d / grid_size)/2/2, y + (d / grid_size)*ile.getY()+(d / grid_size)-(d / grid_size)/6);
                //grille.getIles().get(0).paint(g);
            }
        }

    }

    /**
     * récupère la couleur du composant
     * @return Color
     */
    public Color getColor() {
        return color;
    }

    /**
     * Définit la couleur du composant
     * @param color
     */
    public void setColor(Color color) {
        this.color = color;
    }


    public Grille getGrille() {
        return grille;
    }

    public void setGrille(Grille grille)
    {
        this.grille = grille;
    }
}
