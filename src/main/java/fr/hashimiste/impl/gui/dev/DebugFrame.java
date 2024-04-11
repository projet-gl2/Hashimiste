package fr.hashimiste.impl.gui.dev;

import fr.hashimiste.core.data.Decoder;
import fr.hashimiste.core.data.Stockage;
import fr.hashimiste.core.dev.Debuggable;
import fr.hashimiste.core.gui.JFrameTemplate;
import fr.hashimiste.core.gui.JFrameTemplateProfil;
import fr.hashimiste.core.utils.swing.ListRenderer;
import fr.hashimiste.impl.data.sql.SQLStockage;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.*;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

/**
 * Cette classe représente une fenêtre de débogage.
 * Elle hérite de JFrame et affiche des informations de débogage sur l'application.
 */
public class DebugFrame extends JFrame {

    private static final Vector<JFrameTemplate> fenetreCharger = new Vector<>();
    private static JFrameTemplate fenetreActive;
    private static DebugFrame instance;
    private final transient Stockage stockage;

    private final JTabbedPane panels = new JTabbedPane();
    private final JPanel panRuntime = new JPanel();
    private final JPanel panData = new JPanel();

    private final JComboBox<JFrameTemplate> allerAFenetre = new JComboBox<>(fenetreCharger);
    private final JButton afficherBoite = new JButton("Afficher les boites");
    private final JLabel labFenetresActiv = new JLabel("Fenêtre active: ");
    private final JTextArea valFenetreActive = new JTextArea();
    private final JLabel labProperties = new JLabel("Propriété: ");
    private final JTextArea properties = new JTextArea();
    private final JLabel labStockage = new JLabel("Stockage: ");
    private final JTextArea stockageArea = new JTextArea();
    private final JLabel labProfile = new JLabel("Profile: ");
    private final JTextArea profile = new JTextArea();
    private final JButton rafraichirInfo = new JButton("Rafraichir");
    private final JLabel labInfo = new JLabel("Info additionnel: ");
    private final JTextArea info = new JTextArea();

    private final JList<Map.Entry<Class<?>, Decoder<ResultSet, ?>>> tables = new JList();
    private final JTable table = new JTable();
    private final Map<JComponent, Color> ancienneCouleurs = new HashMap<>();

    /**
     * Constructeur de la classe DebugFrame.
     *
     * @param stockage l'instance de Stockage utilisée pour charger et sauvegarder les données.
     */
    public DebugFrame(Stockage stockage) {
        super("Debug");
        if (instance != null) {
            instance.dispose();
        } else {
            instance = this;
        }
        this.stockage = stockage;
        setSize(400, 300);
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setAlwaysOnTop(true);

        panels.addTab("Runtime", panRuntime);
        configurerRuntimePanel();
        Executors.newSingleThreadScheduledExecutor().scheduleAtFixedRate(() -> {
            if (fenetreActive instanceof Debuggable) {
                info.setText(((Debuggable) fenetreActive).getDebugInfo());
            } else {
                info.setText("");
            }
        }, 0, 10, TimeUnit.MILLISECONDS);

        panels.addTab("Data", panData);
        setupDataPanel();
        add(panels);
    }

    /**
     * Cette méthode est utilisée pour définir la fenêtre active.
     *
     * @param frame la nouvelle fenêtre active.
     */
    public static void setFenetreActive(JFrameTemplate frame) {
        JFrameTemplate old = fenetreActive;
        fenetreActive = frame;
        if (!fenetreCharger.contains(frame)) {
            fenetreCharger.add(frame);
        }
        if (instance != null) {
            instance.frameChanged();
            if (old == null) {
                instance.configurerRuntimePanel();
            }
        }
    }

    /**
     * Cette méthode est utilisée pour configurer le panneau Runtime.
     */
    private void configurerRuntimePanel() {
        panels.setEnabledAt(0, valFenetreActive != null);
        if (fenetreActive == null) {
            return;
        }
        panRuntime.setLayout(new GridBagLayout());
        GridBagConstraints contrainte = new GridBagConstraints();
        contrainte.fill = GridBagConstraints.HORIZONTAL;
        contrainte.weightx = 1;
        contrainte.gridx = 0;
        contrainte.gridy = 0;
        allerAFenetre.setRenderer(new ListRenderer(JFrameTemplate.class, frame -> frame == null ? "Void" : frame.getClass().getSimpleName()));
        allerAFenetre.addActionListener(e -> fenetreActive.changerFenetre((JFrameTemplate) allerAFenetre.getSelectedItem()));
        panRuntime.add(allerAFenetre, contrainte);

        afficherBoite.addActionListener(e -> {
            for (Component composant : fenetreActive.getComponents()) {
                if (composant instanceof JComponent) {
                    afficherBoites((JComponent) composant, e.getActionCommand().equals("show"));
                }
            }
            if (e.getActionCommand().equals("show")) {
                afficherBoite.setText("Cacher");
                afficherBoite.setActionCommand("hide");
            } else {
                afficherBoite.setText("Afficher");
                afficherBoite.setActionCommand("show");
            }
        });
        afficherBoite.setActionCommand("show");
        contrainte.gridx = 1;
        panRuntime.add(afficherBoite, contrainte);

        labFenetresActiv.setLabelFor(valFenetreActive);
        contrainte.weightx = 0.5;
        contrainte.gridy++;
        contrainte.gridx = 1;
        panRuntime.add(labFenetresActiv, contrainte);
        valFenetreActive.setEditable(false);
        valFenetreActive.setLineWrap(true);
        valFenetreActive.setWrapStyleWord(true);
        contrainte.gridx = 2;
        panRuntime.add(valFenetreActive, contrainte);

        labStockage.setLabelFor(stockageArea);
        contrainte.gridy++;
        contrainte.gridx = 1;
        panRuntime.add(labStockage, contrainte);
        stockageArea.setEditable(false);
        stockageArea.setLineWrap(true);
        stockageArea.setWrapStyleWord(true);
        contrainte.gridx = 2;
        panRuntime.add(stockageArea, contrainte);

        labProperties.setLabelFor(properties);
        contrainte.gridy++;
        contrainte.gridx = 1;
        panRuntime.add(labProperties, contrainte);
        properties.setEditable(false);
        properties.setLineWrap(true);
        properties.setWrapStyleWord(true);
        contrainte.gridx = 2;
        panRuntime.add(properties, contrainte);

        labProfile.setLabelFor(profile);
        contrainte.gridy++;
        contrainte.gridx = 1;
        panRuntime.add(labProfile, contrainte);
        profile.setEditable(false);
        profile.setLineWrap(true);
        profile.setWrapStyleWord(true);
        contrainte.gridx = 2;
        panRuntime.add(profile, contrainte);

        labInfo.setLabelFor(info);
        contrainte.gridy++;
        contrainte.gridx = 0;
        rafraichirInfo.addActionListener(e -> {
            if (fenetreActive instanceof Debuggable) {
                info.setText(((Debuggable) fenetreActive).getDebugInfo());
            } else {
                info.setText("");
            }
        });
        panRuntime.add(rafraichirInfo, contrainte);
        contrainte.gridx = 1;
        panRuntime.add(labInfo, contrainte);
        info.setEditable(false);
        info.setLineWrap(true);
        info.setWrapStyleWord(true);
        contrainte.gridx = 2;
        panRuntime.add(info, contrainte);
    }

