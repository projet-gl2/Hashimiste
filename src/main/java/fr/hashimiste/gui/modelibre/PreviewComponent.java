package fr.hashimiste.gui;

import javax.swing.*;
import java.awt.*;
import java.util.Random;

public class PreviewComponent extends JComponent {

    private Color color;

    /**
     *
     *  Créer un composant de prévisualisation
     *
     * @param color     la couleur du composant
     */
    public PreviewComponent(Color color)
    {
        this.color = color;
        //this.setMaximumSize(new Dimension(50,50));
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
    }
}
