package fr.hashimiste.gui;

import javax.swing.*;
import java.awt.event.*;

/**
 * Classe Menu pour la page d'accueil
 */
public class Menu extends JFrame implements ActionListener {
    private final JPanel jp = new JPanel();
    private final JPanel jp2 = new JPanel();
    private final JButton ButAventure = new JButton("Aventure");
    private final JButton ButTutoriel = new JButton("Tutoriel");
    private final JButton ButModeLibre = new JButton("Mode Libre");
    private final JButton ButMultijoueur = new JButton("Multijoueur");
    private final JButton ButTechnique = new JButton("Technique");
    private final JButton ButParametre = new JButton("Parametres");
    private final JButton ButProfils = new JButton("Profils");
    private final JLabel lblSomeText = new JLabel("Hashimiste", SwingConstants.CENTER);
    private final int tailleMinX = 500;
    private final int tailleMinY = 300;
    private final int taillePrefX = 550;
    private final int taillePrefY = 350;
    private final int tailleMaxX = 1920;
    private final int tailleMaxY = 1080;
    private final int espace = 10;
    private final float coefWidth1 = 0.25f;
    private final float coefHeight1 = 0.08f;
    private final float coefWidth2 = 0.1f;
    private final float coefHeight2 = 0.05f;
    private final int tailleButParamProfilX = 100;
    private final int tailleButY = 20;
    private final int tailleButX = 50;
    private final java.awt.Color couleurBouton = new java.awt.Color(160, 158, 188);
    private final java.awt.Color couleurTextBouton = new java.awt.Color(251, 250, 242);
    private final java.awt.Color couleurBoutonDesactive = new java.awt.Color(197, 179, 179);

    /**
     * Constructeur de la classe Menu
     */
    public Menu() {

        // Titre de la fenêtre
        this.setTitle("Hashimiste");
        this.setIconImage(new ImageIcon("src/main/resources/images/iconTransparent.png").getImage());
        this.setSize(tailleMinX, tailleMinY);

        // Minimum size pour la fenêtre
        this.setMinimumSize(new java.awt.Dimension(tailleMinX, tailleMinY));
        // Preferred size pour la fenêtre
        this.setPreferredSize(new java.awt.Dimension(taillePrefX, taillePrefY));
        // Taille maximum pour la fenêtre
        this.setMaximumSize(new java.awt.Dimension(tailleMaxX, tailleMaxY));

        // Bloquer le redimensionnement de la fenêtre
        //this.setResizable(false);


        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);

        jp.setLayout(new BoxLayout(jp, BoxLayout.Y_AXIS));
        // Taille de jp
        jp.setPreferredSize(new java.awt.Dimension(tailleMaxX, tailleMaxY));
        // Taille de jp2
        jp2.setPreferredSize(new java.awt.Dimension(tailleMaxX, tailleMaxY));

        ButAventure.addActionListener((e) -> pageAventure());
        ButTutoriel.addActionListener((e) -> pageTutoriel());
        ButModeLibre.addActionListener((e) -> pageModeLibre());
        ButMultijoueur.addActionListener((e) -> pageMultijoueur());
        ButTechnique.addActionListener((e) -> pageTechnique());
        ButParametre.addActionListener((e) -> pageParametre());
        ButProfils.addActionListener((e) -> pageProfils());

        // Minimum size pour les boutons et texte ( relative à la taille de la fenêtre ( calcule de la taille de la fenêtre / 5 ) )
        lblSomeText.setMinimumSize(new java.awt.Dimension(tailleButX, tailleButY));
        ButAventure.setMinimumSize(new java.awt.Dimension(tailleButX, tailleButY));
        ButTutoriel.setMinimumSize(new java.awt.Dimension(tailleButX, tailleButY));
        ButModeLibre.setMinimumSize(new java.awt.Dimension(tailleButX, tailleButY));
        ButMultijoueur.setMinimumSize(new java.awt.Dimension(tailleButX, tailleButY));
        ButTechnique.setMinimumSize(new java.awt.Dimension(tailleButX, tailleButY));
        ButParametre.setMinimumSize(new java.awt.Dimension(tailleButParamProfilX, tailleButY));
        ButProfils.setMinimumSize(new java.awt.Dimension(tailleButParamProfilX, tailleButY));


