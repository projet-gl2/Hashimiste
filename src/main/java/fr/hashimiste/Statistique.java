package fr.hashimiste;

import java.sql.*;
import java.sql.Connection;

public class Statistique {
    public void save(int val, String nom_profil) {
        try {
            Class.forName("org.sqlite.JDBC");
            Connection connection = DriverManager.getConnection("jdbc:sqlite:"+Constant.CHEMIN_BDD);

            int id_profil = 0;
            String selectProfilQuery = "SELECT id_profil FROM profil WHERE nom="+nom_profil;
            PreparedStatement selectProfilStatement = connection.prepareStatement(selectProfilQuery);
            ResultSet resultSet = selectProfilStatement.executeQuery();
            if (resultSet.next()) {
                id_profil = resultSet.getInt("id_profil");
            }

            String insertQuery = "INSERT INTO statistique (id_stat, id_p, value) VALUES (?, ?, ?)";
            PreparedStatement connect_stat = connection.prepareStatement(insertQuery);
            connect_stat.setString(1, "DEFAULT");
            connect_stat.setInt(2, id_profil);
            connect_stat.setInt(3, val);
            connect_stat.execute();

            connect_stat.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
