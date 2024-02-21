package fr.hashimiste.gui;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.BoxLayout;
import javax.swing.Box;
import javax.swing.JComponent;
import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.ActionEvent;
import java.awt.Component;

// import components

import static fr.hashimiste.gui.Image.*;

/**
 * Classe Menu pour la page d'accueil
 *
 * @author Arthur Dureau
 */
public class Menu extends JFrame implements ActionListener {
    /**
     * JPanel pour les composants principaux
     */
    private final JPanel JP = new JPanel();
    /**
     * JPanel pour les boutons Paramètres et Profils
     */
    private final JPanel JP2 = new JPanel();
    /**
     * JButton pour la page Aventure
     */
    private final JButton BUT_AVENTURE = new JButton("Aventure");
    /**
     * JButton pour la page Tutoriel
     */
    private final JButton BUT_TUTORIEL = new JButton("Tutoriel");
    /**
     * JButton pour la page Mode Libre
     */
    private final JButton BUT_MODE_LIBRE = new JButton("Mode Libre");
    /**
     * JButton pour la page Multijoueur
     */
    private final JButton BUT_MULTIJOUEUR = new JButton("Multijoueur");
    /**
     * JButton pour la page Technique
     */
    private final JButton BUT_TECHNIQUE = new JButton("Techniques");
    /**
     * JButton pour la page Paramètres
     */
    private final JButton BUT_PARAMETRE = new JButton("Parametres");
    /**
     * JButton pour la page Profils
     */
    private final JButton BUT_PROFIL = new JButton("Profils");
    /**
     * JLabel pour le texte
     */
    private final JLabel LABEL_TEXT = new JLabel("Hashimiste", SwingConstants.CENTER);
    /**
     * Taille minimum pour la fenêtre en largeur
     */
    public static int TAILLE_MIN_X = 500;
    /**
     * Taille minimum pour la fenêtre en hauteur
     */
    public static int TAILLE_MIN_Y = 300;
    /**
     * Taille préférée pour la fenêtre en largeur
     */
    public static int TAILLE_PREF_X = 550;
    /**
     * Taille préférée pour la fenêtre en hauteur
     */
    public static int TAILLE_PREF_Y = 350;
    /**
     * Taille maximum pour la fenêtre en largeur
     */
    public static int TAILLE_MAX_X = 1920;
    /**
     * Taille maximum pour la fenêtre en hauteur
     */
    public static int TAILLE_MAX_Y = 1080;
    /**
     * Espace entre les composants
     */
    public static int ESPACE = 10;
    /**
     * Premier coefficient pour la taille des composants en largeur
     */
    public static float COEF_WIDTH_1 = 0.25f;
    /**
     * Coefficient pour la taille des composants en hauteur
     */
    public static float COEF_HEIGHT_1 = 0.08f;
    /**
     * Deuxième coefficient pour la taille des composants en largeur
     */
    public static float COEF_WIDTH_2 = 0.1f;
    /**
     * Deuxième coefficient pour la taille des composants en hauteur
     */
    public static float COEF_HEIGHT_2 = 0.05f;
    /**
     * Taille des boutons Paramètres et Profils en largeur
     */
    public static int TAILLE_BUT_PARAM_PROFIL_X = 100;
    /**
     * Taille des boutons en hauteur
     */
    public static int TAILLE_BUT_Y = 20;
    /**
     * Taille des boutons en largeur
     */
    public static int TAILLE_BUT_X = 50;

