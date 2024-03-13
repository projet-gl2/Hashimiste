package fr.hashimiste.impl.data.sql;

import fr.hashimiste.core.data.Decoder;
import fr.hashimiste.core.data.Filter;
import fr.hashimiste.core.data.Join;
import fr.hashimiste.core.data.Stockage;
import fr.hashimiste.core.data.sql.Identifiable;
import fr.hashimiste.core.data.sql.SQLEncoder;
import fr.hashimiste.core.jeu.Grille;
import fr.hashimiste.core.jeu.Historique;
import fr.hashimiste.core.jeu.Ile;
import fr.hashimiste.core.jeu.Sauvegarde;
import fr.hashimiste.core.joueur.Profil;
import fr.hashimiste.core.joueur.Statistique;
import fr.hashimiste.core.utils.Assert;
import fr.hashimiste.impl.Main;
import fr.hashimiste.impl.data.sql.decoder.*;
import fr.hashimiste.impl.data.sql.encoder.*;

import java.sql.*;
import java.util.*;
import java.util.function.Predicate;

/**
 * La classe SQLStockage implémente l'interface Stockage.
 * Elle fournit des méthodes pour gérer le stockage des données dans une base de données SQL.
 */
public class SQLStockage implements Stockage {

    private final Connection connection;
    private final Map<Class<?>, Decoder<ResultSet, ?>> decoders = new HashMap<>();
    private final Map<Class<?>, SQLEncoder<?>> encoders = new HashMap<>();
    private final Map<Class<?>, List<Object>> caches = new HashMap<>();

    /**
     * Constructeur de SQLStockage.
     *
     * @param connection la connexion à la base de données.
     */
    public SQLStockage(Connection connection) {
        this.connection = connection;
        enregistrerEncodeurs();
        enregistrerDecodeurs();
        try {
            miseEnPlaceTable();
        } catch (SQLException e) {
            System.err.println("Erreur lors de la création des tables");
            e.printStackTrace();
        }
    }

    /**
     * Constructeur de SQLStockage.
     *
     * @param driver le nom du pilote JDBC.
     * @param url    l'URL de la base de données.
     * @throws ClassNotFoundException si le pilote JDBC n'est pas trouvé.
     * @throws NoSuchFieldException   si le champ 'PREFIX' n'est pas trouvé dans la classe du pilote.
     * @throws IllegalAccessException si l'accès au champ 'PREFIX' est refusé.
     * @throws SQLException           si une erreur de base de données se produit.
     */
    public SQLStockage(String driver, String url) throws ClassNotFoundException, NoSuchFieldException, IllegalAccessException, SQLException {
        Class<? extends Driver> driverClass = Class.forName(driver).asSubclass(Driver.class);
        if (!url.startsWith("jdbc:")) {
            String prefix = (String) driverClass.getField("PREFIX").get(null);
            url = prefix + url;
        }
        this.connection = DriverManager.getConnection(url);
        enregistrerEncodeurs();
        enregistrerDecodeurs();
        try {
            miseEnPlaceTable();
        } catch (SQLException e) {
            System.err.println("Erreur lors de la création des tables");
            throw new RuntimeException(e);
        }
    }

    /**
     * Cette méthode privée est utilisée pour configurer les tables dans la base de données.
     * Elle crée les tables nécessaires si elles n'existent pas déjà.
     *
     * @throws SQLException si une erreur de base de données se produit.
     */
    private void miseEnPlaceTable() throws SQLException {
        creerTable("profil", "id_profil INTEGER PRIMARY KEY AUTOINCREMENT", "nom TEXT");
        creerTable("statistique", new String[]{"id_stat INTEGER PRIMARY KEY AUTOINCREMENT", "id_profil INTEGER REFERENCES profil", "nom TEXT", "id_entity INTEGER", "valeur INTEGER"}, new String[]{"id_stat"}, new String[]{"id_stat", "id_profil", "nom", "id_entity"});
        creerTable("map", "id_map INTEGER PRIMARY KEY AUTOINCREMENT", "nom TEXT", "difficulte INTEGER", "largeur INTEGER", "hauteur INTEGER");
        creerTable("ile", "id_ile INTEGER PRIMARY KEY AUTOINCREMENT", "id_map INTEGER REFERENCES map", "x INTEGER", "y INTEGER", "n INTEGER");
        creerTable("historique", new String[]{"date TIMESTAMP", "id_map INTEGER REFERENCES map", "id_ile1 INTEGER REFERENCES ile", "id_ile2 INTEGER REFERENCES ile", "action INTEGER", "avant TIMESTAMP NULL REFERENCES historique(date)"}, new String[]{"date"}, null);
        creerTable("sauvegarde", new String[]{"id_profil INTEGER REFERENCES profil", "nom TEXT", "reference TIMESTAMP REFERENCES historique(date)"}, new String[]{"id_profil", "nom"}, new String[]{"id_profil", "nom"});

    }

