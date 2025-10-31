package com.purkynka.paintpp.ui.shared.popup;

import atlantafx.base.theme.Styles;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;

/**
 * Contains the cancel and submit {@link Button Buttons} of a {@link PopupStage}.
 */
public class PopupButtons extends HBox {
    private final Button cancelButton;
    private final Button submitButton;

    /**
     * Constructs a new {@link PopupButtons}, using the provided {@link String Strings} and {@link Runnable Runnables},
     * for the button texts and on action events.
     * @param cancelText The text of the cancel button
     * @param onCancel The runnable to run when the cancel button is clicked
     * @param submitText The text of the submit button
     * @param onSubmit The runnable to run when the submit button is clicked
     */
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
