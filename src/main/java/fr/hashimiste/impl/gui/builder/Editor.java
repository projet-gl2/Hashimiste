package fr.hashimiste.impl.gui.builder;

import fr.hashimiste.core.data.Stockage;
import fr.hashimiste.core.gui.JFrameTemplate;
import fr.hashimiste.core.jeu.Case;
import fr.hashimiste.core.jeu.Difficulte;
import fr.hashimiste.core.jeu.Grille;
import fr.hashimiste.core.jeu.Ile;
import fr.hashimiste.impl.data.sql.SQLStockage;
import fr.hashimiste.impl.jeu.CaseVideImpl;
import fr.hashimiste.impl.jeu.IleImpl;

import javax.swing.*;
import javax.swing.filechooser.FileFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.File;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

/**
 * Cette classe représente l'éditeur de grille du jeu.
 * Elle hérite de JFrameTemplate et permet de créer, charger et sauvegarder des grilles de jeu.
 */
public class Editor extends JFrameTemplate {

    private final JPanel optionsPanel = new JPanel();
    private final JPanel gridPanel = new JPanel();

    private final JLabel widthLabel = new JLabel("Largeur");
    private final JTextField widthField = new JTextField();
    private final JLabel heightLabel = new JLabel("Hauteur");
    private final JTextField heightField = new JTextField();
    private final JComboBox<String> difficulty = new JComboBox<>(Arrays.stream(Difficulte.values())
            .map(Difficulte::name)
            .map(String::toLowerCase)
            .map(s -> s.substring(0, 1).toUpperCase() + s.substring(1))
            .toArray(String[]::new)
    );
    private final JButton butCharger = new JButton("Charger");
    private final JButton butSauvegarder = new JButton("Sauvegarder");

    private transient GrilleBuilder grille = new GrilleBuilder();

