package fr.hashimiste.maps;

import org.sqlite.JDBC;

import java.sql.*;
import java.util.ArrayList;

/**
 * La classe Profil représente un profil avec un nom.
 */
public class Profil {
    /** Le nom du profil. */
    String nom;

    /**
     * Constructeur de la classe Profil.
     *
     * @param nom Le nom du profil.
     */
    Profil(String nom) {
        this.nom = nom;
    }

    public String getNom() {
        return this.nom;
    }

    /**
     * Charge tous les profils à partir de la base de données.
     *
     * @return Une liste d'objets Profil contenant tous les profils chargés.
     */
    public static ArrayList<Profil> chargerProfil() {
        ArrayList<Profil> profils = new ArrayList<>();
        try {
            // Chargement du driver JDBC
            Class.forName("org.sqlite.JDBC");
            // Connexion à la base de données
            Connection connection = DriverManager.getConnection(JDBC.PREFIX + SQLConstant.DB_FICHIER);

            // Requête SQL pour sélectionner tous les profils
            String selectAllProfil = "SELECT ALL nom FROM profil";
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(selectAllProfil);

            // Parcours des résultats et création d'objets Profil
            while(resultSet.next()) {
                String nom = resultSet.getString("nom");
                Profil profil = new Profil(nom);
                profils.add(profil);
            }

            // Fermeture des ressources
            resultSet.close();
            statement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return profils;
    }

    /**
     * Sauvegarde un nouveau profil dans la base de données.
     *
     * @param nom Le nom du profil à sauvegarder.
     */
    public void save(String nom) {
        try {
            // Chargement du driver JDBC
            Class.forName("org.sqlite.JDBC");
            // Connexion à la base de données
            Connection connection = DriverManager.getConnection(JDBC.PREFIX + SQLConstant.DB_FICHIER);

            // Requête SQL pour insérer un nouveau profil
            String insertQuery = "INSERT INTO profil (nom) VALUES (?)";
            PreparedStatement preparedStatement = connection.prepareStatement(insertQuery);
            preparedStatement.setString(1, nom);
            preparedStatement.execute();

            // Fermeture des ressources
            preparedStatement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}