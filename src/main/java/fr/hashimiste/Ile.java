package fr.hashimiste;

import java.sql.*;
import java.sql.Connection;

/**
 * La classe Ile hérite de la classe Connection du package fr.hashimiste.
 */
public class Ile {

    /**
     * Méthode pour sauvegarder les informations d'une île dans la base de données.
     *
     * @param x        La coordonnée x de l'île.
     * @param y        La coordonnée y de l'île.
     * @param n        La valeur n de l'île.
     * @param nom_map  Le nom de la carte associée à l'île.
     */
    public void save(int x, int y, int n, String nom_map) {
        try {
            // Chargement du driver JDBC SQLite
            Class.forName("org.sqlite.JDBC");

            // Établissement de la connexion à la base de données en utilisant la méthode Constant.CHEMIN_BDD héritée
            Connection connection = DriverManager.getConnection("jdbc:sqlite:"+ Constant.CHEMIN_BDD);

            // Initialisation de l'identifiant de la carte à 0
            int id_map = 0;

            // Requête pour récupérer l'identifiant de la carte à partir de son nom
            String selectMapQuery = "SELECT id_map FROM map WHERE nom="+nom_map;

            // Préparation de la requête SQL
            PreparedStatement selectMapStatement = connection.prepareStatement(selectMapQuery);

            // Exécution de la requête et récupération du résultat
            ResultSet resultSet = selectMapStatement.executeQuery();

            // Si un résultat est retourné
            if (resultSet.next()) {
                // Récupération de l'identifiant de la carte
                id_map = resultSet.getInt("id_map");
            }

            // Requête pour insérer les informations de l'île dans la table "ile"
            String insertQuery = "INSERT INTO ile (id_ile, id_m, x, y, n) VALUES (?, ?, ?, ?, ?)";

            // Préparation de la requête d'insertion
            PreparedStatement preparedStatement = connection.prepareStatement(insertQuery);

            // Définition des valeurs des paramètres de la requête
            preparedStatement.setString(1, "DEFAULT"); // Valeur par défaut pour l'identifiant de l'île
            preparedStatement.setInt(2, id_map); // Identifiant de la carte
            preparedStatement.setInt(3, x); // Coordonnée x de l'île
            preparedStatement.setInt(4, y); // Coordonnée y de l'île
            preparedStatement.setInt(5, n); // Valeur n de l'île
            // Exécution de la requête d'insertion
            preparedStatement.execute();

            // Fermeture des ressources
            preparedStatement.close(); // Fermeture du PreparedStatement
            connection.close(); // Fermeture de la connexion à la base de données
        } catch (SQLException | ClassNotFoundException e) {
            // Gestion des exceptions
            e.printStackTrace(); // Affichage de la trace de la pile en cas d'erreur
        }
    }
}