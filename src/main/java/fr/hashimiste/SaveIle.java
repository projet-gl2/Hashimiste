package fr.hashimiste;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class SaveIle {
    public static void main(String[] args) {
        if (args.length != 3) {
            System.out.println("Nombre d'argument incorrect : 3 arguments attendus");
            System.out.println("Usage : java SaveIle \"x\" \"y\" \"n\"");
            System.err.println("Nombre d'argument incorrect");
        }
        String bddHashi = "base.db";

        Integer x = Integer.valueOf(args[0]);
        Integer y = Integer.valueOf(args[1]);
        Integer n = Integer.valueOf(args[2]);

        try {
            Connection connection = DriverManager.getConnection(bddHashi);

            String insertQuery = "INSERT INTO ile (x, y, n) VALUES (?, ?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(insertQuery);
            preparedStatement.setInt(1, x);
            preparedStatement.setInt(2, y);
            preparedStatement.setInt(3, n);
            preparedStatement.executeUpdate();

            preparedStatement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}