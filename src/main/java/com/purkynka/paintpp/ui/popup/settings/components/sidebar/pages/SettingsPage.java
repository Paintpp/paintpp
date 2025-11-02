package com.purkynka.paintpp.ui.popup.settings.components.sidebar.pages;

import javafx.scene.layout.GridPane;
import org.kordamp.ikonli.javafx.FontIcon;

public abstract class SettingsPage extends GridPane {
    public final FontIcon fontIcon;

    public SettingsPage(FontIcon fontIcon) {
        this.fontIcon = fontIcon;
    }
    
    public SettingsPage() {
        fontIcon = null;
    }
}
