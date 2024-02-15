package fr.hashimiste.gui;
/**
 * La classe Jeu correspond au jeu lorsqu'on est face à l'écran de jeu avec les boutons ainsi que la grille, il y divers boutons (retour arrière, poser un checkpoint, aide, etc)
 **/
import java.awt.*;
import java.awt.event.*;
public class MenuJeu {

    private Frame frame;
    private Button[] boutons; //Liste des boutons pour les fonctions

    /**
     * Constante qui indique le nombre de boutons de le menu
     **/
    public static final int NBBOUTON = 7;

    /**
     * Constante qui correspond à l'emplacement du bouton menu dans le tableau de boutons
     **/
    public  static  final int MENU = 1;
    /**
     * Constante qui correspond à l'emplacement du bouton verifier dans le tableau de boutons
     **/
    public  static  final int VERIFIER = 2;
    /**
     * Constante qui correspond à l'emplacement du bouton charger dans le tableau de boutons
     **/
    public  static  final int CHARGER = 3;
    /**
     * Constante qui correspond à l'emplacement du bouton sauvegarde dans le tableau de boutons
     **/
    public  static  final int SAUVEGARDE = 4;
    /**
     * Constante qui correspond à l'emplacement du bouton retour dans le tableau de boutons
     **/
    public  static  final int RETOUR = 5;
    /**
     * Constante qui correspond à l'emplacement du bouton aide dans le tableau de boutons
     **/
    public  static  final int AIDE = 6;

    // grille;
    // grilleComplete;

    /**
     * Méthode de création de la fenetre de jeu avec la grille ainsi que la liste de boutons chacun correpondant à une fonction prédéfinit
     **/
    public MenuJeu(/*la grille de jeu, la grile solution*/){
        //this.grille = /* la grille de jeu */;
        //this.grilleComplete = /* la grille solution */

        // Titre de la frame
        frame = new Frame("Hashimiste");
        frame.setLayout(new GridBagLayout());

        // Contrainte pour la disposition vertical et horizontal
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.BOTH;

        // Création du panel pour les boutons
        Panel boutonsPanel = new Panel();
        boutonsPanel.setLayout(new FlowLayout(FlowLayout.LEFT));

        // Création des boutons
        boutons = new Button[NBBOUTON];

        boutons[MENU] = new Button("Menu");
        boutonsPanel.add(boutons[MENU]);

        boutons[VERIFIER] = new Button("Verifier");
        boutonsPanel.add(boutons[VERIFIER]);

        boutons[CHARGER] = new Button("Charger");
        boutonsPanel.add(boutons[CHARGER]);

        boutons[SAUVEGARDE] = new Button("Sauvegarde");
        boutonsPanel.add(boutons[SAUVEGARDE]);

        boutons[RETOUR] = new Button("Retour");
        boutonsPanel.add(boutons[RETOUR]);

        boutons[AIDE] = new Button("Aide");
        boutonsPanel.add(boutons[AIDE]);

        // Rajouter les actions pour chaque boutons


        // Liste des boutons
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weighty = 1.0; // Prend tout l'espace vertical restant
        frame.add(boutonsPanel, gbc);
        // Grille de jeu
        //gbc.gridy = 1;
        //frame.add(grillePanel,gbc);

        // Fermeture de la fenetre
        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });

        frame.setSize(Toolkit.getDefaultToolkit().getScreenSize()); // Affichage adapté à la taille de l'écran
        frame.setVisible(true); // Affiche la fenêtre

    }

    /**
     * Méthode qui permet de retourner à la grille en annulant de coup précédent
     **/
    public void retourArriere(){
        //Si sauvegarde existante alors chargement sauvegarde (Bouton cliquable)

        //Sinon ne rien faire (Bouton non cliquable)
    }

    /**
     * Méthode qui permet à l'utilisateur de poser un point de sauvegarde rapide
     **/
    public void poserCheckpoint(){
        //Si sauvegarde existe alors on supprime l'ancienne

        //On créer un nouveau checkpoint
    }

    /**
     * Méthode qui permet de revenir à un point de sauvegarde rapide que l'utilisateur pourra créer
     **/
    public void chargerCheckpoint(){
        //Si checkpoint existe alors on charge (Bouton cliquable)

        //Sinon checkpoint n'existe pas (Bouton non cliquable)
    }

    /**
     * Méthode qui vérifie île par île si la grille est bonne
     **/
    public void verifierGrille(){
        //Appel de la fonction de map

    }

    /**
     *  Méthode qui permet de generer le jeu avec la liste de boutons ainsi que la grille
     *  @param args Argument passé lors du démarrage
     **/
    public static void main(String[] args){
        new MenuJeu();
    }
}