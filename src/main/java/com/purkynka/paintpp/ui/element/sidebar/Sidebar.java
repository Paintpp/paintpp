package com.purkynka.paintpp.ui.element.sidebar;

import javafx.geometry.Insets;
import javafx.scene.layout.*;

public class Sidebar extends VBox {
    public static final double MIN_SIDEBAR_WIDTH = 320;
    public static final double MAX_SIDEBAR_WIDTH = 512;

    private VBox imageSwapperButtonContainer;

    public Sidebar() {
        super();

        this.getStyleClass().add("sidebar");
        this.setPadding(new Insets(16));
        this.setMinWidth(MIN_SIDEBAR_WIDTH);
        this.setMaxWidth(MAX_SIDEBAR_WIDTH);

        this.setupElements();

        this.getChildren().add(imageSwapperButtonContainer);
    }

    private void setupElements() {
        this.imageSwapperButtonContainer = new ImageSwapperButtonContainer(this);
    }
}
