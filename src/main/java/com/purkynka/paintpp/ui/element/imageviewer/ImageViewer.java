package com.purkynka.paintpp.ui.element.imageviewer;

import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;

public class ImageViewer extends Pane {
    public ImageViewer() {
        super();

        this.getStyleClass().add("image-viewer");

        VBox.setVgrow(this, Priority.ALWAYS);
    }
}
