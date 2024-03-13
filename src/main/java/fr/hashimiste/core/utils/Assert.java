package fr.hashimiste.core.utils;

/**
 * Classe utilitaire pour les assertions
 */
public class Assert {
    /**
     * Constructeur privé pour empêcher l'instanciation de la classe
     */
    private Assert() {
        throw new IllegalStateException("Utility class");
    }

    /**
     * Vérifie qu'un objet n'est pas null.
     * @param o l'objet a vérifié.
     */
    public static void nonNull(Object o) {
        nonNull(o, "Un argument ne peut pas être null.");
    }

    /**
     * Vérifie qu'un objet n'est pas null.
     * @param o l'objet a vérifié.
     * @param message le message d'erreur.
     */
    public static void nonNull(Object o, String message) {
        if (o == null) {
            throw new IllegalArgumentException(message);
        }
    }
}
