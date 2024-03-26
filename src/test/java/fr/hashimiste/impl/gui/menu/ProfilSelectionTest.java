package fr.hashimiste.impl.gui.menu;
import java.io.IOException;
import org.junit.jupiter.api.Test;

import java.awt.*;

class ProfilSelectionTest extends fr.hashimiste.impl.gui.menu.Test{
    private ProfilSelection profilSelection;

    @Override
    protected Container getTestContainer() {
        return profilSelection;
    }

    /**
     * Teste le menu de selection de profil
     * @throws IOException
     */
    @Test
    void testMenuProfilSelection() throws IOException{
        testerMenu(profilSelection, "Hashimiste", new Dimension(500, 300));

        testThemeMenu(profilSelection, "default");
        testThemeMenu(profilSelection, "candy");

        System.out.println("Le test du changement de thème réussi");
    }
}