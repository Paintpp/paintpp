package com.purkynka.paintpp.ui.element.actionbar;

import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;

public class ActionBar extends VBox {
    public static final double ACTION_BAR_WIDTH = 96d;

    public ActionBar() {
        super();

        this.getStyleClass().add("action-bar");

        HBox.setHgrow(this, Priority.NEVER);
        this.setMinWidth(ACTION_BAR_WIDTH);
        this.setMaxWidth(ACTION_BAR_WIDTH);
    }
}
