package fr.hashimiste;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Cette classe est utilisée pour initialiser la base de données avec les tables nécessaires.
 */
public class Bdd {

    /**
     * Méthode principale pour initialiser la base de données.
     * @param args Les arguments de la ligne de commande (non utilisés dans cette application).
     */
    public static void main(String[] args) {
        // Nom du fichier de base de données SQLite
        String bddHashi = "base.db";

        try {
            // Chargement du pilote JDBC SQLite
            Class.forName("org.sqlite.JDBC");
            // Établissement de la connexion à la base de données
            Connection bdd = DriverManager.getConnection("jdbc:sqlite:"+bddHashi);

            // Requêtes de création des tables
            String creaTable_map = "CREATE TABLE map (id_map serial PRIMARY KEY, nom varchar(15), difficulte integer);";
            String creaTable_save = "CREATE TABLE save (id_save serial PRIMARY KEY, id_p integer REFERENCES profil, nom varchar(15));";
            String creaTable_profil = "CREATE TABLE profil (id_profil serial PRIMARY KEY, nom varchar(15));";
            String creaTable_statistique = "CREATE TABLE statistic (id_stat serial PRIMARY KEY, id_p integer REFERENCES profil, value integer);";
            String creaTable_score = "CREATE TABLE score (id_p integer REFERENCES profil, id_m integer REFERENCES map, score integer, PRIMARY KEY (id_p, id_m));";
            String creaTable_ile = "CREATE TABLE ile (id_ile serial PRIMARY KEY, id_m integer REFERENCES map, x integer, y integer, n integer);";
            String creaTable_pont = "CREATE TABLE pont (id_dep integer REFERENCES ile, id_dest integer REFERENCES ile, id_save integer REFERENCES save, n integer, PRIMARY KEY (id_dep, id_dest));";
            String creaTable_historique = "CREATE TABLE historique (date_h date, id_m integer REFERENCES map, id_p integer REFERENCES profil, id_dep integer REFERENCES ile, id_dest integer REFERENCES ile, etat integer, PRIMARY KEY (id_m, id_p));";

            // Préparation des requêtes de création des tables
            PreparedStatement connect_map = bdd.prepareStatement(creaTable_map);
            PreparedStatement connect_save = bdd.prepareStatement(creaTable_save);
            PreparedStatement connect_profil = bdd.prepareStatement(creaTable_profil);
            PreparedStatement connect_statistique = bdd.prepareStatement(creaTable_statistique);
            PreparedStatement connect_score = bdd.prepareStatement(creaTable_score);
            PreparedStatement connect_ile = bdd.prepareStatement(creaTable_ile);
            PreparedStatement connect_pont = bdd.prepareStatement(creaTable_pont);
            PreparedStatement connect_historique = bdd.prepareStatement(creaTable_historique);

            // Exécution des requêtes de création des tables
            connect_map.execute();
            connect_save.execute();
            connect_profil.execute();
            connect_statistique.execute();
            connect_score.execute();
            connect_ile.execute();
            connect_pont.execute();
            connect_historique.execute();

            // Fermeture des connexions
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