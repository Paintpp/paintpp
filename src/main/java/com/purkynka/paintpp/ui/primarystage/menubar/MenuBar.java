package com.purkynka.paintpp.ui.primarystage.menubar;

import javafx.scene.control.Menu;
import org.kordamp.ikonli.javafx.FontIcon;
import org.kordamp.ikonli.materialdesign2.*;

/**
 * {@link javafx.scene.control.MenuBar} for the editor's quick actions.
 */
public class MenuBar extends javafx.scene.control.MenuBar {
    private final FileMenu fileMenu;
    private final Menu optionsMenu;
    private final Menu aboutMenu;
    private final Menu helpMenu;

    /**
     * Constructs a new {@link MenuBar}.
     */
    public MenuBar() {
        super();

        fileMenu = new FileMenu();
        optionsMenu = new Menu("_Options", new FontIcon(MaterialDesignC.COG));
        optionsMenu.setMnemonicParsing(true);
        aboutMenu = new Menu("_About", new FontIcon(MaterialDesignC.CARD_TEXT));
        aboutMenu.setMnemonicParsing(true);
        helpMenu = new Menu("_Help", new FontIcon(MaterialDesignH.HELP_CIRCLE));
        helpMenu.setMnemonicParsing(true);
        
        
        getMenus().addAll(fileMenu, optionsMenu, aboutMenu, helpMenu);
    }
}
