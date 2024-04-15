package fr.hashimiste.impl.gui.menu;

import fr.hashimiste.core.gui.JFrameTemplateProfil;
import fr.hashimiste.core.jeu.Grille;
import fr.hashimiste.core.jeu.Historique;
import fr.hashimiste.core.jeu.Ile;
import fr.hashimiste.impl.data.sql.filter.EqFilter;
import fr.hashimiste.impl.gui.component.GameComponent;

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
    private final GameComponent demo = new GameComponent(null) {
        @Override
        public void onNewAction(Ile ile1, Ile ile2, Historique.Action action) {
            // Ne rien faire
        }
    };

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
                Grille grille = stockage.charger(Grille.class, new EqFilter("id_map", technique.getGrilleId())).stream().findFirst().orElse(null);
                demo.setGrille(grille);
                if (grille != null) {
                    demo.loadSave(grille.getSauvegardes(stockage)
                            .stream()
                            .filter(s -> s.getSauvegardeTimestamp().getTime() == technique.getSauvegardeTimestamp()
                                    && s.getProfil().getId() == 1)
                            .findFirst().orElse(null)
                    );
                }
            }
        });
        description.setLineWrap(true);
        description.setWrapStyleWord(true);
        description.setEditable(false);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(0, 1));
        panel.add(description);
        panel.add(demo);

        add(haut, BorderLayout.NORTH);
        add(scroll, BorderLayout.WEST);
        add(panel, BorderLayout.CENTER);
    }
}
