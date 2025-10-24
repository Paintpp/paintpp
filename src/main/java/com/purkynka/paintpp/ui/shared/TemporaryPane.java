package com.purkynka.paintpp.ui.shared;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;

public class TemporaryPane extends StackPane {
    private Label label;

    public TemporaryPane(String text) {
        label = new Label(text);
        label.setAlignment(Pos.CENTER);

        getChildren().add(label);
    }
}
