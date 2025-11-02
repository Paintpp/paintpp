package com.purkynka.paintpp.ui.popup.settings;

import atlantafx.base.theme.Styles;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;

public class PopupSettingsButtons extends HBox {
    private final Button okButton;
    private final Button cancelButton;
    private final Button applyButton;


    public PopupSettingsButtons(Runnable onOk, Runnable onCancel, Runnable onApply) {
        super(8);

        setPadding(new Insets(16, 0, 0, 0));

        var filler = new Pane();
        HBox.setHgrow(filler, Priority.ALWAYS);

        okButton = new Button("Ok");
        okButton.getStyleClass().add(Styles.ACCENT);
        okButton.setOnAction(_ -> onOk.run());

        cancelButton = new Button("Cancel");
        cancelButton.setOnAction(_ -> onCancel.run());

        applyButton = new Button("Apply");
        applyButton.setOnAction(_ -> onApply.run());
        applyButton.setDisable(true);

        getChildren().addAll(filler, okButton, cancelButton, applyButton);
    }

    public void setApplyButtonDisable(boolean value) {
        applyButton.setDisable(value);
    }
}
