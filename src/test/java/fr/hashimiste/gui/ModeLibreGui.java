package fr.hashimiste.gui;

import fr.hashimiste.gui.modelibre.ModeLibre;

import javax.swing.*;

/**
 * Classe de test pour démarer le GUI du mode libre
 * @author elie
 */
public class ModeLibreGui {

    /**
     * lance le gui avec un look and feel adapter pour mac
     * @param args
     */
    public static void main(String[] args) {
        try {
            for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                if ("Metal".equals(info.getName())) {
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        new ModeLibre();
    }

}
