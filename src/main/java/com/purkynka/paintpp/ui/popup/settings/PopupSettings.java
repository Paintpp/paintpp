package com.purkynka.paintpp.ui.popup.settings;

import com.purkynka.paintpp.ui.popup.settings.components.sidebar.pages.SettingPages;
import com.purkynka.paintpp.ui.shared.popup.PopupBaseRoot;
import com.purkynka.paintpp.ui.shared.popup.PopupStage;
import javafx.stage.Stage;

public class PopupSettings extends PopupStage {
    private final SettingPages settingPages;

    public PopupSettings(SettingPages settingPages) {
        this.settingPages = settingPages;
        super();
    }

    @Override
    protected PopupBaseRoot constructRoot(Stage stage) {
        return new SettingsPopupRoot(stage, "Settings", settingPages);
    }
}
