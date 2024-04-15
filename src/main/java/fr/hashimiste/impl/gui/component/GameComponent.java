package fr.hashimiste.impl.gui.component;

import fr.hashimiste.core.jeu.*;
import fr.hashimiste.core.jeu.Historique.Action;
import fr.hashimiste.impl.gui.theme.DefaultTheme;
import fr.hashimiste.impl.jeu.GrilleImpl;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.geom.Line2D;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * Cette classe est une sous classe de previewComponent
 * elle ajoute nottament des interactions
 *
 * @author elie
 */
public abstract class GameComponent extends PreviewComponent implements MouseMotionListener, MouseListener {

    /**
     * Liste des ponts potentiels à afficher
     */
    private final List<Bridge> potentialsBridges;
    /**
     * Liste des ponts réels à afficher
     */
    private final List<Bridge> bridges;

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
        if (getGrille() == null) return;

        // calcul des constantes
        final double factor = Math.min((getSize().getWidth() - 5) / getGrille().getDimension().width, (getSize().getHeight() - 5) / getGrille().getDimension().height);
        final int zeroX = (int) ((getSize().width / 2d) - ((getGrille().getDimension().width * factor) / 2));
        final int zeroY = (int) ((getSize().height / 2d) - ((getGrille().getDimension().height * factor) / 2));
        final Graphics2D g2 = (Graphics2D) g;
        final float cell_size = (float) factor;
        float lineThickness = ((this.getWidth() - zeroX - zeroX) / getGrille().getDimension().width) / 12;

        // definition épaisseur d'un pont potentiel
        g2.setStroke(new BasicStroke(lineThickness * 4));

        // Espacement entre les deux lignes d'un pont double
        int bridgeSpacing = (int) (cell_size / 12);

        // Dessiner les ponts potentiels
        //
        for (Bridge bridge : potentialsBridges) {

            // selection de la couleur du pont à dessiner en fonction de si il est posable ou non
            Color color = isCrossing(bridge) ? new Color(237,0,16, 80) : DefaultTheme.INSTANCE.getPotentialBridgeColor();
            g2.setColor(color);

            // Si les deux ils du pont ne sont pas pleines
            if(!isIsleFull(bridge.getIle1()) && !isIsleFull(bridge.getIle2())) {

                // Si le pont est horizontal
                if (estHorizontal(bridge)) {
                    // dessin du pont
                    g2.draw(new Line2D.Float(zeroX + cell_size * bridge.ile1.getX() + cell_size + bridgeSpacing, zeroY + cell_size * bridge.ile1.getY() + cell_size / 2,
                            zeroX + cell_size * bridge.ile2.getX() - bridgeSpacing, zeroY + cell_size * bridge.ile1.getY() + cell_size / 2));
                } else { // sinon (vertical)
                    // dessin du pont
                    g2.draw(new Line2D.Float(zeroX + cell_size * bridge.ile1.getX() + cell_size / 2, zeroY + cell_size * bridge.ile1.getY() + cell_size + bridgeSpacing,
                            zeroX + cell_size * bridge.ile2.getX() + cell_size / 2, zeroY + cell_size * bridge.ile2.getY() - bridgeSpacing));
                }
            }

        }

        // defibition épaisseur d'un pont reel
        g2.setStroke(new BasicStroke(lineThickness));

        // definition de la couleur du pont
        g2.setColor(Color.BLACK);

