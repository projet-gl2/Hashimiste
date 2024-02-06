package fr.hashimiste;

import org.sqlite.JDBC;

import java.sql.*;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Cette classe représente une île dans un jeu.
 * Les îles sont définies par leurs coordonnées x et y ainsi que leur numéro n.
 * Les îles sont également enregistrées dans une collection statique pour un accès global.
 */
public class Ile {

    /**
     * Une collection statique qui contient toutes les îles indexées par leur identifiant.
     */
    protected static final Map<Integer, Ile> ILES = new HashMap<>();

    /**
     * Les coordonnées x, y de l'île et son numéro n.
     */
    int x, y, n;

    /**
     * Constructeur de la classe Ile.
     * @param x La coordonnée x de l'île.
     * @param y La coordonnée y de l'île.
     * @param n Le numéro de l'île.
     */
    Ile(int x, int y, int n) {
        this.x = x;
        this.y = y;
        this.n = n;
    }

    /**
     * Méthode statique pour charger les îles à partir d'une base de données pour une carte spécifique.
     * @param idMap L'identifiant de la carte pour laquelle charger les îles.
     * @return Une liste d'objets Ile représentant les îles chargées.
     */
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

    /**
     * Méthode pour sauvegarder une île dans la base de données.
     * @param nom_map Le nom de la carte dans laquelle sauvegarder l'île.
     */
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