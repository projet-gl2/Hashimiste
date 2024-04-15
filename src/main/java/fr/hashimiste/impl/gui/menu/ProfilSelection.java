package fr.hashimiste.impl.gui.menu;

import fr.hashimiste.core.data.Stockage;
import fr.hashimiste.core.gui.JFrameTemplate;
import fr.hashimiste.core.joueur.Profil;
import fr.hashimiste.core.utils.StringUtils;
import fr.hashimiste.impl.Main;
import fr.hashimiste.impl.joueur.ProfilImpl;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;

/**
 * Cette classe représente la sélection du profil dans le jeu.
 * Elle hérite de JFrameTemplate.
 */
public class ProfilSelection extends JFrameTemplate {
    public static final int MAX_CHARACTER_NOM = 10;
    public static final int MAX_PROFILS = 0;
    public static final int ESPACE_ENTRE_PROFILS = 10;

    private final Map<Profil, Menu> menuCharger = new HashMap<>();

    private final JPanel comptePanel = new JPanel();
    private final JPanel bagPanel = new JPanel(new GridBagLayout());
    private final JScrollPane scrollPane = new JScrollPane(
            bagPanel,
            ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER,
            ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED
    );

    /**
     * Constructeur de la classe ProfilSelection.
     *
     * @param propertiesFile le fichier de propriétés.
     * @param stockage       le stockage des données.
     */
    public ProfilSelection(File propertiesFile, Stockage stockage) throws HeadlessException {
        super(null, propertiesFile, stockage, new Dimension(500, 300));
        appliquerTheme(comptePanel, bagPanel);

        scrollPane.setMinimumSize(new Dimension(500, 260));

        setContentPane(scrollPane);
        comptePanel.setLayout(new BoxLayout(comptePanel, BoxLayout.X_AXIS));
        bagPanel.add(comptePanel);

        afficherListe();
    }

    /**
     * Cette méthode est utilisée pour afficher la liste des profils.
     */
    private void afficherListe() {
        comptePanel.removeAll();
        for (Profil profil : stockage.charger(Profil.class)) {
            if (profil.getId() == 1 && !Main.DEVELOPMENT) { // Ignore system profile
                continue;
            }
            afficherProfil(profil.getNom(), this::chargerProfil, this::suppressionProfil);
            comptePanel.add(Box.createHorizontalStrut(ESPACE_ENTRE_PROFILS));
        }
        afficherProfil("\0+\0", this::ajouterProfil);
        comptePanel.revalidate();
        comptePanel.repaint();
    }

    /**
     * Cette méthode est utilisée pour afficher un profil.
     *
     * @param label       le label du profil.
     * @param clickGauche l'action à effectuer lors d'un clic gauche.
     */
    private void afficherProfil(String label, Consumer<MouseEvent> clickGauche) {
        afficherProfil(label, clickGauche, null);
    }

