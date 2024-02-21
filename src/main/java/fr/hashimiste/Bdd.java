package fr.hashimiste;

import org.sqlite.JDBC;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Cette classe est utilisée pour créer les tables dans la base de données.
 */
public class Bdd {

    /**
     * Méthode principale pour exécuter la création des tables dans la base de données.
     *
     * @param args les arguments de la ligne de commande (non utilisés dans cette application)
     */
    public static void main(String[] args) {
        try {
            // Chargement du pilote JDBC pour SQLite
            Class.forName("org.sqlite.JDBC");

            // Connexion à la base de données
            Connection bdd = DriverManager.getConnection(JDBC.PREFIX + SQLConstant.DB_FICHIER);

            // Création des requêtes SQL pour chaque table
            String creaTable_map = "CREATE TABLE map (id_map serial PRIMARY KEY, nom varchar(15), difficulte integer);";
            String creaTable_save = "CREATE TABLE save (id_save serial PRIMARY KEY, id_p integer REFERENCES profil, nom varchar(15));";
            String creaTable_profil = "CREATE TABLE profil (id_profil serial PRIMARY KEY, nom varchar(15));";
            String creaTable_statistique = "CREATE TABLE statistic (id_stat serial PRIMARY KEY, id_p integer REFERENCES profil, value integer);";
            String creaTable_score = "CREATE TABLE score (id_p integer REFERENCES profil, id_m integer REFERENCES map, valeur integer, PRIMARY KEY (id_p, id_m));";
            String creaTable_ile = "CREATE TABLE ile (id_ile serial PRIMARY KEY, id_m integer REFERENCES map, x integer, y integer, n integer);";
            String creaTable_pont = "CREATE TABLE pont (id_dep integer REFERENCES ile, id_dest integer REFERENCES ile, id_save integer REFERENCES save, n integer, PRIMARY KEY (id_dep, id_dest));";
            String creaTable_historique = "CREATE TABLE historique (date_h date, id_m integer REFERENCES map, id_p integer REFERENCES profil, id_dep integer REFERENCES ile, id_dest integer REFERENCES ile, etat integer, PRIMARY KEY (id_m, id_p));";

            // Préparation des requêtes SQL
            PreparedStatement connect_map = bdd.prepareStatement(creaTable_map);
            PreparedStatement connect_save = bdd.prepareStatement(creaTable_save);
            PreparedStatement connect_profil = bdd.prepareStatement(creaTable_profil);
            PreparedStatement connect_statistique = bdd.prepareStatement(creaTable_statistique);
            PreparedStatement connect_score = bdd.prepareStatement(creaTable_score);
            PreparedStatement connect_ile = bdd.prepareStatement(creaTable_ile);
            PreparedStatement connect_pont = bdd.prepareStatement(creaTable_pont);
            PreparedStatement connect_historique = bdd.prepareStatement(creaTable_historique);

            // Exécution des requêtes SQL pour créer les tables
            connect_map.execute();
            connect_save.execute();
            connect_profil.execute();
            connect_statistique.execute();
            connect_score.execute();
            connect_ile.execute();
            connect_pont.execute();
            connect_historique.execute();

            // Fermeture des connexions et des ressources
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
            // En cas d'erreur SQL, afficher la trace de l'exception
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            // En cas d'erreur de chargement du pilote JDBC, lancer une RuntimeException
            throw new RuntimeException(e);
        }
    }
}