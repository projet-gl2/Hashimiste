package fr.hashimiste;

import org.sqlite.JDBC;

import java.sql.*;
import java.sql.Connection;
import java.sql.ResultSet;

public class Score {
    public static int chargerScore(String nomProfil, String nomMap) {
        int score;
        try {
            Class.forName("org.sqlite.JDBC");
            Connection connection = DriverManager.getConnection(JDBC.PREFIX + SQLConstant.DB_FICHIER);

            String selectIdMap = "SELECT id_map FROM map WHERE nom=?";
            PreparedStatement idMapStatement = connection.prepareStatement(selectIdMap);
            idMapStatement.setString(1, nomMap);
            ResultSet resultSet = idMapStatement.executeQuery();
            int idMap = 0;
            idMap = resultSet.getInt("id_map");

            String selectIdProfil = "SELECT id_profil FROM profil WHERE nom=?";
            PreparedStatement idProfilStatement = connection.prepareStatement(selectIdProfil);
            idProfilStatement.setString(1, nomProfil);
            resultSet = idProfilStatement.executeQuery();
            int idProfil = 0;
            idProfil = resultSet.getInt("id_profil");

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
            }
            else {
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
    public void save(int score, String nomMap, String nomProfil) {
        try {
            Class.forName("org.sqlite.JDBC");
            Connection connection = DriverManager.getConnection(JDBC.PREFIX + SQLConstant.DB_FICHIER);

            String selectIdMap = "SELECT id_map FROM map WHERE nom=?";
            PreparedStatement idMapStatement = connection.prepareStatement(selectIdMap);
            idMapStatement.setString(1, nomMap);
            ResultSet resultSet = idMapStatement.executeQuery();
            int idMap = 0;
            if (resultSet.next()) {
                idMap = resultSet.getInt("id_map");
            }

            String selectIdProfil = "SELECT id_profil FROM profil WHERE nom=?";
            PreparedStatement idProfilStatement = connection.prepareStatement(selectIdProfil);
            idProfilStatement.setString(1, nomProfil);
            resultSet = idProfilStatement.executeQuery();
            int idProfil = 0;
            if (resultSet.next()) {
                idProfil = resultSet.getInt("id_profil");
            }

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
