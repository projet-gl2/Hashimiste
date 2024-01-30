package fr.hashimiste;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class bdd {
    public static void main(String[] args) {
        String bddHashi = "base.db";

        try {
            Class.forName("org.sqlite.JDBC");
            Connection bdd = DriverManager.getConnection("jdbc:sqlite:"+bddHashi);

            /*String insertQuery = "INSERT INTO employees (first_name, last_name, age) VALUES (?, ?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(insertQuery);
            preparedStatement.setString(1, "John");
            preparedStatement.setString(2, "Doe");
            preparedStatement.setInt(3, 30);
            preparedStatement.executeUpdate();*/

            // Creation des tables
			String creaTable_map = "CREATE TABLE map (id_map serial PRIMARY KEY, nom varchar(15), difficulte integer);";
            String creaTable_save = "CREATE TABLE save (id_save serial PRIMARY KEY, id_p integer REFERENCES profil, nom varchar(15));";
            String creaTable_profil = "CREATE TABLE profil (id_profil serial PRIMARY KEY, nom varchar(15));";
            String creaTable_statistique = "CREATE TABLE statistic (id_stat serial PRIMARY KEY, id_p integer REFERENCES profil, value integer);";
            String creaTable_score = "CREATE TABLE score (id_p integer REFERENCES profil, id_m integer REFERENCES map, score integer, PRIMARY KEY (id_p, id_m));";
            String creaTable_ile = "CREATE TABLE ile (id_ile serial PRIMARY KEY, id_m integer REFERENCES map, x integer, y integer, n integer);";
            String creaTable_pont = "CREATE TABLE pont (id_dep integer REFERENCES ile, id_dest integer REFERENCES ile, id_save integer REFERENCES save, n integer, PRIMARY KEY (id_dep, id_dest));";
            String creaTable_historique = "CREATE TABLE historique (date_h date, id_m integer REFERENCES map, id_p integer REFERENCES profil, id_dep integer REFERENCES ile, id_dest integer REFERENCES ile, etat integer, PRIMARY KEY (id_m, id_p));";

            // Connexion de chaque create à la base de donnée
            PreparedStatement connect_map = bdd.prepareStatement(creaTable_map);
            PreparedStatement connect_save = bdd.prepareStatement(creaTable_save);
            PreparedStatement connect_profil = bdd.prepareStatement(creaTable_profil);
            PreparedStatement connect_statistique = bdd.prepareStatement(creaTable_statistique);
            PreparedStatement connect_score = bdd.prepareStatement(creaTable_score);
            PreparedStatement connect_ile = bdd.prepareStatement(creaTable_ile);
            PreparedStatement connect_pont = bdd.prepareStatement(creaTable_pont);
            PreparedStatement connect_historique = bdd.prepareStatement(creaTable_historique);

            // Exécution des create
            connect_map.execute();
            connect_save.executeQuery();
            connect_profil.executeQuery();
            connect_statistique.executeQuery();
            connect_score.executeQuery();
            connect_ile.executeQuery();
            connect_pont.executeQuery();
            connect_historique.executeQuery();

            // Fermeture des connexion
            connect_map.close();
            connect_save.close();
            connect_profil.close();
            connect_statistique.close();
            connect_score.close();
            connect_ile.close();
            connect_pont.close();
            connect_historique.close();
            bdd.close();

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}