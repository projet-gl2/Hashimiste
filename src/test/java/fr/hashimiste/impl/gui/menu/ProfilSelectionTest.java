package fr.hashimiste.impl.gui.menu;

import fr.hashimiste.core.data.Filter;
import fr.hashimiste.core.data.Join;
import fr.hashimiste.core.data.Stockage;
import fr.hashimiste.core.gui.JFrameTemplate;
import fr.hashimiste.core.gui.JFrameTemplateProfil;
import fr.hashimiste.core.joueur.Profil;
import fr.hashimiste.impl.joueur.ProfilImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.awt.*;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ProfilSelectionTest extends fr.hashimiste.impl.gui.menu.Test{
    private ProfilSelection profilSelection;

    @Override
    protected Container getTestContainer() {
        return profilSelection;
    }

    @Test
    void testMenuProfilSelection(){
        testerMenu(profilSelection, "Hashimiste", new Dimension(500, 300));

        assertEquals("default", profilSelection.getProperties().getProperty("theme"), "Le thème devrait être '" + "default" + "'");

        // Test d'un autre thème
        profilSelection.getProperties().setProperty("theme", "candy");
        assertEquals("candy", profilSelection.getProperties().getProperty("theme"), "Le thème devrait être 'candy'");

        // Test de repassé au thème par défaut
        profilSelection.getProperties().setProperty("theme", "default");
        assertEquals("default", profilSelection.getProperties().getProperty("theme"), "Le thème devrait être '" + "default" + "'");

        System.out.println("Le test du changement de thème réussi");
    }

}