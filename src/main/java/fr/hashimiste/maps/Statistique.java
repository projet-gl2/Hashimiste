package fr.hashimiste.maps;

import org.sqlite.JDBC;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * La classe Statistique permet de sauvegarder des données statistiques dans une base de données SQLite.
 */
public class Statistique {

    /**
     * Enregistre une nouvelle statistique dans la base de données.
     *
     * @param val La valeur de la statistique à sauvegarder.
     * @param name Le nom du profil auquel la statistique est associée.
     */
    public void save(int val, String name) {
        try {
            // Chargement du driver JDBC SQLite
            Class.forName("org.sqlite.JDBC");

            // Établissement de la connexion à la base de données
            Connection connection = DriverManager.getConnection(JDBC.PREFIX + SQLConstant.DB_FICHIER);

            // Préparation de la requête d'insertion
            String insertQuery = "INSERT INTO statistique (id_stat, id_p, value) VALUES (?, ?, ?)";
            PreparedStatement connect_stat = connection.prepareStatement(insertQuery);

            // Paramètres de la requête
            connect_stat.setString(1, "DEFAULT"); // ID de statistique par défaut
            connect_stat.setString(2, "(SELECT id_profil FROM profil where nom=name)"); // ID du profil obtenu par le nom (Note: SQL Injection vulnerability)
            connect_stat.setInt(3, val); // Valeur de la statistique

            // Exécution de la requête
            connect_stat.execute();

            // Fermeture des ressources
            connect_stat.close();
            connection.close();
        } catch (SQLException e) {
            // Gestion des exceptions SQL
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            // Gestion de l'exception de classe non trouvée (Driver JDBC)
            throw new RuntimeException(e);
        }
    }
}
