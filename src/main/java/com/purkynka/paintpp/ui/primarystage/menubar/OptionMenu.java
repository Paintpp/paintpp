package com.purkynka.paintpp.ui.primarystage.menubar;

import com.purkynka.paintpp.ui.popup.settings.PopupSettings;
import com.purkynka.paintpp.ui.popup.settings.components.sidebar.pages.SettingPages;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import org.kordamp.ikonli.javafx.FontIcon;
import org.kordamp.ikonli.materialdesign2.MaterialDesignB;
import org.kordamp.ikonli.materialdesign2.MaterialDesignC;

public class OptionMenu extends MenuButton {
    private final MenuItem general;
    private final MenuItem appearance;

    public OptionMenu() {
        super("_Options", new FontIcon(MaterialDesignC.COG));
        setMnemonicParsing(true);

        general = new MenuItem("_General", new FontIcon(MaterialDesignC.CARD_TEXT));
        general.setOnAction(_ -> new PopupSettings(SettingPages.GENERAL));
        general.setMnemonicParsing(true);

        appearance = new MenuItem("_Appearance", new FontIcon(MaterialDesignB.BRUSH));
        appearance.setOnAction(_ -> new PopupSettings(SettingPages.APPEARANCE));
        appearance.setMnemonicParsing(true);

        getItems().addAll(general, appearance);
    }
}
