package fr.hashimiste.maps;

import org.sqlite.JDBC;

import java.sql.*;
import java.util.Date;

/**
 * Cette classe fournit des méthodes pour charger et sauvegarder des données d'historique dans une base de données.
 */
public class Historique {

    /**
     * Charge les données d'historique à partir de la base de données.
     *
     * @param date      La date de l'historique à charger.
     * @param nomMap    Le nom de la carte associée à l'historique.
     * @param nomProfil Le nom du profil associé à l'historique.
     * @return          La carte chargée à partir de la base de données ou null s'il n'y a pas de sauvegarde correspondante.
     */
    public static Map charger(Date date, String nomMap, String nomProfil) {
        try {
            Class.forName("org.sqlite.JDBC");
            Connection connection = DriverManager.getConnection(JDBC.PREFIX + SQLConstant.DB_FICHIER);

            String selectIdMap = "SELECT id_map FROM map WHERE nom=?";
            PreparedStatement idMapStatement = connection.prepareStatement(selectIdMap);
            idMapStatement.setString(1, nomMap);
            ResultSet resultSet = idMapStatement.executeQuery();
            int idMap = 0;
            idMap = resultSet.getInt("id_map");

            String selectIdProfil = "SELECT id_profil FROM profil WHERE nom=?";
            PreparedStatement idProfilStatement = connection.prepareStatement(selectIdProfil);
            idProfilStatement.setString(1, nomProfil);
            resultSet = idProfilStatement.executeQuery();
            int idProfil = 0;
            idProfil = resultSet.getInt("id_profil");

            String selectHist = "SELECT * FROM historique WHERE date=? AND id_m=? AND id_p=?";
            PreparedStatement preparedStatement = connection.prepareStatement(selectHist);
            preparedStatement.setDate(1, (java.sql.Date) date);
            preparedStatement.setInt(2, idMap);
            preparedStatement.setInt(3, idProfil);
            ResultSet result = preparedStatement.executeQuery();

            if (result.next()) {
                idProfilStatement.close();
                idMapStatement.close();
                preparedStatement.close();
                connection.close();
                return Map.chargerMap(idMap);
            }
            else {
                System.out.println("Aucune sauvegarde n'a été trouvé avec ces informations : " + date + ", " + nomMap + ", " + nomProfil);
                idProfilStatement.close();
                idMapStatement.close();
                preparedStatement.close();
                connection.close();
                return null;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Sauvegarde les données d'historique dans la base de données.
     *
     * @param etat      L'état de l'historique à sauvegarder.
     * @param x_ile1    La coordonnée x de la première île.
     * @param y_ile1    La coordonnée y de la première île.
     * @param x_ile2    La coordonnée x de la deuxième île.
     * @param y_ile2    La coordonnée y de la deuxième île.
     * @param nomMap    Le nom de la carte associée à l'historique.
     * @param nomProfil Le nom du profil associé à l'historique.
     */
    public void save(int etat, int x_ile1, int y_ile1, int x_ile2, int y_ile2, String nomMap, String nomProfil) {
        try {
            Class.forName("org.sqlite.JDBC");
            Connection connection = DriverManager.getConnection(JDBC.PREFIX + SQLConstant.DB_FICHIER);

            String selectIdDep = "SELECT id_ile FROM ile JOIN map ON ile.id_m = map.id_map WHERE x=? and y=? and nom=?";
            PreparedStatement idDepStatement = connection.prepareStatement(selectIdDep);
            idDepStatement.setInt(1, x_ile1);
            idDepStatement.setInt(2, y_ile1);
            idDepStatement.setString(3, nomMap);
            ResultSet resultSet = idDepStatement.executeQuery();
            int idIleDep = 0;
            if (resultSet.next()) {
                idIleDep = resultSet.getInt("id_ile");
            }

            String selectIdDest = "SELECT id_ile FROM ile JOIN map ON ile.id_m = map.id_map WHERE x=? and y=? and nom=?";
            PreparedStatement idDestStatement = connection.prepareStatement(selectIdDest);
            idDestStatement.setInt(1, x_ile2);
            idDestStatement.setInt(2, y_ile2);
            idDestStatement.setString(3, nomMap);
            resultSet = idDestStatement.executeQuery();
            int idIleDest = 0;
            if (resultSet.next()) {
                idIleDest = resultSet.getInt("id_ile");
            }

            String selectIdMap = "SELECT id_map FROM map WHERE nom=?";
            PreparedStatement idMapStatement = connection.prepareStatement(selectIdMap);
            idMapStatement.setString(1, nomMap);
            resultSet = idMapStatement.executeQuery();
            int idMap = 0;
            if (resultSet.next()) {
                idMap = resultSet.getInt("id_map");
            }

            String selectIdProfil = "SELECT id_profil FROM profil WHERE nom=?";
            PreparedStatement idProfilStatement = connection.prepareStatement(selectIdProfil);
            idProfilStatement.setString(1, nomProfil);
            resultSet = idProfilStatement.executeQuery();
            int idProfil = 0;
            if (resultSet.next()) {
                idProfil = resultSet.getInt("id_profil");
            }

            String insertQuery = "INSERT INTO historique (date_h, id_m, id_p, id_dep, id_dest, etat) VALUES (?, ?, ?, ?, ?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(insertQuery);
            preparedStatement.setDate(1, (java.sql.Date) new Date());
            preparedStatement.setInt(2, idMap);
            preparedStatement.setInt(3, idProfil);
            preparedStatement.setInt(4, idIleDep);
            preparedStatement.setInt(5, idIleDest);
            preparedStatement.setInt(6, etat);
            preparedStatement.execute();

            idProfilStatement.close();
            idMapStatement.close();
            idDepStatement.close();
            idDestStatement.close();
            preparedStatement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}