    /**
     * Constructeur de la classe Menu
     */
    public Menu() {

        configureWindow("Hashimiste", ICON_TRANSPARENT, TAILLE_MIN_X, TAILLE_MIN_Y, TAILLE_PREF_X, TAILLE_PREF_Y, TAILLE_MAX_X, TAILLE_MAX_Y, true);

        JP.setLayout(new BoxLayout(JP, BoxLayout.Y_AXIS));

        JP.setPreferredSize(new Dimension(TAILLE_MAX_X, TAILLE_MAX_Y));
        JP2.setPreferredSize(new Dimension(TAILLE_MAX_X, TAILLE_MAX_Y));

        BUT_AVENTURE.addActionListener((e) -> pageAventure());
        BUT_TUTORIEL.addActionListener((e) -> pageTutoriel());
        BUT_MODE_LIBRE.addActionListener((e) -> pageModeLibre());
        BUT_MULTIJOUEUR.addActionListener((e) -> pageMultijoueur());
        BUT_TECHNIQUE.addActionListener((e) -> pageTechnique());
        BUT_PARAMETRE.addActionListener((e) -> pageParametre());
        BUT_PROFIL.addActionListener((e) -> pageProfils());

        // Taille des composants
        setComponentSize(LABEL_TEXT, TAILLE_BUT_X, TAILLE_BUT_Y, TAILLE_BUT_X, TAILLE_BUT_Y, TAILLE_PREF_X / 5, TAILLE_BUT_Y);
        setComponentSize(BUT_AVENTURE, TAILLE_BUT_X, TAILLE_BUT_Y, TAILLE_BUT_X, TAILLE_BUT_Y, TAILLE_PREF_X / 5, TAILLE_BUT_Y);
        setComponentSize(BUT_TUTORIEL, TAILLE_BUT_X, TAILLE_BUT_Y, TAILLE_BUT_X, TAILLE_BUT_Y, TAILLE_PREF_X / 5, TAILLE_BUT_Y);
        setComponentSize(BUT_MODE_LIBRE, TAILLE_BUT_X, TAILLE_BUT_Y, TAILLE_BUT_X, TAILLE_BUT_Y, TAILLE_PREF_X / 5, TAILLE_BUT_Y);
        setComponentSize(BUT_MULTIJOUEUR, TAILLE_BUT_X, TAILLE_BUT_Y, TAILLE_BUT_X, TAILLE_BUT_Y, TAILLE_PREF_X / 5, TAILLE_BUT_Y);
        setComponentSize(BUT_TECHNIQUE, TAILLE_BUT_X, TAILLE_BUT_Y, TAILLE_BUT_X, TAILLE_BUT_Y, TAILLE_PREF_X / 5, TAILLE_BUT_Y);
        setComponentSize(BUT_PARAMETRE, TAILLE_BUT_X, TAILLE_BUT_Y, TAILLE_PREF_X / 5, TAILLE_BUT_Y, TAILLE_PREF_X / 5, TAILLE_BUT_Y);
        setComponentSize(BUT_PROFIL, TAILLE_BUT_X, TAILLE_BUT_Y, TAILLE_PREF_X / 5, TAILLE_BUT_Y, TAILLE_PREF_X / 5, TAILLE_BUT_Y);

        // Centrage des éléments
        LABEL_TEXT.setAlignmentX(JLabel.CENTER_ALIGNMENT);
        BUT_AVENTURE.setAlignmentX(JButton.CENTER_ALIGNMENT);
        BUT_TUTORIEL.setAlignmentX(JButton.CENTER_ALIGNMENT);
        BUT_MODE_LIBRE.setAlignmentX(JButton.CENTER_ALIGNMENT);
        BUT_MULTIJOUEUR.setAlignmentX(JButton.CENTER_ALIGNMENT);
        BUT_TECHNIQUE.setAlignmentX(JButton.CENTER_ALIGNMENT);

        // Utilisation de la méthode pour la couleur des boutons
        setButtonColors(BUT_AVENTURE, new Color(Couleur.COULEUR_BOUTON.getRGB()), new Color(Couleur.COULEUR_TEXTE_BOUTON.getRGB()));
        setButtonColors(BUT_TUTORIEL, new Color(Couleur.COULEUR_BOUTON.getRGB()), new Color(Couleur.COULEUR_TEXTE_BOUTON.getRGB()));
        setButtonColors(BUT_MODE_LIBRE, new Color(Couleur.COULEUR_BOUTON.getRGB()), new Color(Couleur.COULEUR_TEXTE_BOUTON.getRGB()));
        setButtonColors(BUT_MULTIJOUEUR, new Color(Couleur.COULEUR_BOUTON_DESACTIVE.getRGB()), new Color(Couleur.COULEUR_TEXTE_BOUTON.getRGB()));
        setButtonColors(BUT_TECHNIQUE, new Color(Couleur.COULEUR_BOUTON.getRGB()), new Color(Couleur.COULEUR_TEXTE_BOUTON.getRGB()));
        setButtonColors(BUT_PARAMETRE, new Color(Couleur.COULEUR_BOUTON.getRGB()), new Color(Couleur.COULEUR_TEXTE_BOUTON.getRGB()));
        setButtonColors(BUT_PROFIL, new Color(Couleur.COULEUR_BOUTON.getRGB()), new Color(Couleur.COULEUR_TEXTE_BOUTON.getRGB()));

        configureButtonMouseListener(BUT_AVENTURE);
        configureButtonMouseListener(BUT_TUTORIEL);
        configureButtonMouseListener(BUT_MODE_LIBRE);
        configureButtonMouseListener(BUT_TECHNIQUE);
        configureButtonMouseListener(BUT_PARAMETRE);
        configureButtonMouseListener(BUT_PROFIL);

        addComponentWithStrut(JP, LABEL_TEXT, ESPACE);
        addComponentWithStrut(JP, BUT_AVENTURE, ESPACE);
        addComponentWithStrut(JP, BUT_TUTORIEL, ESPACE);
        addComponentWithStrut(JP, BUT_MODE_LIBRE, ESPACE);
        // Bouton multijoueur désactivé
        BUT_MULTIJOUEUR.setEnabled(false);
        addComponentWithStrut(JP, BUT_MULTIJOUEUR, ESPACE);
        addComponentWithStrut(JP, BUT_TECHNIQUE, ESPACE);

        // setLayout box pour les boutons Paramètres et Profils
        JP2.setLayout(new BoxLayout(JP2, BoxLayout.X_AXIS));
        BUT_PARAMETRE.setAlignmentX(JButton.LEFT_ALIGNMENT);
        BUT_PROFIL.setAlignmentX(JButton.RIGHT_ALIGNMENT);
        JP2.add(BUT_PARAMETRE);
        JP2.add(Box.createHorizontalGlue());
        JP2.add(BUT_PROFIL);
        JP.add(JP2);

        // Gestionnaire d'événements pour le redimensionnement si on veut le réactiver pour la fenêtre
        this.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                // Preferred size pour la fenêtre
                setPreferredSize(new Dimension(Math.max(getWidth(), TAILLE_MIN_X), Math.max(getHeight(), TAILLE_MIN_Y)));
                // Taille maximum pour la fenêtre
                setMaximumSize(new Dimension(Math.max(getWidth(), TAILLE_MAX_X), Math.max(getHeight(), TAILLE_MAX_Y)));

                // Taille maximum pour les boutons
                int width = (int) (getWidth() * COEF_WIDTH_1);
                int height = (int) (getHeight() * COEF_HEIGHT_1);

                // Minimum size pour les boutons et texte ( relative à la taille de la fenêtre )
                setComponentSize(LABEL_TEXT, width, height, width, height, width, height);
                setComponentSize(BUT_AVENTURE, width, height, width, height, width, height);
                setComponentSize(BUT_TUTORIEL, width, height, width, height, width, height);
                setComponentSize(BUT_MODE_LIBRE, width, height, width, height, width, height);
                setComponentSize(BUT_MULTIJOUEUR, width, height, width, height, width, height);
                setComponentSize(BUT_TECHNIQUE, width, height, width, height, width, height);
                setComponentSize(BUT_PARAMETRE, TAILLE_BUT_PARAM_PROFIL_X, TAILLE_BUT_X, width, height, width, height);
                setComponentSize(BUT_PROFIL, TAILLE_BUT_PARAM_PROFIL_X, TAILLE_BUT_X, width, height, width, height);

                // Taille de JP
                JP.setPreferredSize(
                        new Dimension(Math.max(getWidth(), TAILLE_MAX_X), Math.max(getHeight(), TAILLE_MAX_Y)));
                JP2.setPreferredSize(
                        new Dimension(Math.max(getWidth(), TAILLE_MAX_X), Math.max(getHeight(), TAILLE_MAX_Y)));

            }
        });

        // Detecter quand on clique sur agrandir ou réduire
        this.addWindowStateListener(e -> {
            setPreferredSize(new Dimension(Math.max(getWidth(), TAILLE_MIN_X), Math.max(getHeight(), TAILLE_MIN_Y)));
            setMaximumSize(new Dimension(Math.max(getWidth(), TAILLE_MAX_X), Math.max(getHeight(), TAILLE_MAX_Y)));

            if (e.getNewState() == 0) {
                // Taille maximum pour les boutons
                int width = (int) (getWidth() * COEF_WIDTH_1);
                int height = (int) (getHeight() * COEF_HEIGHT_1);

                // Minimum size pour les boutons et texte ( relative à la taille de la fenêtre )
                setComponentSize(LABEL_TEXT, width, height, width, height, width, height);
                setComponentSize(BUT_AVENTURE, width, height, width, height, width, height);
                setComponentSize(BUT_TUTORIEL, width, height, width, height, width, height);
                setComponentSize(BUT_MODE_LIBRE, width, height, width, height, width, height);
                setComponentSize(BUT_MULTIJOUEUR, width, height, width, height, width, height);
                setComponentSize(BUT_TECHNIQUE, width, height, width, height, width, height);
                setComponentSize(BUT_PARAMETRE, width, height, width, height, width, height);
                setComponentSize(BUT_PROFIL, width, height, width, height, width, height);

                // Taille de JP
                JP.setPreferredSize(
                        new Dimension(Math.max(getWidth(), TAILLE_MAX_X), Math.max(getHeight(), TAILLE_MAX_Y)));
                JP2.setPreferredSize(
                        new Dimension(Math.max(getWidth(), TAILLE_MAX_X), Math.max(getHeight(), TAILLE_MAX_Y)));

            } else {
                // Taille maximum pour les boutons
                int width = (int) (getWidth() * COEF_WIDTH_2);
                int height = (int) (getHeight() * COEF_HEIGHT_2);

                // Minimum size pour les boutons et texte
                setComponentSize(LABEL_TEXT, width, height, width, height, TAILLE_PREF_X / 5, TAILLE_BUT_Y);
                setComponentSize(BUT_AVENTURE, width, height, width, height, TAILLE_PREF_X / 5, TAILLE_BUT_Y);
                setComponentSize(BUT_TUTORIEL, width, height, width, height, TAILLE_PREF_X / 5, TAILLE_BUT_Y);
                setComponentSize(BUT_MODE_LIBRE, width, height, width, height, TAILLE_PREF_X / 5, TAILLE_BUT_Y);
                setComponentSize(BUT_MULTIJOUEUR, width, height, width, height, TAILLE_PREF_X / 5, TAILLE_BUT_Y);
                setComponentSize(BUT_TECHNIQUE, width, height, width, height, TAILLE_PREF_X / 5, TAILLE_BUT_Y);
                setComponentSize(BUT_PARAMETRE, width, height, width, height, TAILLE_PREF_X / 5, TAILLE_BUT_Y);
                setComponentSize(BUT_PROFIL, width, height, width, height, TAILLE_PREF_X / 5, TAILLE_BUT_Y);

                // Taille de JP
                JP.setPreferredSize(new Dimension(Math.max(getWidth(), TAILLE_MIN_X), Math.max(getHeight(), TAILLE_MIN_Y)));
                JP2.setPreferredSize(new Dimension(Math.max(getWidth(), TAILLE_MAX_X), Math.max(getHeight(), TAILLE_MAX_Y)));
            }
        });

        // La taille de la fenêtre s'adapte à la taille des éléments
        this.pack();
        this.setContentPane(JP);
        // Changer la couleur de fond de la fenêtre
        JP.setBackground(new Color(Couleur.COULEUR_FOND.getRGB()));
        JP2.setBackground(new Color(Couleur.COULEUR_FOND.getRGB()));
        this.setVisible(true);
    }

    /**
     * Méthode pour ajouter un composant avec un espace
     *
     * @param panel     JPanel où ajouter le composant
     * @param component Composant à ajouter
     * @param space     Espace entre le composant et le suivant
     */
    private void addComponentWithStrut(JPanel panel, Component component, int space) {
        panel.add(component);
        panel.add(Box.createVerticalStrut(space));
    }

    /**
     * Méthode pour configurer la fenêtre
     *
     * @param title      Titre de la fenêtre
     * @param iconPath   Chemin de l'icône de la fenêtre
     * @param minWidth   Taille minimum en largeur
     * @param minHeight  Taille minimum en hauteur
     * @param prefWidth  Taille préférée en largeur
     * @param prefHeight Taille préférée en hauteur
     * @param maxWidth   Taille maximum en largeur
     * @param maxHeight  Taille maximum en hauteur
     * @param resizable  Redimensionnement de la fenêtre
     */
    private void configureWindow(String title, String iconPath, int minWidth, int minHeight, int prefWidth, int prefHeight, int maxWidth, int maxHeight, boolean resizable) {
        this.setTitle(title);
        this.setIconImage(new ImageIcon(iconPath).getImage());
        this.setSize(minWidth, minHeight);

        // Définir la taille minimum, préférée et maximum de la fenêtre
        this.setMinimumSize(new Dimension(minWidth, minHeight));
        this.setPreferredSize(new Dimension(prefWidth, prefHeight));
        this.setMaximumSize(new Dimension(maxWidth, maxHeight));

        // Option pour bloquer le redimensionnement de la fenêtre
        this.setResizable(resizable);

        // Définir l'opération de fermeture par défaut et centrer la fenêtre
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
    }


    /**
     * Méthode pour configurer les boutons
     *
     * @param button JButton à configurer
     */
    private void configureButtonMouseListener(JButton button) {
        button.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent evt) {
                button.setBackground(new Color(Couleur.COULEUR_TEXTE_BOUTON.getRGB()));
                button.setForeground(new Color(Couleur.COULEUR_BOUTON.getRGB()));
            }

            public void mouseExited(MouseEvent evt) {
                button.setBackground(new Color(Couleur.COULEUR_BOUTON.getRGB()));
                button.setForeground(new Color(Couleur.COULEUR_TEXTE_BOUTON.getRGB()));
            }
        });
    }

    /**
     * Méthode pour changer la taille des composants
     *
     * @param component   Composant à changer la taille
     * @param minWidth    Taille minimum en largeur
     * @param minHeight   Taille minimum en hauteur
     * @param prefWidth   Taille préférée en largeur
     * @param prefHeight  Taille préférée en hauteur
     * @param maxWidth    Taille maximum en largeur
     * @param maxHeight   Taille maximum en hauteur
     */
    private void setComponentSize(JComponent component, int minWidth, int minHeight, int prefWidth, int prefHeight, int maxWidth, int maxHeight) {
        component.setMinimumSize(new Dimension(minWidth, minHeight));
        component.setPreferredSize(new Dimension(prefWidth, prefHeight));
        component.setMaximumSize(new Dimension(maxWidth, maxHeight));
    }

    /**
     * Méthode pour changer les couleurs des boutons
     *
     * @param button     JButton à changer les couleurs
     * @param backgroundColor Couleur de fond
     * @param foregroundColor Couleur du texte
     */
    private void setButtonColors(JButton button, Color backgroundColor, Color foregroundColor) {
        button.setBackground(backgroundColor);
        button.setForeground(foregroundColor);
    }



    /**
     * Methode pour changer de page pour la page Aventure
     */
    public void pageAventure() {
        this.dispose();
        // Appel de la page Aventure
    }

    /**
     * Methode pour changer de page pour la page Tutoriel
     */
    public void pageTutoriel() {
        this.dispose();
        // Appel de la page Tutoriel
    }

    /**
     * Methode pour changer de page pour la page Mode Libre
     */
    public void pageModeLibre() {
        this.dispose();
        // Appel de la page Mode Libre
    }

    /**
     * Methode pour changer de page pour la page Multijoueur
     */
    public void pageMultijoueur() {
        this.dispose();
        // Appel de la page Multijoueur
    }

    /**
     * Methode pour changer de page pour la page Technique
     */
    public void pageTechnique() {
        MenuTechnique t = new MenuTechnique();
        this.dispose();
    }

    /**
     * Methode pour changer de page pour la page Paramètres
     */
    public void pageParametre() {
        MenuParametres p = new MenuParametres();
        this.dispose();
        // Appel de la page Paramètres
    }

    /**
     * Methode pour changer de page pour la page Profils
     */
    public void pageProfils() {
        MenuProfilCreation pc = new MenuProfilCreation();
        this.dispose();
        // Appel de la page Profils
    }

    /**
     * Methode pour les actions des boutons
     *
     * @param e ActionEvent pour les boutons
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        throw new UnsupportedOperationException("Unimplemented method 'actionPerformed'");
    }

    /**
     * Méthode main pour lancer l'application
     *
     * @param args Arguments du main
     */
    public static void main(String[] args) {
        Menu m = new Menu();
    }

}