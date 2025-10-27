package com.purkynka.paintpp.ui.shared.popup;

import javafx.geometry.Insets;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

/**
 * Sets up the default structure of a {@link PopupStage}.
 * <p>
 * Includes a {@link #gridPane} that form elements can use.
 */
public abstract class PopupRoot extends VBox {
    protected final PopupTitle popupTitle;
    protected final GridPane gridPane;
    protected final PopupButtons popupButtons;

    /**
     * Constructs a new {@link PopupRoot} with the provided {@link String Strings} as the title and
     * button texts.
     * @param title The text to use as the title
     * @param cancelText The text inside the cancel button
     * @param submitText The text inside the submit button
     */
    public PopupRoot(String title, String cancelText, String submitText) {
        super();

        gridPane = new GridPane();

        setPadding(new Insets(24));

        gridPane.setHgap(8);
        gridPane.setVgap(8);

        popupTitle = new PopupTitle(title);
        popupButtons = new PopupButtons(cancelText, this::onCancel, submitText, this::onSubmit);

        getChildren().addAll(popupTitle, gridPane, popupButtons);
    }

    /**
     * Constructor overload for a {@link PopupRoot} with default button texts.
     * @param title The text to use as the title
     */
    public PopupRoot(String title) {
        this(title, "Cancel", "Submit");
    }

    /**
     * Runs whenever the cancel button is pressed.
     */
    protected abstract void onCancel();

    /**
     * Runs whenever the submit button is pressed.
     */
    protected abstract void onSubmit();
}
