package fr.hashimiste.impl.gui.component;

import fr.hashimiste.core.gui.Theme;
import fr.hashimiste.core.jeu.Direction;
import fr.hashimiste.core.jeu.Grille;
import fr.hashimiste.core.jeu.Ile;
import fr.hashimiste.core.jeu.Case;
import fr.hashimiste.impl.gui.theme.DefaultTheme;
import fr.hashimiste.impl.jeu.GrilleImpl;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.geom.Line2D;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Cette classe est une sous classe de previewComponent
 * elle ajoute nottament des interactions
 *
 * @author elie
 */
public class GameComponent extends PreviewComponent implements MouseMotionListener, MouseListener {

    /**
     * Cette classe représente des ponts
     *
     * @author elie
     */
    public class Bridge {

        Ile ile1; // première ile du pont
        Ile ile2; // deuxième ile du pont

        boolean duo = false; // pont double
        boolean hor; // disposition du pont true: horizontal & false: vertical

        /**
         * Constructeur d'un pont potentiel
         *
         * @param ile1
         * @param ile2
         * @param hor
         */
        public Bridge(Ile ile1, Ile ile2, boolean hor) {
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

    /**
     * Liste des ponts potentiels à afficher
     */
    private List<Bridge> potentialsBridges;

    /**
     * Liste des ponts réels à afficher
     */
    private List<Bridge> bridges;

    /**
     * Constructeur de la classe GameComponent.
     *
     * @param grille la grille à prévisualiser.
     */
    public GameComponent(Grille grille) {
        super(grille);
        potentialsBridges = new ArrayList<>();
        bridges = new ArrayList<>();

    }

    /**
     * Cette méthode est utilisée pour dessiner le composant de prévisualisation avec effet de survolement.
     *
     * @param g l'instance de Graphics utilisée pour dessiner le composant.
     */
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        double factor = Math.min((getSize().getWidth() - 5) / getGrille().getDimension().width, (getSize().getHeight() - 5) / getGrille().getDimension().height);
        //System.out.println("factor: " + factor);
        int zeroX = (int) ((getSize().width / 2d) - ((getGrille().getDimension().width * factor) / 2));
        int zeroY = (int) ((getSize().height / 2d) - ((getGrille().getDimension().height * factor) / 2));
        float cell_size = (this.getWidth() - zeroX - zeroX) / getGrille().getDimension().width;
        Graphics2D g2 = (Graphics2D) g;

        float lineThickness = cell_size / 12; // Ajustez le dénominateur selon vos besoins
        g2.setStroke(new BasicStroke(lineThickness));
        cell_size=(float)factor;

        // Espacement entre les deux lignes d'un pont double
        int bridgeSpacing = (int) (cell_size/12);

        // Dessiner les ponts potentiels
        for (Bridge bridge : potentialsBridges) {
            g2.setColor(DefaultTheme.INSTANCE.getPotentialBridgeColor());
            if(BridgeAlreadyExists(bridge) == -1) {
                if (bridge.hor) {
                    g2.draw(new Line2D.Float(zeroX + cell_size * bridge.ile1.getX() + cell_size, zeroY + cell_size * bridge.ile1.getY() + cell_size / 2,
                            zeroX + cell_size * bridge.ile2.getX(), zeroY + cell_size * bridge.ile1.getY() + cell_size / 2));
                } else {
                    g2.draw(new Line2D.Float(zeroX + cell_size * bridge.ile1.getX() + cell_size / 2, zeroY + cell_size * bridge.ile1.getY() + cell_size,
                            zeroX + cell_size * bridge.ile2.getX() + cell_size / 2, zeroY + cell_size * bridge.ile2.getY()));
                }
            }

        }

        // Dessiner les ponts réels
        for (Bridge bridge : bridges) {
            g2.setColor(Color.BLACK);
            if (bridge.duo) { // si pont double
                if (bridge.hor) {
                    g2.draw(new Line2D.Float(zeroX + cell_size * bridge.ile1.getX() + cell_size, zeroY + cell_size * bridge.ile1.getY() + cell_size / 2 - bridgeSpacing,
                            zeroX + cell_size * bridge.ile2.getX(), zeroY + cell_size * bridge.ile1.getY() + cell_size / 2 - bridgeSpacing));
                    g2.draw(new Line2D.Float(zeroX + cell_size * bridge.ile1.getX() + cell_size, zeroY + cell_size * bridge.ile1.getY() + cell_size / 2 + bridgeSpacing,
                            zeroX + cell_size * bridge.ile2.getX(), zeroY + cell_size * bridge.ile1.getY() + cell_size / 2 + bridgeSpacing));
                } else {
                    g2.draw(new Line2D.Float(zeroX + cell_size * bridge.ile1.getX() + cell_size / 2 - bridgeSpacing, zeroY + cell_size * bridge.ile1.getY() + cell_size,
                            zeroX + cell_size * bridge.ile2.getX() + cell_size / 2 - bridgeSpacing, zeroY + cell_size * bridge.ile2.getY()));
                    g2.draw(new Line2D.Float(zeroX + cell_size * bridge.ile1.getX() + cell_size / 2 + bridgeSpacing, zeroY + cell_size * bridge.ile1.getY() + cell_size,
                            zeroX + cell_size * bridge.ile2.getX() + cell_size / 2 + bridgeSpacing, zeroY + cell_size * bridge.ile2.getY()));
                }
            } else { // sinon si pont simple
                if (bridge.hor) {
                    g2.draw(new Line2D.Float(zeroX + cell_size * bridge.ile1.getX() + cell_size, zeroY + cell_size * bridge.ile1.getY() + cell_size / 2,
                            zeroX + cell_size * bridge.ile2.getX(), zeroY + cell_size * bridge.ile1.getY() + cell_size / 2));
                } else {
                    g2.draw(new Line2D.Float(zeroX + cell_size * bridge.ile1.getX() + cell_size / 2, zeroY + cell_size * bridge.ile1.getY() + cell_size,
                            zeroX + cell_size * bridge.ile2.getX() + cell_size / 2, zeroY + cell_size * bridge.ile2.getY()));
                }
            }
        }
    }


