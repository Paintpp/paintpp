package com.purkynka.paintpp.ui.primarystage.menubar;

import atlantafx.base.theme.Styles;
import atlantafx.base.theme.Tweaks;
import com.purkynka.paintpp.ui.popup.about.AboutPopup;
import com.purkynka.paintpp.ui.popup.help.HelpPopup;
import javafx.scene.control.Button;
import org.kordamp.ikonli.javafx.FontIcon;
import org.kordamp.ikonli.materialdesign2.MaterialDesignC;
import org.kordamp.ikonli.materialdesign2.MaterialDesignH;


/**
 * {@link javafx.scene.control.MenuBar} for the editor's quick actions.
 */
public class ToolBar extends javafx.scene.control.ToolBar {
    private final FileMenu fileMenu;
    private final OptionMenu optionsMenu;
    private final Button aboutMenu;
    private final Button helpMenu;

    /**
     * Constructs a new {@link ToolBar}.
     */
    public ToolBar() {
        super();

        fileMenu = new FileMenu();
        fileMenu.getStyleClass().addAll(Tweaks.NO_ARROW, Styles.FLAT);
        optionsMenu = new OptionMenu();
        optionsMenu.getStyleClass().addAll(Tweaks.NO_ARROW, Styles.FLAT);
        aboutMenu = new Button("_About", new FontIcon(MaterialDesignC.CARD_TEXT));
        aboutMenu.setMnemonicParsing(true);
        aboutMenu.setOnAction(_ -> new AboutPopup());
        helpMenu = new Button("_Help", new FontIcon(MaterialDesignH.HELP_CIRCLE));
        helpMenu.setMnemonicParsing(true);
        helpMenu.setOnAction(_ -> new HelpPopup());

        getItems().addAll(fileMenu, optionsMenu, aboutMenu, helpMenu);
    }
}
