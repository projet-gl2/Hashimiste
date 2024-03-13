package fr.hashimiste.impl.gui.menu;

import fr.hashimiste.core.gui.JFrameTemplate;
import fr.hashimiste.core.gui.JFrameTemplateProfil;
import fr.hashimiste.core.image.AppImage;
import fr.hashimiste.core.joueur.Profil;
import fr.hashimiste.core.utils.SizeComponentAdapter;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * Cette classe représente le menu principal du jeu.
 * Elle hérite de JFrameTemplateProfil.
 */
public class Menu extends JFrameTemplateProfil {

    private final Dimension defaultDimension = new Dimension(800, 600);

    private final JPanel centre = new JPanel();
    private final JPanel bas = new JPanel();

    private final JLabel titre = new JLabel(new ImageIcon(AppImage.INSTANCE.getTitre(true)));

    private final JButton butAventure = creerBoutton("Aventure", new Aventure(this));
    private final JButton butTutoriel = creerBoutton("Tutoriel", (JFrame) null);
    private final JButton butModeLibre;
    private final JButton butMulti = creerBoutton("Multijoueur", (JFrame) null);
    private final JButton butTechnique = creerBoutton("Technique", new Technique(this));
    private final JButton butParametre = creerBoutton("Paramètre", new Parametre(this));
    private final JButton butProfils = creerBoutton("Profils", fenetreParente);

    private final Dimension defaultButtonSize;

    /**
     * Constructeur de la classe Menu.
     *
     * @param parent la fenêtre parente.
     * @param profil le profil du joueur.
     */
    public Menu(JFrameTemplate parent, Profil profil) {
        super(parent, new Dimension(800, 600), profil);
        this.butModeLibre = creerBoutton("Mode libre", new ModeLibre(this));

        appliquerTheme(centre, bas);

        final JButton[] buttons = {butAventure, butTutoriel, butModeLibre, butMulti, butTechnique, butParametre, butProfils};

        this.setLayout(new BorderLayout());
        centre.setLayout(new BoxLayout(centre, BoxLayout.Y_AXIS));
        bas.setLayout(new BoxLayout(bas, BoxLayout.X_AXIS));

        for (JComponent comp : new JComponent[]{titre, butAventure, butTutoriel, butModeLibre, butMulti, butTechnique}) {
            comp.setAlignmentX(Component.CENTER_ALIGNMENT);
            centre.add(comp);
            centre.add(Box.createVerticalStrut(10));
        }

        bas.add(butParametre);
        bas.add(Box.createHorizontalGlue());
        bas.add(butProfils);

        int maxWidth = 0;
        int maxHeight = 0;
        for (JButton button : buttons) {
            int width = button.getPreferredSize().width;
            int height = button.getPreferredSize().height;
            if (width > maxWidth) {
                maxWidth = width;
            }
            if (height > maxHeight) {
                maxHeight = height;
            }
        }
        defaultButtonSize = new Dimension(maxWidth, maxHeight);
        for (JButton button : buttons) {
            button.setSize(defaultButtonSize);
            button.setMinimumSize(defaultButtonSize);
            button.setMaximumSize(defaultButtonSize);
            button.setPreferredSize(defaultButtonSize);
            button.revalidate();
        }

        this.add(centre, BorderLayout.CENTER);
        this.add(bas, BorderLayout.SOUTH);

        this.addComponentListener(new SizeComponentAdapter(defaultDimension, defaultButtonSize, buttons));

        BufferedImage titreImg = AppImage.INSTANCE.getTitre(false);
        this.setMinimumSize(new Dimension(titreImg.getWidth(),
                defaultButtonSize.height * buttons.length
                        + 10 * buttons.length
                        + titreImg.getHeight()
        ));
    }
}