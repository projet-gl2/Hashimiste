package fr.hashimiste.core.utils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

/**
 * La classe SizeComponentAdapter est un adaptateur de composant qui gère le redimensionnement des composants.
 * Elle étend ComponentAdapter et redéfinit la méthode componentResized pour redimensionner les composants en fonction de la taille de la fenêtre.
 */
public class SizeComponentAdapter extends ComponentAdapter {
    private final JComponent[] composants;
    private final Dimension tailleParDefaut;
    private final Dimension tailleComposantParDefaut;

    /**
     * Constructeur de SizeComponentAdapter.
     *
     * @param tailleParDefaut          la taille par défaut de la fenêtre.
     * @param tailleComposantParDefaut la taille par défaut des composants.
     * @param composants               les composants à redimensionner.
     */
    public SizeComponentAdapter(Dimension tailleParDefaut, Dimension tailleComposantParDefaut, JComponent... composants) {
        this.tailleParDefaut = tailleParDefaut;
        this.tailleComposantParDefaut = tailleComposantParDefaut;
        this.composants = composants;
    }

    /**
     * Méthode appelée lorsque la taille du composant est modifiée.
     * Redimensionne les composants en fonction de la taille de la fenêtre.
     *
     * @param e l'événement de composant.
     */
    @Override
    public void componentResized(ComponentEvent e) {
        double ratioHauteur = e.getComponent().getHeight() / tailleParDefaut.getHeight();
        double ratioLargeur = e.getComponent().getWidth() / tailleParDefaut.getWidth();
        if (ratioHauteur < 1) {
            ratioHauteur = 1;
        }
        if (ratioLargeur < 1) {
            ratioLargeur = 1;
        }
        Dimension taille = new Dimension((int) (tailleComposantParDefaut.getWidth() * ratioLargeur), (int) (tailleComposantParDefaut.getHeight() * ratioHauteur));
        for (JComponent composant : composants) {
            composant.setSize(taille);
            composant.setMinimumSize(taille);
            composant.setMaximumSize(taille);
            composant.setPreferredSize(taille);
            composant.revalidate();
        }
    }
}