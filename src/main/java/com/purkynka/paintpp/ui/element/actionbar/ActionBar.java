package com.purkynka.paintpp.ui.element.actionbar;

import com.purkynka.paintpp.ui.CommonCSS;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;

public class ActionBar extends VBox {
    public static final double ACTION_BAR_WIDTH = 96d;
    private static final double ACTUAL_ACTION_BAR_WIDTH = ACTION_BAR_WIDTH + CommonCSS.BORDER_WIDTH;

    public ActionBar() {
        super();

        this.getStyleClass().add("action-bar");

        HBox.setHgrow(this, Priority.NEVER);
        this.setMinWidth(ACTUAL_ACTION_BAR_WIDTH);
        this.setMaxWidth(ACTUAL_ACTION_BAR_WIDTH);
    }
}
