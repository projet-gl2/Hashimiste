package fr.hashimiste.gui.modelibre;

import fr.hashimiste.maps.Grille;
import fr.hashimiste.maps.Ile;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Random;

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
        this.setMinimumSize(new Dimension(50,50));
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
                int t = d / 7;
                g.drawOval(x + t * ile.getX(), y + t *ile.getY(), t, t);
                g.setFont(new Font("Andale Mono", Font.BOLD, t));
                g.drawString(String.valueOf(ile.getNbPontPossible()), x + t*ile.getX()+t/2/2, y + t*ile.getY()+(t)-t/6);
                //grille.getIles().get(0).paint(g);
            }
        }

    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }
}
