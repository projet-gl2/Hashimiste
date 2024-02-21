package fr.hashimiste.maps;

import fr.hashimiste.techniques.Direction;
import org.sqlite.JDBC;

import java.awt.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;

/**
 * La classe Ile représente une île dans une grille (Grille).
 * Elle contient des informations sur la grille à laquelle elle appartient, ses coordonnées et le nombre de ponts qu'elle doit avoir.
 * Elle contient également une liste de ponts (Pont) qui lui sont liés.
 * Elle fournit des méthodes pour créer un pont vers une autre île, récupérer des informations sur l'île et ses ponts, et vérifier si l'île est complète (c'est-à-dire si tous ses ponts ont été créés).
 */
public class Ile extends Component {
    private final Grille grille;
    private final int n; //valeur de l'île
    private final List<Pont> pontsLiees = new ArrayList<>();

    /**
     * Une collection statique qui contient toutes les îles indexées par leur identifiant.
     */
    protected static Map<Integer, Ile> ILES = new HashMap<>();

    /**
     * Créer une ile
     *
     * @param grille la grille de l'ile
     * @param x      l'abscisse de l'ile
     * @param y      l'ordonnée de l'ile
     * @param nbPont le nombre de ponts que l'ile doit avoir
     * @throws IllegalArgumentException si le nombre de ponts est négatif ou égal à 0
     */
    public Ile(Grille grille, int x, int y, int nbPont) {
        if (nbPont <= 0) {
            throw new IllegalArgumentException("Le nombre de ponts ne peut pas être négatif ou égal à 0");
        }
        this.grille = grille;
        setLocation(x, y);
        setSize(20, 20);
        this.n = nbPont;
    }

    /**
     * Créer un pont entre l'ile et une autre ile
     *
     * @param ile l'ile à relier
     * @param n   le nombre de ponts à créer
     * @return le pont créé
     */
    public Pont creerPont(Ile ile, int n) {
        return new Pont(this, ile, n);
    }

    /**
     * Récupérer la grille de l'ile
     *
     * @return la grille de l'ile
     */
    public Grille getGrille() {
        return grille;
    }

    @Override
    public void paint(Graphics g) {
        g.setColor(Color.BLACK);
        g.drawOval(getX(), getY(), getWidth(), getHeight());
        g.drawString(String.valueOf(getNbPontPossible()), getX() + 8, getY() + 14);
        super.paint(g);
    }

    public int getN() {
        return n;
    }

    /**
     * Récupérer le nombre de ponts maximum qu'il est possible de faire depuis l'ile
     *
     * @return le nombre maximum de ponts
     */
    public int getNbPontPossible() {
        // TODO
        return 0;
    }

    /**
     * Récupérer le nombre de ponts liés à l'ile
     *
     * @return le nombre de ponts liés à l'ile
     */
    public int getNbPont() {
        return pontsLiees.stream().mapToInt(Pont::getN).sum();
    }

    /**
     * Vérifier si l'ile est complète
     *
     * @return true si l'ile est complète, false sinon
     */
    public boolean isComplete() {
        return getNbPont() == getNbPontPossible();
    }

    /**
     * Retourne le nombre de ponts liés à l'ile qui vérifient le filtre
     *
     * @param filtre le filtre à appliquer
     * @return le nombre de ponts liés à l'ile qui vérifient le filtre
     */
    public int getNbPont(Predicate<Ile> filtre) {
        return pontsLiees.stream().filter(pont -> pont.getIle1() == this || pont.getIle2() == this).filter(pont -> filtre.test(pont.getIle1()) || filtre.test(pont.getIle2())).mapToInt(Pont::getN).sum();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Ile ile = (Ile) o;
        return getNbPontPossible() == ile.getNbPontPossible() && getX() == ile.getX() && getY() == ile.getY();
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 31 * hash + getX();
        hash = 31 * hash + getY();
        hash = 31 * hash + getNbPontPossible();
        return hash;
    }

