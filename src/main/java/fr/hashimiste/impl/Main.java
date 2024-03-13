package fr.hashimiste.impl;

import fr.hashimiste.core.data.Stockage;
import fr.hashimiste.core.gui.JFrameTemplate;
import fr.hashimiste.core.gui.JFrameTemplateProfil;
import fr.hashimiste.core.jeu.Grille;
import fr.hashimiste.core.joueur.Profil;
import fr.hashimiste.impl.data.sql.SQLStockage;
import fr.hashimiste.impl.data.sql.filter.EqFilter;
import fr.hashimiste.impl.gui.dev.DebugFrame;
import fr.hashimiste.impl.gui.jeu.Jeu;
import fr.hashimiste.impl.gui.menu.Menu;
import fr.hashimiste.impl.gui.menu.*;

import java.awt.*;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.Properties;

/**
 * Cette classe est le point d'entrée de l'application.
 */
public class Main {
    public static final boolean DEVELOPMENT;

    static {
        Properties properties = new Properties();
        try {
            File propertiesFile = new File("hashimiste.properties");
            if (propertiesFile.exists()) {
                try (InputStream inStream = propertiesFile.toURI().toURL().openStream()) {
                    properties.load(inStream);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        DEVELOPMENT = getProperty("environment", properties) != null && getProperty("environment", properties).equals("dev");
    }

    /**
     * Le point d'entrée de l'application.
     *
     * @param args les arguments de la ligne de commande.
     * @throws Exception si une erreur se produit lors de l'exécution.
     */
    public static void main(String[] args) throws Exception {
        File propertiesFile = new File("hashimiste.properties");
        Properties properties = new Properties();
        if (propertiesFile.exists()) {
            try (InputStream inStream = propertiesFile.toURI().toURL().openStream()) {
                properties.load(inStream);
            }
        } else {
            properties.setProperty("theme", "default");
            try (FileOutputStream out = new FileOutputStream(propertiesFile)) {
                properties.store(out, "Hashimiste properties");
            }
        }

        Stockage stockage = new SQLStockage("org.sqlite.JDBC", "jdbc:sqlite:hashimiste.db");
        recupererFenetreAsAfficher(propertiesFile, properties, stockage).setVisible(true);
        if (DEVELOPMENT) {
            new DebugFrame(stockage).setVisible(true);
        }
    }

    /**
     * Cette méthode est utilisée pour obtenir le cadre de départ de l'application.
     *
     * @param propertiesFile le fichier de propriétés.
     * @param properties     les propriétés.
     * @param stockage       le stockage des données.
     * @return le cadre de départ.
     */
    private static Frame recupererFenetreAsAfficher(File propertiesFile, Properties properties, Stockage stockage) {
        JFrameTemplate frame = new ProfilSelection(propertiesFile, stockage);
        if (getProperty("profil", properties) == null) {
            return frame;
        }
        Profil profil = stockage.get(Profil.class, new EqFilter("nom", getProperty("profil", properties)));
        if (profil == null) {
            return frame;
        }
        DebugFrame.setFenetreActive(frame);
        frame = new Menu(frame, profil);
        DebugFrame.setFenetreActive(frame);
        String startingFrame = getProperty("frame", properties);
        if (startingFrame == null || startingFrame.equals("Menu")) {
            return frame;
        }
        switch (startingFrame) {
            case "Aventure":
                frame = new Aventure((JFrameTemplateProfil) frame);
                break;
            case "ModeLibre":
                frame = new ModeLibre((JFrameTemplateProfil) frame);
                break;
            case "Parametres":
                frame = new Parametre((JFrameTemplateProfil) frame);
                break;
            case "Technique":
                frame = new Technique((JFrameTemplateProfil) frame);
                break;
            case "Jeu":
                String grilleIdStr = getProperty("grille", properties);
                int grilleId = -1;
                try {
                    grilleId = Integer.parseInt(grilleIdStr);
                } catch (NumberFormatException e) {
                    frame = new ModeLibre((JFrameTemplateProfil) frame);
                }
                Grille grille = stockage.get(Grille.class, new EqFilter("id_map", grilleId));
                if (grille != null) {
                    frame = new Jeu((JFrameTemplateProfil) frame, grille);
                } else {
                    frame = new ModeLibre((JFrameTemplateProfil) frame);
                }
                break;
            default:
                break;
        }
        return frame;
    }

    /**
     * Cette méthode est utilisée pour obtenir une propriété.
     *
     * @param key        la clé de la propriété.
     * @param properties les propriétés.
     * @return la valeur de la propriété.
     */
    private static String getProperty(String key, Properties properties) {
        return System.getProperty(key) != null ? System.getProperty(key) : properties.getProperty(key);
    }
}