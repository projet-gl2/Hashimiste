package fr.hashimiste;

import java.sql.*;
import java.sql.Connection;
import java.util.Date;
import java.sql.ResultSet;

public class Historique extends fr.hashimiste.Connection {
    public void save(int etat, int x_ile1, int y_ile1, int x_ile2, int y_ile2, String nomMap, String nomProfil) {
        try {
            Class.forName("org.sqlite.JDBC");
            Connection connection = DriverManager.getConnection("jdbc:sqlite:"+getBDD());

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

            String selectIdMap = "SELECT id_map FROM map WHERE nom=?";
            PreparedStatement idMapStatement = connection.prepareStatement(selectIdMap);
            idMapStatement.setString(1, nomMap);
            resultSet = idMapStatement.executeQuery();
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

            String insertQuery = "INSERT INTO historique (date_h, id_m, id_p, id_dep, id_dest, etat) VALUES (?, ?, ?, ?, ?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(insertQuery);
            preparedStatement.setDate(1, (java.sql.Date) new Date());
            preparedStatement.setInt(2, idMap);
            preparedStatement.setInt(3, idProfil);
            preparedStatement.setInt(4, idIleDep);
            preparedStatement.setInt(5, idIleDest);
            preparedStatement.setInt(6, etat);
            preparedStatement.execute();

            idProfilStatement.close();
            idMapStatement.close();
            idDepStatement.close();
            idDestStatement.close();
            preparedStatement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}