    /**
     * Cette méthode est utilisée pour configurer le panneau Data.
     */
    private void setupDataPanel() {
        Map<Class<?>, Decoder<ResultSet, ?>> decoders = ((SQLStockage) stockage).getDecoders();
        tables.setListData(decoders.entrySet().toArray(new Map.Entry[0]));
        tables.setCellRenderer(new ListRenderer(Map.Entry.class, c -> ((Class<?>) c.getKey()).getSimpleName()));
        tables.addListSelectionListener(this::sqlSelection);
        panData.setLayout(new BorderLayout());
        panData.add(new JScrollPane(tables), BorderLayout.WEST);
        panData.add(new JScrollPane(table), BorderLayout.CENTER);
    }

    /**
     * Cette méthode est appelée lorsque l'utilisateur sélectionne une table SQL dans la liste.
     *
     * @param e l'événement de sélection de la liste.
     */
    private void sqlSelection(ListSelectionEvent e) {
        if (!e.getValueIsAdjusting()) {
            String[][] data;
            String[] colonnes;
            Map.Entry<Class<?>, Decoder<ResultSet, ?>> selection = tables.getSelectedValue();
            ResultSet rs = ((SQLStockage) stockage).executeQuery("SELECT * FROM " + selection.getValue().getNomContaineur());
            try {
                colonnes = new String[rs.getMetaData().getColumnCount()];
                for (int i = 0; i < colonnes.length; i++) {
                    colonnes[i] = rs.getMetaData().getColumnName(i + 1);
                }
                List<String[]> lignes = new ArrayList<>();
                int i = 0;
                while (rs.next()) {
                    lignes.add(new String[colonnes.length]);
                    for (int j = 0; j < colonnes.length; j++) {
                        lignes.get(i)[j] = rs.getString(j + 1);
                    }
                    i++;
                }
                data = lignes.toArray(new String[0][0]);
            } catch (SQLException throwables) {
                throwables.printStackTrace();
                data = new String[0][0];
                colonnes = new String[0];
            }

            table.setModel(new DefaultTableModel(data, colonnes));
        }
    }

    /**
     * Cette méthode est appelée lorsque la fenêtre active change.
     */
    private void frameChanged() {
        allerAFenetre.setSelectedItem(valFenetreActive);
        valFenetreActive.setText(valFenetreActive.getClass().getName());
        properties.setText(fenetreActive.getProperties().toString());
        stockageArea.setText(stockage.toString());
        if (fenetreActive instanceof JFrameTemplateProfil) {
            JFrameTemplateProfil profilFrame = (JFrameTemplateProfil) fenetreActive;
            profile.setText(String.format("Profil{id=%d, nom='%s'}", profilFrame.getProfil().getId(), profilFrame.getProfil().getNom()));
        } else {
            profile.setText("");
        }
        if (valFenetreActive instanceof Debuggable) {
            info.setText(((Debuggable) valFenetreActive).getDebugInfo());
        } else {
            info.setText("");
        }
    }

    /**
     * Cette méthode est utilisée pour afficher ou cacher les boîtes de composants.
     *
     * @param component le composant à afficher ou cacher.
     * @param afficher      vrai pour afficher les boîtes, faux pour les cacher.
     */
    private void afficherBoites(JComponent component, boolean afficher) {
        if (afficher) {
            ancienneCouleurs.put(component, component.getBackground());
        }
        component.setBackground(afficher ? new Color(ThreadLocalRandom.current().nextInt(0x1000000)) : ancienneCouleurs.get(component));
        if (!afficher) {
            ancienneCouleurs.remove(component);
        }
        for (Component child : component.getComponents()) {
            if (child instanceof JComponent) {
                afficherBoites((JComponent) child, afficher);
            }
        }
    }

}