package fr.hashimiste.impl.gui.component;

import fr.hashimiste.core.jeu.Direction;
import fr.hashimiste.core.jeu.Grille;
import fr.hashimiste.core.jeu.Ile;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
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
            //System.out.println(x / i + " : " + y / i);
            Ile Isle = getIsle(x/i, y/i);
            boolean isOnIsle = Isle != null;
            /*System.out.println("on isle : " + isOnIsle);
            System.out.println("isle on left: " + isIsleOnLeft(x/i, y/i));
            System.out.println("isle on right: " + isIsleOnRight(x/i, y/i));
            System.out.println("isle on top: " + isIsleOnTop(x/i, y/i));
            System.out.println("isle on bottom: " + isIsleOnBottom(x/i, y/i));*/

            Ile ileOuest = checkNearIsle(Direction.OUEST,x/i, y/i);
            Ile ileEst = checkNearIsle(Direction.EST,x/i, y/i);
            Ile ileNord = checkNearIsle(Direction.NORD, x/i, y/i);
            Ile ileSud = checkNearIsle(Direction.SUD,x/i, y/i);


            if(!isOnIsle)
            {
                hoverBridge.clear();
                if(ileOuest != null && ileEst != null)
                {
                    hoverBridge.add(new Bridge(ileOuest, ileEst, true));
                }

                if(ileNord != null && ileSud != null)
                {
                    hoverBridge.add(new Bridge(ileNord, ileSud, false));
                }

                if(ileOuest == null && ileEst == null && ileNord == null && ileSud == null)
                {
                    hoverBridge.clear();
                }



            }else{


            }

            if(isBridgeHover())
            {
                System.out.println("bridge: " + hoverBridge.size());
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
                }
                break;

            case EST:
                for(int i = x+1; i <= getGrille().getDimension().width-1; i++)
                {
                    ile = getIsle(i, y);
                }
                break;

            case NORD:
                for(int i = y-1; i>0; i--)
                {
                    ile = getIsle(x, i);
                }
                break;

            case SUD:
                for(int i = y+1; i<getGrille().getDimension().width-1; i++)
                {
                    ile = getIsle(x,i);
                }
                break;
        }

        return ile;
    }



    @Override
    public void mouseDragged(MouseEvent e) {}
}
