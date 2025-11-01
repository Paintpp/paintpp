package com.purkynka.paintpp.ui.shared.popup.confirmation;

import com.purkynka.paintpp.ui.shared.Title;
import com.purkynka.paintpp.ui.shared.popup.PopupBaseRoot;
import com.purkynka.paintpp.ui.shared.popup.PopupStage;
import javafx.geometry.Insets;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

/**
 * Sets up the default structure of a {@link PopupStage}.
 * <p>
 * Includes a {@link #gridPane} that form elements can use.
 */
public abstract class PopupConfirmationRoot extends PopupBaseRoot {
    protected final Title popupTitle;
    protected final GridPane gridPane;
    protected final PopupConfirmationButtons popupConfirmationButtons;

    /**
     * Constructs a new {@link PopupConfirmationRoot} with the provided {@link String Strings} as the title and
     * button texts.
     * @param title The text to use as the title
     * @param cancelText The text inside the cancel button
     * @param submitText The text inside the submit button
     */
    public PopupConfirmationRoot(Stage stage, String title, String cancelText, String submitText) {
        super();                
        gridPane = new GridPane();

        setPadding(new Insets(24));

        gridPane.setHgap(8);
        gridPane.setVgap(8);

        popupTitle = new Title(title);
        popupConfirmationButtons = new PopupConfirmationButtons(cancelText, this::onCancel, submitText, this::onSubmit);

        getChildren().addAll(popupTitle, gridPane, popupConfirmationButtons);

        stage.sceneProperty().addListener((_, _, newScene) -> {
            if (newScene != null) {
                newScene.setOnKeyPressed(event -> {
                    switch (event.getCode()) {
                        case KeyCode.ENTER -> onSubmit();
                        case KeyCode.ESCAPE -> onCancel();
                    }
                });
                
                // Stops the input boxes from interfering with escape.
                newScene.addEventFilter(KeyEvent.KEY_PRESSED, event -> {
                    if (event.getCode() == KeyCode.ESCAPE) {
                        onCancel();
                        event.consume();
                    }
                });
            }
        });
    }

    /**
     * Constructor overload for a {@link PopupConfirmationRoot} with default button texts.
     * @param title The text to use as the title
     */
    public PopupConfirmationRoot(Stage stage, String title) {
        this(stage, title, "Cancel", "Submit");
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
