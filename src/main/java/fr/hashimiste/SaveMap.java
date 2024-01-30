package fr.hashimiste;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class SaveMap {
    public static void main(String[] args) {
        if (args.length != 2) {
            System.out.println("Nombre d'argument incorrect : 2 arguments attendus");
            System.out.println("Usage : java SaveMap \"nom\" \"difficulte\"");
            System.err.println("Nombre d'argument incorrect");
        }
        String bddHashi = "base.db";

        String nom = args[0];
        Integer diff = Integer.valueOf(args[1]);

        try {
            Connection connection = DriverManager.getConnection(bddHashi);

            String insertQuery = "INSERT INTO map (nom, difficulte) VALUES (?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(insertQuery);
            preparedStatement.setString(1, nom);
            preparedStatement.setInt(2, diff);
            preparedStatement.executeUpdate();

            preparedStatement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}