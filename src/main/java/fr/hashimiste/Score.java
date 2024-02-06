package fr.hashimiste;

import org.sqlite.JDBC;

import java.sql.*;
import java.sql.Connection;
import java.sql.ResultSet;

/**
 * Cette classe gère la gestion des scores pour un profil et une carte donnés.
 */
public class Score {

    /**
     * Récupère le score pour un profil et une carte donnés.
     *
     * @param nomProfil Le nom du profil pour lequel récupérer le score.
     * @param nomMap    Le nom de la carte pour laquelle récupérer le score.
     * @return Le score correspondant au profil et à la carte spécifiés, ou -1 s'il n'existe pas de score pour ces informations.
     * @throws RuntimeException Si une erreur SQL survient ou si la classe JDBC n'est pas trouvée.
     */
    public static int chargerScore(String nomProfil, String nomMap) {
        int score;
        try {
            Class.forName("org.sqlite.JDBC");
            Connection connection = DriverManager.getConnection(JDBC.PREFIX + SQLConstant.DB_FICHIER);

            // Récupérer l'ID de la carte
            String selectIdMap = "SELECT id_map FROM map WHERE nom=?";
            PreparedStatement idMapStatement = connection.prepareStatement(selectIdMap);
            idMapStatement.setString(1, nomMap);
            ResultSet resultSet = idMapStatement.executeQuery();
            int idMap = 0;
            if (resultSet.next()) {
                idMap = resultSet.getInt("id_map");
            }

            // Récupérer l'ID du profil
            String selectIdProfil = "SELECT id_profil FROM profil WHERE nom=?";
            PreparedStatement idProfilStatement = connection.prepareStatement(selectIdProfil);
            idProfilStatement.setString(1, nomProfil);
            resultSet = idProfilStatement.executeQuery();
            int idProfil = 0;
            if (resultSet.next()) {
                idProfil = resultSet.getInt("id_profil");
            }

            // Récupérer le score associé à l'ID de la carte et à l'ID du profil
            String selectScore = "SELECT valeur FROM score WHERE id_m=? and id_p=?";
            PreparedStatement statement = connection.prepareStatement(selectScore);
            statement.setInt(1, idMap);
            statement.setInt(2, idProfil);
            ResultSet result = statement.executeQuery(selectScore);

            idProfilStatement.close();
            idMapStatement.close();

            if (result.next()) {
                score = result.getInt("valeur");
                statement.close();
                connection.close();
                return score;
            } else {
                System.out.println("Aucun score n'a été trouvé avec ces informations : " + nomMap + ", " + nomProfil);
                statement.close();
                connection.close();
                return -1;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Enregistre un nouveau score pour un profil et une carte donnés.
     *
     * @param score     Le score à enregistrer.
     * @param nomMap    Le nom de la carte pour laquelle enregistrer le score.
     * @param nomProfil Le nom du profil pour lequel enregistrer le score.
     * @throws RuntimeException Si une erreur SQL survient ou si la classe JDBC n'est pas trouvée.
     */
    public void save(int score, String nomMap, String nomProfil) {
        try {
            Class.forName("org.sqlite.JDBC");
            Connection connection = DriverManager.getConnection(JDBC.PREFIX + SQLConstant.DB_FICHIER);

            // Récupérer l'ID de la carte
            String selectIdMap = "SELECT id_map FROM map WHERE nom=?";
            PreparedStatement idMapStatement = connection.prepareStatement(selectIdMap);
            idMapStatement.setString(1, nomMap);
            ResultSet resultSet = idMapStatement.executeQuery();
            int idMap = 0;
            if (resultSet.next()) {
                idMap = resultSet.getInt("id_map");
            }

            // Récupérer l'ID du profil
            String selectIdProfil = "SELECT id_profil FROM profil WHERE nom=?";
            PreparedStatement idProfilStatement = connection.prepareStatement(selectIdProfil);
            idProfilStatement.setString(1, nomProfil);
            resultSet = idProfilStatement.executeQuery();
            int idProfil = 0;
            if (resultSet.next()) {
                idProfil = resultSet.getInt("id_profil");
            }

            // Insérer le nouveau score dans la base de données
            String insertQuery = "INSERT INTO statistique (id_p, id_m, value) VALUES (?, ?, ?)";
            PreparedStatement connect_score = connection.prepareStatement(insertQuery);
            connect_score.setInt(1, idProfil);
            connect_score.setInt(2, idMap);
            connect_score.setInt(3, score);
            connect_score.executeUpdate();

            connect_score.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