        // Dessiner les ponts réels
        //
        for (Bridge bridge : bridges) {

            // barrer les iles complètes
            if(isIsleFull(bridge.getIle1())) // ile1
            {
                g2.draw(new Line2D.Float(zeroX + cell_size * bridge.getIle1().getX()+cell_size/5, zeroY +cell_size *bridge.getIle1().getY()+cell_size/5, zeroX + cell_size * bridge.getIle1().getX()+cell_size-cell_size/5, zeroY +cell_size *bridge.getIle1().getY()+cell_size-cell_size/5));
            }
            if(isIsleFull(bridge.getIle2())) // ile2
            {
                g2.draw(new Line2D.Float(zeroX + cell_size * bridge.getIle2().getX()+cell_size/5, zeroY +cell_size *bridge.getIle2().getY()+cell_size/5, zeroX + cell_size * bridge.getIle2().getX()+cell_size-cell_size/5, zeroY +cell_size *bridge.getIle2().getY()+cell_size-cell_size/5));
            }

            if (bridge.n == 2) { // si pont double
                if (estHorizontal(bridge)) { // dessin pont hozizontal
                    g2.draw(new Line2D.Float(zeroX + cell_size * bridge.ile1.getX() + cell_size, zeroY + cell_size * bridge.ile1.getY() + cell_size / 2 - bridgeSpacing,
                            zeroX + cell_size * bridge.ile2.getX(), zeroY + cell_size * bridge.ile1.getY() + cell_size / 2 - bridgeSpacing));
                    g2.draw(new Line2D.Float(zeroX + cell_size * bridge.ile1.getX() + cell_size, zeroY + cell_size * bridge.ile1.getY() + cell_size / 2 + bridgeSpacing,
                            zeroX + cell_size * bridge.ile2.getX(), zeroY + cell_size * bridge.ile1.getY() + cell_size / 2 + bridgeSpacing));
                } else {                    // dessin pont vertical
                    g2.draw(new Line2D.Float(zeroX + cell_size * bridge.ile1.getX() + cell_size / 2 - bridgeSpacing, zeroY + cell_size * bridge.ile1.getY() + cell_size,
                            zeroX + cell_size * bridge.ile2.getX() + cell_size / 2 - bridgeSpacing, zeroY + cell_size * bridge.ile2.getY()));
                    g2.draw(new Line2D.Float(zeroX + cell_size * bridge.ile1.getX() + cell_size / 2 + bridgeSpacing, zeroY + cell_size * bridge.ile1.getY() + cell_size,
                            zeroX + cell_size * bridge.ile2.getX() + cell_size / 2 + bridgeSpacing, zeroY + cell_size * bridge.ile2.getY()));
                }
            } else { // sinon si pont simple
                if (estHorizontal(bridge)) { // dessin pont hozizontal
                    g2.draw(new Line2D.Float(zeroX + cell_size * bridge.ile1.getX() + cell_size, zeroY + cell_size * bridge.ile1.getY() + cell_size / 2,
                            zeroX + cell_size * bridge.ile2.getX(), zeroY + cell_size * bridge.ile1.getY() + cell_size / 2));
                } else {                    // dessin pont vertical
                    g2.draw(new Line2D.Float(zeroX + cell_size * bridge.ile1.getX() + cell_size / 2, zeroY + cell_size * bridge.ile1.getY() + cell_size,
                            zeroX + cell_size * bridge.ile2.getX() + cell_size / 2, zeroY + cell_size * bridge.ile2.getY()));
                }
            }
        }
    }

    /**
     * Evenement de mouvement de la souris
     *
     * @param e the event to be processed
     */
    @Override
    public void mouseMoved(MouseEvent e) {
        refreshBridge(e.getX(), e.getY());
    }

    /**
     * rafraichit la liste des ponts potentiels en fonction de la position de la souris
     *
     * @param souris_x
     * @param souris_y
     */
    public void refreshBridge(int souris_x, int souris_y) {
        potentialsBridges.clear(); // vide la liste des ponts potentiels

        // calcul des constantes
        final double factor = Math.min((getSize().getWidth() - 5) / getGrille().getDimension().width, (getSize().getHeight() - 5) / getGrille().getDimension().height);
        final int zeroX = (int) ((getSize().width / 2d) - ((getGrille().getDimension().width * factor) / 2));
        final int zeroY = (int) ((getSize().height / 2d) - ((getGrille().getDimension().height * factor) / 2));

        // Si la souris est dans le component
        if (souris_x >= zeroX && souris_y >= zeroY) {
            final int i = (this.getWidth() - zeroX - zeroX) / getGrille().getDimension().width;
            final int x = (souris_x - zeroX) / i; // position x de la case survolé
            final int y = (souris_y - zeroY) / i; // position y de la case survolé
            final Ile ile = getIsle(x, y);
            final boolean isOnIsle = ile != null; // Si la souris est sur une ile

            final Ile ileOuest = checkNearIsle(Direction.OUEST, x, y);
            final Ile ileEst = checkNearIsle(Direction.EST, x, y);
            final Ile ileNord = checkNearIsle(Direction.NORD, x, y);
            final Ile ileSud = checkNearIsle(Direction.SUD, x, y);

            if (!isOnIsle) { // Si la souris n'est pas sur une ile
                if (ileOuest != null && ileEst != null) {
                    potentialsBridges.add(new Bridge(ileOuest, ileEst, -1));
                }

                if (ileNord != null && ileSud != null) {
                    potentialsBridges.add(new Bridge(ileNord, ileSud, -1));
                }
                if (potentialsBridges.size() >= 2) {
                    int t = getNearestBridge(souris_x, souris_y);
                    Bridge b = potentialsBridges.get(t);
                    potentialsBridges.clear();
                    potentialsBridges.add(b);
                }
            } else {
                for (Bridge bridge : bridges) {
                    if ((bridge.ile1 == ile || bridge.ile2 == ile) && bridge.n != 2) {
                        potentialsBridges.add(bridge);
                        break;
                    }
                }
                if (ileOuest != null) {
                    potentialsBridges.add(new Bridge(ileOuest, ile, -1));
                }
                if (ileEst != null) {
                    potentialsBridges.add(new Bridge(ile, ileEst, -1));
                }
                if (ileSud != null) {
                    potentialsBridges.add(new Bridge(ile, ileSud, -1));
                }
                if (ileNord != null) {
                    potentialsBridges.add(new Bridge(ileNord, ile, -1));
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
            if (ile instanceof Ile && ile.getX() == x && ile.getY() == y) return (Ile) ile;
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
     *
     * @param souris_x
     * @param souris_y
     * @return index
     */
    public int getNearestBridge(int souris_x, int souris_y) {
        final double factor = Math.min((getSize().getWidth() - 5) / getGrille().getDimension().width, (getSize().getHeight() - 5) / getGrille().getDimension().height);
        final int zeroX = (int) ((getSize().width / 2d) - ((getGrille().getDimension().width * factor) / 2));
        final int zeroY = (int) ((getSize().height / 2d) - ((getGrille().getDimension().height * factor) / 2));
        final int i = (this.getWidth() - zeroX - zeroX) / getGrille().getDimension().width;
        final int x = (souris_x - zeroX) / i;
        final int y = (souris_y - zeroY) / i;
        if (potentialsBridges.size() > 1 && getIsle(x, y) == null) {


            if(((isIsleFull(potentialsBridges.get(0).getIle1()) && !isIsleFull(potentialsBridges.get(0).getIle2()) || !isIsleFull(potentialsBridges.get(0).getIle1()) && isIsleFull(potentialsBridges.get(0).getIle2())) && !isBridgeExist(potentialsBridges.get(0).getIle1(), potentialsBridges.get(0).getIle2()))) {
                return 1;
            }

            if(((isIsleFull(potentialsBridges.get(1).getIle1()) && !isIsleFull(potentialsBridges.get(1).getIle2()) || !isIsleFull(potentialsBridges.get(1).getIle1()) && isIsleFull(potentialsBridges.get(1).getIle2())) && !isBridgeExist(potentialsBridges.get(1).getIle1(), potentialsBridges.get(1).getIle2()))) {
                return 0;
            }


            final double sy = souris_y - (zeroY + y * factor);
            final double quarter = factor / 4;
            if (sy < quarter || sy > quarter * 3) {
                return 1;
            } else {
                return 0;
            }
        }
        return 0;
    }

    /**
     * Retourne si le nombre de pont relié à l'île est superieur ou égal à sa capacité
     * @param ile
     * @return boolean
     */
    private boolean isIsleFull(Ile ile)
    {
        return ile.getNbPont() >= ile.getN();
    }

    /**
     * Evenement de clique de la souris
     *
     * @param e the event to be processed
     */
    @Override
    public void mousePressed(MouseEvent e) {
        final double factor = Math.min((getSize().getWidth() - 5) / getGrille().getDimension().width, (getSize().getHeight() - 5) / getGrille().getDimension().height);
        final int zeroX = (int) ((getSize().width / 2d) - ((getGrille().getDimension().width * factor) / 2));
        final int zeroY = (int) ((getSize().height / 2d) - ((getGrille().getDimension().height * factor) / 2));
        final int i = (this.getWidth() - zeroX - zeroX) / getGrille().getDimension().width;
        final int x = (e.getX() - zeroX) / i;
        final int y = (e.getY() - zeroY) / i;
        // Si des ponts potentiels sont détectés
        if (isBridgeHover() && getIsle(x,y) == null) {

            Bridge selectedBridge = potentialsBridges.get(getNearestBridge(e.getX(), e.getY()));
            int index = BridgeAlreadyExists(selectedBridge);

            if (index < 0) {
                // Ajouter le pont à la liste des ponts
                boolean pose = true;
                for (Bridge b : bridges) {
                    if (isCrossing(selectedBridge, b)) {
                        pose = false;
                        break;
                    }
                }

                if(isIsleFull(selectedBridge.getIle1()) || isIsleFull(selectedBridge.getIle2())) pose = false;


                if (pose) {

                    bridges.add(selectedBridge);
                    ((GrilleImpl) getGrille()).poserPont(selectedBridge.ile1, selectedBridge.ile2, 1);
                    onNewAction(selectedBridge.ile1, selectedBridge.ile2, Action.UN_PONT);
                }
            } else {
                Bridge currentBridge = bridges.get(index);

                // Le pont est déjà dans la liste, le rendre double ou le supprimer
                if (currentBridge.n == 2 || (isIsleFull(currentBridge.getIle1()) || isIsleFull(currentBridge.getIle2()))) {
                    // Supprimer le pont
                    onNewAction(selectedBridge.ile1, selectedBridge.ile2, Action.AUCUN_PONT);
                    ((GrilleImpl) getGrille()).supprimerPont(selectedBridge.ile1, selectedBridge.ile2);
                    bridges.remove(index);
                } else {
                    // Rendre le pont double
                    currentBridge.n = 2;
                    onNewAction(selectedBridge.ile1, selectedBridge.ile2, Action.DEUX_PONTS);
                    ((GrilleImpl) getGrille()).supprimerPont(selectedBridge.ile1, selectedBridge.ile2);
                    ((GrilleImpl) getGrille()).poserPont(selectedBridge.ile1, selectedBridge.ile2, 2);
                }
            }

            // Rafraîchir les ponts potentiels après chaque clic de souris
            refreshBridge(e.getX(), e.getY());

            repaint();
        }
    }

    public abstract void onNewAction(Ile ile1, Ile ile2, Action action);

    /**
     * methode qui retourne si le pont passer en paramètre existe déjà et retourne sont index ou -1 sinon
     *
     * @param bridge
     * @return index
     */
    private int BridgeAlreadyExists(Bridge bridge) {
        for (int i = 0; i < bridges.size(); i++) {
            final Bridge b = bridges.get(i);
            if (bridge.ile1.getX() == b.ile1.getX() && bridge.ile1.getY() == b.ile1.getY() &&
                    bridge.ile2.getX() == b.ile2.getX() && bridge.ile2.getY() == b.ile2.getY()) {
                return i; // Retourner l'index si le pont existe déjà
            }
        }
        return -1; // Retourner -1 si le pont n'existe pas
    }

    /**
     * Fonction retournant si 2 ponts se croisent
     *
     * @param bridge1 premier pont
     * @param bridge2 deuxième pont
     * @return boolean si les ponts se croisent
     */
    private boolean isCrossing(Bridge bridge1, Bridge bridge2) {
        return isCrossing(bridge1, bridge2, 2);
    }

    private boolean isCrossing(Bridge bridge)
    {
        boolean cross = false;
        for(Bridge b : bridges)
        {
            if(!b.equals(bridge))
            {
                if(isCrossing(b,bridge)) cross = true;
            }
        }
        return cross;
    }

    /**
     * Fonction retournant si 2 ponts se croisent
     *
     * @param bridge1   premier pont
     * @param bridge2   deuxième pont
     * @param recursion nombre de récursion restant
     * @return boolean si les ponts se croisent
     */
    private boolean isCrossing(Bridge bridge1, Bridge bridge2, int recursion) {
        if (recursion == 0) {
            return false;
        }
        if (estHorizontal(bridge1) && !estHorizontal(bridge2)) {
            final int y1 = bridge1.getIle1().getY();
            final int y2 = bridge1.getIle2().getY();
            final int y3 = bridge2.getIle1().getY();
            final int y4 = bridge2.getIle2().getY();

            if (y1 < y4 && y2 > y3) {
                final int x1 = bridge1.getIle1().getX();
                final int x2 = bridge1.getIle2().getX();
                final int x3 = bridge2.getIle1().getX();
                final int x4 = bridge2.getIle2().getX();
                return x1 <= x4 && x2 >= x3;
            }
        } else {
            return isCrossing(bridge2, bridge1, recursion - 1);
        }
        return false;
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

    private boolean estHorizontal(Bridge bridge) {
        return bridge.ile1.getY() == bridge.ile2.getY();
    }

    public void loadSave(Sauvegarde sauvegarde) {
        bridges.clear();
        getGrille().chargerSauvegarde(sauvegarde);
        if (sauvegarde == null) {
            repaint();
            return;
        }
        Historique historique = sauvegarde.getReference();
        List<Historique> historiques = new ArrayList<>();
        while (historique.getAction() != Action.NOUVELLE_GRILLE) {
            historiques.add(historique);
            historique = historique.getAvant();
        }
        historiques.sort(Comparator.comparing(Historique::getTimestamp));
        for (Historique histo : historiques) {
            if (histo.getAction() == Action.UN_PONT) {
                bridges.add(new Bridge(histo.getIle1(), histo.getIle2(), 1));
            } else if (histo.getAction() == Action.DEUX_PONTS) {
                bridges.stream()
                        .filter(b -> b.ile1 == histo.getIle1() && b.ile2 == histo.getIle2())
                        .findFirst()
                        .ifPresent(b -> b.n = 2);
            } else {
                bridges.removeIf(b -> b.ile1 == histo.getIle1() && b.ile2 == histo.getIle2());
            }
        }
        repaint();
    }

    private boolean isBridgeExist(Ile ile1, Ile ile2)
    {
        for(Bridge bridge : bridges)
        {
            if(bridge.ile1.equals(ile1) && bridge.ile2.equals(ile2))
            {
                return true;
            }
        }
        return false;
    }

    /**
     * Cette classe représente des ponts
     *
     * @author elie
     */
    public class Bridge {

        Ile ile1; // première ile du pont
        Ile ile2; // deuxième ile du pont

        //boolean duo = false; // pont double
        int n; // disposition du pont true: horizontal & false: vertical

        /**
         * Constructeur d'un pont potentiel
         *
         * @param ile1
         * @param ile2
         * @param n
         */
        public Bridge(Ile ile1, Ile ile2, int n) {
            this.ile1 = ile1;
            this.ile2 = ile2;
            this.n = n;
        }

        public Ile getIle1() {
            return ile1;
        }

        public Ile getIle2() {
            return ile2;
        }

        @Override
        public String toString() {
            return "Bridge{" +
                    "ile1=" + ile1 +
                    ", ile2=" + ile2 +
                    ", n=" + n +
                    '}';
        }
    }
}
