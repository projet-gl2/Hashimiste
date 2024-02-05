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

    @Override
    public void actionPerformed(ActionEvent e) {
        throw new UnsupportedOperationException("Unimplemented method 'actionPerformed'");
    }

    public static void main(String args[]){
        MenuParametres p = new MenuParametres();
    }
}
