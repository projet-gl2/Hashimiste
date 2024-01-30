package fr.hashimiste;

import java.sql.*;
import java.sql.Connection;
import java.sql.ResultSet;

public class Pont {
    public void save(int n, int x_ile1, int y_ile1, int x_ile2, int y_ile2, String nomMap) {

        try {
            Class.forName("org.sqlite.JDBC");
            Connection connection = DriverManager.getConnection("jdbc:sqlite:"+Constant.CHEMIN_BDD);

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
            idDestStatement.execute();
            resultSet = idDestStatement.executeQuery();
            int idIleDest = 0;
            if (resultSet.next()) {
                idIleDest = resultSet.getInt("id_ile");
            }

            String insertQuery = "INSERT INTO pont (id_dep, id_dest, n) VALUES (?, ?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(insertQuery);
            preparedStatement.setInt(1, idIleDep);
            preparedStatement.setInt(2, idIleDest);
            preparedStatement.setInt(3, n);
            preparedStatement.execute();

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