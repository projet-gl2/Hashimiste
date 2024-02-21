package fr.hashimiste.gui.modelibre;

import fr.hashimiste.Difficulte;
import fr.hashimiste.gui.Couleur;
import fr.hashimiste.gui.modelibre.components.GridComponent;
import fr.hashimiste.maps.Grille;
import fr.hashimiste.maps.Ile;

import javax.swing.*;
import java.awt.*;
import java.util.Arrays;

public class GrilleJeu extends JFrame {


    Grille grille = new Grille(new Dimension(7,7), Difficulte.FACILE, Arrays.asList(new Ile(null, 1,1,3),
            new Ile(null, 1,1,3),
            new Ile(null, 5,3,3),
            new Ile(null, 6,0, 2),
            new Ile(null, 0,0, 2),
            new Ile(null,0,6,6),
            new Ile(null, 6,6,5)
    ));

    public GrilleJeu()
    {
        //this.setLayout(null);
        this.setSize(400,400);
        this.setLocationRelativeTo(null);
        this.setVisible(true);

        GridComponent grid = new GridComponent(Couleur.COULEUR_BOUTON, grille);
        grid.setLocation(10,10);
        grid.setSize(this.getWidth()-50, this.getWidth()-50);
        this.add(grid);

        JButton b = new JButton();
        b.setBounds(10,10,this.getWidth(),50);
        //this.add(b);
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
    }
}
