package fr.hashimiste;

import org.sqlite.JDBC;

import java.sql.*;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Ile {
    protected static final Map<Integer, Ile> ILES = new HashMap<>();
    int x, y, n;
    Ile(int x, int y, int n) {
        this.x = x;
        this.y = y;
        this.n = n;
    }
    public static ArrayList<Ile> chargerIle(int idMap) {
        ArrayList<Ile> iles = new ArrayList<>();
        try {
            Class.forName("org.sqlite.JDBC");
            Connection connection = DriverManager.getConnection(JDBC.PREFIX + SQLConstant.DB_FICHIER);

            String select = "SELECT * FROM ile WHERE id_m=?";
            PreparedStatement statement = connection.prepareStatement(select);
            statement.setInt(1, idMap);
            ResultSet result = statement.executeQuery(select);

            while(result.next()) {
                Ile ile = new Ile(result.getInt("x"), result.getInt("y"), result.getInt("n"));
                iles.add(ile);
                ILES.put(result.getInt("id"), ile);
            }

            result.close();
            statement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return iles;
    }
    public void save(String nom_map) {
        int x = 0;
        int y = 0;
        int n = 0;
        try {
            Class.forName("org.sqlite.JDBC");
            Connection connection = DriverManager.getConnection(JDBC.PREFIX + SQLConstant.DB_FICHIER);

            int id_map = 0;
            String selectMapQuery = "SELECT id_map FROM map WHERE nom="+nom_map;
            PreparedStatement selectMapStatement = connection.prepareStatement(selectMapQuery);
            ResultSet resultSet = selectMapStatement.executeQuery();
            if (resultSet.next()) {
                id_map = resultSet.getInt("id_map");
            }

            String insertQuery = "INSERT INTO ile (id_ile, id_m, x, y, n) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(insertQuery);
            preparedStatement.setString(1, "DEFAULT");
            preparedStatement.setInt(2, id_map);
            preparedStatement.setInt(3, x);
            preparedStatement.setInt(4, y);
            preparedStatement.setInt(5, n);
            preparedStatement.execute();

            preparedStatement.close();
            connection.close();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}