    /**
     * Cette méthode privée est utilisée pour créer une table dans la base de données.
     * Elle prend en paramètre le nom de la table et un tableau de colonnes.
     *
     * @param nom      le nom de la table à créer.
     * @param colonnes un tableau de chaînes de caractères représentant les colonnes de la table.
     * @throws SQLException si une erreur de base de données se produit.
     */
    private void creerTable(String nom, String... colonnes) throws SQLException {
        creerTable(nom, colonnes, null, null);
    }

    /**
     * Cette méthode privée est utilisée pour créer une table dans la base de données.
     * Elle prend en paramètre le nom de la table, un tableau de colonnes, un tableau de clés primaires et un tableau d'éléments uniques.
     *
     * @param nom         le nom de la table à créer.
     * @param colonne     un tableau de chaînes de caractères représentant les colonnes de la table.
     * @param clePrimaire un tableau de chaînes de caractères représentant les clés primaires de la table.
     * @param uniques     un tableau de chaînes de caractères représentant les éléments uniques de la table.
     * @throws SQLException si une erreur de base de données se produit.
     */
    private void creerTable(String nom, String[] colonne, String[] clePrimaire, String[] uniques) throws SQLException {
        StringBuilder sb = new StringBuilder("CREATE TABLE IF NOT EXISTS ").append(nom).append(" (");
        sb.append(String.join(", ", colonne));
        if (clePrimaire != null && clePrimaire.length > 0) {
            sb.append(", PRIMARY KEY (").append(String.join(", ", clePrimaire)).append(")");
        }
        if (uniques != null && uniques.length > 0) {
            sb.append(", UNIQUE (").append(String.join(", ", uniques)).append(")");
        }
        sb.append(")");
        try (Statement stmt = connection.createStatement()) {
            logQuery(sb.toString(), 1);
            stmt.execute(sb.toString());
        }
    }

    /**
     * Cette méthode privée est utilisée pour enregistrer les encodeurs pour chaque classe de données.
     * Chaque classe de données est associée à un encodeur spécifique qui est utilisé pour convertir les objets de cette classe en une forme qui peut être stockée dans la base de données.
     */
    private void enregistrerEncodeurs() {
        encoders.put(Profil.class, new ProfilEncoder(this));
        encoders.put(Statistique.class, new StatistiqueEncoder());
        encoders.put(Grille.class, new GrilleEncoder(this));
        encoders.put(Ile.class, new IleEncoder());
        encoders.put(Historique.class, new HistoriqueEncoder(this));
        encoders.put(Sauvegarde.class, new SauvegardeEncoder(this));
    }

    /**
     * Cette méthode privée est utilisée pour enregistrer les décodeurs pour chaque classe de données.
     * Chaque classe de données est associée à un décodeur spécifique qui est utilisé pour convertir les données stockées dans la base de données en objets de cette classe.
     */
    private void enregistrerDecodeurs() {
        decoders.put(Profil.class, new ProfilDecoder(this));
        decoders.put(Statistique.class, new StatistiqueDecoder(this));
        decoders.put(Grille.class, new GrilleDecoder(this));
        decoders.put(Ile.class, new IleDecoder(this));
        decoders.put(Historique.class, new HistoriqueDecoder(this));
        decoders.put(Sauvegarde.class, new SauvegardeDecoder(this));
    }

