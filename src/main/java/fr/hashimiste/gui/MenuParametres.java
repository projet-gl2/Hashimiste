package fr.hashimiste.gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
 * Classe pour l'affichage du menu des paramètres
 * 
 * @author Henzo
 * @version 16.0.2
 */

public class MenuParametres extends JFrame implements ActionListener{
    private JPanel jp = new JPanel();
    private JButton butMenu = new JButton("Menu");

    private Color couleurBouton = new java.awt.Color(160, 158, 188);
    private Color couleurTextBouton = new java.awt.Color(251, 250, 242);

    private int tailleMinX = 500;
    private int tailleMinY = 300;
    private int tailleMaxX = 1920;
    private int tailleMaxY = 1080;

    public MenuParametres(){
        this.setTitle("Hashimiste");
        this.setIconImages(getIconImages());

        //Taille minimum pour la fenêtre
        this.setMinimumSize(new Dimension(tailleMinX, tailleMinY));
        //Preferred size pour la fenêtre
        this.setPreferredSize(new Dimension(tailleMinX, tailleMinY));
        //Taille maximum pour la fenêtre
        this.setMaximumSize(new Dimension(tailleMaxX, tailleMaxY));

        //Bloquer le redimensionnement de la fenêtre
        this.setResizable(false);

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);

        jp.setLayout(new BoxLayout(jp, BoxLayout.Y_AXIS));

        //Ajout d'un actionListener au bouton du menu pour appeler la méthode pageMenu
        butMenu.addActionListener((e) -> pageMenu());

        //Taille minimum des composants
        butMenu.setMinimumSize(new Dimension(30, 20));

        //Preferred size des composants
        butMenu.setPreferredSize(new Dimension(30, 20));

        //Taille maximum des composants
        butMenu.setMaximumSize(new Dimension(80, 20));

        butMenu.setAlignmentX(JButton.RIGHT_ALIGNMENT);

        //Couleur du font et du texte des composants
        butMenu.setBackground(new Color(couleurBouton.getRGB()));
        butMenu.setForeground(new Color(couleurTextBouton.getRGB()));

        //Ajout d'un mouseListener au bouton du menu pour changer la couleur quand on survole le bouton
        butMenu.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent e){
                butMenu.setBackground(new Color(couleurTextBouton.getRGB()));
                butMenu.setForeground(new Color(couleurBouton.getRGB()));
            }

            public void mouseExited(MouseEvent e){
                butMenu.setBackground(new Color(couleurBouton.getRGB()));
                butMenu.setForeground(new Color(couleurTextBouton.getRGB()));
            }
        });

        jp.add(butMenu);

        this.setContentPane(jp);

        this.pack();
        this.setVisible(true);
    }

    /**
     * Méthode pour afficher la page du menu et détruire la page des paramètres
     */
    public void pageMenu(){
        Menu m = new Menu();
        this.dispose();
    }

    /**
     * Exception levée pour les actions effectués par des composants
     * @param e Action qui vient d'être effectuée
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        throw new UnsupportedOperationException("Unimplemented method 'actionPerformed'");
    }

    /**
     * Méthode main pour lancer la fenêtre (individuellement)
     * @param args Arguments du main (entrés en ligne de commande)
     */
    public static void main(String args[]){
        MenuParametres p = new MenuParametres();
    }
}
