package fr.hashimiste.gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

/**
 * La classe Jeu correspond au jeu lorsqu'on est face à l'écran de jeu avec les boutons ainsi que la grille, il y divers boutons (retour arrière, poser un checkpoint, aide, etc)
 * 
 * @author Ilyas 
 **/
public class MenuJeu extends JFrame{

    private ArrayList<JButton> boutons = new ArrayList<>(); //Liste des boutons pour les fonctions

    /**
     * Constante qui indique le nombre de boutons de le menu
     **/
    public static final int NB_BOUTON = 7;

    private JButton butMenu = new JButton("Menu");
    private JButton butVerifier = new JButton("Vérifier");
    private JButton butCharger = new JButton("Charger");
    private JButton butSauvegarder = new JButton("Sauvegarder");
    private JButton butCheckpoint = new JButton("Checkpoint");
    private JButton butRetour = new JButton("Retour");
    private JButton butAide = new JButton("Aide");

    // grille;
    // grilleComplete;

    /**
     * Méthode de création de la fenetre de jeu avec la grille ainsi que la liste de boutons chacun correpondant à une fonction prédéfinit
     **/
    public MenuJeu(/*la grille de jeu, la grile solution*/){
        //this.grille = /* la grille de jeu */;
        //this.grilleComplete = /* la grille solution */

        // Titre de la frame
        super("Hashimiste");
        this.setLayout(new GridBagLayout());

        // Contrainte pour la disposition vertical et horizontal
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.BOTH;

        // Création du panel pour les boutons
        JPanel jp = new JPanel();
        jp.setLayout(new FlowLayout(FlowLayout.LEFT));

        butMenu.addActionListener((e) -> pageMenu());
        butVerifier.addActionListener((e) -> verifierGrille());
        butCharger.addActionListener((e) -> charger());
        butSauvegarder.addActionListener((e) -> sauvegarder());
        butCheckpoint.addActionListener((e) -> poserCheckpoint());
        butRetour.addActionListener((e) -> retourArriere());
        butAide.addActionListener((e) -> demandeAide());

        boutons.add(butMenu);
        boutons.add(butVerifier);
        boutons.add(butCharger);
        boutons.add(butSauvegarder);
        boutons.add(butCheckpoint);
        boutons.add(butRetour);
        boutons.add(butAide);

        for(JButton b : boutons){
            b.setBackground(Couleur.COULEUR_BOUTON);
            b.setForeground(Couleur.COULEUR_TEXTE_BOUTON);

            b.addMouseListener(new MouseAdapter() {
                public void mouseEntered(MouseEvent e){
                    b.setBackground(Couleur.COULEUR_TEXTE_BOUTON);
                    b.setForeground(Couleur.COULEUR_BOUTON);
                }

                public void mouseExited(MouseEvent e){
                    b.setBackground(Couleur.COULEUR_BOUTON);
                    b.setForeground(Couleur.COULEUR_TEXTE_BOUTON);
                }
            });
        }

        // Création des boutons
        for(JButton b : boutons){
            jp.add(b);
        }


        // Liste des boutons
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weighty = 1.0; // Prend tout l'espace vertical restant
        this.add(jp, gbc);
        // Grille de jeu
        //gbc.gridy = 1;
        //frame.add(grillePanel,gbc);

        // Fermeture de la fenetre
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });

        this.setIconImage(new ImageIcon(Image.ICON_TRANSPARENT).getImage());

        this.setSize(Toolkit.getDefaultToolkit().getScreenSize()); // Affichage adapté à la taille de l'écran
        this.setVisible(true); // Affiche la fenêtre

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
    public void charger(){
        //Si checkpoint existe alors on charge (Bouton cliquable)

        //Sinon checkpoint n'existe pas (Bouton non cliquable)
    }

    /**
     * Méthode qui permet de sauvegarder notre partie pour quitter le jeu
     */
    public void sauvegarder(){

    }

    /**
     * Méthode qui vérifie île par île si la grille est bonne
     **/
    public void verifierGrille(){
        //Appel de la fonction de map

    }

    /**
     * Méthode pour demander de l'aide pendant une partie
     */
    public void demandeAide(){

    }

    public void pageMenu(){
        new Menu();
        this.dispose();
    }

    /**
     *  Méthode qui permet de generer le jeu avec la liste de boutons ainsi que la grille
     *  @param args Argument passé lors du démarrage
     **/
    public static void main(String[] args){
        new MenuJeu();
    }
}