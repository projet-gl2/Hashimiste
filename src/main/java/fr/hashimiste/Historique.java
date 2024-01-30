package fr.hashimiste;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Date;

public class Historique {
    public static void main(String[] args) {
        if (args.length != 1) {
            System.out.println("Nombre d'argument incorrect : 1 argument attendu");
            System.out.println("Usage : java SaveMap \"etat\"");
            System.err.println("Nombre d'argument incorrect");
        }
        String bddHashi = "base.db";

        Integer etat = Integer.valueOf(args[0]);

        try {
            Connection connection = DriverManager.getConnection(bddHashi);

            String insertQuery = "INSERT INTO historique (date_h, etat) VALUES (?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(insertQuery);
            preparedStatement.setDate(1, (java.sql.Date) new Date());
            preparedStatement.setInt(2, etat);
            preparedStatement.executeUpdate();

            preparedStatement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}