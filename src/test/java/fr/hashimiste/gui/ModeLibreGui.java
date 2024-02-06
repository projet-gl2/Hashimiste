package fr.hashimiste.gui;

import fr.hashimiste.gui.modelibre.ModeLibre;

import javax.swing.*;

public class ModeLibreGui {

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
