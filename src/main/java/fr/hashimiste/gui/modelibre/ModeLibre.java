package fr.hashimiste.gui.modelibre;

import fr.hashimiste.Difficulte;
import fr.hashimiste.gui.Couleur;
import fr.hashimiste.gui.Image;
import fr.hashimiste.maps.Grille;
import fr.hashimiste.maps.Ile;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.net.URL;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

public class ModeLibre extends JFrame  {

    // Variable de test
    private final PreviewComponent[] previewTab = new PreviewComponent[10];

    Grille grille = new Grille(Difficulte.FACILE, Arrays.asList(new Ile(null, 1,1,3),
            new Ile(null, 1,1,3),
            new Ile(null, 5,3,3),
            new Ile(null, 6,0, 2),
            new Ile(null, 0,0, 2),
            new Ile(null,0,6,6),
            new Ile(null, 6,6,5)
            ));




    private final PreviewComponent bigPreview = new PreviewComponent(Color.GRAY, grille);

    private final int tailleMinX = 550;
    private final int tailleMinY = 500;
    private final int tailleMaxX = 1920;
    private final int tailleMaxY = 1080;

    private final JPanel panelFacile = new JPanel();
    private final JPanel panelMoyen = new JPanel();
    private final JPanel panelDifficile = new JPanel();
    private final JPanel panelPreview = new JPanel();

    private final JLabel facileLabel = new JLabel();
    private final JLabel moyenLabel = new JLabel();
    private final JLabel difficileLabel = new JLabel();

    private final JButton menuButton = new JButton();
    private final JButton playButton = new JButton();

    /**
     *
     *  Créer le GUI du mode Libre
     *
     */
    public ModeLibre()
    {
        // parametrage de la fenètre
        this.setTitle("Hashimiste > Mode Libre");
        this.setIconImages(getIconImages());
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(tailleMinX, tailleMinY);
        this.setLocationRelativeTo(null);
        this.setLayout(null);

        // Minimum size pour la fenêtre
        this.setMinimumSize(new java.awt.Dimension(tailleMinX, tailleMinY));
        // Preferred size pour la fenêtre
        this.setPreferredSize(new java.awt.Dimension(tailleMinX, tailleMinY));
        // Taille maximum pour la fenêtre
        this.setMaximumSize(new java.awt.Dimension(tailleMaxX, tailleMaxY));

        // definition des couleurs de fond
        //Color color = UIManager.getColor ( "Panel.background" );
        Color color = Couleur.getCouleurFond();
        getContentPane().setBackground(color);
        panelFacile.setBackground(color);
        panelMoyen.setBackground(color);
        panelDifficile.setBackground(color);
        panelPreview.setBackground(color);
        bigPreview.setColor(color);

        menuButton.setOpaque(true);
        menuButton.setBackground(Couleur.getCouleurBouton());
        menuButton.setForeground(Couleur.getCouleurTextBouton());

        playButton.setOpaque(true);
        playButton.setBackground(Couleur.getCouleurBouton());
        playButton.setForeground(Couleur.getCouleurTextBouton());



        //URL iconUrl = getClass().getResource("/resources/images/logo.png");
        //ImageIcon icon = new ImageIcon(iconUrl.toString());
        //setIconImage(icon.getImage());

        //this.setIconImage(new ImageIcon(Objects.requireNonNull(Toolkit.getDefaultToolkit().getClass().getResource("/resources/images/icon.png"))).getImage());


        this.setIconImage(new ImageIcon(Image.getCheminIconeTransparent()).getImage());




        // definition des textes
        facileLabel.setText("Facile");
        moyenLabel.setText("Moyen");
        difficileLabel.setText("Difficile");
        menuButton.setText("Menu");
        playButton.setText("Jouer");

        // création des layouts pour les grilles
        panelFacile.setLayout(new GridLayout(2,5));
        panelMoyen.setLayout(new GridLayout(2,5));
        panelDifficile.setLayout(new GridLayout(2,5));
        panelPreview.setLayout(null);

        // ajout des composants de prévisualisation dans les grilles
        for(int i = 0; i <= 9; i++)
        {
            Color c = Couleur.getCouleurBouton();/*new Color((int)(Math.random() * 0x1000000))*/;
            panelFacile.add(new PreviewComponent(c, grille));
            panelMoyen.add(new PreviewComponent(c, null));
            panelDifficile.add(new PreviewComponent(c, null));
        }

        // ajout des composants à la fenêtre
        this.add(facileLabel);
        this.add(moyenLabel);
        this.add(difficileLabel);
        this.add(panelFacile);
        this.add(panelMoyen);
        this.add(panelDifficile);
        this.add(panelPreview);
        panelPreview.add(bigPreview);
        panelPreview.add(menuButton);
        panelPreview.add(playButton);

        MouseListener gridListener = new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                System.out.println("bip");
                Point point = new Point(e.getX(), e.getY());
                Component c = panelFacile.getComponentAt(e.getPoint());
                System.out.println(c.getClass());
                if(c instanceof PreviewComponent previewComponent)
                {
                    playButton.setVisible(true);
                    bigPreview.setVisible(true);
                    bigPreview.setColor(previewComponent.getColor());
                    bigPreview.paint(bigPreview.getGraphics());
                    //playButton.paint(playButton.getGraphics());
                    panelPreview.paint(panelPreview.getGraphics());
                    System.out.println("preview");

                }

            }
        };

        panelFacile.addMouseListener(gridListener);
        panelMoyen.addMouseListener(gridListener);
        panelDifficile.addMouseListener(gridListener);



        //menuButton.setOpaque(true);
        //menuButton.setForeground(Color.BLUE);
        //menuButton.setBackground(Color.BLACK);

        playButton.setVisible(false);
        bigPreview.setVisible(false);
        this.setVisible(true); // affichage de a fenêtre
        paint(getGraphics()); // on redessine la fenêtre avant de l'afficher
        this.repaint();
        // Force un redessin de la fenêtre pour corriger les problèmes de rendu initiaux
        SwingUtilities.invokeLater(() -> {
            this.revalidate();
            this.repaint();
        });

    }

    /**
     *
     * Affiche les différents composants sur la fenêtre
     *
     * @param g the specified Graphics window
     */
    @Override
    public void paint(Graphics g) {
        int h = getHeight()- getInsets().top-facileLabel.getHeight()*3;
        int w = getWidth()-(getWidth()/3);
        panelFacile.setBounds(0,facileLabel.getHeight(), w, h/3);
        panelMoyen.setBounds(0,h/3+facileLabel.getHeight()*2, w, h/3);
        panelDifficile.setBounds(0,(h/3)*2+facileLabel.getHeight()*3, w, h/3);
        panelPreview.setBounds(w,0,getWidth()/3, getHeight());
        menuButton.setBounds(0,0,getWidth()/3, /*h/10*/20);
        bigPreview.setBounds(0,30,panelPreview.getWidth(), panelPreview.getWidth());
        playButton.setBounds(panelPreview.getWidth()/4, bigPreview.getHeight()+40, panelPreview.getWidth()/2, 20);
        facileLabel.setBounds(10,0,100,20);
        moyenLabel.setBounds(10, h/3+facileLabel.getHeight(), 100,20);
        difficileLabel.setBounds(10,(h/3)*2+facileLabel.getHeight()*2,100,20);
        super.paint(g);
    }
}