        // Prefered size pour les boutons et texte
        lblSomeText.setPreferredSize(new java.awt.Dimension(tailleButX, tailleButY));
        ButAventure.setPreferredSize(new java.awt.Dimension(tailleButX, tailleButY));
        ButTutoriel.setPreferredSize(new java.awt.Dimension(tailleButX, tailleButY));
        ButModeLibre.setPreferredSize(new java.awt.Dimension(tailleButX, tailleButY));
        ButMultijoueur.setPreferredSize(new java.awt.Dimension(tailleButX, tailleButY));
        ButTechnique.setPreferredSize(new java.awt.Dimension(tailleButX, tailleButY));
        ButParametre.setPreferredSize(new java.awt.Dimension(taillePrefX/5, tailleButY));
        ButProfils.setPreferredSize(new java.awt.Dimension(taillePrefX/5, tailleButY));


        // Taille maximum pour les boutons
        lblSomeText.setMaximumSize(new java.awt.Dimension(taillePrefX/5, tailleButY));
        ButAventure.setMaximumSize(new java.awt.Dimension(taillePrefX/5, tailleButY));
        ButTutoriel.setMaximumSize(new java.awt.Dimension(taillePrefX/5, tailleButY));
        ButModeLibre.setMaximumSize(new java.awt.Dimension(taillePrefX/5, tailleButY));
        ButMultijoueur.setMaximumSize(new java.awt.Dimension(taillePrefX/5, tailleButY));
        ButTechnique.setMaximumSize(new java.awt.Dimension(taillePrefX/5, tailleButY));
        ButParametre.setMaximumSize(new java.awt.Dimension(taillePrefX/5, tailleButY));
        ButProfils.setMaximumSize(new java.awt.Dimension(taillePrefX/5, tailleButY));


        // Centrage des éléments
        lblSomeText.setAlignmentX(JLabel.CENTER_ALIGNMENT);
        ButAventure.setAlignmentX(JButton.CENTER_ALIGNMENT);
        ButTutoriel.setAlignmentX(JButton.CENTER_ALIGNMENT);
        ButModeLibre.setAlignmentX(JButton.CENTER_ALIGNMENT);
        ButMultijoueur.setAlignmentX(JButton.CENTER_ALIGNMENT);
        ButTechnique.setAlignmentX(JButton.CENTER_ALIGNMENT);

        // Couleur des boutons en utilisant l'exadecimal A09EBC
        ButAventure.setBackground(new java.awt.Color(couleurBouton.getRGB()));
        ButTutoriel.setBackground(new java.awt.Color(couleurBouton.getRGB()));
        ButModeLibre.setBackground(new java.awt.Color(couleurBouton.getRGB()));
        ButMultijoueur.setBackground(new java.awt.Color(couleurBoutonDesactive.getRGB()));
        ButTechnique.setBackground(new java.awt.Color(couleurBouton.getRGB()));
        ButParametre.setBackground(new java.awt.Color(couleurBouton.getRGB()));
        ButProfils.setBackground(new java.awt.Color(couleurBouton.getRGB()));

        // Couleur du text des boutons en utilisant l'exadecimal FBFAF2
        ButAventure.setForeground(new java.awt.Color(couleurTextBouton.getRGB()));
        ButTutoriel.setForeground(new java.awt.Color(couleurTextBouton.getRGB()));
        ButModeLibre.setForeground(new java.awt.Color(couleurTextBouton.getRGB()));
        ButMultijoueur.setForeground(new java.awt.Color(couleurTextBouton.getRGB()));
        ButTechnique.setForeground(new java.awt.Color(couleurTextBouton.getRGB()));
        ButParametre.setForeground(new java.awt.Color(couleurTextBouton.getRGB()));
        ButProfils.setForeground(new java.awt.Color(couleurTextBouton.getRGB()));

