package com.purkynka.paintpp.ui.element.sidebar;

import javafx.scene.layout.VBox;

public class Sidebar extends VBox {
    public static final double MIN_SIDEBAR_WIDTH = 256;
    public static final double MAX_SIDEBAR_WIDTH = 512;

    public Sidebar() {
        super();

        this.getStyleClass().add("sidebar");
        this.setMinWidth(MIN_SIDEBAR_WIDTH);
        this.setMaxWidth(MAX_SIDEBAR_WIDTH);
    }
}