    /**
     * Cette méthode est utilisée pour afficher un profil.
     *
     * @param label       le label du profil.
     * @param clickGauche l'action à effectuer lors d'un clic gauche.
     * @param clickDroit  l'action à effectuer lors d'un clic droit.
     */
    private void afficherProfil(String label, Consumer<MouseEvent> clickGauche, Consumer<MouseEvent> clickDroit) {
        JButton but = new JButton(label.equals("\0+\0") ? " " : label);
        but.setIcon(new ImageIcon(dessineInitiales(label)));
        but.setBackground(theme.getButtonColor());
        but.setForeground(theme.getButtonTextColor());
        but.setAlignmentX(Component.CENTER_ALIGNMENT);
        but.setAlignmentY(Component.CENTER_ALIGNMENT);
        but.setVerticalTextPosition(SwingConstants.BOTTOM);
        but.setHorizontalTextPosition(SwingConstants.CENTER);
        but.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                if (SwingUtilities.isLeftMouseButton(e) && clickGauche != null) {
                    clickGauche.accept(e);
                } else if (SwingUtilities.isRightMouseButton(e) && clickDroit != null) {
                    clickDroit.accept(e);
                }
            }
        });
        appliquerTheme(but);
        comptePanel.add(but);
    }

    /**
     * Cette méthode est utilisée pour dessiner les initiales.
     *
     * @param label le label du profil.
     * @return une image contenant les initiales.
     */
    private BufferedImage dessineInitiales(String label) {
        String tmp = label;
        StringBuilder initials = new StringBuilder(tmp.substring(0, 1));
        while (tmp.contains(" ")) {
            initials.append(tmp.charAt(tmp.indexOf(" ") + 1));
            tmp = tmp.substring(tmp.indexOf(" ") + 1);
        }
        if (label.equals("\0+\0")) {
            initials = new StringBuilder("+");
        }

        int width = 16 * initials.length();
        BufferedImage initialIcon = new BufferedImage(width, 16, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g = initialIcon.createGraphics();
        g.setColor(theme.getButtonTextColor());
        g.setFont(new Font("Arial", Font.BOLD, 16));
        Rectangle2D stringBounds = g.getFontMetrics().getStringBounds(initials.toString(), g);
        g.drawString(initials.toString(), (int) ((width / 2d) - stringBounds.getCenterX()), 16);
        g.dispose();
        return initialIcon;
    }

    /**
     * Cette méthode est utilisée pour supprimer un profil.
     *
     * @param event l'événement de la souris.
     */
    private void suppressionProfil(MouseEvent event) {
        stockage.supprimer(stockage.charger(Profil.class)
                .stream()
                .map(Profil.class::cast)
                .filter(profil -> profil.getNom().equals(((JButton) event.getSource()).getText()))
                .findFirst()
                .orElse(null));
        afficherListe();
    }

    /**
     * Cette méthode est utilisée pour charger un profil.
     *
     * @param event l'événement de la souris.
     */
    private void chargerProfil(MouseEvent event) {
        Profil profil = stockage.charger(Profil.class)
                .stream()
                .map(Profil.class::cast)
                .filter(p -> p.getNom().equals(((JButton) event.getSource()).getText()))
                .findFirst()
                .orElseThrow(IllegalStateException::new);
        menuCharger.computeIfAbsent(profil, p -> new Menu(this, p));
        changerFenetre(menuCharger.get(profil));
    }

    /**
     * Cette méthode est utilisée pour ajouter un profil.
     *
     * @param event l'événement de la souris.
     */
    private void ajouterProfil(MouseEvent event) {
        String nom = null;
        while (nom == null) {
            nom = checkPseudo(JOptionPane.showInputDialog(this, "Entrez le nom du profil", "Nouveau profil", JOptionPane.QUESTION_MESSAGE));
        }
        if (nom.equals("\0")) {
            return;
        }
        stockage.sauvegarder(new ProfilImpl(nom));
        afficherListe();
    }

    /**
     * Cette méthode est utilisée pour vérifier le pseudo.
     *
     * @param nom le nom du profil.
     * @return le nom du profil ou <code>null</code> si le nom est invalide ou <code>\0</code> si le nombre de profils est atteint ou que l'utilisateur a annulé.
     */
    private String checkPseudo(String nom) {
        if (nom == null || (MAX_PROFILS != 0 && stockage.charger(Profil.class).size() >= MAX_PROFILS)) {
            if (nom != null) {
                JOptionPane.showMessageDialog(this, "Le nombre de profils maximum est de " + MAX_PROFILS, "Erreur", JOptionPane.ERROR_MESSAGE);
            }
            return "\0";
        }
        if (StringUtils.estVide(nom)) {
            JOptionPane.showMessageDialog(this, "Le nom ne peut pas être vide", "Erreur", JOptionPane.ERROR_MESSAGE);
            return null;
        }
        if (nom.length() > MAX_CHARACTER_NOM) {
            JOptionPane.showMessageDialog(this, "Le nom ne peut pas dépasser " + MAX_CHARACTER_NOM + " caractères", "Erreur", JOptionPane.ERROR_MESSAGE);
            return null;
        }
        if (stockage.charger(Profil.class).stream().map(Profil::getNom).anyMatch(nom::equals)) {
            JOptionPane.showMessageDialog(this, "Le nom du profil existe déjà", "Erreur", JOptionPane.ERROR_MESSAGE);
            return null;
        }
        return nom;
    }
}
