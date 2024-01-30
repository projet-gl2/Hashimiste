package fr.hashimiste;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Pont {
    public static void main(String[] args) {
        if (args.length != 1) {
            System.out.println("Nombre d'argument incorrect : 1 argument attendu");
            System.out.println("Usage : java SaveMap \"n\"");
            System.err.println("Nombre d'argument incorrect");
        }
        String bddHashi = "base.db";

        Integer n = Integer.valueOf(args[0]);

        try {
            Connection connection = DriverManager.getConnection(bddHashi);

            //String select_idDep = "";

            String insertQuery = "INSERT INTO pont (n) VALUES (?)";
            PreparedStatement preparedStatement = connection.prepareStatement(insertQuery);
            preparedStatement.setInt(1, n);
            preparedStatement.executeUpdate();

            preparedStatement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}