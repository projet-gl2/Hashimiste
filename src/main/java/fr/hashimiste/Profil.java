package fr.hashimiste;

import org.sqlite.JDBC;

import java.sql.*;
import java.sql.Connection;
import java.util.ArrayList;

public class Profil {
    String nom;

    Profil(String nom) {
        this.nom = nom;
    }

    public static ArrayList<Profil> chargerProfil() {
        ArrayList<Profil> profils = new ArrayList<>();
        try {
            Class.forName("org.sqlite.JDBC");
            Connection connection = DriverManager.getConnection(JDBC.PREFIX + SQLConstant.DB_FICHIER);

            String selectAllProfil = "SELECT ALL nom FROM profil";
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(selectAllProfil);

            while(resultSet.next()) {
                String nom = resultSet.getString("nom");
                Profil profil = new Profil(nom);
                profils.add(profil);
            }

            resultSet.close();
            statement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return profils;
    }

    public void save(String nom) {

        try {
            Class.forName("org.sqlite.JDBC");
            Connection connection = DriverManager.getConnection(JDBC.PREFIX + SQLConstant.DB_FICHIER);

            String insertQuery = "INSERT INTO profil (nom) VALUES (?)";
            PreparedStatement preparedStatement = connection.prepareStatement(insertQuery);
            preparedStatement.setString(1, nom);
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
