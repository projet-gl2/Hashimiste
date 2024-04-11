package fr.hashimiste.core.utils.swing;

import javax.swing.*;
import java.awt.*;
import java.util.function.Function;

/**
 * La classe ListRenderer est un rendu de liste personnalisé.
 * Elle étend DefaultListCellRenderer pour personnaliser l'affichage des éléments de la liste.
 */
public class ListRenderer extends DefaultListCellRenderer {

    private final transient Function<Object, String> transformateur;

    /**
     * Constructeur de ListRenderer.
     *
     * @param clazz          la classe des éléments de la liste.
     * @param transformateur une fonction pour convertir les éléments de la liste en chaînes de caractères.
     */
    public <T> ListRenderer(Class<T> clazz, Function<T, String> transformateur) {
        this.transformateur = o -> transformateur.apply(clazz.cast(o));
    }

    /**
     * Récupère le composant qui est utilisé pour dessiner la cellule dans la liste.
     *
     * @param list         la liste JList que nous dessinons.
     * @param value        la valeur retournée par list.getModel().getElementAt(index).
     * @param index        l'index de la cellule.
     * @param isSelected   vrai si la cellule spécifiée est sélectionnée.
     * @param cellHasFocus vrai si la cellule spécifiée a le focus.
     * @return un composant dont paint() est appelé pour dessiner la cellule.
     */
    @Override
    public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
        return super.getListCellRendererComponent(list,
                transformateur.apply(value),
                index,
                isSelected,
                cellHasFocus
        );
    }
}