package com.purkynka.paintpp.ui.shared;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;

/**
 * A temporary {@link StackPane} that just holds a centered {@link Label}.
 */
public class TemporaryPane extends StackPane {
    private Label label;

    /**
     * Constructs a new {@link TemporaryPane} containing the provided {@link String}.
     * @param text The text inside the temporary pane
     */
    public TemporaryPane(String text) {
        label = new Label(text);
        label.setAlignment(Pos.CENTER);

        getChildren().add(label);
    }
}
