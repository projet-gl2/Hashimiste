package fr.hashimiste;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Date;

public class Score {
    public String bddHashi = "base.db";
    public void save(int id_p, int id_m, int score) {
        try {
            Class.forName("org.sqlite.JDBC");
            Connection connection = DriverManager.getConnection("jdbc:sqlite:" + bddHashi);

            String insertQuery = "INSERT INTO statistique (id_p, id_m, value) VALUES (?, ?, ?)";
            PreparedStatement connect_score = connection.prepareStatement(insertQuery);
            connect_score.setString(1, "SELECT id_profil FROM profil where " + id_p + ")");
            connect_score.setString(2, "(SELECT id_map FROM map where " + id_m + ")");
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