        // Changer la couleur du bouton quand on passe la souris dessus
        ButAventure.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                ButAventure.setBackground(new java.awt.Color(couleurTextBouton.getRGB()));
                ButAventure.setForeground(new java.awt.Color(couleurBouton.getRGB()));
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                ButAventure.setBackground(new java.awt.Color(couleurBouton.getRGB()));
                ButAventure.setForeground(new java.awt.Color(couleurTextBouton.getRGB()));
            }

        });

        ButTutoriel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                ButTutoriel.setBackground(new java.awt.Color(couleurTextBouton.getRGB()));
                ButTutoriel.setForeground(new java.awt.Color(couleurBouton.getRGB()));
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                ButTutoriel.setBackground(new java.awt.Color(couleurBouton.getRGB()));
                ButTutoriel.setForeground(new java.awt.Color(couleurTextBouton.getRGB()));
            }
        });

        ButModeLibre.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                ButModeLibre.setBackground(new java.awt.Color(couleurTextBouton.getRGB()));
                ButModeLibre.setForeground(new java.awt.Color(couleurBouton.getRGB()));
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                ButModeLibre.setBackground(new java.awt.Color(couleurBouton.getRGB()));
                ButModeLibre.setForeground(new java.awt.Color(couleurTextBouton.getRGB()));
            }
        });

        ButTechnique.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                ButTechnique.setBackground(new java.awt.Color(couleurTextBouton.getRGB()));
                ButTechnique.setForeground(new java.awt.Color(couleurBouton.getRGB()));
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                ButTechnique.setBackground(new java.awt.Color(couleurBouton.getRGB()));
                ButTechnique.setForeground(new java.awt.Color(couleurTextBouton.getRGB()));
            }
        });

        ButParametre.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                ButParametre.setBackground(new java.awt.Color(couleurTextBouton.getRGB()));
                ButParametre.setForeground(new java.awt.Color(couleurBouton.getRGB()));
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                ButParametre.setBackground(new java.awt.Color(couleurBouton.getRGB()));
                ButParametre.setForeground(new java.awt.Color(couleurTextBouton.getRGB()));
            }
        });

        ButProfils.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                ButProfils.setBackground(new java.awt.Color(couleurTextBouton.getRGB()));
                ButProfils.setForeground(new java.awt.Color(couleurBouton.getRGB()));
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                ButProfils.setBackground(new java.awt.Color(couleurBouton.getRGB()));
                ButProfils.setForeground(new java.awt.Color(couleurTextBouton.getRGB()));
            }
        });

        jp.add(lblSomeText);
        jp.add(Box.createVerticalStrut(espace));
        jp.add(ButAventure);
        jp.add(Box.createVerticalStrut(espace));
        jp.add(ButTutoriel);
        jp.add(Box.createVerticalStrut(espace));
        jp.add(ButModeLibre);
        jp.add(Box.createVerticalStrut(espace));
        // Bouton multijoueur désactivé
        ButMultijoueur.setEnabled(false);
        jp.add(ButMultijoueur);
        jp.add(Box.createVerticalStrut(espace));
        jp.add(ButTechnique);
        jp.add(Box.createVerticalStrut(espace));

        // setLayout box pour les boutons Paramètres et Profils
        jp2.setLayout(new BoxLayout(jp2, BoxLayout.X_AXIS));
        ButParametre.setAlignmentX(JButton.LEFT_ALIGNMENT);
        ButProfils.setAlignmentX(JButton.RIGHT_ALIGNMENT);
        jp2.add(ButParametre);
        //jp2.add(Box.createHorizontalStrut(100));
        jp2.add(Box.createHorizontalGlue());
        jp2.add(ButProfils);
        jp.add(jp2);


        // Gestionnaire d'événements pour le redimensionnement si on veut le réactiver pour la fenêtre
        this.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                // Minimum size pour la fenêtre
                setSize(Math.max(getWidth(), tailleMinX), Math.max(getHeight(), tailleMinY));
                // Preferred size pour la fenêtre
                setPreferredSize(new java.awt.Dimension(Math.max(getWidth(), tailleMinX), Math.max(getHeight(), tailleMinY)));
                // Taille maximum pour la fenêtre
                setMaximumSize(new java.awt.Dimension(Math.max(getWidth(), tailleMaxX), Math.max(getHeight(), tailleMaxY)));

                // Taille maximum pour les boutons
                int width = (int) (getWidth() * coefWidth1);
                int height = (int) (getHeight() * coefHeight1);

                // Minimum size pour les boutons et texte ( relative à la taille de la fenêtre ( calcule de la taille de la fenêtre / 5 ) )
                lblSomeText.setMinimumSize(new java.awt.Dimension(width, height));
                ButAventure.setMinimumSize(new java.awt.Dimension(width, height));
                ButTutoriel.setMinimumSize(new java.awt.Dimension(width, height));
                ButModeLibre.setMinimumSize(new java.awt.Dimension(width, height));
                ButMultijoueur.setMinimumSize(new java.awt.Dimension(width, height));
                ButTechnique.setMinimumSize(new java.awt.Dimension(width, height));
                // taille minimum de 100 pour le width pour les boutons Paramètres et Profils
                ButParametre.setMinimumSize(new java.awt.Dimension(tailleButParamProfilX, tailleButX));
                ButProfils.setMinimumSize(new java.awt.Dimension(tailleButParamProfilX, tailleButX));

                // Prefered size pour les boutons et texte ( relative à la taille de la fenêtre ( calcule de la taille de la fenêtre / 5 ) )
                lblSomeText.setPreferredSize(new java.awt.Dimension(width, height));
                ButAventure.setPreferredSize(new java.awt.Dimension(width, height));
                ButTutoriel.setPreferredSize(new java.awt.Dimension(width, height));
                ButModeLibre.setPreferredSize(new java.awt.Dimension(width, height));
                ButMultijoueur.setPreferredSize(new java.awt.Dimension(width, height));
                ButTechnique.setPreferredSize(new java.awt.Dimension(width, height));
                ButParametre.setPreferredSize(new java.awt.Dimension(width, height));
                ButProfils.setPreferredSize(new java.awt.Dimension(width, height));

                // Taille maximum pour les boutons
                lblSomeText.setMaximumSize(new java.awt.Dimension(width, height));
                ButAventure.setMaximumSize(new java.awt.Dimension(width, height));
                ButTutoriel.setMaximumSize(new java.awt.Dimension(width, height));
                ButModeLibre.setMaximumSize(new java.awt.Dimension(width, height));
                ButMultijoueur.setMaximumSize(new java.awt.Dimension(width, height));
                ButTechnique.setMaximumSize(new java.awt.Dimension(width, height));
                ButParametre.setMaximumSize(new java.awt.Dimension(width, height));
                ButProfils.setMaximumSize(new java.awt.Dimension(width, height));

                // Taille de jp
                jp.setPreferredSize(new java.awt.Dimension(Math.max(getWidth(), tailleMaxX), Math.max(getHeight(), tailleMaxY)));
                jp2.setPreferredSize(new java.awt.Dimension(Math.max(getWidth(), tailleMaxX), Math.max(getHeight(), tailleMaxY)));

            }
        });

        // Detecter quand on clique sur agrandir ou réduire
        this.addWindowStateListener(new WindowStateListener() {
            @Override
            public void windowStateChanged(WindowEvent e) {
                // Minimum size pour la fenêtre
                setSize(Math.max(getWidth(), tailleMinX), Math.max(getHeight(), tailleMinY));
                // Preferred size pour la fenêtre
                setPreferredSize(new java.awt.Dimension(Math.max(getWidth(), tailleMinX), Math.max(getHeight(), tailleMinY)));
                // Taille maximum pour la fenêtre
                setMaximumSize(new java.awt.Dimension(Math.max(getWidth(), tailleMaxX), Math.max(getHeight(), tailleMaxY)));

                if(e.getNewState() == 0){
                    // Taille maximum pour les boutons
                    int width = (int) (getWidth() * coefWidth1);
                    int height = (int) (getHeight() * coefHeight1);

                    // Minimum size pour les boutons et texte ( relative à la taille de la fenêtre ( calcule de la taille de la fenêtre / 5 ) )
                    lblSomeText.setMinimumSize(new java.awt.Dimension(width, height));
                    ButAventure.setMinimumSize(new java.awt.Dimension(width, height));
                    ButTutoriel.setMinimumSize(new java.awt.Dimension(width, height));
                    ButModeLibre.setMinimumSize(new java.awt.Dimension(width, height));
                    ButMultijoueur.setMinimumSize(new java.awt.Dimension(width, height));
                    ButTechnique.setMinimumSize(new java.awt.Dimension(width, height));
                    ButParametre.setMinimumSize(new java.awt.Dimension(width, height));
                    ButProfils.setMinimumSize(new java.awt.Dimension(width, height));

                    // Prefered size pour les boutons et texte ( relative à la taille de la fenêtre ( calcule de la taille de la fenêtre / 5 ) )
                    lblSomeText.setPreferredSize(new java.awt.Dimension(width, height));
                    ButAventure.setPreferredSize(new java.awt.Dimension(width, height));
                    ButTutoriel.setPreferredSize(new java.awt.Dimension(width, height));
                    ButModeLibre.setPreferredSize(new java.awt.Dimension(width, height));
                    ButMultijoueur.setPreferredSize(new java.awt.Dimension(width, height));
                    ButTechnique.setPreferredSize(new java.awt.Dimension(width, height));
                    ButParametre.setPreferredSize(new java.awt.Dimension(width, height));
                    ButProfils.setPreferredSize(new java.awt.Dimension(width, height));

                    // Taille maximum pour les boutons
                    lblSomeText.setMaximumSize(new java.awt.Dimension(width, height));
                    ButAventure.setMaximumSize(new java.awt.Dimension(width, height));
                    ButTutoriel.setMaximumSize(new java.awt.Dimension(width, height));
                    ButModeLibre.setMaximumSize(new java.awt.Dimension(width, height));
                    ButMultijoueur.setMaximumSize(new java.awt.Dimension(width, height));
                    ButTechnique.setMaximumSize(new java.awt.Dimension(width, height));
                    ButParametre.setMaximumSize(new java.awt.Dimension(width, height));
                    ButProfils.setMaximumSize(new java.awt.Dimension(width, height));

                    // Taille de jp
                    jp.setPreferredSize(new java.awt.Dimension(Math.max(getWidth(), tailleMaxX), Math.max(getHeight(), tailleMaxY)));
                    jp2.setPreferredSize(new java.awt.Dimension(Math.max(getWidth (), tailleMaxX), Math.max(getHeight(), tailleMaxY)));

                }else{
                    // Taille maximum pour les boutons
                    int width = (int) (getWidth() * coefWidth2);
                    int height = (int) (getHeight() * coefHeight2);

                    // Minimum size pour les boutons et texte ( relative à la taille de la fenêtre ( calcule de la taille de la fenêtre / 5 ) )
                    lblSomeText.setMinimumSize(new java.awt.Dimension(width, height));
                    ButAventure.setMinimumSize(new java.awt.Dimension(width, height));
                    ButTutoriel.setMinimumSize(new java.awt.Dimension(width, height));
                    ButModeLibre.setMinimumSize(new java.awt.Dimension(width, height));
                    ButMultijoueur.setMinimumSize(new java.awt.Dimension(width, height));
                    ButTechnique.setMinimumSize(new java.awt.Dimension(width, height));
                    ButParametre.setMinimumSize(new java.awt.Dimension(width, height));
                    ButProfils.setMinimumSize(new java.awt.Dimension(width, height));

                    // Prefered size pour les boutons et texte ( relative à la taille de la fenêtre ( calcule de la taille de la fenêtre / 5 ) )
                    lblSomeText.setPreferredSize(new java.awt.Dimension(width, height));
                    ButAventure.setPreferredSize(new java.awt.Dimension(width, height));
                    ButTutoriel.setPreferredSize(new java.awt.Dimension(width, height));
                    ButModeLibre.setPreferredSize(new java.awt.Dimension(width, height));
                    ButMultijoueur.setPreferredSize(new java.awt.Dimension(width, height));
                    ButTechnique.setPreferredSize(new java.awt.Dimension(width, height));
                    ButParametre.setPreferredSize(new java.awt.Dimension(width, height));
                    ButProfils.setPreferredSize(new java.awt.Dimension(width, height));

                    // Taille de jp
                    jp.setPreferredSize(new java.awt.Dimension(Math.max(getWidth(), tailleMinX), Math.max(getHeight(), tailleMinY)));
                    jp2.setPreferredSize(new java.awt.Dimension(Math.max(getWidth(), tailleMaxX), Math.max(getHeight(), tailleMaxY)));
                }
            }
        });

        // La taille de la fenêtre s'adapte à la taille des éléments
        this.pack();
        this.setContentPane(jp);
        this.setVisible(true);
    }

    /**
     * Méthode pour changer de page pour la page Aventure
     * @return void
     */
    public void pageAventure() {
        this.dispose();
        // Appel de la page Aventure
    }

    /**
     * Méthode pour changer de page pour la page Tutoriel
     */
    public void pageTutoriel() {
        this.dispose();
        // Appel de la page Tutoriel
    }

    /**
     * Méthode pour changer de page pour la page Mode Libre
     */
    public void pageModeLibre() {
        this.dispose();
        // Appel de la page Mode Libre
    }

    /**
     * Méthode pour changer de page pour la page Multijoueur
     */
    public void pageMultijoueur() {
        this.dispose();
        // Appel de la page Multijoueur
    }

    /**
     * Méthode pour changer de page pour la page Technique
     * @return void
     */
    public void pageTechnique() {
        MenuTechnique t = new MenuTechnique();
        this.dispose();
    }

    /**
     * Méthode pour changer de page pour la page Paramètres
     * @return void
     */
    public void pageParametre() {
        this.dispose();
        // Appel de la page Paramètres
    }

    /**
     * Méthode pour changer de page pour la page Profils
     * @return void
     */
    public void pageProfils() {
        this.dispose();
        // Appel de la page Profils
    }

    /**
     * Methode pour les actions des boutons
     * @param e ActionEvent pour les boutons
     * @return void
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        throw new UnsupportedOperationException("Unimplemented method 'actionPerformed'");
    }

    /**
     * Méthode main pour lancer l'application
     * @param args
     * @return void
     */
    public static void main(String[] args) {
        Menu m = new Menu();
    }

}