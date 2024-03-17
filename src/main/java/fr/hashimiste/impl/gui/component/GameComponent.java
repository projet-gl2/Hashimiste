package fr.hashimiste.impl.gui.component;

import fr.hashimiste.core.jeu.Direction;
import fr.hashimiste.core.jeu.Grille;
import fr.hashimiste.core.jeu.Ile;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.awt.geom.Line2D;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class GameComponent extends PreviewComponent implements MouseMotionListener {


    public class Bridge{

        Ile ile1;
        Ile ile2;
        boolean hor;
        public Bridge(Ile ile1, Ile ile2, boolean hor)
        {
            this.ile1 = ile1;
            this.ile2 = ile2;
            this.hor = hor;
        }

        public Ile getIle1() {
            return ile1;
        }

        public Ile getIle2() {
            return ile2;
        }
    }

    private boolean hoveringBridge = false;
    private int hoveredBridgeRow = -1;
    private int hoveredBridgeCol = -1;

    private List<Bridge> hoverBridge;


    /**
     * Constructeur de la classe GameComponent.
     *
     * @param grille la grille à prévisualiser.
     */
    public GameComponent(Grille grille) {
        super(grille);
        hoverBridge = new ArrayList<>();

    }

    /**
     * Cette méthode est utilisée pour dessiner le composant de prévisualisation avec effet de survêtement.
     *
     * @param g l'instance de Graphics utilisée pour dessiner le composant.
     */
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        for(Bridge bridge : hoverBridge)
        {

            double factor = Math.min((getSize().getWidth() - 5) / getGrille().getDimension().width, (getSize().getHeight() - 5) / getGrille().getDimension().height);
            int zeroX = (int) ((getSize().width / 2d) - ((getGrille().getDimension().width * factor) / 2));
            int zeroY = (int) ((getSize().height / 2d) - ((getGrille().getDimension().height * factor) / 2));
            int cell_size = (this.getWidth()-zeroX-zeroX) / getGrille().getDimension().width;


            Graphics2D g2 = (Graphics2D) g;
            g2.setColor(Color.YELLOW);
            g2.setStroke(new BasicStroke(3));
            if(bridge.hor) // dessiner pont horizontal
            {
                g2.draw(new Line2D.Float( zeroX+cell_size*bridge.ile1.getX()+cell_size, zeroY+cell_size*bridge.ile1.getY()+cell_size/2, zeroX+cell_size*bridge.ile2.getX(), zeroY+cell_size*bridge.ile1.getY() + cell_size/2));

            }else{ // dessiner pont vertical
                g2.draw(new Line2D.Float( zeroX+cell_size*bridge.ile1.getX()+cell_size/2, zeroY+cell_size*bridge.ile1.getY()+cell_size, zeroX+cell_size*bridge.ile2.getX()+cell_size/2, zeroY+cell_size*bridge.ile2.getY()));

            }
        }


    }
    @Override
    public void mouseMoved(MouseEvent e) {
        double factor = Math.min((getSize().getWidth() - 5) / getGrille().getDimension().width, (getSize().getHeight() - 5) / getGrille().getDimension().height);
        int zeroX = (int) ((getSize().width / 2d) - ((getGrille().getDimension().width * factor) / 2));
        int zeroY = (int) ((getSize().height / 2d) - ((getGrille().getDimension().height * factor) / 2));
        if (e.getX() >= zeroX && e.getY() >= zeroY) {

            int i = (this.getWidth() - zeroX - zeroX) / getGrille().getDimension().width;
            int x = (e.getX() - zeroX) / i;
            int y = (e.getY() - zeroY) / i;
            Ile Isle = getIsle(x, y);
            boolean isOnIsle = Isle != null;

            Ile ileOuest = checkNearIsle(Direction.OUEST, x, y);
            Ile ileEst = checkNearIsle(Direction.EST, x, y);
            Ile ileNord = checkNearIsle(Direction.NORD, x, y);
            Ile ileSud = checkNearIsle(Direction.SUD, x, y);

            System.out.println("O: " + ileOuest + " | E: " + ileEst + " | N: " + ileNord + " | S: " + ileSud);

            if (!isOnIsle) {
                hoverBridge.clear();
                if (ileOuest != null && ileEst != null) {
                    hoverBridge.add(new Bridge(ileOuest, ileEst, true));
                }

                if (ileNord != null && ileSud != null) {
                    hoverBridge.add(new Bridge(ileNord, ileSud, false));
                }
            } else {
                hoverBridge.clear();
                System.out.println("on isle");
            }

            repaint(); // Repaint the component to reflect changes

            if (isBridgeHover()) {
                System.out.println("bridge: " + hoverBridge.size());
            } else {
                // Do something if no bridge is hovered
            }
        }
    }


    public boolean isBridgeHover()
    {
        return !hoverBridge.isEmpty();
    }

    public Ile getIsle(int x, int y)
    {
        for(Ile ile : getGrille().getIles())
        {
            if(ile.getX() == x && ile.getY() == y) return ile;
        }
        return null;
    }

    public Ile checkNearIsle(Direction dir, int x, int y)
    {
        Ile ile  = null;
        switch(dir)
        {
            case OUEST:
                for(int i = x-1; i >= 0; i--)
                {
                    ile = getIsle(i , y);
                    if(ile != null)break;
                }
                break;

            case EST:
                for(int i = x+1; i <= getGrille().getDimension().width-1; i++)
                {
                    ile = getIsle(i, y);
                    if(ile != null)break;
                }
                break;

            case NORD:
                for(int i = y-1; i>0; i--)
                {
                    ile = getIsle(x, i);
                    if(ile != null)break;
                }
                break;

            case SUD:
                for(int i = y+1; i<getGrille().getDimension().width-1; i++)
                {
                    ile = getIsle(x,i);
                    if(ile != null)break;
                }
                break;
        }

        return ile;
    }



    @Override
    public void mouseDragged(MouseEvent e) {}
}