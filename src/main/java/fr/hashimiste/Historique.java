package fr.hashimiste;

import java.sql.*;
import java.sql.Connection;
import java.util.Date;
import java.sql.ResultSet;

/**
 * Cette classe représente un historique d'actions avec une base de données.
 * Elle étend la classe de connexion pour accéder à la base de données.
 */
public class Historique {

    /**
     * Méthode pour enregistrer un historique.
     *
     * @param etat     L'état de l'historique à enregistrer.
     * @param x_ile1   La coordonnée x de l'île de départ.
     * @param y_ile1   La coordonnée y de l'île de départ.
     * @param x_ile2   La coordonnée x de l'île de destination.
     * @param y_ile2   La coordonnée y de l'île de destination.
     * @param nomMap   Le nom de la carte associée à l'historique.
     * @param nomProfil Le nom du profil associé à l'historique.
     */
    public void save(int etat, int x_ile1, int y_ile1, int x_ile2, int y_ile2, String nomMap, String nomProfil) {
        try {
            // Chargement du driver JDBC SQLite
            Class.forName("org.sqlite.JDBC");

            // Établissement de la connexion à la base de données
            Connection connection = DriverManager.getConnection("jdbc:sqlite:" + Constant.CHEMIN_BDD);

            // Requête pour récupérer l'identifiant de l'île de départ
            String selectIdDep = "SELECT id_ile FROM ile JOIN map ON ile.id_m = map.id_map WHERE x=? and y=? and nom=?";
            PreparedStatement idDepStatement = connection.prepareStatement(selectIdDep);
            idDepStatement.setInt(1, x_ile1);
            idDepStatement.setInt(2, y_ile1);
            idDepStatement.setString(3, nomMap);
            ResultSet resultSet = idDepStatement.executeQuery();
            int idIleDep = 0;
            if (resultSet.next()) {
                idIleDep = resultSet.getInt("id_ile");
            }

            // Requête pour récupérer l'identifiant de l'île de destination
            String selectIdDest = "SELECT id_ile FROM ile JOIN map ON ile.id_m = map.id_map WHERE x=? and y=? and nom=?";
            PreparedStatement idDestStatement = connection.prepareStatement(selectIdDest);
            idDestStatement.setInt(1, x_ile2);
            idDestStatement.setInt(2, y_ile2);
            idDestStatement.setString(3, nomMap);
            resultSet = idDestStatement.executeQuery();
            int idIleDest = 0;
            if (resultSet.next()) {
                idIleDest = resultSet.getInt("id_ile");
            }

            // Requête pour récupérer l'identifiant de la carte
            String selectIdMap = "SELECT id_map FROM map WHERE nom=?";
            PreparedStatement idMapStatement = connection.prepareStatement(selectIdMap);
            idMapStatement.setString(1, nomMap);
            resultSet = idMapStatement.executeQuery();
            int idMap = 0;
            if (resultSet.next()) {
                idMap = resultSet.getInt("id_map");
            }

            // Requête pour récupérer l'identifiant du profil
            String selectIdProfil = "SELECT id_profil FROM profil WHERE nom=?";
            PreparedStatement idProfilStatement = connection.prepareStatement(selectIdProfil);
            idProfilStatement.setString(1, nomProfil);
            resultSet = idProfilStatement.executeQuery();
            int idProfil = 0;
            if (resultSet.next()) {
                idProfil = resultSet.getInt("id_profil");
            }

            // Requête pour insérer les données dans la table historique
            String insertQuery = "INSERT INTO historique (date_h, id_m, id_p, id_dep, id_dest, etat) VALUES (?, ?, ?, ?, ?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(insertQuery);
            preparedStatement.setDate(1, (java.sql.Date) new Date());
            preparedStatement.setInt(2, idMap);
            preparedStatement.setInt(3, idProfil);
            preparedStatement.setInt(4, idIleDep);
            preparedStatement.setInt(5, idIleDest);
            preparedStatement.setInt(6, etat);
            preparedStatement.execute();

            // Fermeture des déclarations et de la connexion
            idProfilStatement.close();
            idMapStatement.close();
            idDepStatement.close();
            idDestStatement.close();
            preparedStatement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            // Lancement d'une exception en cas de non chargement du driver JDBC
            throw new RuntimeException(e);
        }
    }
}