    /**
     * Evenement de mouvement de la souris
     * @param e the event to be processed
     */
    @Override
    public void mouseMoved(MouseEvent e) {

        refreshBridge(e.getX(), e.getY());
    }

    /**
     * rafraichit la liste des ponts potentiels en fonction de la position de la souris
     * @param souris_x
     * @param souris_y
     */
    public void refreshBridge(int souris_x, int souris_y) {
        potentialsBridges.clear();
        double factor = Math.min((getSize().getWidth() - 5) / getGrille().getDimension().width, (getSize().getHeight() - 5) / getGrille().getDimension().height);
        int zeroX = (int) ((getSize().width / 2d) - ((getGrille().getDimension().width * factor) / 2));
        int zeroY = (int) ((getSize().height / 2d) - ((getGrille().getDimension().height * factor) / 2));

        if (souris_x >= zeroX && souris_y >= zeroY) {
            int i = (this.getWidth() - zeroX - zeroX) / getGrille().getDimension().width;
            int x = (souris_x - zeroX) / i;
            int y = (souris_y - zeroY) / i;
            Ile ile = getIsle(x, y);
            boolean isOnIsle = ile != null;
            System.out.println(isOnIsle);

            Ile ileOuest = checkNearIsle(Direction.OUEST, x, y);
            Ile ileEst = checkNearIsle(Direction.EST, x, y);
            Ile ileNord = checkNearIsle(Direction.NORD, x, y);
            Ile ileSud = checkNearIsle(Direction.SUD, x, y);

            if (!isOnIsle) {
                if (ileOuest != null && ileEst != null) {
                    potentialsBridges.add(new Bridge(ileOuest, ileEst, true));
                }

                if (ileNord != null && ileSud != null) {
                    potentialsBridges.add(new Bridge(ileNord, ileSud, false));
                }
            } else {
                for (Bridge bridge : bridges) {
                    if ((bridge.ile1 == ile || bridge.ile2 == ile) && !bridge.duo) {
                        potentialsBridges.add(bridge);
                        break;
                    }
                }
                if (ileOuest != null) {
                    potentialsBridges.add(new Bridge(ileOuest, ile, true));
                }
                if (ileEst != null) {
                    potentialsBridges.add(new Bridge(ile, ileEst, true));
                }
                if (ileSud != null) {
                    potentialsBridges.add(new Bridge(ile, ileSud, false));
                }
                if (ileNord != null) {
                    potentialsBridges.add(new Bridge(ileNord, ile, false));
                }
            }
        }
        repaint();
    }


    /**
     * Retourne vrai si au moins un potentiel pont est survolé
     *
     * @return boolean
     */
    public boolean isBridgeHover() {
        return !potentialsBridges.isEmpty();
    }

    /**
     * Retourne l'ile de la grille correspondant au coordonnées, null sinon
     *
     * @param x position x
     * @param y position y
     * @return Ile
     */
    public Ile getIsle(int x, int y) {
        for (Case ile : getGrille().getIles()) {
            if (ile instanceof Ile && ile.getX() == x && ile.getY() == y) return (Ile)ile;
        }
        return null;
    }