    /**
     * Cette méthode est utilisée pour charger des données d'une classe spécifique à partir de la base de données.
     * Elle prend en paramètre la classe des données à charger et une chaîne de caractères représentant une clause SQL supplémentaire.
     *
     * @param clazz la classe des données à charger.
     * @param extra une chaîne de caractères représentant une clause SQL supplémentaire.
     * @return une liste d'objets de la classe spécifiée, chargés à partir de la base de données.
     * @throws IllegalArgumentException si la classe spécifiée est nulle ou si elle n'est pas gérée par ce stockage.
     * @throws RuntimeException         si une erreur de base de données se produit.
     */
    @Override
    public <T> List<T> charger(Class<T> clazz, String extra) {
        Assert.nonNull(clazz);
        if (!decoders.containsKey(clazz)) {
            throwNonGerer(clazz);
        }
        Decoder<ResultSet, T> decoder = (Decoder<ResultSet, T>) decoders.get(clazz);
        List<T> list = new ArrayList<>();
        List<T> cache = getCache(clazz);
        try (ResultSet rs = executeQuery("SELECT * FROM " + decoder.getNomContaineur() + (extra == null ? "" : " " + extra))) {
            while (rs.next()) {
                T t = decoder.creer(rs);
                if (!cache.contains(t)) {
                    list.add(t);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        cache.clear();
        cache.addAll(list);
        return cache;
    }

    /**
     * Cette méthode est utilisée pour charger des données d'une classe spécifique à partir de la base de données.
     * Elle prend en paramètre la classe des données à charger, une liste de jointures et un filtre.
     *
     * @param clazz     la classe des données à charger.
     * @param jointures une liste de jointures.
     * @param filtre    un filtre.
     * @return une liste d'objets de la classe spécifiée, chargés à partir de la base de données.
     */
    @Override
    public <T> List<T> charger(Class<T> clazz, List<Join> jointures, Filter filtre) {
        String extra = "";
        if (jointures != null) {
            extra += " " + String.join(" ", jointures.stream().map(Join::getJointure).toArray(String[]::new));
        }
        if (filtre != null) {
            extra += " WHERE " + filtre.getFiltre();
        }
        return charger(clazz, extra);
    }

    /**
     * Cette méthode est utilisée pour sauvegarder une liste d'objets dans la base de données.
     *
     * @param list la liste d'objets à sauvegarder.
     * @throws IllegalArgumentException si la liste est nulle ou si la classe des objets n'est pas gérée par ce stockage.
     * @throws RuntimeException         si une erreur de base de données se produit.
     */
    @Override
    public <T> void sauvegarder(List<T> list) {
        Assert.nonNull(list);
        if (list.isEmpty()) {
            return;
        }
        T first = list.get(0);
        SQLEncoder<T> encoder = encoders.entrySet().stream()
                .filter(entry -> entry.getKey().isAssignableFrom(first.getClass()))
                .map(entry -> (SQLEncoder<T>) entry.getValue())
                .findFirst()
                .orElseThrow(getNonGerer(first.getClass()));
        String query =
                "REPLACE INTO " + encoder.getNomTable() +
                        (encoder.getColonnes().length != 0 && Arrays.stream(encoder.getColonnes()).noneMatch(Objects::isNull) ? " (" + String.join(", ", encoder.getColonnes()) + ")" : "") +
                        " VALUES (" + String.join(", ", Arrays.stream(encoder.getColonnes())
                        .map(c -> "?")
                        .toArray(String[]::new)) + ")";
        logQuery(query, 0);
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            for (T t : list) {
                encoder.sauvegarder(stmt, t);
                stmt.addBatch();
            }
            int modifier = stmt.executeBatch()[0];

            if (modifier > 0) {
                try (ResultSet ligneGenerer = stmt.getGeneratedKeys()) {
                    int i = 0;
                    if (ligneGenerer.next()) {
                        if (list.get(i) instanceof Identifiable.UNSAFE) {
                            ((Identifiable.UNSAFE) list.get(i)).setId(ligneGenerer.getInt("last_insert_rowid()"));
                        }
                        encoder.apresInsertion(list.get(i), ligneGenerer);
                    }
                }
            } else {
                for (T t : list) {
                    encoder.apresInsertion(t, null);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Cette méthode est utilisée pour supprimer des données d'une classe spécifique de la base de données.
     * Elle prend en paramètre la classe des données à supprimer et un filtre.
     *
     * @param clazz  la classe des données à supprimer.
     * @param filtre un filtre.
     * @throws IllegalArgumentException si la classe est nulle ou si elle n'est pas gérée par ce stockage.
     * @throws RuntimeException         si une erreur de base de données se produit.
     */
    @Override
    public <T> void supprimer(Class<T> clazz, Filter filtre) {
        Assert.nonNull(clazz);
        Decoder<ResultSet, T> decoder = decoders.entrySet().stream()
                .filter(entry -> entry.getKey().isAssignableFrom(clazz))
                .map(entry -> (Decoder<ResultSet, T>) entry.getValue())
                .findFirst()
                .orElseThrow(getNonGerer(clazz));
        String query = "DELETE FROM " + decoder.getNomContaineur() + (filtre == null ? "" : " WHERE " + filtre.getFiltre());
        logQuery(query, 0);
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        getCache(clazz).clear();
    }

    /**
     * Cette méthode est utilisée pour obtenir le cache pour une classe spécifique.
     *
     * @param clazz la classe pour laquelle obtenir le cache.
     * @return une liste d'objets de la classe spécifiée, présents dans le cache.
     * @throws IllegalArgumentException si la classe est nulle.
     */
    public <T> List<T> getCache(Class<T> clazz) {
        Assert.nonNull(clazz);
        caches.computeIfAbsent(clazz, k -> new ArrayList<>());
        return (List<T>) caches.get(clazz);
    }

    /**
     * Cette méthode est utilisée pour obtenir un objet d'une classe spécifique du cache, en fonction d'un filtre.
     *
     * @param clazz  la classe de l'objet à obtenir.
     * @param filtre un filtre.
     * @return un objet optionnel de la classe spécifiée, qui correspond au filtre.
     * @throws IllegalArgumentException si la classe est nulle ou si elle n'est pas gérée par ce stockage.
     */
    public <T> Optional<T> getDepuisCache(Class<T> clazz, Predicate<T> filtre) {
        Assert.nonNull(clazz);
        if (!decoders.containsKey(clazz)) {
            throwNonGerer(clazz);
        }
        List<T> cache = getCache(clazz);
        return cache.stream().filter(filtre).findFirst();
    }

    /**
     * Cette méthode est utilisée pour exécuter une requête SQL.
     *
     * @param query la requête SQL à exécuter.
     * @return un ResultSet contenant le résultat de la requête.
     * @throws RuntimeException si une erreur de base de données se produit.
     */
    public ResultSet executeQuery(String query) {
        logQuery(query, 1);
        try {
            return connection.createStatement().executeQuery(query);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Cette méthode privée est utilisée pour enregistrer une requête SQL dans le journal.
     * Elle est utilisée uniquement lorsque le mode DEBUG est activé.
     *
     * @param query la requête SQL à enregistrer.
     * @param deep  la profondeur de la pile d'appels.
     */
    private void logQuery(String query, int deep) {
        if (!Main.DEVELOPMENT) {
            return;
        }
        StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
        System.out.println("Requête SQL provenant de " + stackTrace[Math.min(5 + deep, stackTrace.length - 2)] + " -> " + query);
    }

    /**
     * Cette méthode est utilisée pour obtenir les décodeurs.
     *
     * @return une carte des décodeurs.
     */
    public Map<Class<?>, Decoder<ResultSet, ?>> getDecoders() {
        return decoders;
    }

    /**
     * Cette méthode est utilisée pour obtenir une représentation sous forme de chaîne de caractères de l'objet SQLStockage.
     *
     * @return une chaîne de caractères représentant l'objet SQLStockage.
     */
    @Override
    public String toString() {
        try {
            return "SQLStockage{connection=" + connection.getMetaData().getURL() + "}";
        } catch (SQLException e) {
            return "SQLStockage{connection='" + e + "'}";
        }
    }
}
