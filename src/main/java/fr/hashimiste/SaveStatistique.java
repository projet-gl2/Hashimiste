package fr.hashimiste;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class SaveStatistique {
    public static void main(String[] args) {
        String bddHashi = "base.db";

        if (args.length != 1) {
            System.out.println("Nombre d'argument incorrect : 1 arguments attendus");
            System.out.println("Usage : java SaveStatistique \"valeur\"");
            System.err.println("Nombre d'argument incorrect");
        }

        int val = Integer.valueOf(args[0]);

        try {
            Connection bdd = DriverManager.getConnection(bddHashi);

            String insertQuery = "INSERT INTO statistique (id_stat, id_p, value) VALUES (?, ?, ?)";
            PreparedStatement connect_stat = bdd.prepareStatement(insertQuery);
            connect_stat.setString(1, "DEFAULT");
            connect_stat.setString(2, "(SELECT id_profil FROM profil)");
            connect_stat.setInt(3, val);
            connect_stat.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
