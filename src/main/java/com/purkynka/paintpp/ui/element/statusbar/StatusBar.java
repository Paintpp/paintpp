package com.purkynka.paintpp.ui.element.statusbar;

import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;

public class StatusBar extends HBox {
    public static final double STATUS_BAR_HEIGHT = 32;

    public StatusBar() {
        super();

        this.getStyleClass().add("status-bar");

        VBox.setVgrow(this, Priority.NEVER);
        this.setMinHeight(STATUS_BAR_HEIGHT);
        this.setMaxHeight(STATUS_BAR_HEIGHT);
    }
}
