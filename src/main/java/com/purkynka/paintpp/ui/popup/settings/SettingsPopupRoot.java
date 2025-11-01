package com.purkynka.paintpp.ui.popup.settings;

import com.purkynka.paintpp.ui.shared.Title;
import com.purkynka.paintpp.ui.shared.popup.PopupBaseRoot;
import javafx.geometry.Insets;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class SettingsPopupRoot extends PopupBaseRoot {
    private final Stage stage;

    protected final Title popupTitle;
    protected final Title settingOptionTitle;
    protected final GridPane gridPane;
    protected final PopupSettingsButtons popupSettingsButtons;


    public SettingsPopupRoot(Stage stage, String title, SettingOption settingOption) {
        super();
        this.stage = stage;

        gridPane = new GridPane();

        setPadding(new Insets(24));

        gridPane.setHgap(8);
        gridPane.setVgap(8);

        popupTitle = new Title(title);
        settingOptionTitle = new Title(settingOption.name());
        popupSettingsButtons = new PopupSettingsButtons(this::onOK, this::onCancel, this::onApply);

        getChildren().addAll(popupTitle, settingOptionTitle, gridPane, popupSettingsButtons);

        stage.sceneProperty().addListener((_, _, newScene) -> {
            if (newScene != null) {
                newScene.addEventHandler(KeyEvent.KEY_PRESSED, event -> {
                    if (event.getCode() == KeyCode.ENTER) {
                        onOK();
                        event.consume();
                    }
                });
            }
        });
    }

    protected void onOK() {
        onApply();
        stage.close();
    }

    protected void onCancel() {
        stage.close();
    }

    protected void onApply() {
    }
}
