package fr.hashimiste;

import org.sqlite.JDBC;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;

public class Map {
    ArrayList<Ile> iles;
    ArrayList<Pont> ponts;
    Map(ArrayList<Ile> listIles, ArrayList<Pont> listPonts) {
        this.iles = listIles;
        this.ponts = listPonts;
    }
    public static Map chargerMap(int idMap) {
        return new Map(Ile.chargerIle(idMap), Pont.chargerPont(idMap));
    }
    public void save(String nom, int diff) {
        iles.forEach(ile -> ile.save(nom));
        ponts.forEach(Pont::save);
        try {
            Class.forName("org.sqlite.JDBC");
            Connection connection = DriverManager.getConnection(JDBC.PREFIX + SQLConstant.DB_FICHIER);

            String insertQuery = "INSERT INTO map (nom, difficulte) VALUES (?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(insertQuery);
            preparedStatement.setString(1, nom);
            preparedStatement.setInt(2, diff);
            preparedStatement.execute();

            preparedStatement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}