    /**
     * Constructeur de la classe Editor.
     *
     * @param propertiesFile le fichier de propriétés.
     * @param stockage       l'instance de Stockage utilisée pour charger et sauvegarder les grilles.
     * @param size           la taille de la fenêtre de l'éditeur.
     */
    public Editor(File propertiesFile, Stockage stockage, Dimension size) {
        super(null, propertiesFile, stockage, size);

        widthLabel.setLabelFor(widthField);
        widthField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                super.keyTyped(e);
                if (e.getKeyChar() == KeyEvent.VK_ENTER) {
                    grille.setDimension(new Dimension(Integer.parseInt(widthField.getText()), (int) grille.getDimension().getHeight()));
                    updateLayout();
                }
                if (!Character.isDigit(e.getKeyChar())) {
                    e.consume();
                }
            }
        });
        widthField.setText(((int) grille.getDimension().getWidth()) + "");

        heightLabel.setLabelFor(heightField);
        heightField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                super.keyTyped(e);
                if (e.getKeyChar() == KeyEvent.VK_ENTER) {
                    grille.setDimension(new Dimension((int) grille.getDimension().getWidth(), Integer.parseInt(heightField.getText())));
                    updateLayout();
                }
                if (!Character.isDigit(e.getKeyChar())) {
                    e.consume();
                }
            }
        });
        heightField.setText(((int) grille.getDimension().getHeight()) + "");

        difficulty.addActionListener(e -> grille.setDifficulte(Difficulte.valueOf(difficulty.getSelectedItem().toString().toUpperCase())));

        butCharger.addActionListener(this::charger);
        butSauvegarder.addActionListener(this::sauvegarder);

        optionsPanel.setLayout(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.weightx = 0.5;
        constraints.weighty = 0.1;

        optionsPanel.add(widthLabel, constraints);
        constraints.gridx = 1;
        optionsPanel.add(widthField, constraints);

        constraints.gridy = 1;
        constraints.gridx = 0;
        optionsPanel.add(heightLabel, constraints);
        constraints.gridx = 1;
        optionsPanel.add(heightField, constraints);

        constraints.gridy = 2;
        constraints.gridx = 0;
        optionsPanel.add(difficulty, constraints);

        constraints.gridy = 3;
        constraints.gridx = 0;
        optionsPanel.add(butCharger, constraints);
        constraints.gridx = 1;
        optionsPanel.add(butSauvegarder, constraints);

        optionsPanel.setBorder(BorderFactory.createTitledBorder("Options"));

        this.setLayout(new BorderLayout());
        this.add(optionsPanel, BorderLayout.WEST);
        this.add(gridPanel, BorderLayout.CENTER);

        updateLayout();
    }

    /**
     * Point d'entrée principal de l'application.
     *
     * @param args les arguments de la ligne de commande.
     */
    public static void main(String[] args) {
        JFrame frame = new Editor(new File("hashimiste.properties"), null, null);
        frame.setVisible(true);
    }

    /**
     * Cette méthode est utilisée pour mettre à jour l'affichage de l'éditeur.
     */
    private void updateLayout() {
        gridPanel.setLayout(new GridLayout((int) grille.getDimension().getHeight(), (int) grille.getDimension().getWidth()));
        gridPanel.removeAll();
        for (int x = 0; x < grille.getDimension().getHeight(); x++) {
            for (int y = 0; y < grille.getDimension().getWidth(); y++) {
                Case ile = grille.getIle(x, y);
                if (!(ile instanceof CaseVideImpl)) {
                    gridPanel.add(new Cell((Ile)ile, grille));
                } else {
                    gridPanel.add(new Cell(x, y, grille));
                }
            }
        }
        gridPanel.revalidate();
        gridPanel.repaint();
        this.pack();
    }

    /**
     * Cette méthode est utilisée pour charger une grille à partir du stockage.
     *
     * @param e l'événement qui a déclenché cette action.
     */
    private void charger(ActionEvent e) {
        Stockage stockage = getStockage();
        if (stockage == null) {
            return;
        }
        List<Grille> grilles = stockage.charger(Grille.class);
        if (!grilles.isEmpty()) {
            String id = (String) JOptionPane.showInputDialog(this, "Choisissez une grille à charger",
                    "Charger une grille",
                    JOptionPane.QUESTION_MESSAGE,
                    null,
                    Arrays.stream(grilles.toArray())
                            .map(Grille.class::cast)
                            .map(Grille::getId)
                            .map(Object::toString)
                            .toArray(), grilles.get(0).getId());
            grille = new GrilleBuilder(grilles.stream()
                    .filter(g -> Integer.toString(g.getId()).equals(id))
                    .findFirst()
                    .orElseThrow(IllegalStateException::new));
        }
        updateLayout();
    }

    /**
     * Cette méthode est utilisée pour sauvegarder la grille actuelle dans le stockage.
     *
     * @param event l'événement qui a déclenché cette action.
     */
    private void sauvegarder(ActionEvent event) {
        Stockage stockage = getStockage();
        if (stockage == null) {
            return;
        }
        for (Component component : gridPanel.getComponents()) {
            if (component instanceof Cell && ((Cell) component).getN() != 0) {
                grille.ajouterIle(((Cell) component).conversionIle());
            }
        }
        System.out.println("grille = " + grille);
        stockage.sauvegarder(grille);
        grille.clear();
    }

    /**
     * Cette méthode est utilisée pour obtenir une instance de Stockage à partir d'un fichier sélectionné par l'utilisateur.
     *
     * @return une instance de Stockage, ou null si l'utilisateur n'a pas sélectionné de fichier.
     */
    private Stockage getStockage() {
        JFileChooser fileChooser = new JFileChooser(".");
        fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        fileChooser.setFileFilter(new FileFilter() {
            @Override
            public boolean accept(File f) {
                return f.getName().endsWith(".db");
            }

            @Override
            public String getDescription() {
                return "Base de données";
            }
        });
        int result = fileChooser.showOpenDialog(this);
        if (result == JFileChooser.APPROVE_OPTION) {
            File file = fileChooser.getSelectedFile();
            try {
                return new SQLStockage("org.sqlite.JDBC", "jdbc:sqlite:" + file.getAbsolutePath());
            } catch (ClassNotFoundException | NoSuchFieldException | IllegalAccessException | SQLException ex) {
                throw new RuntimeException(ex);
            }
        }
        return null;
    }
}
