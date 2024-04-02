package fr.hashimiste.impl.gui.jeu;

import fr.hashimiste.core.dev.Debuggable;
import fr.hashimiste.core.gui.JFrameTemplateProfil;
import fr.hashimiste.core.jeu.Grille;
import fr.hashimiste.core.jeu.Historique;
import fr.hashimiste.core.jeu.Sauvegarde;
import fr.hashimiste.core.utils.CollectionsUtils;
import fr.hashimiste.impl.gui.component.GameComponent;
import fr.hashimiste.impl.gui.component.PreviewComponent;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Cette classe représente le jeu.
 * Elle hérite de JFrameTemplateProfil et implémente Debuggable.
 */
public class Jeu extends JFrameTemplateProfil implements Debuggable, MouseMotionListener {

    private final transient Grille grille;
    private final JButton butMenu = creerBoutton("Menu", fenetreParente);
    private final JButton butVerifier = creerBoutton("Vérifier", this::verifier);
    private final JButton butAide = creerBoutton("Aide", this::aide);
    private final JButton butCharger = creerBoutton("Charger", this::charger);
    private final JButton butSauvegarder = creerBoutton("Sauvegarder", this::sauvegarder);
    private final JButton butCheckpoint = creerBoutton("Checkpoint", this::checkpoint);
    private final List<Historique> historiques = new ArrayList<>();
    private transient Historique precedent;
    private transient List<Sauvegarde> sauvegardes;


    /**
     * Constructeur de la classe Jeu.
     *
     * @param parent la fenêtre parente.
     * @param grille la grille de jeu.
     */
    public Jeu(JFrameTemplateProfil parent, Grille grille) {
        super(parent);
        this.grille = grille;


        setLayout(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.fill = GridBagConstraints.BOTH;
        constraints.weighty = 0; // Set weighty to 0 for the buttons

        JPanel panelButtons = new JPanel(new FlowLayout(FlowLayout.LEFT));


        for (JButton button : new JButton[]{butMenu, butVerifier, butCharger, butSauvegarder, butCheckpoint, butPrecedent, butAide}) {
            panelButtons.add(button);
        }
        add(panelButtons, constraints);

        this.setMinimumSize(new Dimension(700, 400));

        // Create the game panel
        JPanel game = new JPanel(new BorderLayout());


        // Add the PreviewComponent to the center of the game panel
        GameComponent gameComponent = new GameComponent(grille);
        game.add(gameComponent, BorderLayout.CENTER);



        // Add the game panel to the frame
        constraints.gridy = 1; // Set the gridy to 1 to place it below the buttons
        constraints.weighty = 1; // Set weighty to 1 to make it fill the remaining space
        add(game, constraints);

        game.addMouseMotionListener(gameComponent);
        game.addMouseListener(gameComponent);
    }
    /**
     * Cette méthode est utilisée pour vérifier le jeu.
     */
    private void verifier() {
        System.out.println(grille.verification(precedent));
        JOptionPane.showMessageDialog(this, "Verifier", "Verifier", JOptionPane.INFORMATION_MESSAGE);
        // TODO : Implementer la vérification
    }

    /**
     * Cette méthode est utilisée pour charger le jeu.
     */
    private void charger() {
        String nomSauvegarde = (String) JOptionPane.showInputDialog(this,
                "Choisissez une sauvegarde",
                "Charger",
                JOptionPane.QUESTION_MESSAGE,
                null,
                sauvegardes.stream().map(Sauvegarde::getNom).toArray(),
                sauvegardes.get(0).getNom());
        if (nomSauvegarde != null) {
            Sauvegarde save = sauvegardes.stream().filter(s -> s.getNom().equals(nomSauvegarde)).findFirst().orElse(null);
            if (save != null) {
//                grille.chargerSauvegarde(save); TODO
                chargerHistorique(save.getReference());
            }
        }
    }

    /**
     * Cette méthode est utilisée pour charger l'historique du jeu.
     *
     * @param historique l'historique a charger.
     */
    private void chargerHistorique(Historique historique) {
        historiques.clear();
        Historique tmp = historique;
        while (tmp != null) {
            historiques.add(tmp);
            tmp = tmp.getAvant();
        }
        Collections.sort(historiques, Comparator.comparing(Historique::getTimestamp));
        precedent = historique;
        setActive(precedent != null
                && precedent.getIle1() != null
                && precedent.getIle2() != null
                && precedent.getAction() != Historique.Action.NOUVELLE_GRILLE, butPrecedent);
    }

    /**
     * Cette méthode est utilisée pour sauvegarder le jeu.
     */
    private void sauvegarder() {
        String nomSauvegarde = JOptionPane.showInputDialog(this, "Nom de la sauvegarde", "Sauvegarder", JOptionPane.QUESTION_MESSAGE);
        if (nomSauvegarde != null) {
            Sauvegarde save = precedent.creerSauvegarde(profil, nomSauvegarde);
            stockage.sauvegarder(save);
            grille.rafraichirSauvegardes(stockage);
            sauvegardes = grille.getSauvegardes(stockage);
            setActive(!sauvegardes.isEmpty(), butCharger);
        }
    }

    private final JButton butPrecedent = creerBoutton("Retour", this::precedent);

    /**
     * Cette méthode est utilisée pour créer un point de contrôle dans le jeu.
     */
    private void checkpoint() {
        stockage.sauvegarder(precedent.creerSauvegarde(profil, "Checkpoint"));
        grille.rafraichirSauvegardes(stockage);
        sauvegardes = grille.getSauvegardes(stockage);
        setActive(!sauvegardes.isEmpty(), butCharger);
    }

    /**
     * Cette méthode est utilisée pour revenir à l'état précédent du jeu.
     */
    private void precedent() {
        precedent = precedent.getAvant();
        setActive(precedent != null
                && precedent.getIle1() != null
                && precedent.getIle2() != null
                && precedent.getAction() != Historique.Action.NOUVELLE_GRILLE, butPrecedent);
    }

    /**
     * Cette méthode est utilisée pour obtenir de l'aide dans le jeu.
     */
    private void aide() {
        JOptionPane.showMessageDialog(this, "Aide", "Aide", JOptionPane.INFORMATION_MESSAGE);
        // TODO : Implementer l'aide
    }

    /**
     * Cette méthode est utilisée pour obtenir des informations de débogage.
     *
     * @return une chaîne de caractères contenant des informations de débogage.
     */
    @Override
    public String getDebugInfo() {
        StringBuilder sb = new StringBuilder();
        sb.append("Grille: ").append(grille).append("\n");
        sb.append("Sauvegardes: ").append(sauvegardes).append("\n");
        sb.append("Historique: ").append("\n");
        for (Historique h : historiques) {
            if (h.equals(precedent)) {
                sb.append(" -> ");
            } else {
                sb.append("    ");
            }
            sb.append(h).append("\n");
        }
        return sb.toString();
    }


    @Override
    public void mouseDragged(MouseEvent e) {

    }

    @Override
    public void mouseMoved(MouseEvent e) {
        System.out.println("Mouse moved");
    }
}
