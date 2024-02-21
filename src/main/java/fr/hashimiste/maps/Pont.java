package fr.hashimiste.maps;

import org.sqlite.JDBC;

import java.awt.*;
import java.sql.*;
import java.util.ArrayList;

/**
 * La classe Pont représente un pont entre deux îles (Ile).
 * Elle contient des informations sur les deux îles qu'elle relie et le nombre de ponts entre elles.
 * Elle contient également des méthodes pour modifier le nombre de ponts et récupérer des informations sur les îles et le nombre de ponts.
 */
public class Pont extends Component {
    private final Ile ile1;
    private final Ile ile2;
    private int n;

    /**
     * Créer un pont
     *
     * @param ile1 l'ile 1
     * @param ile2 l'ile 2
     * @param n    le nombre de ponts
     * @throws IllegalArgumentException si le nombre de ponts est négatif,
     *                                  si une des iles est nulle,
     *                                  si les iles sont les mêmes
     */
    public Pont(Ile ile1, Ile ile2, int n) {
        if (n < 0) {
            throw new IllegalArgumentException("Le nombre de ponts ne peut pas être négatif");
        }
        if (ile1 == null || ile2 == null) {
            throw new IllegalArgumentException("Les iles ne peuvent pas être nulles");
        }
        if (ile1.equals(ile2)) {
            throw new IllegalArgumentException("Les iles ne peuvent pas être les mêmes");
        }
        this.ile1 = ile1;
        this.ile2 = ile2;
        this.n = n;
        ile1.addPont(this);
        ile2.addPont(this);
    }

    @Override
    public void paint(Graphics g) {
        g.setColor(Color.BLACK);
        boolean horizontal = ile1.getY() == ile2.getY();
        for (int i = 0; i < n; i++) {
            if (horizontal) {
                g.drawLine(ile1.getX() + ile1.getWidth() / 2 + i * 20 + ile1.getWidth() / 2,
                        ile1.getY() + ile1.getHeight() / 2,
                        ile2.getX() + ile2.getWidth() / 2 + i * 20 - ile2.getWidth() / 2,
                        ile2.getY() + ile2.getHeight() / 2);
            } else {
                g.drawLine(ile1.getX() + ile1.getHeight() / 2,
                        ile1.getY() + ile1.getHeight() / 2 + i * 20 + ile1.getHeight() / 2,
                        ile2.getX() + ile2.getHeight() / 2,
                        ile2.getY() + ile2.getHeight() / 2 + i * 20 - ile2.getHeight() / 2);
            }
        }
        super.paint(g);
    }

    /**
     * Modifie le nombre de ponts
     *
     * @param n le nouveau nombre de ponts
     */
    public void setN(int n) {
        this.n = n;
    }

    /**
     * Récupérer l'ile 1
     *
     * @return l'ile 1
     */
    public Ile getIle1() {
        return ile1;
    }

    /**
     * Récupérer l'ile 2
     *
     * @return l'ile 2
     */
    public Ile getIle2() {
        return ile2;
    }

    /**
     * Récupérer le nombre de ponts
     *
     * @return le nombre de ponts
     */
    public int getN() {
        return n;
    }

    public boolean constructionPossible() {
        return true;
    }

    @Override
    public String toString() {
        return "Pont{" +
                "ile1=" + ile1 +
                ", ile2=" + ile2 +
                ", n=" + n +
                '}';
    }

    /**
     * Méthode statique pour charger les ponts d'une carte donnée depuis la base de données.
     *
     * @param idMap L'identifiant de la carte pour laquelle charger les ponts.
     * @return Une liste d'objets Pont chargés depuis la base de données.
     */
    public static ArrayList<Pont> chargerPont(int idMap) {
        ArrayList<Pont> ponts = new ArrayList<>();
        try {
            Class.forName("org.sqlite.JDBC");
            Connection connection = DriverManager.getConnection(JDBC.PREFIX + SQLConstant.DB_FICHIER);

            String select = "SELECT * FROM pont WHERE id_m=?";
            PreparedStatement statement = connection.prepareStatement(select);
            statement.setInt(1, idMap);
            ResultSet result = statement.executeQuery(select);

            while (result.next()) {
                int id1 = result.getInt("id_dep");
                int id2 = result.getInt("id_dest");

                // Création d'un nouveau pont à partir des données de la base de données
                Pont pont = new Pont(Ile.ILES.get(id1), Ile.ILES.get(id2), result.getInt("n"));
                ponts.add(pont);
            }

            result.close();
            statement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return ponts;
    }

    /**
     * Méthode pour sauvegarder le pont dans la base de données.
     * Cette méthode suppose que les coordonnées des îles et le nom de la carte ont déjà été définis.
     */
    public void save() {
        int x_ile1 = 0;
        int y_ile1 = 0;
        int x_ile2 = 0;
        int y_ile2 = 0;
        int n = 0;
        String nomMap = "";
        try {
            Class.forName("org.sqlite.JDBC");
            Connection connection = DriverManager.getConnection(JDBC.PREFIX + SQLConstant.DB_FICHIER);

            // Recherche de l'identifiant de l'île de départ dans la base de données
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

            // Recherche de l'identifiant de l'île de destination dans la base de données
            String selectIdDest = "SELECT id_ile FROM ile JOIN map ON ile.id_m = map.id_map WHERE x=? and y=? and nom=?";
            PreparedStatement idDestStatement = connection.prepareStatement(selectIdDest);
            idDestStatement.setInt(1, x_ile2);
            idDestStatement.setInt(2, y_ile2);
            idDestStatement.setString(3, nomMap);
            idDestStatement.execute();
            resultSet = idDestStatement.executeQuery();
            int idIleDest = 0;
            if (resultSet.next()) {
                idIleDest = resultSet.getInt("id_ile");
            }

            // Insertion du pont dans la base de données
            String insertQuery = "INSERT INTO pont (id_dep, id_dest, n) VALUES (?, ?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(insertQuery);
            preparedStatement.setInt(1, idIleDep);
            preparedStatement.setInt(2, idIleDest);
            preparedStatement.setInt(3, n);
            preparedStatement.execute();

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
