package fr.hashimiste.gui.modelibre;

import fr.hashimiste.maps.Grille;

import java.awt.*;

public class GridComponent extends PreviewComponent{


    /**
     * Créer un composant de prévisualisation
     *
     * @param color  la couleur du composant
     * @param grille
     */
    public GridComponent(Color color, Grille grille) {
        super(color, grille);
    }


    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
    }
}
