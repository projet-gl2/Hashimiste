package fr.hashimiste;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * La classe Save hérite de la classe Connection du package fr.hashimiste.
 */
public class Save {

    /**
     * Méthode pour sauvegarder des données avec un nom et un nom de profil donnés.
     *
     * @param nom Le nom des données à sauvegarder.
     * @param nom_profil Le nom du profil associé aux données.
     */
    public void save(String nom, String nom_profil) {
        try {
            // Chargement du pilote JDBC SQLite
            Class.forName("org.sqlite.JDBC");
            // Établissement de la connexion à la base de données en utilisant le chemin récupéré à partir de la classe parente
            java.sql.Connection connection = DriverManager.getConnection("jdbc:sqlite:" + Constant.CHEMIN_BDD);

            int id_profil = 0;
            // Requête pour récupérer l'identifiant du profil à partir du nom du profil donné
            String selectProfilQuery = "SELECT id_profil FROM profil WHERE nom=?";
            PreparedStatement selectProfilStatement = connection.prepareStatement(selectProfilQuery);
            selectProfilStatement.setString(1, nom_profil); // Attribution de la valeur au paramètre de la requête
            ResultSet resultSet = selectProfilStatement.executeQuery(); // Exécution de la requête
            if (resultSet.next()) {
                id_profil = resultSet.getInt("id_profil"); // Récupération de l'identifiant du profil
            }

            // Requête pour insérer les données dans la table Save
            String insertQuery = "INSERT INTO Save (id_save, id_p, nom) VALUES (?, ?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(insertQuery);
            preparedStatement.setInt(2, id_profil); // Attribution de l'identifiant de profil récupéré
            preparedStatement.setString(3, nom); // Attribution du nom donné
            preparedStatement.execute(); // Exécution de la requête d'insertion

            // Fermeture des ressources
            preparedStatement.close();
            connection.close();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace(); // Affichage des erreurs rencontrées pendant l'exécution
        }
    }
}