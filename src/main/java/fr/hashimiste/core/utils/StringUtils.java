package fr.hashimiste.core.utils;

import java.util.concurrent.ThreadLocalRandom;

/**
 * La classe StringUtils est une classe utilitaire pour les chaînes de caractères.
 * Elle contient des méthodes pour répéter une chaîne, générer une chaîne aléatoire et vérifier si une chaîne est vide.
 * Cette classe ne peut pas être instanciée.
 */
public class StringUtils {
    /**
     * Constructeur privé pour empêcher l'instanciation de la classe.
     * Lève une exception si une tentative d'instanciation est faite.
     */
    private StringUtils() {
        throw new IllegalStateException("Classe utilitaire");
    }

    /**
     * Répète une chaîne de caractères un certain nombre de fois.
     *
     * @param s la chaîne à répéter.
     * @param n le nombre de répétitions.
     * @return la chaîne répétée.
     */
    public static String repete(String s, int n) {
        return new String(new char[n]).replace("\0", s);
    }

    /**
     * Génère une chaîne de caractères aléatoire de longueur spécifiée.
     *
     * @param length la longueur de la chaîne à générer.
     * @return la chaîne aléatoire.
     */
    public static String random(int length) {
        String chars = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < length; i++) {
            sb.append(chars.charAt(ThreadLocalRandom.current().nextInt(chars.length())));
        }
        return sb.toString();
    }

    /**
     * Vérifie si une chaîne de caractères est vide ou null.
     *
     * @param s la chaîne à vérifier.
     * @return vrai si la chaîne est vide ou null, faux sinon.
     */
    public static boolean estVide(String s) {
        return s == null || s.trim().isEmpty();
    }
}