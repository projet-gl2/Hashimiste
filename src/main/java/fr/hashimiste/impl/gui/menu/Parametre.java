package fr.hashimiste.impl.gui.menu;

import fr.hashimiste.core.gui.JFrameTemplateProfil;

import javax.swing.*;
import java.awt.*;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Cette classe représente les paramètres du jeu.
 * Elle hérite de JFrameTemplateProfil.
 */
public class Parametre extends JFrameTemplateProfil {

    private final JPanel centre = new JPanel();
    private final JPanel bas = new JPanel();

    private final JButton butMenu = creerBoutton("Menu", fenetreParente);
    private final JLabel labTheme = new JLabel("Thème : ");
    private final JComboBox<String> themes = new JComboBox<>(new String[]{"default", "debug", "candy", "dark"});
    private final JButton butSave = creerBoutton("Sauvegarder", this::sauvegarder);

    /**
     * Constructeur de la classe Parametre.
     *
     * @param parent la fenêtre parente.
     */
    public Parametre(JFrameTemplateProfil parent) {
        super(parent, new Dimension(800, 600));

        appliquerTheme(centre, bas, labTheme, themes);

        labTheme.setLabelFor(themes);

        themes.setSelectedItem(properties.getProperty("theme"));

        centre.add(labTheme);
        centre.add(themes);

        bas.add(butMenu);
        bas.add(Box.createHorizontalGlue());
        bas.add(butSave);

        this.setLayout(new BorderLayout());
        bas.setLayout(new BoxLayout(bas, BoxLayout.X_AXIS));

        this.add(centre, BorderLayout.CENTER);
        this.add(bas, BorderLayout.SOUTH);
    }

    /**
     * Cette méthode est utilisée pour sauvegarder les paramètres.
     */
    private void sauvegarder() {
        properties.setProperty("theme", (String) themes.getSelectedItem());
        try (FileOutputStream fileOutputStream = new FileOutputStream(fichierProperties)) {
            properties.store(fileOutputStream, "Hashimiste properties");
        } catch (IOException e) {
            e.printStackTrace();
        }
        changerFenetre(fenetreParente);
    }
}