package fr.hashimiste;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Profil {
    public static void main(String[] args) {
        if (args.length != 1) {
            System.out.println("Nombre d'argument incorrect : 1 argument attendu");
            System.out.println("Usage : java SaveProfil \"nom\"");
            System.err.println("Nombre d'argument incorrect");
        }
        String bddHashi = "base.db";

        String nom = args[0];

        try {
            Connection connection = DriverManager.getConnection(bddHashi);

            String insertQuery = "INSERT INTO map (nom) VALUES (?)";
            PreparedStatement preparedStatement = connection.prepareStatement(insertQuery);
            preparedStatement.setString(1, nom);
            preparedStatement.executeUpdate();

            preparedStatement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