    @Override
    public String toString() {
        return "Ile{" +
                "x=" + getX() +
                ", y=" + getY() +
                ", nbPont=" + n +
                '}';
    }

    /**
     * Ajoute un pont à l'ile
     *
     * @param pont le pont à ajouter
     */
    void addPont(Pont pont) {
        pontsLiees.add(pont);
    }

    /**
     * Compte le nombre de voisins possibles pour l'île, c'est-à-dire le nombre
     * d'îles différentes avec lesquelles il est possible de construire un pont.
     * @return nombre d'îles voisines.
     */
    public int getNbVoisin(){
        // TODO
        return 0;
    }

    /**
     * Compte le nombre de voisins de valeur "1" possibles pour l'île.
     * @return nombre d'îles voisines de valeur "1".
     */
    public int getNbVoisinAvec1(){
        // TODO
        return 0;
    }

    /**
     * Vérifie ce qu'il y a en face de l'île dans la direction donnée.
     * @param d Direction à vérifier peut être NORD, EST, SUD ou OUEST
     * @return -1 s'il n'y aucune île accessible,
     * 0 s'il y a une île sans pont,
     * 1 s'il y a une île reliée par un pont,
     * 2 s'il y a une île réliée par deux ponts
     */
    public int nbPontsDirection(Direction d){
        // TODO
        return 0;
    }


    /**
     * Vérifie la valeur de l'île en face de l'île appelée dans la direction donnée.
     * @param d Direction à vérifier peut être NORD, EST, SUD ou OUEST
     * @return -1 s'il n'y aucune île accessible, la valeur de l'île en face sinon.
     */
    public int valeurIleDirection(Direction d){
        // TODO
        return 0;
    }

    /**
     * Méthode statique pour charger les îles à partir d'une base de données pour une carte spécifique.
     * @param idMap L'identifiant de la carte pour laquelle charger les îles.
     * @return Une liste d'objets Ile représentant les îles chargées.
     */
    public ArrayList<Ile> chargerIle(int idMap) {
        ArrayList<Ile> iles = new ArrayList<>();
        try {
            Class.forName("org.sqlite.JDBC");
            Connection connection = DriverManager.getConnection(JDBC.PREFIX + SQLConstant.DB_FICHIER);

            String select = "SELECT * FROM ile WHERE id_m=?";
            PreparedStatement statement = connection.prepareStatement(select);
            statement.setInt(1, idMap);
            ResultSet result = statement.executeQuery(select);

            while(result.next()) {
                Ile ile = new Ile(this.grille, result.getInt("x"), result.getInt("y"), result.getInt("n"));
                iles.add(ile);
                ILES.put(result.getInt("id"), ile);
            }

            result.close();
            statement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return iles;
    }

    /**
     * Méthode pour sauvegarder une île dans la base de données.
     * @param nom_map Le nom de la carte dans laquelle sauvegarder l'île.
     */
    public void save(String nom_map) {
        int x = 0;
        int y = 0;
        int n = 0;
        try {
            Class.forName("org.sqlite.JDBC");
            Connection connection = DriverManager.getConnection(JDBC.PREFIX + SQLConstant.DB_FICHIER);

            int id_map = 0;
            String selectMapQuery = "SELECT id_map FROM map WHERE nom="+nom_map;
            PreparedStatement selectMapStatement = connection.prepareStatement(selectMapQuery);
            ResultSet resultSet = selectMapStatement.executeQuery();
            if (resultSet.next()) {
                id_map = resultSet.getInt("id_map");
            }

            String insertQuery = "INSERT INTO ile (id_ile, id_m, x, y, n) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(insertQuery);
            preparedStatement.setString(1, "DEFAULT");
            preparedStatement.setInt(2, id_map);
            preparedStatement.setInt(3, x);
            preparedStatement.setInt(4, y);
            preparedStatement.setInt(5, n);
            preparedStatement.execute();

            preparedStatement.close();
            connection.close();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
