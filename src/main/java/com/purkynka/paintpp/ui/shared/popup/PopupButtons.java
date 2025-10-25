package com.purkynka.paintpp.ui.shared.popup;

import atlantafx.base.theme.Styles;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;

public class PopupButtons extends HBox {
    private Button cancelButton;
    private Button submitButton;

    public PopupButtons(String cancelText, Runnable onCancel, String submitText, Runnable onSubmit) {
        super(8);

        setPadding(new Insets(16, 0, 0, 0));

        var filler = new Pane();
        HBox.setHgrow(filler, Priority.ALWAYS);

        cancelButton = new Button(cancelText);
        cancelButton.getStyleClass().add(Styles.DANGER);
        cancelButton.setOnAction(_ -> onCancel.run());

        submitButton = new Button(submitText);
        submitButton.getStyleClass().add(Styles.SUCCESS);
        submitButton.setOnAction(_ -> onSubmit.run());

        getChildren().addAll(filler, cancelButton, submitButton);
    }
}
