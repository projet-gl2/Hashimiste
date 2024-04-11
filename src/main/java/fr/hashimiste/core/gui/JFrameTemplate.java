package fr.hashimiste.core.gui;

import fr.hashimiste.core.data.Stockage;
import fr.hashimiste.core.image.AppImage;
import fr.hashimiste.impl.gui.dev.DebugFrame;
import fr.hashimiste.impl.gui.theme.DebugTheme;
import fr.hashimiste.impl.gui.theme.DefaultTheme;
import fr.hashimiste.impl.gui.theme.CandyTheme;
import fr.hashimiste.impl.gui.theme.DarkTheme;


import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
 * La classe JFrameTemplate est une classe abstraite qui définit le comportement de base d'une fenêtre dans l'application.
 * Elle gère le thème, les propriétés et le stockage des données.
 */
public abstract class JFrameTemplate extends JFrame {
    protected final transient Stockage stockage;
    protected final JFrame fenetreParente;
    protected final File fichierProperties;
    private final List<JComponent> composantStylise = new ArrayList<>();
    protected transient Theme theme;
    protected Properties properties;

    /**
     * Constructeur de JFrameTemplate.
     *
     * @param fenetreParente la fenêtre parente.
     */
    protected JFrameTemplate(JFrameTemplate fenetreParente) {
        this(fenetreParente, fenetreParente.fichierProperties, fenetreParente.stockage, fenetreParente.getSize());
    }

    /**
     * Constructeur de JFrameTemplate.
     *
     * @param fenetreParente la fenêtre parente.
     * @param dimension   la taille de la fenêtre.
     */
    protected JFrameTemplate(JFrameTemplate fenetreParente, Dimension dimension) {
        this(fenetreParente, fenetreParente.fichierProperties, fenetreParente.stockage, dimension);
    }

    /**
     * Constructeur de JFrameTemplate.
     *
     * @param fenetreParente    la fenêtre parente.
     * @param fichierProperties le fichier de propriétés.
     * @param stockage       le système de stockage des données.
     * @param size           la taille de la fenêtre.
     */
    protected JFrameTemplate(JFrame fenetreParente, File fichierProperties, Stockage stockage, Dimension size) {
        this.fenetreParente = fenetreParente;
        this.fichierProperties = fichierProperties;
        rechargerProperties();
        this.stockage = stockage;
        rechargerTheme();

        this.setTitle("Hashimiste");
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setIconImage(AppImage.INSTANCE.getIcon(true));
        if (size != null) {
            this.setSize(size);
            this.setLocationRelativeTo(fenetreParente);
        } else if (fenetreParente != null) {
            this.setSize(fenetreParente.getSize());
            this.setLocationRelativeTo(fenetreParente);
        }
        if (fenetreParente != null) {
            this.setExtendedState(fenetreParente.getExtendedState());
        }
        this.setBackground(theme.getBackgroundColor());
    }

    /**
     * Change la fenêtre actuelle pour une autre.
     *
     * @param window la nouvelle fenêtre.
     */
    public void changerFenetre(JFrame window) {
        window.setLocationRelativeTo(this);
        window.setVisible(true);
        this.dispose();
    }

    /**
     * Crée un bouton qui, lorsqu'il est cliqué, change la fenêtre actuelle pour une autre.
     *
     * @param texte  le texte du bouton.
     * @param fenetre la nouvelle fenêtre.
     * @return le bouton créé.
     */
    protected JButton creerBoutton(String texte, JFrame fenetre) {
        return creerBoutton(texte, fenetre == null ? null : () -> changerFenetre(fenetre));
    }

    /**
     * Crée un bouton avec une action lorsqu'il est cliqué.
     *
     * @param texte   le texte du bouton.
     * @param action l'action à exécuter lors du clic.
     * @return le bouton créé.
     */
    protected JButton creerBoutton(String texte, Runnable action) {
        return creerBoutton(texte, action, action);
    }

