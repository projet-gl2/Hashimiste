package fr.hashimiste;

import org.sqlite.JDBC;

import java.sql.*;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;

public class Pont {
    Ile ile1, ile2;
    int n;
    Pont(Ile ile1, Ile ile2, int n) {
        this.ile1 = ile1;
        this.ile2 = ile2;
        this.n = n;
    }
    public static ArrayList<Pont> chargerPont(int idMap) {
        ArrayList<Pont> ponts = new ArrayList<>();
        try {
            Class.forName("org.sqlite.JDBC");
            Connection connection = DriverManager.getConnection(JDBC.PREFIX + SQLConstant.DB_FICHIER);

            String select = "SELECT * FROM pont WHERE id_m=?";
            PreparedStatement statement = connection.prepareStatement(select);
            statement.setInt(1, idMap);
            ResultSet result = statement.executeQuery(select);

            while(result.next()) {
                int id1 = result.getInt("id_dep");
                int id2 = result.getInt("id_dest");

                Pont pont = new Pont(Ile.ILES.get(id1), Ile.ILES.get(id2), result.getInt("n"));
                ponts.add(pont);
            }

            result.close();
            statement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return ponts;
    }
    public void save() {
        int x_ile1 = 0;
        int y_ile1 = 0;
        int x_ile2 = 0;
        int y_ile2 = 0;
        int n = 0;
        String nomMap = "";
        try {
            Class.forName("org.sqlite.JDBC");
            Connection connection = DriverManager.getConnection(JDBC.PREFIX + SQLConstant.DB_FICHIER);

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