package com.purkynka.paintpp.ui.element.imageviewer;

import com.purkynka.paintpp.logic.image.ImageManager;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;

public class ImageViewer extends Pane {
    private final ImageView imageView;

    public ImageViewer() {
        super();

        this.getStyleClass().add("image-viewer");

        VBox.setVgrow(this, Priority.ALWAYS);

        this.imageView = new ImageView();
        ImageManager.IMAGE_PROVIDER.addUpdateListener(imageProvider -> this.imageView.setImage(imageProvider.getImage()));

        this.getChildren().add(this.imageView);
    }
}
