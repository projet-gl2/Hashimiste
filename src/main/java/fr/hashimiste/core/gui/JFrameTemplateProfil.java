package fr.hashimiste.core.gui;

import fr.hashimiste.core.data.Stockage;
import fr.hashimiste.core.joueur.Profil;

import javax.swing.*;
import java.awt.*;
import java.io.File;

public abstract class JFrameTemplateProfil extends JFrameTemplate {

    protected final transient Profil profil;

    protected JFrameTemplateProfil(JFrameTemplate parent, Profil profil) {
        super(parent);
        this.profil = profil;
    }

    protected JFrameTemplateProfil(JFrameTemplate parent, Dimension dimension, Profil profil) {
        super(parent, dimension);
        this.profil = profil;
    }

    protected JFrameTemplateProfil(JFrameTemplateProfil parent) {
        super(parent);
        this.profil = parent.profil;
    }

    protected JFrameTemplateProfil(JFrameTemplateProfil parent, Dimension dimension) {
        super(parent, dimension);
        this.profil = parent.profil;
    }

    protected JFrameTemplateProfil(JFrame parent, File propertiesFile, Stockage stockage, Dimension size, Profil profil) {
        super(parent, propertiesFile, stockage, size);
        this.profil = profil;
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "{" +
                "parent=" + fenetreParente.getClass().getSimpleName() +
                ", stockage=" + stockage +
                ", properties=" + properties +
                ", profil=" + profil +
                '}';
    }

    public Profil getProfil() {
        return profil;
    }
}
