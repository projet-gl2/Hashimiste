package fr.hashimiste;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class SaveScore {
    public static void main(String[] args) {
        String bddHashi = "base.db";

        if (args.length != 3) {
            System.out.println("Nombre d'argument incorrect : 3 arguments attendus");
            System.out.println("Usage : java SaveScore \"nomProfil\" \"nomMap\" \"score\"");
            System.err.println("Nombre d'argument incorrect");
        }

        int score = Integer.valueOf(args[2]);

        try {
            Connection bdd = DriverManager.getConnection(bddHashi);

            String insertQuery = "INSERT INTO statistique (id_p, id_m, value) VALUES (?, ?, ?)";
            PreparedStatement connect_stat = bdd.prepareStatement(insertQuery);
            connect_stat.setString(1, "SELECT id_profil FROM profil where " + args[0] + ")");
            connect_stat.setString(2, "(SELECT id_map FROM map where " + args[1] + ")");
            connect_stat.setInt(3, score);
            connect_stat.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
