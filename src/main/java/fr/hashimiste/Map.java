package fr.hashimiste;

import org.sqlite.JDBC;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * Classe représentant une carte du jeu, comprenant une liste d'îles et une liste de ponts.
 */
public class Map {
    /** Liste des îles présentes sur la carte. */
    ArrayList<Ile> iles;
    /** Liste des ponts présents sur la carte. */
    ArrayList<Pont> ponts;

    /**
     * Constructeur de la classe Map.
     *
     * @param listIles Liste des îles à ajouter à la carte.
     * @param listPonts Liste des ponts à ajouter à la carte.
     */
    Map(ArrayList<Ile> listIles, ArrayList<Pont> listPonts) {
        this.iles = listIles;
        this.ponts = listPonts;
    }

    /**
     * Méthode statique pour charger une carte à partir de sa base de données.
     *
     * @param idMap Identifiant de la carte à charger.
     * @return La carte chargée.
     */
    public static Map chargerMap(int idMap) {
        return new Map(Ile.chargerIle(idMap), Pont.chargerPont(idMap));
    }

    /**
     * Méthode pour sauvegarder la carte dans la base de données.
     *
     * @param nom Nom de la carte à sauvegarder.
     * @param diff Difficulté de la carte à sauvegarder.
     */
    public void save(String nom, int diff) {
        // Sauvegarde des îles avec le nom spécifié
        iles.forEach(ile -> ile.save(nom));
        // Sauvegarde des ponts
        ponts.forEach(Pont::save);
        try {
            // Connexion à la base de données SQLite
            Class.forName("org.sqlite.JDBC");
            Connection connection = DriverManager.getConnection(JDBC.PREFIX + SQLConstant.DB_FICHIER);

            // Requête d'insertion pour ajouter la carte à la table 'map'
            String insertQuery = "INSERT INTO map (nom, difficulte) VALUES (?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(insertQuery);
            preparedStatement.setString(1, nom);
            preparedStatement.setInt(2, diff);
            preparedStatement.execute();

            // Fermeture des ressources
            preparedStatement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            // Lancement d'une exception en cas de classe non trouvée
            throw new RuntimeException(e);
        }
    }
}