package fr.hashimiste.gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

import static fr.hashimiste.gui.Fenetre.*;

/**
 * Classe pour l'affichage du menu des paramètres.
 * 
 * @author Henzo Ageorges
 * @version 16.0.2
 */

public class MenuParametres extends JFrame implements ActionListener{
    private JPanel jp = new JPanel();
    private JButton butMenu = new JButton("Menu");

    public MenuParametres(){
        this.setTitle("Hashimiste");
        this.setIconImages(getIconImages());

        //Taille minimum pour la fenêtre
        this.setMinimumSize(new Dimension(TAILLE_MIN_X, TAILLE_MIN_Y));
        //Preferred size pour la fenêtre
        this.setPreferredSize(new Dimension(TAILLE_MIN_X, TAILLE_MIN_Y));
        //Taille maximum pour la fenêtre
        this.setMaximumSize(new Dimension(TAILLE_MAX_X, TAILLE_MAX_Y));

        //Bloquer le redimensionnement de la fenêtre
        this.setResizable(false);

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);

        jp.setLayout(new BoxLayout(jp, BoxLayout.Y_AXIS));

        //Ajout d'un actionListener au bouton du menu pour appeler la méthode pageMenu
        butMenu.addActionListener((e) -> pageMenu());

        //Taille minimum des composants
        butMenu.setMinimumSize(new Dimension(TAILLE_MIN_COMPOSANT_X, TAILLE_MIN_COMPOSANT_Y));

        //Preferred size des composants
        butMenu.setPreferredSize(new Dimension(TAILLE_PREF_COMPOSANT_X, TAILLE_PREF_COMPOSANT_Y));

        //Taille maximum des composants
        butMenu.setMaximumSize(new Dimension(TAILLE_MAX_COMPOSANT_X, TAILLE_MAX_COMPOSANT_Y));

        butMenu.setAlignmentX(JButton.RIGHT_ALIGNMENT);

        //Couleur du font et du texte des composants
        butMenu.setBackground(new Color(Couleur.COULEUR_BOUTON.getRGB()));
        butMenu.setForeground(new Color(Couleur.COULEUR_TEXTE_BOUTON.getRGB()));

        //Ajout d'un mouseListener au bouton du menu pour changer la couleur quand on survole le bouton
        butMenu.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent e){
                butMenu.setBackground(new Color(Couleur.COULEUR_TEXTE_BOUTON.getRGB()));
                butMenu.setForeground(new Color(Couleur.COULEUR_BOUTON.getRGB()));
            }

            public void mouseExited(MouseEvent e){
                butMenu.setBackground(new Color(Couleur.COULEUR_BOUTON.getRGB()));
                butMenu.setForeground(new Color(Couleur.COULEUR_TEXTE_BOUTON.getRGB()));
            }
        });

        jp.add(butMenu);

        this.setContentPane(jp);

        this.setIconImage(new ImageIcon(Image.ICON_TRANSPARENT).getImage());

        this.pack();
        this.setVisible(true);
    }

    /**
     * Méthode pour afficher la page du menu et détruire la page des paramètres
     */
    public void pageMenu(){
        new Menu();
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
        new MenuParametres();
    }
}
