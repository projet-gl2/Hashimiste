package fr.hashimiste;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Statistique {
     public String bddHashi = "base.db";
    public void save(int val) {
        try {
            Class.forName("org.sqlite.JDBC");
            Connection connection = DriverManager.getConnection("jdbc:sqlite:"+bddHashi);

            String insertQuery = "INSERT INTO statistique (id_stat, id_p, value) VALUES (?, ?, ?)";
            PreparedStatement connect_stat = connection.prepareStatement(insertQuery);
            connect_stat.setString(1, "DEFAULT");
            connect_stat.setString(2, "(SELECT id_profil FROM profil)");
            connect_stat.setInt(3, val);
            connect_stat.executeUpdate();

            connect_stat.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
