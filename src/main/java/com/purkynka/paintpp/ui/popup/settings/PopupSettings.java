package com.purkynka.paintpp.ui.popup.settings;

import com.purkynka.paintpp.ui.shared.popup.PopupBaseRoot;
import com.purkynka.paintpp.ui.shared.popup.PopupStage;
import javafx.stage.Stage;

public class PopupSettings extends PopupStage {
    private final SettingOption settingOption;

    public PopupSettings(SettingOption settingOption) {
        this.settingOption = settingOption;
        super();
    }

    @Override
    protected PopupBaseRoot constructRoot(Stage stage) {
        return new SettingsPopupRoot(stage, "Settings", settingOption);
    }
}
