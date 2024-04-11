package fr.hashimiste.impl.gui.menu;

import fr.hashimiste.core.gui.JFrameTemplateProfil;
import fr.hashimiste.core.jeu.Grille;
import fr.hashimiste.impl.data.sql.filter.EqFilter;
import fr.hashimiste.impl.gui.jeu.Jeu;

import javax.swing.*;
import java.awt.*;
import java.util.List;

/**
 * Cette classe représente le mode Aventure du jeu.
 * Elle hérite de JFrameTemplateProfil.
 */
public class Aventure extends JFrameTemplateProfil {

    private final JPanel centre = new JPanel();
    private final JPanel bas = new JPanel();

    private final JButton butMenu = creerBoutton("Menu", fenetreParente);

    private final JLabel labAventure = new JLabel("Mode Aventure");

    private final transient List<Grille> grilles = stockage.charger(Grille.class, new EqFilter("aventure", 1));

    // Niveau as affiché avec la classe fr.hashimiste.impl.gui.jeu.Jeu [new Jeu(this, laGrille)]
    private final JFrame[] niveaux = new JFrame[]{
            new Jeu(this, grilles.get(1)),
            new Jeu(this, grilles.get(2)),
            new Jeu(this, grilles.get(3)),
            new Jeu(this, grilles.get(0)),
            new Jeu(this, grilles.get(4)),
            new Jeu(this, grilles.get(5)),
            new Jeu(this, grilles.get(6)),
            new Jeu(this, grilles.get(7)),
            new Jeu(this, grilles.get(8)),
            new Jeu(this, grilles.get(9))
    };

    /**
     * Constructeur de la classe Aventure.
     *
     * @param parent la fenêtre parente.
     */
    public Aventure(JFrameTemplateProfil parent) {
        super(parent);
        appliquerTheme(centre, bas, labAventure);

        labAventure.setAlignmentX(Component.CENTER_ALIGNMENT);
        labAventure.setFont(new Font("Arial", Font.BOLD, 30));

        centre.setLayout(new BoxLayout(centre, BoxLayout.Y_AXIS));
        centre.add(Box.createVerticalStrut(20));
        centre.add(labAventure);
        centre.add(Box.createVerticalStrut(10));
        for (int i = 0; i < niveaux.length; i++) {
            centre.add(Box.createVerticalStrut(10));
            JButton button = creerBoutton("Niveau " + (i + 1), niveaux[i]);
            centre.add(button);
            button.setAlignmentX(Component.CENTER_ALIGNMENT);
        }

        bas.add(Box.createHorizontalGlue());
        bas.add(butMenu);

        setLayout(new BorderLayout());
        bas.setLayout(new BoxLayout(bas, BoxLayout.X_AXIS));

        add(centre, BorderLayout.CENTER);
        add(bas, BorderLayout.SOUTH);
    }
}