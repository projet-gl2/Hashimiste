package fr.hashimiste.gui;

import javax.imageio.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;

public class Menu extends JFrame implements ActionListener {
    private JPanel jp = new JPanel();
    private JPanel jp2 = new JPanel();
    private JButton ButAventure = new JButton("Aventure");
    private JButton ButTutoriel = new JButton("Tutoriel");
    private JButton ButModeLibre = new JButton("Mode Libre");
    private JButton ButMultijoueur = new JButton("Multijoueur");
    private JButton ButTechnique = new JButton("Technique");
    private JButton ButParametre = new JButton("Parametres");
    private JButton ButProfils = new JButton("Profils");
    private JLabel lblSomeText = new JLabel("Hashimiste", SwingConstants.CENTER);
    // taille de la fenêtre minimum et maximum
    private int tailleMinX = 500;
    private int tailleMinY = 300;
    private int taillePrefX = 550;
    private int taillePrefY = 350;
    private int tailleMaxX = 1920;
    private int tailleMaxY = 1080;

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
        this.setResizable(false);


        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);

        jp.setLayout(new BoxLayout(jp, BoxLayout.Y_AXIS));

        ButAventure.addActionListener((e) -> pageAventure());
        ButTutoriel.addActionListener((e) -> pageTutoriel());
        ButModeLibre.addActionListener((e) -> pageModeLibre());
        ButMultijoueur.addActionListener((e) -> pageMultijoueur());
        ButTechnique.addActionListener((e) -> pageTechnique());
        ButParametre.addActionListener((e) -> pageParametre());
        ButProfils.addActionListener((e) -> pageProfils());

        // Minimum size pour les boutons et texte ( relative à la taille de la fenêtre ( calcule de la taille de la fenêtre / 5 ) )
        lblSomeText.setPreferredSize(new java.awt.Dimension(50, 20));
        ButAventure.setPreferredSize(new java.awt.Dimension(50, 20));
        ButTutoriel.setPreferredSize(new java.awt.Dimension(50, 20));
        ButModeLibre.setPreferredSize(new java.awt.Dimension(50, 20));
        ButMultijoueur.setPreferredSize(new java.awt.Dimension(50, 20));
        ButTechnique.setPreferredSize(new java.awt.Dimension(50, 20));
        ButParametre.setPreferredSize(new java.awt.Dimension(50, 20));
        ButProfils.setPreferredSize(new java.awt.Dimension(50, 20));


        // Prefered size pour les boutons et texte ( relative à la taille de la fenêtre ( calcule de la taille de la fenêtre / 5 ) )
        lblSomeText.setPreferredSize(new java.awt.Dimension(50, 20));
        ButAventure.setPreferredSize(new java.awt.Dimension(50, 20));
        ButTutoriel.setPreferredSize(new java.awt.Dimension(50, 20));
        ButModeLibre.setPreferredSize(new java.awt.Dimension(50, 20));
        ButMultijoueur.setPreferredSize(new java.awt.Dimension(50, 20));
        ButTechnique.setPreferredSize(new java.awt.Dimension(50, 20));
        ButParametre.setPreferredSize(new java.awt.Dimension(50, 20));
        ButProfils.setPreferredSize(new java.awt.Dimension(50, 20));


        // Taille maximum pour les boutons
        lblSomeText.setMaximumSize(new java.awt.Dimension(100, 20));
        ButAventure.setMaximumSize(new java.awt.Dimension(100, 20));
        ButTutoriel.setMaximumSize(new java.awt.Dimension(100, 20));
        ButModeLibre.setMaximumSize(new java.awt.Dimension(100, 20));
        ButMultijoueur.setMaximumSize(new java.awt.Dimension(100, 20));
        ButTechnique.setMaximumSize(new java.awt.Dimension(100, 20));
        ButParametre.setMaximumSize(new java.awt.Dimension(100, 20));
        ButProfils.setMaximumSize(new java.awt.Dimension(100, 20));


        // Centrage des éléments
        lblSomeText.setAlignmentX(JLabel.CENTER_ALIGNMENT);
        ButAventure.setAlignmentX(JButton.CENTER_ALIGNMENT);
        ButTutoriel.setAlignmentX(JButton.CENTER_ALIGNMENT);
        ButModeLibre.setAlignmentX(JButton.CENTER_ALIGNMENT);
        ButMultijoueur.setAlignmentX(JButton.CENTER_ALIGNMENT);
        ButTechnique.setAlignmentX(JButton.CENTER_ALIGNMENT);

        // Couleur des boutons en utilisant l'exadecimal A09EBC
        ButAventure.setBackground(new java.awt.Color(160, 158, 188));
        ButTutoriel.setBackground(new java.awt.Color(160, 158, 188));
        ButModeLibre.setBackground(new java.awt.Color(160, 158, 188));
        ButMultijoueur.setBackground(new java.awt.Color(160, 158, 188));
        ButTechnique.setBackground(new java.awt.Color(160, 158, 188));
        ButParametre.setBackground(new java.awt.Color(160, 158, 188));
        ButProfils.setBackground(new java.awt.Color(160, 158, 188));

        // Couleur du text des boutons en utilisant l'exadecimal FBFAF2
        ButAventure.setForeground(new java.awt.Color(251, 250, 242));
        ButTutoriel.setForeground(new java.awt.Color(251, 250, 242));
        ButModeLibre.setForeground(new java.awt.Color(251, 250, 242));
        ButMultijoueur.setForeground(new java.awt.Color(251, 250, 242));
        ButTechnique.setForeground(new java.awt.Color(251, 250, 242));
        ButParametre.setForeground(new java.awt.Color(251, 250, 242));
        ButProfils.setForeground(new java.awt.Color(251, 250, 242));




        jp.add(lblSomeText);
        jp.add(Box.createVerticalStrut(10));
        jp.add(ButAventure);
        jp.add(Box.createVerticalStrut(10));
        jp.add(ButTutoriel);
        jp.add(Box.createVerticalStrut(10));
        jp.add(ButModeLibre);
        jp.add(Box.createVerticalStrut(10));
        // Bouton multijoueur désactivé
        ButMultijoueur.setEnabled(false);
        jp.add(ButMultijoueur);
        jp.add(Box.createVerticalStrut(10));
        jp.add(ButTechnique);
        jp.add(Box.createVerticalStrut(10));

        // setLayout box pour les boutons Paramètres et Profils
        jp2.setLayout(new BoxLayout(jp2, BoxLayout.X_AXIS));
        ButParametre.setAlignmentX(JButton.LEFT_ALIGNMENT);
        ButProfils.setAlignmentX(JButton.RIGHT_ALIGNMENT);
        jp2.add(ButParametre);
        // Espace entre les boutons ButParametre collé en bas à gauche et ButProfils collé en bas à droite en laissant la taille preférée ( autre que horizontaleGlu et verticaleGlu)
        jp2.add(Box.createHorizontalStrut(100));

        jp2.add(ButProfils);
        jp.add(jp2);

        /*
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
                int width = (int) (getWidth() * 0.1);
                int height = (int) (getHeight() * 0.05);

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


            }
        });
        */



        // La taille de la fenêtre s'adapte à la taille des éléments
        this.pack();
        this.setContentPane(jp);
        this.setVisible(true);
    }

    public void pageAventure() {
        this.dispose();
        // Appel de la page Aventure
    }

    public void pageTutoriel() {
        this.dispose();
        // Appel de la page Tutoriel
    }

    public void pageModeLibre() {
        this.dispose();
        // Appel de la page Mode Libre
    }

    public void pageMultijoueur() {
        this.dispose();
        // Appel de la page Multijoueur
    }

    public void pageTechnique() {
        this.dispose();
        // Appel de la page Technique
    }

    public void pageParametre() {
        this.dispose();
        // Appel de la page Paramètres
    }

    public void pageProfils() {
        this.dispose();
        // Appel de la page Profils
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        throw new UnsupportedOperationException("Unimplemented method 'actionPerformed'");
    }

    public static void main(String[] args) {
        Menu m = new Menu();
    }

}