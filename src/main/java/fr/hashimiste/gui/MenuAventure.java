package fr.hashimiste.gui; /**
 *  La classe Aventure correspond au menu de jeu lorsqu'on est sur le mode aventure
 **/
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
public class MenuAventure {

    private Frame frame;
    private Button[] boutons; //Liste des boutons pour les niveaux
    private Button menuButton; //Bouton pour le menu
    private int nbBouton; //Nombre de niveaux
    private int largeurBouton;
    private int hauteurBouton;


    /**
     * Constante qui indique le nombre de niveau  du mode aventure
     **/
    public static final int NB_NIVEAU = 10;

    /**
     * Constante qui indique la largeur des boutons des niveaux
     **/
    public static final int LARGE_BOUT = 20;

    /**
     * Constante qui indique la longueur  des boutons des niveaux
     **/
    public static final int LONG_BOUT = 20;



    /**
     *  Méthode de création du mode aventure avec la liste des niveaux et le bouton de retour au menu
     * @param nbBouton nombre de boutons pour les niveaux
     * @param largeurBouton taille de la largeur du bouton
     * @param hauteurBouton taille de la hauteur du bouton
     *  Il faudra voir si le nombre de niveau est statique on pourra changer le constructeur afin de faire ça de manière statique de meme pour la taille des boutons
     **/
    public MenuAventure() {

        this.nbBouton = NB_NIVEAU;
        this.largeurBouton = LARGE_BOUT;
        this.hauteurBouton = LONG_BOUT;

        // Titre de la frame
        frame = new Frame("Hashimiste");
        frame.setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;

        Panel boutonsPanel = new Panel();
        boutonsPanel.setLayout(new FlowLayout(FlowLayout.CENTER));

        boutons = new Button[nbBouton];
        for (int i = 0; i < nbBouton; i++) {
            boutons[i] = new Button("Niveau " + i + " ");
            boutonsPanel.add(boutons[i]);
            // Rajouter l'action pour lié les niveaux aux boutons
        }

        menuButton = new Button("Menu"); // Création du bouton pour le menu
        menuButton.addActionListener((e) -> pageMenu());

        Panel menuPanel = new Panel();
        menuPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        menuPanel.add(menuButton);

        Panel titrePanel = new Panel();
        titrePanel.setLayout(new FlowLayout(FlowLayout.CENTER));

        Label titre = new Label("MODE AVENTURE");
        titre.setFont(new Font("Arial", Font.BOLD, 20));
        titrePanel.add(titre);

        gbc.gridx = 0;
        //Titre
        gbc.gridy = 0;
        frame.add(titrePanel, gbc);
        //Liste de Boutons
        gbc.gridy = 1;
        frame.add(boutonsPanel, gbc);
        // Bouton Menu
        gbc.gridy = 2;
        frame.add(menuPanel, gbc);

        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });

        frame.setSize(Toolkit.getDefaultToolkit().getScreenSize());
        frame.setLocationRelativeTo(null); // Centre la fenêtre sur l'écran
        frame.setVisible(true); // Affiche la fenêtre
    }

    /**
     * Méthode pour afficher la page du menu et détruire la page des paramètres
     * */
    public void pageMenu(){
        new Menu();
        frame.dispose();
    }

    /**
     *  Le main ici sert seulement à tester le menu comme dit précédemment on pourra passer les valeurs en statique une fois que le nombre de niveau aura été définit
     *  @param args Argument passé lors du démarrage
     **/
    public static void main(String[] args) {
        new MenuAventure();
    }
}







