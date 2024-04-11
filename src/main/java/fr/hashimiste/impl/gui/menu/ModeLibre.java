package fr.hashimiste.impl.gui.menu;

import fr.hashimiste.core.dev.Debuggable;
import fr.hashimiste.core.gui.JFrameTemplateProfil;
import fr.hashimiste.core.image.AppImage;
import fr.hashimiste.core.jeu.Difficulte;
import fr.hashimiste.core.jeu.Grille;
import fr.hashimiste.core.joueur.StatistiqueKey;
import fr.hashimiste.core.utils.SizeComponentAdapter;
import fr.hashimiste.impl.gui.component.PreviewComponent;
import fr.hashimiste.impl.gui.jeu.Jeu;
import fr.hashimiste.impl.jeu.GrilleImpl;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Cette classe représente le mode libre du jeu.
 * Elle hérite de JFrameTemplateProfil et implémente Debuggable.
 */
public class ModeLibre extends JFrameTemplateProfil implements Debuggable {

    public static final PreviewComponent GRILLE_VIDE_PREVIEW = new PreviewComponent(new GrilleImpl(-1, new Dimension(7, 7), Difficulte.FACILE, false, new ArrayList<>())) {
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            BufferedImage logo = AppImage.INSTANCE.getLogo(true);
            double factor = Math.min((getSize().getWidth() - 5) / logo.getWidth(), (getSize().getHeight() - 5) / logo.getHeight());
            int zeroX = (int) ((getSize().width / 2d) - ((logo.getWidth() * factor) / 2));
            int zeroY = (int) ((getSize().height / 2d) - ((logo.getHeight() * factor) / 2));
            g.drawImage(logo, zeroX, zeroY, (int) (logo.getWidth() * factor), (int) (logo.getHeight() * factor), null);
        }
    };

    private final JPanel centre = new JPanel();
    private final JPanel bas = new JPanel();
    private final JPanel preview = new JPanel();

    private final JPanel facile = new JPanel();
    private final JPanel moyen = new JPanel();
    private final JPanel difficile = new JPanel();

    private final JTextArea txtStats = new JTextArea("Placer la souris sur une grille pour voir les statistiques.");

    private final transient List<Grille> grilles = stockage.charger(Grille.class);

    private final JButton butMenu = creerBoutton("Menu", fenetreParente);

    /**
     * Constructeur de la classe ModeLibre.
     *
     * @param parent la fenêtre parente.
     */
    public ModeLibre(JFrameTemplateProfil parent) {
        super(parent);
        appliquerTheme(centre, bas);

        centre.setLayout(new BoxLayout(centre, BoxLayout.Y_AXIS));

        txtStats.setEditable(false);
        txtStats.setFocusable(false);
        txtStats.setLineWrap(true);
        txtStats.setWrapStyleWord(true);
        appliquerTheme(txtStats, facile, moyen, difficile, preview, butMenu);

        preview.setLayout(new GridLayout(0, 1));
        preview.setBorder(BorderFactory.createTitledBorder("Aperçu"));
        preview.setPreferredSize(new Dimension(200, 600));
        preview.setBackground(Color.GRAY);
        preview.add(GRILLE_VIDE_PREVIEW);
        preview.add(txtStats);

        facile.setBorder(BorderFactory.createTitledBorder("Facile"));
        moyen.setBorder(BorderFactory.createTitledBorder("Moyen"));
        difficile.setBorder(BorderFactory.createTitledBorder("Difficile"));

        JPanel[] panelDifficulte = {facile, moyen, difficile};
        for (JPanel panel : panelDifficulte) {
            panel.setPreferredSize(new Dimension(200, 200));
            panel.setLayout(new GridLayout(2, 5));
            centre.add(panel);
        }

        for (Grille grille : grilles) {
            JPanel panel;
            switch (grille.getDifficulte()) {
                case FACILE:
                    panel = facile;
                    break;
                case MOYEN:
                    panel = moyen;
                    break;
                case DIFFICILE:
                    panel = difficile;
                    break;
                default:
                    throw new IllegalStateException("Unexpected value: " + grille.getDifficulte());
            }
            PreviewComponent previewComponent = new PreviewComponent(grille);
            panel.add(previewComponent);
            previewComponent.addMouseListener(new MouseAdapter() {
                final JComponent previewComp = new PreviewComponent(grille);

                @Override
                public void mouseEntered(MouseEvent e) {
                    super.mouseEntered(e);
                    previewComponent.setBorder(BorderFactory.createEmptyBorder());
                    preview.remove(GRILLE_VIDE_PREVIEW);
                    preview.add(previewComp, 0);
                    txtStats.setText(String.format(
                            "Taille: %dx%d\n" +
                                    "Partie jouer: %d\n" +
                                    "Partie gagnée: %d\n" +
                                    "Meilleur temps: %ds\n" +
                                    "Moyenne temps: %ds\n" +
                                    "Cliquer pour jouer.",
                            grille.getDimension().width, grille.getDimension().height,
                            profil.getStatistique(StatistiqueKey.PARTIE_JOUEE, grille.getId()).getValeur(),
                            profil.getStatistique(StatistiqueKey.PARTIE_GAGNEE, grille.getId()).getValeur(),
                            profil.getStatistique(StatistiqueKey.TEMPS_MIN, grille.getId()).getValeur(),
                            profil.getStatistique(StatistiqueKey.TEMPS_MOYEN, grille.getId()).getValeur()));
                    preview.repaint();
                }

                @Override
                public void mouseExited(MouseEvent e) {
                    super.mouseExited(e);
                    previewComponent.setBorder(null);
                    preview.remove(previewComp);
                    preview.add(GRILLE_VIDE_PREVIEW, 0);
                    txtStats.setText("Placer la souris sur une grille pour voir les statistiques.");
                    preview.repaint();
                }

                @Override
                public void mousePressed(MouseEvent e) {
                    ModeLibre.super.changerFenetre(new Jeu(ModeLibre.this, grille));
                    super.mouseClicked(e);
                }
            });
        }
        for (JPanel panel : panelDifficulte) {
            for (int i = panel.getComponentCount(); i < 10; i++) {
                panel.add(new PreviewComponent(null));
            }
        }


        bas.add(Box.createHorizontalGlue());
        bas.add(butMenu);

        setLayout(new BorderLayout());
        bas.setLayout(new BoxLayout(bas, BoxLayout.X_AXIS));

        addComponentListener(new SizeComponentAdapter(this.getSize(), new Dimension(200, 600), preview));

        add(centre, BorderLayout.CENTER);
        add(preview, BorderLayout.EAST);
        add(bas, BorderLayout.SOUTH);
    }

    /**
     * Cette méthode est utilisée pour obtenir des informations de débogage.
     *
     * @return une chaîne de caractères contenant des informations de débogage.
     */
    @Override
    public String getDebugInfo() {
        return String.format("Map affiché:%n  Facile: %d%n  Moyen: %d%n  Difficile: %d%n  Preview: %s",
                Arrays.stream(facile.getComponents()).filter(c -> !c.equals(GRILLE_VIDE_PREVIEW))
                        .filter(PreviewComponent.class::isInstance)
                        .map(PreviewComponent.class::cast)
                        .filter(pc -> pc.getGrille() != null)
                        .count(),
                Arrays.stream(moyen.getComponents()).filter(c -> !c.equals(GRILLE_VIDE_PREVIEW))
                        .filter(PreviewComponent.class::isInstance)
                        .map(PreviewComponent.class::cast)
                        .filter(pc -> pc.getGrille() != null)
                        .count(),
                Arrays.stream(difficile.getComponents()).filter(c -> !c.equals(GRILLE_VIDE_PREVIEW))
                        .filter(PreviewComponent.class::isInstance)
                        .map(PreviewComponent.class::cast)
                        .filter(pc -> pc.getGrille() != null)
                        .count(),
                Arrays.stream(preview.getComponents())
                        .filter(PreviewComponent.class::isInstance)
                        .map(PreviewComponent.class::cast)
                        .map(PreviewComponent::getGrille)
                        .map(c -> !c.estEnregistre() || c.getId() == -1 ? "EMPTY" : c.toString())
                        .findFirst()
                        .orElse(null)
        );
    }
}