package com.purkynka.paintpp.ui.element.statusbar;

import com.purkynka.paintpp.ui.CommonCSS;
import javafx.scene.layout.HBox;

public class StatusBar extends HBox {
    public static final double STATUS_BAR_HEIGHT = 32d;
    private static final double ACTUAL_STATUS_BAR_WIDTH = STATUS_BAR_HEIGHT + CommonCSS.BORDER_WIDTH;

    public StatusBar() {
        super();

        this.getStyleClass().add("status-bar");

        this.setMinHeight(ACTUAL_STATUS_BAR_WIDTH);
        this.setMaxHeight(ACTUAL_STATUS_BAR_WIDTH);
    }
}
