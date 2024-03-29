package fr.hashimiste.impl.gui.jeu;

import fr.hashimiste.core.dev.Debuggable;
import fr.hashimiste.core.gui.JFrameTemplateProfil;
import fr.hashimiste.core.jeu.Grille;
import fr.hashimiste.core.jeu.Historique;
import fr.hashimiste.core.jeu.Sauvegarde;
import fr.hashimiste.core.utils.CollectionsUtils;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Cette classe représente le jeu.
 * Elle hérite de JFrameTemplateProfil et implémente Debuggable.
 */
public class Jeu extends JFrameTemplateProfil implements Debuggable {

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
        JPanel panelButtons = new JPanel(new FlowLayout(FlowLayout.LEFT));
        for (JButton button : new JButton[]{butMenu, butVerifier, butCharger, butSauvegarder, butCheckpoint, butPrecedent, butAide}) {
            panelButtons.add(button);
        }

        GridBagConstraints constraints = new GridBagConstraints();
        constraints.fill = GridBagConstraints.BOTH;
        constraints.weighty = 1;
        add(panelButtons, constraints);

        chargerHistorique(new Historique(grille, null, null, Historique.Action.NOUVELLE_GRILLE));

        sauvegardes = CollectionsUtils.listeDe(grille.getSauvegardes(stockage).stream().filter(s -> s.getProfil().equals(profil)).toArray(Sauvegarde[]::new));
        Collections.sort(sauvegardes, (o1, o2) -> o2.getReference().getTimestamp().compareTo(o1.getReference().getTimestamp()));
        setActive(!sauvegardes.isEmpty(), butCharger);
    }

    /**
     * Cette méthode est utilisée pour vérifier le jeu.
     */
    private void verifier() {
        // TODO
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
        // TODO
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


}
