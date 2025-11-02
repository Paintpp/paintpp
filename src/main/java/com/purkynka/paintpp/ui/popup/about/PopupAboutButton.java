package com.purkynka.paintpp.ui.popup.about;

import atlantafx.base.theme.Styles;
import com.purkynka.paintpp.ui.shared.popup.PopupStage;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;

/**
 * Contains the cancel and submit {@link Button Buttons} of a {@link PopupStage}.
 */
public class PopupAboutButton extends HBox {
    private final Button closeButton;

    /**
     * Constructs a new {@link PopupAboutButton}, using the provided {@link String Strings} and {@link Runnable Runnables},
     * for the button texts and on action events.
     *
     * @param closeText The text of the cancel button
     * @param onClose   The runnable to run when the cancel button is clicked
     */
    public PopupAboutButton(String closeText, Runnable onClose) {
        super(8);

        setPadding(new Insets(16, 0, 0, 0));

        var filler = new Pane();
        HBox.setHgrow(filler, Priority.ALWAYS);

        closeButton = new Button(closeText);
        closeButton.getStyleClass().add(Styles.ACCENT);
        closeButton.setOnAction(_ -> onClose.run());

        getChildren().addAll(filler, closeButton);
    }
}
