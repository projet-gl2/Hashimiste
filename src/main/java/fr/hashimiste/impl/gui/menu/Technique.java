package fr.hashimiste.impl.gui.menu;

import fr.hashimiste.core.gui.JFrameTemplateProfil;

import javax.swing.*;
import java.awt.*;

/**
 * Cette classe représente le menu technique du jeu.
 * Elle hérite de JFrameTemplateProfil.
 */
public class Technique extends JFrameTemplateProfil {

    private final JPanel haut = new JPanel(new GridBagLayout());

    private final JButton butMenu = creerBoutton("Menu", fenetreParente);

    private final DefaultListModel<String> listModel = new DefaultListModel<>();
    private final JList<String> list = new JList<>(listModel);
    private final JScrollPane scroll = new JScrollPane(list);
    private final JTextArea description = new JTextArea();
    private final JLabel exemple_image = new JLabel();

    /**
     * Constructeur de la classe Technique.
     *
     * @param parent la fenêtre parente.
     */
    public Technique(JFrameTemplateProfil parent) {
        super(parent);
        appliquerTheme(haut, list, scroll, description);

        setLayout(new BorderLayout());

        GridBagConstraints constraints = new GridBagConstraints();
        constraints.weightx = 0.1;
        constraints.anchor = GridBagConstraints.WEST;
        haut.add(butMenu, constraints);

        for (fr.hashimiste.core.jeu.Technique value : fr.hashimiste.core.jeu.Technique.values()) {
            listModel.addElement(value.getNom());
        }

        list.addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                fr.hashimiste.core.jeu.Technique technique = fr.hashimiste.core.jeu.Technique.values()[list.getSelectedIndex()];
                description.setText(technique.getDescription());
                /*L'image ne s'affiche pas jsp pourquoi */
                //exemple_image.setIcon(new ImageIcon(technique.getUrl()));
            }
        });
        description.setLineWrap(true);
        description.setWrapStyleWord(true);
        description.setEditable(false);

        add(haut, BorderLayout.NORTH);
        add(scroll, BorderLayout.WEST);
        add(description, BorderLayout.CENTER);
        //add(exemple_image, BorderLayout.SOUTH);
    }
}
