package fr.hashimiste.gui;

import javax.swing.*;
import java.awt.event.*;

public class Menu extends JFrame implements ActionListener {
    private JPanel jp = new JPanel();
    private JPanel jp2 = new JPanel();
    private JButton ButAventure = new JButton("Aventure");
    private JButton ButTutoriel = new JButton("Tutoriel");
    private JButton ButModeLibre = new JButton("Mode Libre");
    private JButton ButMultijoueur = new JButton("Multijoueur");
    private JButton ButTechnique = new JButton("Technique");
    private JButton ButParametre = new JButton("Paramètres");
    private JButton ButProfils = new JButton("Quitter");
    private JLabel lblSomeText = new JLabel("Hashimiste", SwingConstants.CENTER);

    public Menu() {
        this.setTitle("Hashimiste");
        this.setIconImages(getIconImages());
        this.setSize(500, 300);
        // Minimum size pour la fenêtre
        this.setMinimumSize(new java.awt.Dimension(500, 300));

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

        // Minimum size pour les boutons et texte
        lblSomeText.setMinimumSize(new java.awt.Dimension(50, 20));
        ButAventure.setMinimumSize(new java.awt.Dimension(50, 20));
        ButTutoriel.setMinimumSize(new java.awt.Dimension(50, 20));
        ButModeLibre.setMinimumSize(new java.awt.Dimension(50, 20));
        ButMultijoueur.setMinimumSize(new java.awt.Dimension(50, 20));
        ButTechnique.setMinimumSize(new java.awt.Dimension(50, 20));
        ButParametre.setMinimumSize(new java.awt.Dimension(50, 20));
        ButProfils.setMinimumSize(new java.awt.Dimension(50, 20));


        // Preferred size pour le texte et bouton de 100x20
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



        jp.add(lblSomeText);
        jp.add(Box.createVerticalStrut(10));
        jp.add(ButAventure);
        jp.add(Box.createVerticalStrut(10));
        jp.add(ButTutoriel);
        jp.add(Box.createVerticalStrut(10));
        jp.add(ButModeLibre);
        jp.add(Box.createVerticalStrut(10));
        jp.add(ButMultijoueur);
        jp.add(Box.createVerticalStrut(10));
        jp.add(ButTechnique);
        jp.add(Box.createVerticalStrut(10));

        // setLayout box pour les boutons Paramètres et Profils
        jp2.setLayout(new BoxLayout(jp2, BoxLayout.X_AXIS));
        ButParametre.setAlignmentX(JButton.LEFT_ALIGNMENT);
        ButProfils.setAlignmentX(JButton.RIGHT_ALIGNMENT);
        jp2.add(ButParametre);
        // Espace entre les boutons ( ButParametre collé en bas à gauche et ButProfils collé en bas à droite)
        jp2.add(Box.createHorizontalGlue());
        jp2.add(ButProfils);
        jp.add(jp2);

        this.setContentPane(jp);
        this.setVisible(true);
    }

    public void pageAventure() {

    }

    public void pageTutoriel() {

    }

    public void pageModeLibre() {

    }

    public void pageMultijoueur() {

    }

    public void pageTechnique() {

    }

    public void pageParametre() {

    }

    public void pageProfils() {

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        throw new UnsupportedOperationException("Unimplemented method 'actionPerformed'");
    }

    public static void main(String[] args) {
        Menu m = new Menu();
    }

}