package fr.hashimiste.core.dev;

/**
 * L'interface Debuggable définit les méthodes que doivent implémenter les classes qui peuvent fournir des informations de débogage.
 */
public interface Debuggable {
    /**
     * Récupère les informations de débogage.
     * @return les informations de débogage.
     */
    String getDebugInfo();
}