    /**
     * Retourne l'ile la plus proche dans la direction passé en paramètre, null sinon
     *
     * @param dir direction
     * @param x   position x de l'ile de départ
     * @param y   position y de l'ile de départ
     * @return
     */
    public Ile checkNearIsle(Direction dir, int x, int y) {
        Ile ile = null;
        switch (dir) {
            case OUEST:
                for (int i = x - 1; i >= 0; i--) {
                    ile = getIsle(i, y);
                    if (ile != null) break;
                }
                break;
            case EST:
                for (int i = x + 1; i < getGrille().getDimension().width; i++) {
                    ile = getIsle(i, y);
                    if (ile != null) break;
                }
                break;
            case NORD:
                for (int i = y - 1; i >= 0; i--) {
                    ile = getIsle(x, i);
                    if (ile != null) break;
                }
                break;
            case SUD:
                for (int i = y + 1; i < getGrille().getDimension().height; i++) {
                    ile = getIsle(x, i);
                    if (ile != null) break;
                }
                break;
        }
        return ile;
    }

    @Override
    public void mouseDragged(MouseEvent e) {
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    /**
     * Retourne l'index du pont potentiel le plus proche
     * @param souris_x
     * @param souris_y
     * @return index
     */
    public int getNearestBridge(int souris_x, int souris_y)
    {
        double factor = Math.min((getSize().getWidth() - 5) / getGrille().getDimension().width, (getSize().getHeight() - 5) / getGrille().getDimension().height);
        int zeroX = (int) ((getSize().width / 2d) - ((getGrille().getDimension().width * factor) / 2));
        int zeroY = (int) ((getSize().height / 2d) - ((getGrille().getDimension().height * factor) / 2));
        int i = (this.getWidth() - zeroX - zeroX) / getGrille().getDimension().width;
        int x = (souris_x - zeroX) / i;
        int y = (souris_y - zeroY) / i;
        if(potentialsBridges.size() > 1 && getIsle(x,y) == null)
        {
            double sy = souris_y-(zeroY + y*factor);
            double quarter = factor/4;
            System.out.println("x: " + x + " y:" + y + " sx:" + souris_x + " sy" + (sy));
            if(sy < quarter || sy > quarter*3)
            {
                return 1;
            }else{
                return 0;
            }
            //System.out.println(factor);
        }
        return 0;
    }

    /**
     * Evenement de clique de la souris
     * @param e the event to be processed
     */
    @Override
    public void mousePressed(MouseEvent e) {
        // Si des ponts potentiels sont détectés
        if (isBridgeHover()) {
            Bridge selectedBridge = potentialsBridges.get(getNearestBridge(e.getX(), e.getY()));
            int index = BridgeAlreadyExists(selectedBridge);

            System.out.println("index: " + index);
            if (index < 0) {
                // Ajouter le pont à la liste des ponts
                bridges.add(selectedBridge);
                ((GrilleImpl) getGrille()).poserPont(selectedBridge.ile1, selectedBridge.ile2, 1);
            } else {
                Bridge currentBridge = bridges.get(index);

                // Le pont est déjà dans la liste, le rendre double ou le supprimer
                if (currentBridge.duo) {
                    // Supprimer le pont
                    bridges.remove(index);
                } else {
                    // Rendre le pont double
                    currentBridge.duo = true;
                    ((GrilleImpl) getGrille()).poserPont(selectedBridge.ile1, selectedBridge.ile2, 2);
                }
            }

            // Rafraîchir les ponts potentiels après chaque clic de souris
            refreshBridge(e.getX(), e.getY());

            repaint();
            //System.out.println("pb: " + potentialsBridges.size());
            //System.out.println("b: " + bridges.size());
        }
    }


    /**
     * methode qui retourne si le pont passer en paramètre existe déjà et retourne sont index ou -1 sinon
     * @param bridge
     * @return index
     */
    private int BridgeAlreadyExists(Bridge bridge) {
        for (int i = 0; i < bridges.size(); i++) {
            Bridge b = bridges.get(i);
            if (bridge.ile1.getX() == b.ile1.getX() && bridge.ile1.getY() == b.ile1.getY() &&
                    bridge.ile2.getX() == b.ile2.getX() && bridge.ile2.getY() == b.ile2.getY()) {
                return i; // Retourner l'index si le pont existe déjà
            }
        }
        return -1; // Retourner -1 si le pont n'existe pas
    }



    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
