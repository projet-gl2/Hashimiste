package fr.hashimiste.impl.gui.menu;

import static jdk.javadoc.internal.doclets.toolkit.util.DocPath.parent;
import static org.junit.jupiter.api.Assertions.*;

import fr.hashimiste.core.data.Filter;
import fr.hashimiste.core.data.Join;
import fr.hashimiste.core.data.Stockage;
import fr.hashimiste.impl.data.sql.filter.EqFilter;
import fr.hashimiste.impl.joueur.ProfilImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Properties;

import fr.hashimiste.core.joueur.Profil;
import fr.hashimiste.core.gui.JFrameTemplate;


class MenuTest {
    // TODO: write tests for Menu class

    private Menu menu;
    private Profil profil;

    @BeforeEach
    void testMenuInitialisation() throws IOException {
        ProfilImpl test = new ProfilImpl("test");
        Path tempFile = Files.createTempFile("", "");
        tempFile.toFile().deleteOnExit();
        Properties properties = new Properties();
        properties.setProperty("theme", "default");
        properties.store(Files.newOutputStream(tempFile), "Hashimiste properties");
        Stockage stockage = new Stockage() {
            @Override
            public <T> List<T> charger(Class<T> clazz, String extra) {
                if (clazz == Profil.class) {
                    return (List<T>) Collections.singletonList(test);
                }
                return Collections.emptyList();
            }

            @Override
            public <T> List<T> charger(Class<T> clazz, List<Join> jointures, Filter filtre) {
                return this.charger(clazz, (String) null);
            }

            @Override
            public <T> void sauvegarder(List<T> list) {
            }

            @Override
            public <T> void supprimer(Class<T> clazz, Filter filtre) {
            }
        };
        menu = new Menu(new JFrameTemplate(null, tempFile.toFile(), stockage, new Dimension(0, 0)) {
            @Override
            public void changerFenetre(JFrame window) {
                throw new UnsupportedOperationException("Not supported yet.");
            }
        }, test);

        assertEquals(test, menu.getProfil(), "Le profil devrait être le profil passé en paramètre");

        System.out.println("L'initialisation du menu réussi");
    }

    @Test
    void testMenu() {
        // Teste si les composants ne sont pas null
        assertNotNull(menu, "Le menu ne devrait pas être null");

        // Teste si le menu est activé
        assertTrue(menu.isEnabled(), "Le menu devrait être activé");

        // Teste si la dimension par défaut est appliquée
        assertEquals(new Dimension(800, 600), menu.getSize(), "La dimension par défaut devrait être 800x600");

        // Teste si le titre est appliqué
        assertEquals("Hashimiste", menu.getTitle(), "Le titre devrait être 'Hashimiste'");

        // Teste si le thème est appliqué
        assertEquals("default", menu.getProperties().getProperty("theme"), "Le thème devrait être 'default'");

        // Teste d'un autre thème
        menu.getProperties().setProperty("theme", "candy");
        assertEquals("candy", menu.getProperties().getProperty("theme"), "Le thème devrait être 'candy'");

        System.out.println("Le test des composants du menu réussi");
    }

}