    /**
     * Crée un bouton avec une action différente selon le bouton de la souris utilisé pour le clic.
     *
     * @param texte       le texte du bouton.
     * @param cliqueDroit l'action à exécuter lors d'un clic droit.
     * @param cliqueGauche  l'action à exécuter lors d'un clic gauche.
     * @return le bouton créé.
     */
    protected JButton creerBoutton(String texte, Runnable cliqueDroit, Runnable cliqueGauche) {
        JButton button = new JButton(texte);
        button.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (!e.getComponent().isEnabled()) {
                    return;
                }
                if (SwingUtilities.isLeftMouseButton(e)) {
                    cliqueGauche.run();
                } else if (SwingUtilities.isRightMouseButton(e)) {
                    cliqueDroit.run();
                }
            }
        });
        button.setEnabled(cliqueDroit != null || cliqueGauche != null);
        appliquerTheme(button);
        return button;
    }

    /**
     * Applique le thème actuel à un ou plusieurs composants.
     *
     * @param composants les composants à styliser.
     */
    protected void appliquerTheme(JComponent... composants) {
        for (JComponent component : composants) {
            composantStylise.add(component);
            component.setBackground(theme.getBackgroundColor());
            if (component instanceof JButton) {
                JButton button = (JButton) component;
                if (button.isEnabled()) {
                    button.setBackground(theme.getButtonColor());
                } else {
                    button.setBackground(theme.getDisabledButtonColor());
                }
                button.setForeground(theme.getButtonTextColor());
            } else if (component instanceof JLabel ) {
                JLabel label = (JLabel) component;
                label.setForeground(theme.getTextColor());
            } else if (component instanceof  JTextArea)
            {
                JTextArea textArea = (JTextArea) component;
                textArea.setForeground(theme.getTextColor());
            }
        }
    }

    /**
     * Recharge les propriétés à partir du fichier de propriétés.
     */
    protected void rechargerProperties() {
        properties = new Properties();
        try (InputStream inStream = fichierProperties.toURI().toURL().openStream()) {
            properties.load(inStream);
        } catch (Exception e) {
            System.exit(1);
        }
    }

    /**
     * Recharge le thème à partir des propriétés.
     */
    protected void rechargerTheme() {
        this.theme = DefaultTheme.INSTANCE;
        if (properties.getProperty("theme").equals("debug")) {
            this.theme = DebugTheme.INSTANCE;
        }else if(properties.getProperty("theme").equals("candy")){
            this.theme = CandyTheme.INSTANCE;
        }else if(properties.getProperty("theme").equals("dark")){
            this.theme = DarkTheme.INSTANCE;
        }
        appliquerTheme(composantStylise.toArray(new JComponent[0]));
    }

    /**
     * Affiche ou cache cette fenêtre selon la valeur du paramètre b.
     *
     * @param b si vrai, la fenêtre est rendue visible, sinon elle est cachée.
     */
    @Override
    public void setVisible(boolean b) {
        if (b) {
            rechargerProperties();
            rechargerTheme();
            DebugFrame.setFenetreActive(this);
        }
        super.setVisible(b);
    }

    /**
     * Active ou désactive un ou plusieurs composants.
     *
     * @param active     si vrai, les composants sont activés, sinon ils sont désactivés.
     * @param components les composants à activer ou désactiver.
     */
    protected void setActive(boolean active, JComponent... components) {
        for (JComponent component : components) {
            component.setEnabled(active);
            if (component instanceof JButton) {
                JButton button = (JButton) component;
                button.setBackground(active ? theme.getButtonColor() : theme.getDisabledButtonColor());
            }
        }
    }

    /**
     * Retourne une représentation sous forme de chaîne de cette fenêtre.
     *
     * @return une représentation sous forme de chaîne de cette fenêtre.
     */
    @Override
    public String toString() {
        return getClass().getSimpleName() + "{" +
                "parent=" + (fenetreParente == null ? "null" : fenetreParente.getClass().getSimpleName()) +
                ", stockage=" + stockage +
                ", properties=" + properties +
                '}';
    }

    /**
     * Retourne les propriétés de cette fenêtre.
     *
     * @return les propriétés de cette fenêtre.
     */
    public Properties getProperties() {
        return properties;
    }
}