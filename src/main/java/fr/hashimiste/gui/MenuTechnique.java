package fr.hashimiste.gui;

//Importation du package à changer quand tous les packages seront créés
import a.Technique;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

/**
 * Classe pour l'affichage du menu des techniques
 * 
 * @author Henzo
 * @version 16.0.2
 */

public class MenuTechnique extends JFrame implements ActionListener{
    private JPanel jp = new JPanel();
    private JPanel jp2 = new JPanel();
    private DefaultListModel<String> dlm = new DefaultListModel<>();
    private JList<String> jl = new JList<>();
    private JButton butMenu = new JButton("Menu");

    private JLabel description = new JLabel();

    private ArrayList<String> listDesc = new ArrayList<>();

    private Color couleurBouton = new java.awt.Color(160, 158, 188);
    private Color couleurTextBouton = new java.awt.Color(251, 250, 242);

    private int tailleMinX = 950;
    private int tailleMinY = 400;
    private int tailleMaxX = 1920;
    private int tailleMaxY = 1080;

    /**
     * Constructeur pour créer tous les composants de la fenêtre
     */
    public MenuTechnique(){
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

        //Ajout d'un ActionListener pour appeler la méthode qui ferme cette page et ouvre la page du menu
        butMenu.addActionListener((e) -> pageMenu());

        //Taille minimum des composants
        butMenu.setMinimumSize(new Dimension(30, 20));
        jl.setMinimumSize(new Dimension(200, 200));
        description.setMinimumSize(jl.getMinimumSize());

        //Preferred size des composants
        butMenu.setPreferredSize(new Dimension(30, 20));
        jl.setPreferredSize(new Dimension(200, 200));
        description.setPreferredSize(jl.getPreferredSize());

        //Taille maximum des composants
        butMenu.setMaximumSize(new Dimension(80, 20));
        jl.setMaximumSize(new Dimension(300, 200));
        description.setMaximumSize(jl.getMaximumSize());

        butMenu.setAlignmentX(JButton.LEFT_ALIGNMENT);
        description.setAlignmentX(JLabel.LEFT_ALIGNMENT);
        description.setVerticalAlignment(JLabel.TOP);

        butMenu.setBackground(new Color(couleurBouton.getRGB()));
        description.setBackground(Color.WHITE);

        butMenu.setForeground(new Color(couleurTextBouton.getRGB()));

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
        //jp.add(Box.createVerticalStrut(10));

        jp2.setLayout(new BoxLayout(jp2, BoxLayout.X_AXIS));
        jp2.setAlignmentX(JPanel.LEFT_ALIGNMENT);

        //Ajout des descriptions dans la liste de descriptions des techniques et les noms dans la JList
        for(Technique t : Technique.values()){
            listDesc.add(t.getDescription());
            dlm.addElement(t.getNom());
        }

        jl.setModel(dlm);
        jl.setOpaque(true);

        //Ajout d'un MouseListener à la liste pour changer la description de la technique
        jl.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e){
                description.setOpaque(true);
                description.setText("<html>"+listDesc.get(jl.getSelectedIndex())+"</html>");
            }
        });

        jp2.add(jl);
        jp2.add(Box.createHorizontalStrut(20));
        jp2.add(description);
        jp.add(jp2);

        this.setContentPane(jp);

        this.pack();
        this.setVisible(true);
    }

    /**
     * Méthode pour afficher la page du menu et détruire la page des techniques
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
        MenuTechnique t = new MenuTechnique();
    }
}
