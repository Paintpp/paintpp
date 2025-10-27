package com.purkynka.paintpp.ui.primarystage.menubar;

import javafx.scene.control.Menu;

/**
 * {@link javafx.scene.control.MenuBar} for the editor's quick actions.
 */
public class MenuBar extends javafx.scene.control.MenuBar {
    private final FileMenu fileMenu;
    private final Menu optionsMenu;
    private final Menu creditsMenu;

    /**
     * Constructs a new {@link MenuBar}.
     */
    public MenuBar() {
        super();

        fileMenu = new FileMenu();
        optionsMenu = new Menu("Options");
        creditsMenu = new Menu("Credits");

        getMenus().addAll(fileMenu, optionsMenu, creditsMenu);
    }
}
