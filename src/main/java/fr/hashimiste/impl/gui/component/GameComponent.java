package fr.hashimiste.impl.gui.component;

import fr.hashimiste.core.jeu.Grille;
import fr.hashimiste.core.jeu.Ile;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

public class GameComponent extends PreviewComponent implements MouseMotionListener {

    private boolean hoveringBridge = false;
    private int hoveredBridgeRow = -1;
    private int hoveredBridgeCol = -1;

    /**
     * Constructeur de la classe GameComponent.
     *
     * @param grille la grille à prévisualiser.
     */
    public GameComponent(Grille grille) {
        super(grille);


    }

    /**
     * Cette méthode est utilisée pour dessiner le composant de prévisualisation avec effet de survêtement.
     *
     * @param g l'instance de Graphics utilisée pour dessiner le composant.
     */
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        if (hoveringBridge) {
            g.setColor(Color.YELLOW); // Couleur du survol du pont
            g.fillRect(hoveredBridgeCol * getCellSize(), hoveredBridgeRow * getCellSize(), getCellSize(), getCellSize());
        }
    }

    int getCellSize()
    {
        return this.getWidth()/getGrille().getDimension().width;
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        double factor = Math.min((getSize().getWidth() - 5) / getGrille().getDimension().width, (getSize().getHeight() - 5) / getGrille().getDimension().height);
        int zeroX = (int) ((getSize().width / 2d) - ((getGrille().getDimension().width * factor) / 2));
        int zeroY = (int) ((getSize().height / 2d) - ((getGrille().getDimension().height * factor) / 2));
        if(e.getX() >= zeroX && e.getY() >= zeroY) {
            // Imprimez les coordonnées de la souris pour vérifier si l'événement de mouvement de la souris est déclenché


            int x = (e.getX()-zeroX);
            int y = (e.getY()-zeroY);
            int i = (this.getWidth()-zeroX-zeroX) / getGrille().getDimension().width;
            System.out.println(x / i + " : " + y / i);
        }
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        // Imprimez les coordonnées de la souris pour vérifier si l'événement de glissement de la souris est déclenché
        System.out.println("Mouse dragged: (" + e.getX() + ", " + e.getY() + ")");
    }
}
